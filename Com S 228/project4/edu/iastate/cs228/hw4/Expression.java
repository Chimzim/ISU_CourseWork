package edu.iastate.cs228.hw4;

import java.util.HashMap;
import java.util.Map;

public abstract class Expression 
{
	protected String postfixExpression; 		
	protected HashMap<Character, Integer> varTable; // hash map to store variables in the 

	
	protected Expression()
	{
		// no implementation needed 
		// removable when you are done
	}
	
	
	/**
	 * Initialization with a provided hash map. 
	 * 
	 * @param varTbl
	 */
	protected Expression(String st, HashMap<Character, Integer> varTbl)
	{
		varTable.putAll(varTbl);
		postfixExpression = st;
		// TODO 
	}
	
	
	/**
	 * Initialization with a default hash map.
	 * 
	 * @param st
	 */
	protected Expression(String st) 
	{
		postfixExpression = st;
		varTable = new HashMap<Character, Integer>();
		// TODO 
	}

	
	/**
	 * Setter for instance variable varTable.
	 * @param varTbl
	 */
	public void setVarTable(HashMap<Character, Integer> varTbl) 
	{
		for(Map.Entry<Character, Integer> values: varTbl.entrySet()) {
			varTable.put(values.getKey(), values.getValue());
		}
		// TODO 
	}
	
	
	/**
	 * Evaluates the infix or postfix expression. 
	 * 
	 * @return value of the expression 
	 * @throws ExpressionFormatException, UnassignedVariableException
	 */
	public abstract int evaluate() throws ExpressionFormatException, UnassignedVariableException;  

	
	
	// --------------------------------------------------------
	// Helper methods for InfixExpression and PostfixExpression 
	// --------------------------------------------------------

	/** 
	 * Checks if a string represents an integer.  You may call the static method 
	 * Integer.parseInt(). 
	 * 
	 * @param s
	 * @return
	 */
	protected static boolean isInt(String s) 
	{
		if(s == null) {
			return false;
		}
		try {
			int x = Integer.parseInt(s);
			return true;
		}
		catch (NumberFormatException NFE){
			 return false;
		}
	}

	
	/**
	 * Checks if a char represents an operator, i.e., one of '~', '+', '-', '*', '/', '%', '^', '(', ')'. 
	 * 
	 * @param c
	 * @return
	 */
	protected static boolean isOperator(char c) 
	{
		if(c == '~' || c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^' || c =='(' || c == ')') {
			return true;
		}
		return false; 
	}

	
	/** 
	 * Checks if a char is a variable, i.e., a lower case English letter. 
	 * 
	 * @param c
	 * @return
	 */
	protected static boolean isVariable(char c) 
	{
		if(Character.isLetter(c)) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Removes extra blank spaces in a string. 
	 * @param s
	 * @return
	 */
	protected static String removeExtraSpaces(String s) 
	{
		String myString = "";
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) != ' ' && s.charAt(i+1) != ' ') {
				myString += s.charAt(i);
			}
		}
		return myString; 
	}

}
