package cs228hw2.test;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Post Fix calculator. uses stack and queue and uses methods from the precise number class to do opperations 
 * @author chimzim Ogbondah
 *
 */
public class calculator {
	/**
	 * Adds items on the stack based on an input. Checks to see where the values are valid. from there upon meeting an operation 
	 * the items needed for the operation are removed from the queue and then used in the operation. Finally at the end the final answer is printed
	 * temp1 - used to hold the first number popped from the stack
	 * temp2 - used to hold the second number popped from the stack
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Deque228<Integer> postFixCalc = new Deque228<Integer>();
		while(in.hasNext()) {
			if(in.next().matches("[0-9]+") || in.next().matches("-?\\d+")) {
				postFixCalc.add(Integer.parseInt(in.next()));
			}
			else if(in.next().matches("+")) {
				int temp1 = 0;
				int temp2 = 0;
				if(postFixCalc.size() < 2) {
					System.err.println("Can not preform operation due to lack of operands");
				}
				else {
					temp1 = postFixCalc.pop();
					temp2 = postFixCalc.pop();
					postFixCalc.add(temp1+temp2);
				}
			}
			else if(in.next().matches("-")) {
				int temp1 = 0;
				int temp2 = 0;
				if(postFixCalc.size() < 2) {
					System.err.println("Can not preform operation due to lack of operands");
				}
				else {
					temp1 = postFixCalc.pop();
					temp2 = postFixCalc.pop();
					postFixCalc.push(temp1-temp2);
				}
			}
			else if(in.next().matches("neg")) {
				int temp  = 0;
				if(postFixCalc.size() < 1) {
					System.err.println("Can not preform operation due to lack of operands");
				}
				temp = postFixCalc.pop();
				if(temp > 0) {
					postFixCalc.push(temp*-1);
				}
				else {
					postFixCalc.push(temp);
				}
				
			}
			else if(in.next().matches("abs")) {
				int temp = 0;
				if(postFixCalc.size() < 1) {
					System.err.println("Can not preform operation due to lack of operands");
				}
				temp = postFixCalc.pop();
				if(temp > 0) {
					postFixCalc.push(temp);
				} 
				else {
					postFixCalc.push(temp*-1);
				}
			}
			else {
				System.err.println("Can not preform operation due to lack of operands");
			}
		}
		if(postFixCalc.size()> 1) {
			System.err.println("Can not preform operation due to lack of operands");
		}
		else {
			System.out.println(postFixCalc.pop());
		}
	}
}
