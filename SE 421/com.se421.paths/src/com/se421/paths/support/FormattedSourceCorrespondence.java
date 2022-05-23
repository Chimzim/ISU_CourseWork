package com.se421.paths.support;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import com.ensoftcorp.atlas.core.db.graph.Edge;
import com.ensoftcorp.atlas.core.db.graph.Graph;
import com.ensoftcorp.atlas.core.db.graph.GraphElement;
import com.ensoftcorp.atlas.core.db.graph.Node;
import com.ensoftcorp.atlas.core.index.common.SourceCorrespondence;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.Common;
import com.ensoftcorp.atlas.core.xcsg.XCSG;

/**
 * A convenience utility wrapper for pretty printing SourceCorrespondence line numbers and other properties 
 * @author Ben Holland
 */
public class FormattedSourceCorrespondence implements Comparable<FormattedSourceCorrespondence> {
	
	// on demand cache of relative file paths -> character ranges for each line number
	// a line number is represented by the index of the linked list
	private static HashMap<String, LinkedList<Long>> cache = new HashMap<String, LinkedList<Long>>();
	
	private SourceCorrespondence sc;
	private String name;
	private String relativeFilePath;
	private LineNumberRange lineNumberRange = null;
	
	/**
	 * Constructs a FormattedSourceCorrespondence from an Atlas SourceCorrespondence
	 * @param sc
	 */
	private FormattedSourceCorrespondence(SourceCorrespondence sc) {
		this.sc = sc;
	}

	/**
	 * Constructs a FormattedSourceCorrespondence from an Atlas SourceCorrespondence and assigns a name
	 * @param sc
	 * @param name
	 */
	private FormattedSourceCorrespondence(SourceCorrespondence sc, String name) {
		this.sc = sc;
		this.name = name;
	}
	
	/**
	 * Returns true if this formatted source correspondence was given a name
	 * @return
	 */
	public boolean hasName() {
		boolean result = false;
		if (name != null) {
			result = true;
		}
		return result;
	}
	
	/**
	 * Returns this formatted source correspondence name if one was set or null otherwise
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the String of the Eclipse project name containing this correspondent
	 * @return
	 */
	public String getProject() {
		return sc.sourceFile.getProject().getName();
	}
	
	/**
	 * Returns a File object representing the source file location of this correspondent
	 * @return
	 */
	public File getFile() {
		return sc.sourceFile.getLocation().toFile();
	}
	
	/**
	 * Returns a relative file path string starting at the Eclipse project to
	 * the source file that contains this correspondent
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getRelativeFile() throws IOException {
		if (relativeFilePath == null) {
			File baseDirectory = sc.sourceFile.getProject().getLocation().toFile();
			relativeFilePath = OSUtils.ResourceUtils.getRelativePath(
					sc.sourceFile.getLocation().toFile().getCanonicalPath(),
					baseDirectory.getParent(), File.separator);
		}
		return relativeFilePath;
	}

	/**
	 * Returns the source file starting line number (the number of new lines 
	 * to reach the offset of the source correspondent)
	 * @return
	 */
	public long getStartLineNumber() throws IOException {
		if(lineNumberRange == null){
			lineNumberRange = getLineNumberRange(sc);
		}
		return lineNumberRange.startLine;
	}
	
	/**
	 * Returns the source file ending line number (the number of new lines 
	 * to reach the offset plus the length of the source correspondent)
	 * @return
	 * @throws IOException 
	 */
	public long getEndLineNumber() throws IOException {
		if(lineNumberRange == null){
			lineNumberRange = getLineNumberRange(sc);
		}
		return lineNumberRange.endLine;
	}
	
	/**
	 * Returns the condensed line numbers as a string of ranges for the correspondent(s)
	 * @return
	 * @throws IOException 
	 */
	public String getLineNumbers() throws IOException {
		if(lineNumberRange == null){
			lineNumberRange = getLineNumberRange(sc);
		}
		return lineNumberRange.toString();
	}
	
