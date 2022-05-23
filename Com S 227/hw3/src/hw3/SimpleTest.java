package hw3;
import java.util.ArrayList;

import api.Instruction;
import api.NVPair;
import api.SymbolTable;
import hw3.CS227Asm;

public class SimpleTest
{
  public static ArrayList<String> makeTest()
  {
    String[] temp = {
      "data:",
      "count 10",
      "const_one 1",
      "labels:",
      "point_a",
      "point_b",
      "instructions:",
      "load count",
      "point_a",      
      "jumpz point_b",
      "write count",
      "sub const_one",
      "store count",
      "jump point_a",
      "point_b",
      "halt"
    };
    ArrayList<String> program = new ArrayList<>();
    for (String s : temp)
    {
      program.add(s);
    }
    return program;
  }
  
  public static void main(String[] args)
  {
    ArrayList<String> program = makeTest();
    CS227Asm asm = new CS227Asm(program);
    
    // check the symbol table for data, this should give us
    //    count:10
    //    const_one:1
    asm.parseData();
    SymbolTable dataTable = asm.getData();
    for (int i = 0; i < dataTable.size(); i += 1)
    {
      NVPair p = dataTable.getByIndex(i);
      System.out.println(p);
    }
    System.out.println();
    
    // check the symbol table for labels, this should give us
    //    point_a:0
    //    point_b:0
    asm.parseLabels();
    SymbolTable labelTable = asm.getLabels();
    for (int i = 0; i < labelTable.size(); i += 1)
    {
      NVPair p = labelTable.getByIndex(i);
      System.out.println(p);
    }
    System.out.println();
    
    // check the instruction stream before setting operands, this should 
    // give us the seven instructions with correct opcodes, but all operands
    // equal to 00:
    //    +3000 load count
    //    +6300 jumpz point_b
    //    +2000 write count
    //    +5100 sub const_one
    //    +4000 store count
    //    +6000 jump point_a
    //    +8000 halt
    asm.parseInstructions();
    ArrayList<Instruction> instructionStream = asm.getInstructionStream();
    for (Instruction instr : instructionStream)
    {
      System.out.println(instr);
    }
    System.out.println();
    
    // also, the parseInstructions operation should update the label symbol table
    // with correct addresses for the labels, should get
    //    point_a:1
    //    point_b:6
    labelTable = asm.getLabels();
    for (int i = 0; i < labelTable.size(); i += 1)
    {
      NVPair p = labelTable.getByIndex(i);
      System.out.println(p);
    }
    System.out.println();
    
    // check the instruction stream after setting operands
    //    +3007 load count
    //    +6306 jumpz point_b
    //    +2007 write count
    //    +5108 sub const_one
    //    +4007 store count
    //    +6001 jump point_a
    //    +8000 halt
    asm.setOperandValues();
    instructionStream = asm.getInstructionStream();
    for (Instruction instr : instructionStream)
    {
      System.out.println(instr);
    }
    System.out.println();
    
    // check the instruction stream after adding label annotations
    //    +3007 load count
    //    +6306 jumpz point_b (label point_a)
    //    +2007 write count
    //    +5108 sub const_one
    //    +4007 store count
    //    +6001 jump point_a
    //    +8000 halt (label point_b)
    asm.addLabelAnnotations();
    instructionStream = asm.getInstructionStream();
    for (Instruction instr : instructionStream)
    {
      System.out.println(instr);
    }
    System.out.println();
    
    // check the complete machine language program
    //    +3007 load count
    //    +6306 jumpz point_b (label point_a)
    //    +2007 write count
    //    +5108 sub const_one
    //    +4007 store count
    //    +6001 jump point_a
    //    +8000 halt (label point_b)
    //    +0010 count
    //    +0001 const_one
    //    -99999
    ArrayList<String> result = asm.writeCode();
    for (String s : result)
    {
      System.out.println(s);
    }
    System.out.println();
    
    // we should now be able to do all of the above with one call to assemble(),
    // and get exactly the same result as above
    CS227Asm asm2 = new CS227Asm(makeTest());
    result = asm2.assemble();
    for (String s : result)
    {
      System.out.println(s);
    }
    System.out.println();
  }

}