package api;

import java.util.Scanner;

/**
 * An Instruction represents an opcode, operand, and an optional
 * description string.  Initially when an Instruction is constructed,
 * the operand is a symbolic name, but the numerical value can
 * be set later via the <code>setOperand</code> method.  
 * <p>
 * For convenience, the class includes some static constants and 
 * utility methods for associating instruction names with opcodes
 * using NVPairs.
 */
public class Instruction
{

  // we represent the possible opcodes with name/value pairs
  // to associate the written name with the numerical value
  public static final NVPair READ =  new NVPair("read",  10);
  public static final NVPair WRITE = new NVPair("write", 20);
  public static final NVPair LOAD =  new NVPair("load",  30);
  public static final NVPair STORE = new NVPair("store", 40);
  public static final NVPair ADD =   new NVPair("add",   50);
  public static final NVPair SUB =   new NVPair("sub",   51);
  public static final NVPair DIV =   new NVPair("div",   52);
  public static final NVPair MOD =   new NVPair("mod",   53);
  public static final NVPair MUL =   new NVPair("mul",   54);
  public static final NVPair JUMP =  new NVPair("jump",  60);
  public static final NVPair JUMPN = new NVPair("jumpn", 61);
  public static final NVPair JUMPZ = new NVPair("jumpz", 63);
  public static final NVPair HALT =  new NVPair("halt",  80);

  /**
   * The opcode value for this instruction.
   */
  private NVPair opcode;
  
  /**
   * The operand string for this instruction (variable name
   * or label).
   */
  private String operandString;
  
  /**
   * The integer operand value for this instruction.
   */
  private int operand;
  
  /**
   * Description string for this instruction (opcode name plus
   * operand string, except for the "halt" instruction which has 
   * no operand).
   */
  private String description;
  
  /**
   * Constructs an instruction from the given string. The 
   * string consists of an opcode name followed by a variable
   * name or a label, and optionally a comment which is ignored.
   * (In the case of the "halt" instruction, there is no name or 
   * label.)  When first constructed the operand value is always zero.
   * @param s
   *   given string representing an assembly language instruction
   */
  public Instruction(String s)
  {
    Scanner temp = new Scanner(s);
    String opcodeString = temp.next();
    opcode = getByName(opcodeString);
    description = opcode.getName();
    operandString = "";
    if (!opcode.equals(HALT))
    {
      operandString = temp.next();
      description += " " + operandString;
    }
  }
  
  /**
   * Returns the NVPair of an instruction given its name; case insensitive.
   * @param name
   *   the string name to search for
   * @return
   *  The NVPair associated with the given name
   * @throws IllegalArgumentException
   *  if the given name does not correspond to a valid instruction
   */
  public static NVPair getByName(String name)
  {
    if (name.equalsIgnoreCase(READ.getName())) {
      return READ;
    } else if (name.equalsIgnoreCase(WRITE.getName())) {
      return WRITE;
    } else if (name.equalsIgnoreCase(LOAD.getName())) {
      return LOAD;
    } else if (name.equalsIgnoreCase(STORE.getName())) {
      return STORE;
    } else if (name.equalsIgnoreCase(ADD.getName())) {
      return ADD;
    } else if (name.equalsIgnoreCase(SUB.getName())) {
      return SUB;
    } else if (name.equalsIgnoreCase(DIV.getName())) {
      return DIV;
    } else if (name.equalsIgnoreCase(MOD.getName())) {
      return MOD;
    } else if (name.equalsIgnoreCase(MUL.getName())) {
      return MUL;
    } else if (name.equalsIgnoreCase(JUMP.getName())) {
      return JUMP;
    } else if (name.equalsIgnoreCase(JUMPN.getName())) {
      return JUMPN;
    } else if (name.equalsIgnoreCase(JUMPZ.getName())) {
      return JUMPZ;
    } else if (name.equalsIgnoreCase(HALT.getName())) {
      return HALT;
    }
    
    // report error
    throw new IllegalArgumentException("There is no instruction named " + name);
  }

  /**
   * Returns the NVPair of an instruction given its opcode.
   * @param value
   *   the value to search for
   * @return
   *  The NVPair for the instruction with the given opcode
   * @throws IllegalArgumentException
   *  if the given opcode does not correspond to a valid instruction
   */
  public static NVPair getByValue(int value)
  {
    if (value == READ.getValue()) {
      return READ;
    } else if (value == WRITE.getValue()) {
      return WRITE;
    } else if (value == LOAD.getValue()) {
      return LOAD;
    } else if (value == STORE.getValue()) {
      return STORE;
    } else if (value == ADD.getValue()) {
      return ADD;
    } else if (value == SUB.getValue()) {
      return SUB;
    } else if (value == DIV.getValue()) {
      return DIV;
    } else if (value == MOD.getValue()) {
      return MOD;
    } else if (value == MUL.getValue()) {
      return MUL;
    } else if (value == JUMP.getValue()) {
      return JUMP;
    } else if (value == JUMPN.getValue()) {
      return JUMPN;
    } else if (value == JUMPZ.getValue()) {
      return JUMPZ;
    } else if (value == HALT.getValue()) {
      return HALT;
    }
    
    // report error
    throw new IllegalArgumentException("There is no instruction with opcode " + value);
  }

  /**
   * Returns true if this instruction requires a data address.
   * @return
   *   true for READ, WRITE, LOAD, STORE, ADD, SUB, DIV, MOD, and MUL; false otherwise
   */
  public boolean requiresDataAddress()
  {
    return opcode.getValue() < JUMP.getValue();
  }
  
  /**
   * Returns true if this instruction requires a jump target.
   * @return
   *   true for JUMP, JUMPN, and JUMPZ; false otherwise
   */
  public boolean requiresJumpTarget()
  {
    return opcode.equals(JUMP) || 
        opcode.equals(JUMPN) || 
        opcode.equals(JUMPZ);
  }
  
  /**
   * Appends the label information to the instruction's description.
   * @param label
   *   label to be appended 
   */
  public void addLabelToDescription(String label)
  {
    description += " (label " + label + ")";
  }
  
  /**
   * Sets the operand value for this instruction (data address or jump
   * target address)
   * @param value
   *   integer value to be set
   */
  public void setOperand(int value)
  {
    operand = value;
  }
  
  /**
   * Returns the opcode for this instruction object.
   * @return
   *   opcode for this instruction
   */
  public NVPair getOpcode()
  {
    return opcode;
  }
  
  /**
   * Returns the operand string for this instruction (variable name
   * or jump target label).
   * @return
   *   operand string for this instruction
   */
  public String getOperandString()
  {
    return operandString;
  }
  
  /**
   * Returns the operand value for this instruction.
   * @return
   *   operand value
   */
  public int getOperand()
  {
    return operand;
  }
  
  /**
   * Returns this instruction as a string representing a machine instruction
   * followed by a description.
   * @return
   *   string representation of this instruction
   */
  public String toString()
  {
    return String.format("%c%02d%02d %s", '+', opcode.getValue(), operand, description);
  }
}
