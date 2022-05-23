package mini1;

import java.util.Random;


public class mini1 {
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
		for(int i = 0; i < s.length(); i += t.length()) {
			if(s.substring(i).startsWith(t) ){
				counter1++;
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
		if(count == 1){
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
		while(past != present && past != previous) {
			previous = past;
			past = present;
			present = in.nextInt(bound);
			count++;
		}
		return count;
	}
	public static boolean isArithmetic(String text) {
		String[] items = text.split(",");
		int c = 0;
		int countBy = Integer.MIN_VALUE;
		if(text.isEmpty() || items.length <=2) {
			return true;
		}
		int[] values = new int[items.length];
		for(String item: items) {
			try {
				int i = values.length;
				values[c] = i; 
				if(c == 1) {
					countBy = values[1] - values[0]; 
			}
				else if(c != 0){
					if(countBy != (values[c] - values[c-1])) {
						return false;
					}
				}
				c++;
			}
			catch (Exception e) {
					return false;
				}
			}
		return true;
		}
	
		
	}


	