	/**
	 * Returns a pretty print string of the format:
	 * "Filename: <filename> + (line(s) <line number(s)>)"
	 */
	@Override
	public String toString() {
		try {
			String lines = getLineNumbers();
			return "Filename: " + getRelativeFile() + " ("
					+ (lines.contains("-") ? "lines " : "line ") + lines + ")";
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the beginning file character offset into the source file for 
	 * this source correspondent
	 * @return
	 */
	public int getOffset() {
		return sc.offset;
	}

	/**
	 * Returns the length from the beginning file character offset into the 
	 * source file for this source correspondent
	 * @return
	 */
	public int getLength() {
		return sc.length;
	}
	
	/**
	 * The the internal cache for all source files
	 * You should do this if the source file content or location changes
	 */
	public static void clearCache(){
		cache = new HashMap<String, LinkedList<Long>>();
	}
	
	/**
	 * Builds a private static cache of of the new line boundaries for a given source file
	 * @param sourceFile
	 * @return
	 * @throws IOException
	 */
	public static LinkedList<Long> cacheFile(IFile sourceFile) throws IOException {
		IProject project = sourceFile.getProject();
		File baseDirectory = project.getLocation().toFile();
		File file = sourceFile.getLocation().toFile();
		String relativeFilePath = OSUtils.ResourceUtils.getRelativePath(file.getCanonicalPath(), baseDirectory.getParent(), File.separator);
		LinkedList<Long> fileCache;
		if(cache.containsKey(relativeFilePath)){
			fileCache = cache.get(relativeFilePath);
		} else {
			fileCache = new LinkedList<Long>();
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			while (raf.getFilePointer() < raf.length()) {
				raf.readLine();
				fileCache.add(new Long(raf.getFilePointer()));
			}
			raf.close();
		}
		return fileCache;
	}
	
	/**
	 * Gets a line number range for a given source correspondent
	 * @param sc
	 * @return
	 * @throws IOException
	 */
	public static LineNumberRange getLineNumberRange(SourceCorrespondence sc) throws IOException {
		LinkedList<Long> newLineBoundaries = cacheFile(sc.sourceFile);
		long startChar = sc.offset;
		long endChar = (sc.offset + sc.length);
		int startLine = 1;
		int endLine = 1;
		for(long newLineCharBoundary : newLineBoundaries){
			if(startChar >= newLineCharBoundary){
				startLine++;
			}
			if(newLineCharBoundary <= endChar){
				endLine++;
			}
		}
		return new LineNumberRange(startLine, endLine);
	}

	@Override
	public int compareTo(FormattedSourceCorrespondence fsc) {
		try {
			return Long.compare(this.getStartLineNumber(), fsc.getStartLineNumber());
		} catch (IOException e) {
			throw new RuntimeException("Unknown line numbers!");
		}
	}
	
	/**
	 * Returns a collection of source correspondents given a Q
	 * 
	 * @param q
	 * @return
	 */
	public static Collection<FormattedSourceCorrespondence> getSourceCorrespondents(Q q) {
		LinkedList<FormattedSourceCorrespondence> sourceCorrespondents = new LinkedList<FormattedSourceCorrespondence>();
		Graph graph = q.eval();
		for (Node node : graph.nodes()) {
			FormattedSourceCorrespondence fsc = getSourceCorrespondent(node);
			if(fsc != null){
				sourceCorrespondents.add(fsc);
			}
		}
		for (Edge edge : graph.edges()){
			FormattedSourceCorrespondence fsc = getSourceCorrespondent(edge);
			if(fsc != null){
				sourceCorrespondents.add(fsc);
			}
		}
		return sourceCorrespondents;
	}

	/**
	 * Returns a formatted source correspondent given a GraphElement
	 * Returns null if no source correspondents are found.
	 * @param ge
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static FormattedSourceCorrespondence getSourceCorrespondent(GraphElement ge) {
		FormattedSourceCorrespondence sourceCorrespondent = null;
		Object name = ge.attr().get(XCSG.name);
		Object sc = ge.attr().get(XCSG.sourceCorrespondence);
		
		if(sc != null && sc instanceof SourceCorrespondence){
			if(name != null) {
				sourceCorrespondent = new FormattedSourceCorrespondence((SourceCorrespondence) sc, name.toString());
			} else {
				sourceCorrespondent = new FormattedSourceCorrespondence((SourceCorrespondence) sc);
			}
		}
		Object scList = ge.attr().get(XCSG.sourceCorrespondence);
		if (scList != null && scList instanceof List) {
			for (SourceCorrespondence scListItem : (List<SourceCorrespondence>) scList) {
				if (name != null) {
					sourceCorrespondent = new FormattedSourceCorrespondence((SourceCorrespondence) scListItem, name.toString());
				} else {
					sourceCorrespondent = new FormattedSourceCorrespondence((SourceCorrespondence) scListItem);
				}
			}
		}
		return sourceCorrespondent;
	}
	
	/**
	 * Given a Q, creates a pretty print summary the source graph element locations, line number ranges,
	 * and names of graph elements that were methods (if enabled)
	 * of 
	 * @param q
	 * @return
	 * @throws IOException 
	 */
	public static String summarize(Q q, boolean includeMethodNames) throws IOException {
		return summarize(getSourceCorrespondents(q),
				getSourceCorrespondents(includeMethodNames ? q.nodesTaggedWithAny(XCSG.Method) : Common.empty()));
	}

	// helper method for summarizing source correspondents
	private static String summarize(Collection<FormattedSourceCorrespondence> allSources,
									Collection<FormattedSourceCorrespondence> methods) throws IOException {
		StringBuilder result = new StringBuilder();

		// get the files and the line numbers in the source correspondents
		Map<String, SortedSet<LineNumberRange>> filesToLineNumbers = new HashMap<String, SortedSet<LineNumberRange>>();
		for (FormattedSourceCorrespondence fsc : allSources) {
			if (filesToLineNumbers.containsKey(fsc.getRelativeFile())) {
				LineNumberRange line = new LineNumberRange(fsc.getLineNumbers());
				filesToLineNumbers.get(fsc.getRelativeFile()).add(line);
			} else {
				SortedSet<LineNumberRange> lineNumbersSet = new TreeSet<LineNumberRange>();
				lineNumbersSet.add(new LineNumberRange(fsc.getLineNumbers()));
				filesToLineNumbers.put(fsc.getRelativeFile(), lineNumbersSet);
			}
		}

		// get the names of methods (grouped by file) of the methods source
		// correspondents note not using a set here, because the Q should insure there are no
		// duplicates also duplicate method names may be present in a given file
		// (considering inner classes and other structures)
		Map<String, LinkedList<String>> filesToMethods = new HashMap<String, LinkedList<String>>();
		for (FormattedSourceCorrespondence sc : methods) {
			if (sc.hasName()) {
				if (filesToMethods.containsKey(sc.getRelativeFile())) {
					filesToMethods.get(sc.getRelativeFile()).add(sc.getName());
				} else {
					LinkedList<String> names = new LinkedList<String>();
					names.add(sc.getName());
					filesToMethods.put(sc.getRelativeFile(), names);
				}
			}
		}

		if (filesToLineNumbers.size() == 0) {
			result.append("Empty.");
		} else if (filesToLineNumbers.size() == 1) {
			result.append("File: ");
		} else {
			result.append("Files:\n");
		}
		for (Entry<String, SortedSet<LineNumberRange>> entry : filesToLineNumbers.entrySet()) {
			SortedSet<LineNumberRange> condensedLineNumbers = condenseLineNumbers(entry
					.getValue());
			String plurality = "s";
			if (condensedLineNumbers.size() == 1) {
				if (!condensedLineNumbers.first().lines.contains("-")) {
					plurality = "";
				}
			}

			// print the filename and line numbers
			if (!condensedLineNumbers.isEmpty()) {
				result.append(entry.getKey()
						+ " "
						+ condensedLineNumbers.toString()
								.replace("[", "(line" + plurality + " ")
								.replace("]", ")\n"));
			} else {
				// not sure this is even a case, but its here for good measure
				result.append(entry.getKey() + "\n");
			}

			// print the methods for that file
			if (filesToMethods.containsKey(entry.getKey())) {
				LinkedList<String> methodNames = filesToMethods.get(entry.getKey());
				if (methodNames.size() == 1) {
					result.append("Method: ");
				} else if (methodNames.size() > 1) {
					result.append("Methods: ");
				}
				for (String name : methodNames) {
					result.append(name + ", ");
				}
				if (!methodNames.isEmpty()) {
					result.delete(result.length() - 2, result.length());
				}
				result.append("\n");
			}
			result.append("\n");
		}

		return result.toString().trim();
	}

	/**
	 * Helper class for condense line number ranges
	 * @param lines
	 * @return
	 */
	private static SortedSet<LineNumberRange> condenseLineNumbers(SortedSet<LineNumberRange> lines) {
		SortedSet<LineNumberRange> condensed = new TreeSet<LineNumberRange>();
		SortedSet<LineNumberRange> ranges = getLineNumberRanges(lines);
		SortedSet<LineNumberRange> singleLines = getSingleLineNumbers(lines);

		// condense overlapping ranges
		Collection<LineNumberRange> rangesToRemove = new LinkedList<LineNumberRange>();
		for (LineNumberRange rangeOutside : ranges) {
			for (LineNumberRange rangeInside : ranges) {
				if (rangeOutside.equals(rangeInside)) {
					// skip, this is the same range
					continue;
				} else {
					String[] startEndLinesInside = rangeInside.toString()
							.split("-");
					int startInside = Integer.parseInt(startEndLinesInside[0]);
					int endInside = Integer.parseInt(startEndLinesInside[1]);
					String[] startEndLinesOutside = rangeOutside.toString()
							.split("-");
					int startOutside = Integer
							.parseInt(startEndLinesOutside[0]);
					int endOutside = Integer.parseInt(startEndLinesOutside[1]);
					if (startOutside >= startInside && endOutside <= endInside) {
						rangesToRemove.add(rangeOutside);
					}
				}
			}
		}
		ranges.removeAll(rangesToRemove);
		condensed.addAll(ranges);

		// condense single lines into ranges
		Collection<LineNumberRange> singlesToRemove = new LinkedList<LineNumberRange>();
		for (LineNumberRange range : ranges) {
			for (LineNumberRange singleLine : singleLines) {
				String[] startEndLines = range.toString().split("-");
				int start = Integer.parseInt(startEndLines[0]);
				int end = Integer.parseInt(startEndLines[1]);
				int line = Integer.parseInt(singleLine.toString());
				if (line >= start && line <= end) {
					singlesToRemove.add(singleLine);
				}
			}
		}
		singleLines.removeAll(singlesToRemove);
		condensed.addAll(singleLines);

		return joinConsecutiveLines(condensed);
	}

	private static SortedSet<LineNumberRange> getLineNumberRanges(SortedSet<LineNumberRange> allLineNumbers) {
		SortedSet<LineNumberRange> ranges = new TreeSet<LineNumberRange>();
		for (LineNumberRange lineNumber : allLineNumbers) {
			if (lineNumber.toString().contains("-")) {
				ranges.add(lineNumber);
			}
		}
		return ranges;
	}

	private static SortedSet<LineNumberRange> getSingleLineNumbers(SortedSet<LineNumberRange> allLineNumbers) {
		SortedSet<LineNumberRange> ranges = new TreeSet<LineNumberRange>();
		for (LineNumberRange lineNumber : allLineNumbers) {
			if (!lineNumber.toString().contains("-")) {
				ranges.add(lineNumber);
			}
		}
		return ranges;
	}

	private static SortedSet<LineNumberRange> joinConsecutiveLines(SortedSet<LineNumberRange> condensedLines) {
		LineNumberRange toRemove = null;
		found: for (LineNumberRange base : condensedLines) {
			for (LineNumberRange reference : condensedLines) {
				if (!base.lines.equals(reference.lines)) {
					// if its a single test it against each incrementing single (except itself) or 
					// range until either the single or range start is one less than the single or 
					// there are no more singles/ranges to test
					if (base.isSingle()) {
						if (base.getStart() + 1 == reference.getStart()) {
							reference.growBackwardsOne();
							toRemove = base;
							break found;
						}
					} else {
						if (base.getEnd() == reference.getStart()
								|| base.getEnd() + 1 == reference.getStart()) {
							reference.growBackwardsToStart(base.getStart());
							toRemove = base;
							break found;
						}
					}
				}
			}
		}

		if (toRemove != null) {
			condensedLines.remove(toRemove);
			return joinConsecutiveLines(condensedLines);
		}

		return condensedLines;
	}

	// Just a little helper class for sorting line number strings (based on the
	// start line number)
	public static class LineNumberRange implements Comparable<LineNumberRange> {

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((lines == null) ? 0 : lines.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			LineNumberRange other = (LineNumberRange) obj;
			if (lines == null) {
				if (other.lines != null)
					return false;
			} else if (!lines.equals(other.lines))
				return false;
			return true;
		}

		private int startLine;
		private int endLine;
		private String lines;
		private boolean single = false;

		private LineNumberRange(String lines) {
			this.lines = lines;
			if (lines.contains("-")) {
				startLine = Integer.parseInt(lines.split("-")[0]);
				endLine = Integer.parseInt(lines.split("-")[1]);
			} else {
				single = true;
				startLine = Integer.parseInt(lines);
			}
		}

		private LineNumberRange(int startLine, int endLine) {
			this.startLine = startLine;
			this.endLine = endLine;
			if(startLine == endLine){
				this.single = true;
				this.lines = "" + startLine;
			} else {
				this.single = false;
				this.lines = startLine + "-" + endLine;
			}
		}

		public void growBackwardsToStart(int newStart) {
			if (isSingle()) {
				single = false;
				this.lines = newStart + "-" + startLine;
				this.endLine = startLine;
				this.startLine = newStart;
			} else {
				this.startLine = newStart;
				this.lines = startLine + "-" + endLine;
			}
		}

		public int getEnd() {
			return endLine;
		}

		public int getStart() {
			return startLine;
		}

		public boolean isSingle() {
			return single;
		}

		public void growBackwardsOne() {
			if (isSingle()) {
				single = false;
				this.lines = (startLine - 1) + "-" + startLine;
				this.endLine = startLine;
				this.startLine = startLine - 1;
			} else {
				this.startLine = startLine - 1;
				this.lines = startLine + "-" + endLine;
			}
		}

		@Override
		public String toString() {
			return lines;
		}

		@Override
		public int compareTo(LineNumberRange other) {
			if (this.isSingle() && other.isSingle()) {
				return this.startLine - other.startLine;
			} else if (this.isSingle() && !other.isSingle()) {
				if (this.startLine == other.startLine) {
					return this.startLine - other.endLine;
				}
			} else if (!this.isSingle() && other.isSingle()) {
				if (this.startLine == other.startLine) {
					return this.endLine - other.startLine;
				}
			} else {
				if (this.startLine == other.startLine) {
					return this.endLine - other.endLine;
				}
			}
			return this.startLine - other.startLine;
		}
		
	}

}
	