package hw3;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
public class AsmFileUtil {
	/**
	 * fileCode - Holds the instruction stream in a file.
	 * arrayCode - Holds the instruction stream in an array.
	 */
	private static ArrayList<String> fileCode = new ArrayList<String>();
	private static int [] arrayCode;
	/**
	 * Reads the given file and assembles the program, writing the machine code to a file. Descriptions are included only if the annotated parameter is true, otherwise each line in the output file includes just the first five characters (representing the integer value of the instruction). The name of the output file is the same as the name of the input file, with the file extension (portion after the last dot) removed and is replaced with ".mach227". For example, given the filename "test1.asm227", the output file would be named "test1.mach227".
	 * @param filename - holds the file.
	 * @param annotated - Decides which way to annotate the instruction stream.
	 * @throws java.io.FileNotFoundException
	 * size - The size of the instruction stream.
	 * keyboard - writes to the file.
	 */
	public static void assembleAndWriteFile(java.lang.String filename, boolean annotated) throws java.io.FileNotFoundException {
	int i = 0, j = 0;
		if(annotated) {
			int size = assembleFromFile(filename).size(); 
			String r = filename.substring(0, filename.indexOf(".")) + ".mach227";
			File f = new File(r);
			PrintWriter keyboard = new PrintWriter(f);
			while(j < size) {
				keyboard.println(assembleFromFile(filename).get(j));
				j += 1;
			}
			keyboard.close();
			
		}
		else {
			int size = createMemoryImageFromFile(filename).length;
			String r = filename.substring(0, filename.indexOf(".")) + ".mach227";
			File f = new File(r);
			PrintWriter keyboard = new PrintWriter(f);
			while(i < size) {
				keyboard.printf("%+05d\n",createMemoryImageFromFile(filename)[i]);
				i += 1;
			}
			keyboard.printf("-99999\n");
			keyboard.close();
		}

		
	}
	/**
	 * Reads the given file and assembles the program, returning the machine code as a list of strings (including descriptions).
	 * @param filename - Variable that holds the file.
	 * @return Assembled instruction stream.
	 * @throws FileNotFoundException
	 * temp - Holds each updated line from the file.
	 * fromFile - holds the raw instruction stream
	 */
	public static java.util.ArrayList<java.lang.String> assembleFromFile(java.lang.String filename) throws FileNotFoundException {
		File f = new File(filename);
		Scanner in = new Scanner(f);
		
		while(in.hasNextLine()) {
			String temp = in.nextLine();
			fileCode.add(temp);
		}
		in.close();
		CS227Asm fromFile = new CS227Asm(fileCode);
		return fromFile.assemble();
		
	}
	/**
	 * Reads the given file and assembles the program, returning the machine code as an array of integer values (not including the sentinel value).
	 * @param filename - Holds the input file
	 * @return - The array of the instruction stream.
	 * @throws FileNotFoundException
	 * codeSize - The length of the instruction array.
	 * codeLine - Holds the updated lines for the instruction(numerical values).
	 */
	public static int[] createMemoryImageFromFile(java.lang.String filename) throws FileNotFoundException {
		int codeSize = assembleFromFile(filename).size();
		codeSize = codeSize - 1;
		Scanner in;
		int i = 0;
		arrayCode = new int[codeSize];
		while(i <= codeSize) {
			String codeLine = assembleFromFile(filename).get(i);
			in = new Scanner(codeLine);
			arrayCode[i] = in.nextInt();
			i += 1;
		}
			
		return arrayCode;
	}
}
