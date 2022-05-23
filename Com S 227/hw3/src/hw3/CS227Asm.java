package hw3;
import java.util.ArrayList;
import java.util.Scanner;
import api.Instruction;
import api.SymbolTable;
import api.NVPair;
/**
 * implementing an assembler for the simple computer that translates assembly language into machine code.
 * @author ogbondah
 *
 */
public class CS227Asm {
	/**
	 * ComputerLabel - Holds the input data for the Label table.
	 * ComputerData - Holds the input data for the data table.
	 * ComputerInstrucction - Holds the input data for the instruction stream.
	 * rawData - Holds the raw data that will be parsed into the label and data table, and then put into the instruction stream.
	 * keyAdress - Is a place holder for the current spot in the instruction stream.
	 */
	private SymbolTable ComputerLabel = new SymbolTable();
	private SymbolTable ComputerData = new SymbolTable();
	private ArrayList<Instruction> ComputerInstruction = new ArrayList<Instruction>();
	private ArrayList<String> rawData;
	private int keyAdress;
	/**

	 * Constructs an assembler for the given assembly language program, given as an ArrayList of strings (one per line of the program). Initially both symbol tables and the instruction stream are empty.

	 * @param program

	 */

	public CS227Asm(java.util.ArrayList<java.lang.String> program) {
		rawData = program;
	}

	/**

	 * Returns the symbol table for data (variables).

	 * @return The data from the Data table.

	 */
	public SymbolTable getData() {
		return ComputerData;
	}
	/**

	 * Returns the symbol table for labels (jump targets).

	 * @return The data from the Label table.

	 */
	public SymbolTable getLabels() {
		return ComputerLabel;
	}
	/**
	 * Returns the instruction stream. The index of each instruction in the list is its memory location in the generated code.

	 * @return The data from the instruction stream.

	 */

	public java.util.ArrayList<Instruction> getInstructionStream(){
		return ComputerInstruction;
	}
	/**

	 * Assembles the source program represented by this assembler instance and returns the generated machine code and data as an array of strings.

	 * @return The fully written instruction code.

	 */
	public java.util.ArrayList<java.lang.String> assemble(){
		parseData();
		 parseLabels();
		 parseInstructions();
		 setOperandValues();
		 addLabelAnnotations();
		 return writeCode();
	}
	/**

	 * Creates the symbol table for the data section of this assembler's program.
	 *  dataSet - Holds each line input from the raw data.
	 *  dataName - Holds the variable name from the parsed data for the data table.
	 */
	public void parseData() {
		String dataSet = "";
		for(int i = 0; i < rawData.size(); i++) {
			dataSet = rawData.get(i);
			Scanner in = new Scanner(dataSet);
			String dataName = in.next();
			if(!dataName.equals("data:")){
				if(!dataName.equals("labels:")) {
					int dataValue = in.nextInt();
					ComputerData.add(dataName, dataValue);
				}
		 	}
			if(dataName.equals("labels:")) {
				keyAdress = i;
				break;
			}
		}
	}
	/**

	 * Creates the symbol table for the label section of this assembler's program, leaving all label values as zeros.
	 * dataName - Holds the variable name from the parsed data for the label table.
	 * dataSet - Holds each line input from the raw data.
	 */
	public void parseLabels() {
		String dataName = "";
		for(int i = keyAdress; i < rawData.size(); i++) {
			String dataSet = rawData.get(i);
			Scanner in = new Scanner(dataSet);
			if(!dataSet.equals("labels:")){
				dataName = in.next();
				if(!dataName.equals("instructions:")) {
					ComputerLabel.add(dataName, 0);
				}
		 	}
			if(dataName.equals("instructions:")) {
				keyAdress = i;
				break;
			}
		}

	}

	/**

	 * Creates instruction stream from the instruction section of this assembler's program and fills in label addresses in the symbol table for labels.
	 * countForInstruction - Keeps track of how many instructions have been read for the operand values.
	 * dataSet - Holds each line from the raw data.
	 */

	public void parseInstructions() {
		int countForInstructions = 0;
		for(int i = keyAdress; i < rawData.size(); i++) {
			String dataSet = rawData.get(i);
			if(!dataSet.equals("instructions:")) {
				if(ComputerLabel.containsName(dataSet)) {
					ComputerLabel.findByName(dataSet).setValue(countForInstructions);
				}
				else {
				Instruction data = new Instruction(dataSet);
				ComputerInstruction.add(data);
				countForInstructions++;
				}
			}
			}
	}

	/**

	 * Fills in the correct operand value for all instructions (either a data address or jump target address, depending on the instruction).
	 * length - Holds the length of the instruction stream.
	 * tempName - Holds the temporary name from the instruction stream based on the raw data line corresponding with the given instruction
	 * temp - Holds the name from the instruction stream to be found in the label and data tables.
	 * value - Holds the value from either the label or data table based on the name.
	 */

	public void setOperandValues() {
		int length = ComputerInstruction.size();
		for(int i = 0; i < length; i++) {
			String tempName = ComputerInstruction.get(i).getOperandString();
		if(ComputerInstruction.get(i).requiresJumpTarget()) {
			NVPair temp = ComputerLabel.findByName(tempName);
			int value = temp.getValue();
			ComputerInstruction.get(i).setOperand(value);
			}
		if(ComputerInstruction.get(i).requiresDataAddress()) {
			NVPair temp = ComputerData.findByName(tempName);
			int value = temp.getValue();
			ComputerInstruction.get(i).setOperand(value+length);
			}
		} 
	}

	/**

	 * For each instruction in the instruction stream that is a jump target, adds the label to the instruction's description. (See the method addLabelToDescription in the Instruction class.)
	 * pointer - holds the value of the label from the label table.
	 * labelName - Holds the value of the label from the label tabel.
	 */

	public void addLabelAnnotations() {
		for(int i = 0; i < ComputerLabel.size(); i++) {
			int pointer = ComputerLabel.getByIndex(i).getValue();
			String labelName = ComputerLabel.getByIndex(i).getName();
			ComputerInstruction.get(pointer).addLabelToDescription(labelName);
		}
	}

	/**

	 * Generates the machine code and data for this assembler's program, terminated by the string "-99999". Strings for instructions are produced by the Instruction method toString, and strings for data have the value formatted as a four-digit signed integer, followed by a space, followed by the variable name.
	 * ComputerCode - Holds the fully written instruction stream.
	 * Name - holds the name from the data table based on the corresponding data line.
	 * dataValue - Holds the value of the "Name".
	 * formating - String used to format the data into an instruction stream.
	 * @return

	 */

	public java.util.ArrayList<java.lang.String> writeCode(){
		ArrayList<String> ComputerCode = new ArrayList<String>();
		int j = 0;
		for(int i = 0; i < ComputerInstruction.size(); i++) {
			ComputerCode.add(ComputerInstruction.get(i).toString());
		}
		while(j < ComputerData.size()) {
			String Name = ComputerData.getByIndex(j).getName();
			int dataValue = ComputerData.getByIndex(j).getValue();
			String formating = String.format("%+05d %s", dataValue, Name);
			ComputerCode.add(formating);
			j += 1; 
		}
		ComputerCode.add("-99999");

		return ComputerCode;

		

	}
}