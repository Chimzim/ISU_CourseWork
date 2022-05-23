package com.se421.paths.algorithms;

import java.io.IOException;
import java.util.List;

import com.ensoftcorp.atlas.core.db.graph.Node;
import com.ensoftcorp.atlas.core.index.common.SourceCorrespondence;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.xcsg.XCSG;
import com.se421.paths.support.FormattedSourceCorrespondence;

public abstract class PathEnumerator {

	public PathEnumerator() {}
	
	public abstract EnumerationResult enumeratePaths(Q cfg) throws IOException;
	
	private Long getCSourceLineNumber(Node node) {
		long lineNumber = -1;
		if (node.hasAttr(XCSG.sourceCorrespondence)) {
			SourceCorrespondence sc = (SourceCorrespondence) node.getAttr(XCSG.sourceCorrespondence);
			if (sc != null) {
				lineNumber = sc.startLine;
			}
		}
		return lineNumber;
	}

	/**
	 * Returns the starting line of the given node or -1 if the node does not have a
	 * source correspondence
	 * 
	 * Use this utility to in your DFSEnumerator implementation.
	 */
	protected Long getLineNumber(Node node) {
		if(node.taggedWith(XCSG.Language.C) || node.taggedWith(XCSG.Language.CPP)) {
			return getCSourceLineNumber(node);
		} else {
			long line = -1;
			if (node.hasAttr(XCSG.sourceCorrespondence) && node.getAttr(XCSG.sourceCorrespondence) != null) {
				try {
					line = FormattedSourceCorrespondence.getSourceCorrespondent(node).getStartLineNumber();
				} catch (IOException e) {
					line = -1;
				}
			}
			return line;
		}
	}
	
	/**
	 * Holds a path counting result and the path enumeration
	 */
	public static class EnumerationResult {
		private List<List<Long>> enumeratedPaths;

		public EnumerationResult(List<List<Long>> enumeratedPaths) {
			this.enumeratedPaths = enumeratedPaths;
		}

		public List<List<Long>> getEnumeratedPaths() {
			return enumeratedPaths;
		}
		
		@Override
		public String toString() {
			return enumeratedPaths.toString().replace("], [", "]\n[").replace("[[", "[").replace("]]", "]");
		}
	}
	
}
