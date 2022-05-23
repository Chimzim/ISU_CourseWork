package edu.iastate.cs228.hw4;

/**
 *  
 * @author Chimzim Ogbondah
 *
 */

/**
 * 
 * This class evaluates a postfix expression using one stack.    
 *
 */

import java.util.HashMap;
import java.util.NoSuchElementException;


public class PostfixExpression extends Expression 
{
	private int leftOperand = 0;            // left operand for the current evaluation step             
	private int rightOperand = 0;           // right operand (or the only operand in the case of 
	                                    // a unary minus) for the current evaluation step	

	private PureStack<Integer> operandStack;  // stack of operands
	

	/**
	 * Constructor stores the input postfix string and initializes the operand stack.
	 * 
	 * @param st      input postfix string. 
	 * @param varTbl  hash map that stores variables from the postfix string and their values.
	 */
	public PostfixExpression (String st, HashMap<Character, Integer> varTbl)
	{
		// TODO
		postfixExpression = removeExtraSpaces(st);
		varTable = new HashMap<Character, Integer>();
		varTable.putAll(varTbl);
		operandStack = new ArrayBasedStack<Integer>();
	}
	
	
	/**
	 * Constructor supplies a default hash map. 
	 * 
	 * @param s
	 */
	public PostfixExpression (String s)
	{
		postfixExpression = removeExtraSpaces(s);
		varTable = new HashMap<Character, Integer>();
		operandStack = new ArrayBasedStack<Integer>();
		// TODO 
	}

	
	/**
	 * Outputs the postfix expression according to the format in the project description.
	 */
	@Override 
	public String toString()
	{
		// TODO 
		return postfixExpression; 
	}
	

	/**
	 * Resets the postfix expression. 
	 * @param st
	 */
	public void resetPostfix (String st)
	{
		postfixExpression = st; 
	}


	/**
     * Scan the postfixExpression and carry out the following:  
     * 
     *    1. Whenever an integer is encountered, push it onto operandStack.
     *    2. Whenever a binary (unary) operator is encountered, invoke it on the two (one) elements popped from  
     *       operandStack,  and push the result back onto the stack.  
     *    3. On encountering a character that is not a digit, an operator, or a blank space, stop 
     *       the evaluation. 
     *       
     * @return value of the postfix expression 
     * @throws ExpressionFormatException with one of the messages below: 
     *  
     *           -- "Invalid character" if encountering a character that is not a digit, an operator
     *              or a whitespace (blank, tab); 
     *           --	"Too many operands" if operandStack is non-empty at the end of evaluation; 
     *           -- "Too many operators" if getOperands() throws NoSuchElementException; 
     *           -- "Divide by zero" if division or modulo is the current operation and rightOperand == 0;
     *           -- "0^0" if the current operation is "^" and leftOperand == 0 and rightOperand == 0;
     *           -- self-defined message if the error is not one of the above.
     *           
     *         UnassignedVariableException if the operand as a variable does not have a value stored
     *            in the hash map.  In this case, the exception is thrown with the message
     *            
     *           -- "Variable <name> was not assigned a value", where <name> is the name of the variable.  
     *           
     */
	public int evaluate() 
    {
    	for(int i = 0; i < postfixExpression.length(); i++) {
    		if(postfixExpression.charAt(i) != ' ') {
    			if(isVariable(postfixExpression.charAt(i))) {
    				operandStack.push(varTable.get(postfixExpression.charAt(i)));
    			}
    			else if(isOperator(postfixExpression.charAt(i))) {
    				getOperands(postfixExpression.charAt(i));
    				if(rightOperand != leftOperand) {
    					int temp = compute(postfixExpression.charAt(i));
    					operandStack.push(temp);
    							leftOperand = 0;
    		    				rightOperand = 0;
    				}
    			}
    			else {
    				int count = 1;
    				if(i+1 < postfixExpression.length()) {
    					if(!isOperator(postfixExpression.charAt(i+1)) && 
    						!isVariable(postfixExpression.charAt(i+1)) && postfixExpression.charAt(i+1) != ' ') {
    						int j = i+1;
    						count *= 10;
    						while(j+1 < postfixExpression.length()) {
    							if(!isOperator(postfixExpression.charAt(j+1)) && 
    								!isVariable(postfixExpression.charAt(j+1)) && postfixExpression.charAt(j+1) != ' ') {
    								count *= 10;
    								j += 1;
    							}
    							else {
    								break;
    							}
    						int temp = (postfixExpression.charAt(i) * count) + postfixExpression.charAt(j);
    						operandStack.push(temp);
    						i = j;
    						}
    					}
    				}
    			}
    		}
    	}
		return operandStack.peek();  
    }
	

	/**
	 * For unary operator, pops the right operand from operandStack, and assign it to rightOperand. The stack must have at least
	 * one entry. Otherwise, throws NoSuchElementException.
	 * For binary operator, pops the right and left operands from operandStack, and assign them to rightOperand and leftOperand, respectively. The stack must have at least
	 * two entries. Otherwise, throws NoSuchElementException.
	 * @param op
	 * 			char operator for checking if it is binary or unary operator.
	 */
	private void getOperands(char op) throws NoSuchElementException 
	{
		if(operandStack.isEmpty()) {
			throw new NoSuchElementException();
		}
		else{
			if(op == '~') {
			rightOperand = operandStack.pop();
			rightOperand *= -1;
			}
			else if(operandStack.size() < 2) {
				throw new NoSuchElementException();
			}
			else {
			rightOperand = operandStack.pop();
			leftOperand = operandStack.pop();
			}
		}
	}


	/**
	 * Computes "leftOperand op rightOprand" or "op rightOprand" if a unary operator. 
	 * 
	 * @param op operator that acts on leftOperand and rightOperand. 
	 * @return
	 */
	private int compute(char op)  
	{
		if(op == '+' ) {
			return leftOperand + rightOperand;
		}
		else if(op == '-') {
			return leftOperand - rightOperand;
		}
		else if(op == '/') {
			return leftOperand / rightOperand;
		}
		else if(op == '*') {
			return leftOperand * rightOperand;
		}
		else if(op == '%') {
			return leftOperand % rightOperand;
		}
		else {
			return (int) Math.pow(leftOperand, rightOperand);
		}
	}
}
