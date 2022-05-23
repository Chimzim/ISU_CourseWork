package mini1;

import java.util.Random;
import java.util.Scanner;


public class FromLoopToNuts {
	private FromLoopToNuts() { }
	public static int countMatches(java.lang.String s, java.lang.String t) {
		int shorterString;
		int counter = 0;
		if(s.length() < t.length()) {
			shorterString = s.length();
		}
		else {
			shorterString = t.length();
		}
		for(int i = 0; i < shorterString; i++) {
			if(s.charAt(i) == t.charAt(i)) {
				counter += 1;
			}
		}
		return counter;
	}
	
	public static int countSubstrings(java.lang.String t, java.lang.String s) {
		int counter1 = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.substring(i).startsWith(t) ){
				counter1++;
				i += t.length() -1;
			}
		}
		return counter1;
	}
	
	public static int countSubstringsWithOverlap(java.lang.String t, java.lang.String s) {
		int counter = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.substring(i).startsWith(t)) {
				counter++;
			}
		}
		return counter;
	}
	public static boolean differByOneSwap(java.lang.String s, java.lang.String t) {
		boolean offOrNot = false;
		int count = 0;
		if(s.length() < t.length()) {
			return false;
		}
		if(t.length() < s.length()) {
			return false;
		}
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) != t.charAt(i)) {
				count++;
			}
		}
		if(count > 2 || count == 0) {
			offOrNot = false;
		}
		else {
			offOrNot = true;
		}
		return offOrNot;
	}
	
	public static java.lang.String 	eliminateRuns(java.lang.String s) {
		String newString = "";
		if(s.isEmpty()) {
			return newString;
		}
		for(int i = 0; i < s.length(); ++i) {
			if(i == 0) {
				newString += s.charAt(i);
			}
			if(i != 0 && s.charAt(i-1) != s.charAt(i)) {
				newString += s.charAt(i);
			}
		}
		return newString;
	}
	public static int findEscapeCount(double x, double y, int maxIterations) {
		double a = 0;
		double b = 0;
		int count = 0; 
		double tempA, tempB;
		while( (a*a + b*b ) < 4 && maxIterations != count) {
			if(count == 0) {
				a = x;
				b = y;
				count++;
			}
			else {
				tempA = (a * a) - (b * b) + x;
				tempB =  ( (2 * a) * b) + y;
				a = tempA;
				b = tempB;
				count++;
			}
		}
		return count;
	}
	public static int threeInARow(java.util.Random rand, int bound) {
		int previous = -1;
		int past = -2;
		int present = -3;
		int count = 0;
		Random in = rand;
		while(true) {
			if(present == past) { 
				if(past == previous) {
					break;
				}
			}
			previous = past;
			past = present;
			present = in.nextInt(bound);
			count++;
		}
		return count;
	}
	public static boolean isArithmetic(String text) {
		if(text.isEmpty()) {
			return true;
		}
		for(int j = 0; j < text.length(); j++) {
			if(Character.isLetter(text.charAt(j))) {
			return false;
			}
			j++;
		}
		Scanner in = new Scanner(text);
		Scanner in2 = in.useDelimiter(",");
		int value = in2.nextInt();
		int value2 = in2.nextInt();
		int countBy = value2- value;
		for(int i = 0; i < text.length(); ++i) {
			while(in2.hasNextInt()) {
				value = value2;
				value2 = in2.nextInt();
				if(value2 - value != countBy) {
					return false;
				}
			}
		}
		return true;
		
		
	}
}


	
