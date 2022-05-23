package edu.iastate.cs228.hw4;

import java.util.ArrayList;

/**
 *  
 * @author Chimzim Ogbondah
 *
 */

/**
 * 
 * This class evaluates input infix and postfix expressions. 
 *
 */

import java.util.HashMap;
import java.util.Scanner;

public class InfixPostfix 
{

	/**
	 * Repeatedly evaluates input infix and postfix expressions.  See the project description
	 * for the input description. It constructs a HashMap object for each expression and passes it 
	 * to the created InfixExpression or PostfixExpression object. 
	 *  
	 * @param args
	 * @throws ExpressionFormatException 
	 **/
	public static void main(String[] args) throws ExpressionFormatException 
	{
		int numberOfTrials = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("Evaluation of Infix and Postfix Expressions");
		System.out.println("keys: 1 (standard input) 2 (file input) 3 (exit)");
		System.out.println("(Enter 'I' before an infix expression, 'P' before a postfix expression)\n");

		System.out.println("Trials" +numberOfTrials +":");
		int key = in.nextInt();
		ArrayList<Character> variables = new ArrayList<Character>();
		if(key == 1) {
			System.out.println("Expression: ");
			String myString = in.next();
			if(myString.charAt(0) == 'I') {
				String temp = "";
				HashMap<Character, Integer> myMap = new HashMap<Character, Integer>();
				for(int i = 2; i < myString.length(); i++) {
					temp += myString.charAt(i);
					if(Character.isLetter(myString.charAt(i))) {
						variables.add(myString.charAt(i));
					}
				}
				InfixExpression Expression = new InfixExpression(temp);
				System.out.println("Infix form: "+ Expression.toString());
				Expression.postfix();
				System.out.println("Postfix form: "+ Expression.postfixString());
				System.out.println("where");
				for(int i = 0; i < variables.size(); i++) {
					System.out.println(variables.get(i) + " = ");
					int values = in.nextInt();
					myMap.put(variables.get(i), values);
				}
				Expression = new InfixExpression(temp, myMap);
				System.out.println("Expression value: " + Expression.evaluate());
				numberOfTrials += 1;
			}
			else {
				String temp = "";
				HashMap<Character, Integer> myMap = new HashMap<Character, Integer>();
				for(int i = 2; i < myString.length(); i++) {
					temp += myString.charAt(i);
					if(Character.isLetter(myString.charAt(i))) {
						variables.add(myString.charAt(i));
					}
				}
				PostfixExpression Expression = new PostfixExpression(temp);
				System.out.println("Postfix form: "+ Expression.toString());
				System.out.println("where");
				for(int i = 0; i < variables.size(); i++) {
					System.out.println(variables.get(i) + " = ");
					int values = in.nextInt();
					myMap.put(variables.get(i), values);
				}
				Expression = new PostfixExpression(temp, myMap);
				System.out.println("Expression value: " + Expression.evaluate());
				numberOfTrials += 1;
			}
		}
		else if(key == 2) {
			HashMap<Character, Integer> myMap = new HashMap<Character, Integer>();
			System.out.println("Input from a file");
			System.out.println("Enter file name: ");
			String filename = in.next();
			Scanner scan = new Scanner(filename);
			while(scan.hasNextLine()) {
				String toUse = scan.nextLine();
				Scanner forExpression = new Scanner(toUse);
				String myExpression = toUse;
				while(forExpression.hasNext()) {
					String temp = forExpression.next();
					if(temp == "I") {
						for(int i = 2; i < myExpression.length(); i++) {
							toUse += myExpression.charAt(i);
							if(Character.isLetter(myExpression.charAt(i))) {
								variables.add(myExpression.charAt(i));
							}
						}
						InfixExpression Expression = new InfixExpression(toUse);
						System.out.println("Infix form: "+ Expression.toString());
						Expression.postfix();
						System.out.println("Postfix form: "+ Expression.postfixString());
						forExpression =  new Scanner(scan.nextLine());
						for(int i = 0; i < variables.size(); i++) {
							myMap.put(variables.get(i), forExpression.nextInt());
							if(scan.hasNextLine()) {
								forExpression =  new Scanner(scan.nextLine());
							}
						}
						System.out.println("where");
						for(int i = 0; i < variables.size(); i++) {
							System.out.println(variables.get(i) + " = "+ myMap.get(variables.get(i)));
						}
						Expression = new InfixExpression(temp, myMap);
						System.out.println("Expression value: " + Expression.evaluate());
					}
					else {
						for(int i = 2; i < myExpression.length(); i++) {
							toUse += myExpression.charAt(i);
							if(Character.isLetter(myExpression.charAt(i))) {
								variables.add(myExpression.charAt(i));
							}
						}
						PostfixExpression Expression = new PostfixExpression(toUse);
						System.out.println("Postfix form: "+ Expression.toString());
						for(int i = 0; i < variables.size(); i++) {
							myMap.put(variables.get(i), forExpression.nextInt());
							if(scan.hasNextLine()) {
								forExpression =  new Scanner(scan.nextLine());
							}
						}
						System.out.println("where");
						for(int i = 0; i < variables.size(); i++) {
							System.out.println(variables.get(i) + " = "+ myMap.get(variables.get(i)));
						}
						Expression = new PostfixExpression(temp, myMap);
						System.out.println("Expression value: " + Expression.evaluate());
					}
					}
				}
			numberOfTrials+=1;
			}
		else if(key >= 3) {
			System.exit(key);
		}
	}
	
	// helper methods if needed
}
