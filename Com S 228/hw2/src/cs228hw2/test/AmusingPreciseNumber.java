package cs228hw2.test;

import java.io.Reader;
import java.util.LinkedList;
/**
 * 
 * @author chimzim Ogbondah
 *Creates a precise number that will used in the postfix calculator 
 */
public class AmusingPreciseNumber {
	/**
	 * myNumbPrior - number before the decimal place
	 * myNumbAfter - number after the decimal place
	 * myStringNumb - used to iterate through and find the decimal place as well as the string constructor
	 */
	LinkedList<Integer> myNumbPrior = new LinkedList<>();
	LinkedList<Integer> myNumbAfter = new LinkedList<>();
	LinkedList<Character> myStringNumb = new LinkedList<>();
	
	String stringNumb;
	public AmusingPreciseNumber(Reader r ) {
		
	}
	/**
	 * Allows the use to see if the precise number was made correctly
	 * myPreciseNumb - the string used to see if the precise number was made correctly
	 */
	public String toString() {
		int found = 0;
		String myPreciseNumb = "";
		for(int i = 0; i < myNumbPrior.size(); i++) {
			if(myNumbPrior.get(i) == 0 && found == 0) {
				
			}
			else {
				myPreciseNumb += myNumbPrior.get(i);
				found = 1;
			}
		}
		if(myPreciseNumb.length() == 0) {
			myPreciseNumb += "0";
		}
		myPreciseNumb += ".";
		for(int i = 0; i < myNumbAfter.size(); i++) {
			myPreciseNumb += myNumbAfter.get(i);
		}
		return myPreciseNumb;
	}
	/**
	 * creates a new precise number from an int
	 * @param numb integer to be turned into a precise number
	 */
	public AmusingPreciseNumber(int numb) {
		int reset = 0;
		if(numb < 0 ) {
			numb = numb * -1;
			reset = 1;
		}
		while(numb > 0) {
			myNumbPrior.add(0, numb%10);
			numb = numb/10;
		}
		if(reset == 1) {
			myNumbPrior.set(0, myNumbPrior.get(0)*-1);
		}
	}
	/**
	 * Creates a precise number from the string 
	 * @param numb given string 
	 */
	public AmusingPreciseNumber(String numb) {
		boolean found = false;
		int negative = 0;
		for(int i = 0; i < numb.length(); i++) {
			if(found == false) {
				if(numb.charAt(i) == '-') {
					negative = 1;
				}
				if(negative == 1) {
					myNumbPrior.add(Character.getNumericValue(numb.charAt(i)*-1));
				}
				else {
					myNumbPrior.add(Character.getNumericValue(numb.charAt(i)));
				}
				if(numb.charAt(i) == '.') {
					found = true;
				}
			else if(found == true) {
				myNumbAfter.add(Character.getNumericValue(numb.charAt(i)));
			}
		}
			
	}
}
	/**
	 * Creates a precise number based on the given number
	 * @param numb to be turned into a precise number
	 */
	public AmusingPreciseNumber(AmusingPreciseNumber numb) {
		int negative = 0;
		int i = 0;
			while(!numb.myStringNumb.get(i).equals('.')) {
				if(numb.myStringNumb.get(i) == '-') {
					negative = 1;
					i++;
				}
				if(negative == 1) {
					myNumbPrior.add(Character.getNumericValue(numb.myStringNumb.get(i)*-1));
				i++;
				}
				else {
					myNumbPrior.add(Character.getNumericValue(numb.myStringNumb.get(i)));
					i++;
			}
		}
			i++;
			while(i < numb.myStringNumb.size()) {
				myNumbAfter.add(Character.getNumericValue(numb.myStringNumb.get(i)));
				i++;
			}
		
	}
	/**
	 * * This method adds the two given precise numbers together. it checks to see if the outcome will be either a positive or a negative 
	 * based on if it is negative it will make the numbers positive then subtract and return the number to a negative. If the number is a positive then it 
	 * will add the two numbers together and finally return the result.
	 * @param numb number to use with subtraction 
	 */
	public void add(AmusingPreciseNumber numb) {
		LinkedList<Integer> additionB = new LinkedList<>();
		LinkedList<Integer> additionA = new LinkedList<>();
		boolean negative = false;
		int counter = 0;
		int counterA = 0;
		int myCounter = 0;
		int myCounterA = 0;
		if(myNumbPrior.size() > additionB.size()) {
			while(myNumbPrior.size() != additionB.size()) {
				additionB.add(0, 0);
				counter++;
			}
		}
		if(myNumbPrior.size() < additionB.size()) {
			while(myNumbPrior.size() != additionB.size()) {
				myNumbPrior.add(0,0);
				myCounter++;
			}
		}
		if(myNumbAfter.size() > additionA.size()) {
			while(myNumbAfter.size() != additionA.size()) {
				additionA.add(0);
				counterA++;
			}
		}
		if(myNumbAfter.size() < additionA.size()) {
			while(myNumbAfter.size() != additionA.size()) {
				myNumbAfter.add(0);
				counterA++;
			}
		}
		if(additionB.get(counter) < 0 && myNumbPrior.get(myCounter) > 0) {
			if(additionB.get(counter)*-1 > myNumbPrior.get(myCounter)) {
				negative = true;
				additionB.set(counter, additionB.get(myCounter)*-1);
			}
			for(int i = myNumbAfter.size()-1; i >= 0; i--) {
				if(myNumbAfter.get(i) > additionA.get(i) && i != 0) {
					additionA.set(i, additionA.get(i)+10);
					additionA.set(i-1, additionA.get(i-1)-1);
				}
				else if(myNumbAfter.get(i) > additionA.get(i) && i == 0) {
					additionA.set(i, additionA.get(i)+10);
					additionB.set(additionB.size()-1, additionB.get(additionB.size()-1)-1);
				}
				myNumbAfter.set(i, additionA.get(i)-myNumbAfter.get(i));
			}
			for(int i = myNumbPrior.size()-1; i >= 0; i--) {
				if(myNumbAfter.get(i) > additionB.get(i) && i != 0) {
					additionB.set(i, additionB.get(i)+10);
					additionB.set(i-1, additionB.get(i-1)-1);
				}
				myNumbPrior.set(i, additionA.get(i)-myNumbPrior.get(i));
			}
			myNumbPrior.set(0, myNumbPrior.get(0)*-1);
		}
		else if(additionB.get(counter) > 0 && myNumbPrior.get(myCounter) < 0) {
			if(additionB.get(counter) < myNumbPrior.get(myCounter)*-1) {
				negative = true;
			}
			for(int i = myNumbAfter.size()- 1; i >= 0; i--) {
				if(additionA.get(i) > myNumbAfter.get(i) && i != 0) {
					myNumbAfter.set(i, myNumbAfter.get(i)+10);
					myNumbAfter.set(i-1, myNumbAfter.get(i-1)-1);
				}
				else if(additionA.get(i) > myNumbAfter.get(i) && i == 0) {
					myNumbAfter.set(i, myNumbAfter.get(i)+10);
					myNumbPrior.set(myNumbPrior.size()-1, myNumbPrior.get(myNumbPrior.size()-1)-1);
				}
				myNumbPrior.set(i, myNumbAfter.get(i)-additionA.get(i));
			}
			for(int i = myNumbPrior.size()- 1; i >= 0; i--) {
				if(additionB.get(i) > myNumbPrior.get(i) && i != 0) {
					myNumbPrior.set(i, myNumbPrior.get(i)+10);
					myNumbPrior.set(i-1, myNumbPrior.get(i-1)-1);
				}
				myNumbPrior.set(i, myNumbPrior.get(i)-additionB.get(i));
			}
			myNumbPrior.set(0, myNumbPrior.get(0)*-1);
		}
		else if(additionB.get(counter) > 0 && myNumbPrior.get(myCounter) < 0) {
			if(additionB.get(counter) > myNumbPrior.get(myCounter)*-1) {
				for(int i = myNumbAfter.size()-1; i >= 0; i--) {
					if(myNumbAfter.get(i) > additionA.get(i) && i != 0) {
						additionA.set(i, additionA.get(i)+10);
						additionA.set(i-1, additionA.get(i-1)-1);
					}
					else if(myNumbAfter.get(i) > additionA.get(i) && i == 0) {
						additionA.set(i, additionA.get(i)+10);
						additionB.set(additionB.size()-1, additionB.get(additionB.size()-1)-1);
					}
					myNumbAfter.set(i, additionA.get(i)-myNumbAfter.get(i));
				}
				for(int i = myNumbPrior.size()-1; i >= 0; i--) {
					if(myNumbPrior.get(i) > additionB.get(i) && i != 0) {
						additionB.set(i, additionB.get(i)+10);
						additionB.set(i-1, additionB.get(i-1)-1);
					}
					myNumbPrior.set(i, additionB.get(i)-myNumbPrior.get(i));
				}
			}
		}
		else if(additionB.get(counter) < 0 && myNumbPrior.get(myCounter) > 0) {
			if(additionB.get(counter)*-1 < myNumbPrior.get(myCounter)) {
				for(int i = myNumbAfter.size()-1; i >= 0; i--) {
					if(additionA.get(i) > myNumbAfter.get(i) && i != 0) {
						myNumbAfter.set(i, myNumbAfter.get(i)+10);
						myNumbAfter.set(i-1, myNumbAfter.get(i-1)-1);
					}
					else if(additionA.get(i) > myNumbAfter.get(i) && i == 0) {
						myNumbAfter.set(i, myNumbAfter.get(i)+10);
						myNumbPrior.set(myNumbPrior.size()-1, myNumbPrior.get(myNumbPrior.size()-1)-1);
					}
					myNumbAfter.set(i, myNumbAfter.get(i)-additionA.get(i));
				}
				for(int i = myNumbPrior.size()-1; i >= 0; i--) {
					if(additionB.get(i) > myNumbPrior.get(i)) {
						myNumbPrior.set(i, myNumbPrior.get(i)+10);
						myNumbPrior.set(i-1, myNumbPrior.get(i-1)-1);
					}
					myNumbPrior.set(i, myNumbPrior.get(i)-additionB.get(i));
				}
			}
		}
		else {
			for(int i = myNumbAfter.size()-1; i >= 0; i--) {
				myNumbAfter.set(i, myNumbAfter.get(i)+additionA.get(i));
				if(myNumbAfter.get(i) >= 10 && i != 0) {
					myNumbAfter.set(i, myNumbAfter.get(i)%10);
					myNumbAfter.set(i-1, myNumbAfter.get(i)+1);
				}
				else if(myNumbAfter.get(i) >= 10 && i == 0) {
					myNumbAfter.set(i, myNumbAfter.get(i)%10);
					myNumbPrior.set(myNumbPrior.size()-1, myNumbPrior.get(myNumbPrior.size()-1)+1);
				}
			}
			for(int i = myNumbPrior.size()-1; i >= 0; i--) {
				myNumbPrior.set(i, myNumbAfter.get(i)+additionA.get(i));
				if(myNumbAfter.get(i) >= 10 && i != 1) {
					myNumbPrior.set(i, myNumbAfter.get(i)%10);
					myNumbPrior.set(i-1, myNumbAfter.get(i)+1);
				}
				else if(myNumbAfter.get(i) >= 10 && i == 1) {
					if(myNumbPrior.get(0) < 0) {
						myNumbPrior.set(i, myNumbAfter.get(i)%10);
						myNumbPrior.set(i-1, myNumbAfter.get(i)-1);
						negative = true;
					}
					else {
						myNumbPrior.set(i, myNumbAfter.get(i)%10);
						myNumbPrior.set(i-1, myNumbAfter.get(i)+1);
					}
				}
				else if(myNumbAfter.get(i) >= 10 && i == 0) {
					myNumbPrior.set(i, myNumbAfter.get(i)%10);
					if(negative == true) {
						myNumbPrior.add(0, -1);
					}
					else {
						myNumbPrior.add(0, 1);
					}
				}
			}
		}
	}
	/**
	 * This method adds the two given precise numbers together. it checks to see if the outcome will be either a positive or a negative 
	 * based on if it is negative it will make the numbers positive then subtract and return the number to a negative. If the number is a positive then it 
	 * will add the two numbers together and finally return the result.
	 * @param numb1 - Precise number to use for addition
	 * @param numb2 - Precise number to use for addition
	 * @return addition of the precise numbers
	 */
	public static AmusingPreciseNumber add(AmusingPreciseNumber numb1, AmusingPreciseNumber numb2) {
		AmusingPreciseNumber Number1 = new AmusingPreciseNumber(numb1);
		AmusingPreciseNumber Number2 = new AmusingPreciseNumber(numb2);
		boolean negative = false;
		int count1B = 0;
		int count1A = 0;
		int count2B = 0;
		int count2A =0;
	
		if(Number1.myNumbPrior.size() > Number2.myNumbPrior.size()) {
			while(Number2.myNumbPrior.size() != Number1.myNumbPrior.size()) {
				Number2.myNumbPrior.add(0, 0);
				count2B++;
			}
		}
		if(Number1.myNumbPrior.size() < Number2.myNumbPrior.size()) {
			while(Number1.myNumbPrior.size() != Number2.myNumbPrior.size()) {
				Number1.myNumbPrior.add(0,0);
				count1B++;
			}
		}
		if(Number1.myNumbAfter.size() > Number2.myNumbAfter.size()) {
			while(Number1.myNumbAfter.size() != Number2.myNumbAfter.size()) {
				Number2.myNumbAfter.add(0,0);
				count2A++;
			}
		}
		if(Number1.myNumbAfter.size() < Number2.myNumbAfter.size()) {
			while(Number2.myNumbAfter.size() != Number1.myNumbAfter.size()) {
				Number1.myNumbAfter.add(0,0);
				count1A++;
			}
		}
		if(Number1.myNumbPrior.get(count1B) <= 0 && Number2.myNumbPrior.get(count2B) > 0) {
			if(Number1.myNumbAfter.get(count1A)*-1 > Number2.myNumbAfter.get(count2A)) {
				Number1.myNumbPrior.set(count1B, Number1.myNumbPrior.get(count1B)*-1);
				for(int i = Number2.myNumbAfter.size()-1; i >= 0; i--) {
					if(Number2.myNumbAfter.get(i) > Number1.myNumbAfter.get(i) && i != 0) {
						Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)+10);
						Number1.myNumbAfter.set(i-1, Number1.myNumbAfter.get(i-1)-1);
					}
					else if(Number2.myNumbAfter.get(i) > Number1.myNumbAfter.get(i) && i == 0) {
						Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)+10);
						Number1.myNumbPrior.set(Number1.myNumbPrior.size()-1, Number1.myNumbPrior.get(Number1.myNumbPrior.size()-1)-1);
					}
					Number2.myNumbAfter.set(i, Number1.myNumbAfter.get(i)-Number2.myNumbAfter.get(i));
				}
				for(int i = Number2.myNumbPrior.size()-1; i >= 0; i--) {
					if(Number2.myNumbAfter.get(i) > Number1.myNumbPrior.get(i) && i != 0) {
						Number1.myNumbPrior.set(i, Number1.myNumbPrior.get(i)+10);
						Number1.myNumbPrior.set(i-1, Number1.myNumbPrior.get(i-1)-1);
					}
					Number2.myNumbPrior.set(i, Number1.myNumbAfter.get(i)-Number2.myNumbPrior.get(i));
				}
				Number2.myNumbPrior.set(0, Number2.myNumbPrior.get(0)*-1);
				}
		}
		else if(Number1.myNumbPrior.get(count1B) >= 0 && Number2.myNumbPrior.get(count2B) < 0) {
			if(Number1.myNumbPrior.get(count1B) < Number2.myNumbPrior.get(count2B)*-1) {
				for(int i = Number2.myNumbAfter.size()- 1; i >= 0; i--) {
					if(Number1.myNumbAfter.get(i) > Number2.myNumbAfter.get(i) && i != 0) {
						Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)+10);
						Number2.myNumbAfter.set(i-1, Number2.myNumbAfter.get(i-1)-1);
					}
					else if(Number1.myNumbAfter.get(i) > Number2.myNumbAfter.get(i) && i == 0) {
						Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)+10);
						Number2.myNumbPrior.set(Number2.myNumbPrior.size()-1, Number2.myNumbPrior.get(Number2.myNumbPrior.size()-1)-1);
					}
					Number2.myNumbPrior.set(i, Number2.myNumbAfter.get(i)-Number1.myNumbAfter.get(i));
				}
				for(int i = Number2.myNumbPrior.size()- 1; i >= 0; i--) {
					if(Number1.myNumbPrior.get(i) > Number2.myNumbPrior.get(i) && i != 0) {
						Number2.myNumbPrior.set(i, Number2.myNumbPrior.get(i)+10);
						Number2.myNumbPrior.set(i-1, Number2.myNumbPrior.get(i-1)-1);
					}
					Number2.myNumbPrior.set(i, Number2.myNumbPrior.get(i)-Number1.myNumbPrior.get(i));
				}
				Number2.myNumbPrior.set(0, Number2.myNumbPrior.get(0)*-1);
				}
		}
		else if(Number1.myNumbPrior.get(count1B) >= 0 && Number2.myNumbPrior.get(count2B) < 0) {
			if(Number1.myNumbPrior.get(count1B) > Number2.myNumbPrior.get(count2B)*-1) {
				for(int i = Number2.myNumbAfter.size()-1; i >= 0; i--) {
					if(Number2.myNumbAfter.get(i) > Number1.myNumbAfter.get(i) && i != 0) {
						Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)+10);
						Number1.myNumbAfter.set(i-1, Number1.myNumbAfter.get(i-1)-1);
					}
					else if(Number2.myNumbAfter.get(i) > Number1.myNumbAfter.get(i) && i == 0) {
						Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)+10);
						Number1.myNumbPrior.set(Number1.myNumbPrior.size()-1, Number1.myNumbPrior.get(Number1.myNumbPrior.size()-1)-1);
					}
					Number2.myNumbAfter.set(i, Number1.myNumbAfter.get(i)-Number2.myNumbAfter.get(i));
				}
				for(int i = Number2.myNumbPrior.size()-1; i >= 0; i--) {
					if(Number2.myNumbPrior.get(i) > Number1.myNumbPrior.get(i) && i != 0) {
						Number1.myNumbPrior.set(i, Number1.myNumbPrior.get(i)+10);
						Number1.myNumbPrior.set(i-1, Number1.myNumbPrior.get(i-1)-1);
					}
					Number2.myNumbPrior.set(i, Number1.myNumbPrior.get(i)-Number2.myNumbPrior.get(i));
				}
			}
		}
		else if(Number1.myNumbPrior.get(count1B) <= 0 && Number2.myNumbPrior.get(count2B) >= 0) {
			if(Number1.myNumbPrior.get(count1B)*-1 < Number2.myNumbPrior.get(count2B)) {
				for(int i = Number2.myNumbAfter.size()-1; i >= 0; i--) {
					if(Number1.myNumbAfter.get(i) > Number2.myNumbAfter.get(i) && i != 0) {
						Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)+10);
						Number2.myNumbAfter.set(i-1, Number2.myNumbAfter.get(i-1)-1);
					}
					else if(Number1.myNumbAfter.get(i) > Number2.myNumbAfter.get(i) && i == 0) {
						Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)+10);
						Number2.myNumbPrior.set(Number2.myNumbPrior.size()-1, Number2.myNumbPrior.get(Number2.myNumbPrior.size()-1)-1);
					}
					Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)-Number1.myNumbAfter.get(i));
				}
				for(int i = Number2.myNumbPrior.size()-1; i >= 0; i--) {
					if(Number1.myNumbPrior.get(i) > Number2.myNumbPrior.get(i)) {
						Number2.myNumbPrior.set(i, Number2.myNumbPrior.get(i)+10);
						Number2.myNumbPrior.set(i-1, Number2.myNumbPrior.get(i-1)-1);
					}
					Number2.myNumbPrior.set(i, Number2.myNumbPrior.get(i)-Number1.myNumbPrior.get(i));
				}
			}
		}
		else {
			for(int i = Number2.myNumbAfter.size()-1; i >= 0; i--) {
				Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)+Number1.myNumbAfter.get(i));
				if(Number2.myNumbAfter.get(i) >= 10 && i != 0) {
					Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)%10);
					Number2.myNumbAfter.set(i-1, Number2.myNumbAfter.get(i)+1);
				}
				else if(Number2.myNumbAfter.get(i) >= 10 && i == 0) {
					Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)%10);
					Number2.myNumbPrior.set(Number2.myNumbPrior.size()-1, Number2.myNumbPrior.get(Number2.myNumbPrior.size()-1)+1);
				}
			}
			for(int i = Number2.myNumbPrior.size()-1; i >= 0; i--) {
				Number2.myNumbPrior.set(i, Number2.myNumbAfter.get(i)+Number1.myNumbAfter.get(i));
				if(Number2.myNumbAfter.get(i) >= 10 && i != 1) {
					Number2.myNumbPrior.set(i, Number2.myNumbAfter.get(i)%10);
					Number2.myNumbPrior.set(i-1, Number2.myNumbAfter.get(i)+1);
				}
				else if(Number2.myNumbAfter.get(i) >= 10 && i == 1) {
					if(Number2.myNumbPrior.get(0) < 0) {
						Number2.myNumbPrior.set(i, Number2.myNumbAfter.get(i)%10);
						Number2.myNumbPrior.set(i-1, Number2.myNumbAfter.get(i)-1);
						negative = true;
					}
					else {
						Number2.myNumbPrior.set(i, Number2.myNumbAfter.get(i)%10);
						Number2.myNumbPrior.set(i-1, Number2.myNumbAfter.get(i)+1);
					}
				}
					else if(Number2.myNumbAfter.get(i) >= 10 && i == 0) {
					Number2.myNumbPrior.set(i, Number2.myNumbAfter.get(i)%10);
					if(negative == true) {
						Number2.myNumbPrior.add(0, -1);
					}
					else {
						Number2.myNumbPrior.add(0, 1);
					}
					}
				}
			}
		return Number2;
		}
	/**
	 * Changes the given precise number to a positive if it is negative otherwise it leaves it the same
	 * @param numb - given precise number
	 * @return absolute value of the precise number
	 */
		public static AmusingPreciseNumber abs(AmusingPreciseNumber numb)  {
			AmusingPreciseNumber temp = new AmusingPreciseNumber(numb);
			if(temp.myNumbPrior.get(0) > 0) {
				return temp;
			}
			else {
				temp.myNumbPrior.set(0, temp.myNumbPrior.get(0)*-1); 
				return temp;
			}
		}
		public static AmusingPreciseNumber negate(AmusingPreciseNumber numb) {
			AmusingPreciseNumber temp = new AmusingPreciseNumber(numb);
			if(temp.myNumbPrior.get(0) < 0) {
				return temp;
			}
			else {
				temp.myNumbPrior.set(0, temp.myNumbPrior.get(0)*-1);
				return temp;
			}
		}
		/**
		 * changes the number to a positive if it is negative 
		 */
		public void abs() { 
			if(myNumbPrior.get(0) < 0) {
				myNumbPrior.set(0, myNumbPrior.get(0)*-1);
			}
		}
		/**
		 * Changes the number to a negative 
		 */
		public void negate() {
			if(myNumbPrior.get(0) > 0) {
				myNumbPrior.set(0,  myNumbPrior.get(0)*-1);
			}
		}
		/**
		 * Uses four test cases. When one number is negative and is subtracting a positive number, this will result in a bigger negative number so 
		 * both numbers are changed to positive added and then made negative after the addition. A positive subtracting negative which will result in a positive number 
		 * so the negative number is made positive and then it is added. A positive subtracting a positive which will give a smaller number and then a negative subtraction a negative which results in a negative 
		 * number. I used the respective operation watching for carry out numbers in order to make sure that the out put for the opperation was right.
		 * Number2 - number used for the subtraction 
		 * Number1 - Number used for the subtraction
		 * @param numb The precise number to use during the subtraction
		 */
		public static AmusingPreciseNumber subtract(AmusingPreciseNumber numb1, AmusingPreciseNumber numb2) {
			AmusingPreciseNumber Number1 = new AmusingPreciseNumber(numb1);
			AmusingPreciseNumber Number2 = new AmusingPreciseNumber(numb2);
			int counter= 0;
			int counter2 = 0;
			if(Number1.myNumbPrior.size() > Number2.myNumbPrior.size()) {
				while(Number2.myNumbPrior.size() != Number1.myNumbPrior.size()) {
					Number2.myNumbPrior.add(0, 0);
					counter2++;
				}
			}
			if(Number1.myNumbPrior.size() < Number2.myNumbPrior.size()) {
				while(Number1.myNumbPrior.size() != Number2.myNumbPrior.size()) {
					Number1.myNumbPrior.add(0,0);
					counter++;
				}
			}
			if(Number1.myNumbAfter.size() > Number2.myNumbAfter.size()) {
				while(Number1.myNumbAfter.size() != Number2.myNumbAfter.size()) {
					Number2.myNumbAfter.add(0,0);
					counter2++;
				}
			}
			if(Number1.myNumbAfter.size() < Number2.myNumbAfter.size()) {
				while(Number2.myNumbAfter.size() != Number1.myNumbAfter.size()) {
					Number1.myNumbAfter.add(0,0);
					counter++;
				}
			}
			//where the subtraction starts
			if(Number1.myNumbPrior.get(counter) < 0 && Number2.myNumbPrior.get(counter2) > 0) {
				Number1.myNumbPrior.set(counter, Number1.myNumbPrior.get(counter)*-1);
				for(int i = Number1.myNumbAfter.size()-1; i >= 0; i--) {
					Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)+Number2.myNumbAfter.get(i));
					if(Number1.myNumbAfter.get(i) >= 10 && i > 0) {
						Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)%10);
						Number1.myNumbAfter.set(i-1, Number1.myNumbAfter.get(i)+1);
					}
					else if(Number1.myNumbAfter.get(i) >= 10 && i == 0) {
						Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)%10);
						Number1.myNumbPrior.set(Number1.myNumbPrior.get(Number1.myNumbPrior.size()-1), Number1.myNumbPrior.get(Number1.myNumbPrior.size()-1)+1);
					}
				}
					for(int i = Number1.myNumbPrior.size()-1; i >= 0; i--) {
						Number1.myNumbPrior.set(i, Number1.myNumbPrior.get(i)+Number2.myNumbPrior.get(i));
						if(Number1.myNumbPrior.get(i) >= 10 && i > 0) {
							Number1.myNumbPrior.set(i, Number1.myNumbPrior.get(i)%10);
							Number1.myNumbPrior.set(i-1, Number1.myNumbPrior.get(i)+1);
						}
						else if(Number1.myNumbPrior.get(i) >= 10 && i == 0) {
							Number1.myNumbPrior.set(i, Number1.myNumbPrior.get(i)%10);
							Number1.myNumbPrior.add(0,1);
						}
					}
					Number1.myNumbPrior.set(0, Number1.myNumbPrior.get(0)*-1);
				}
	
			else if(Number1.myNumbPrior.get(counter) > 0 && Number2.myNumbPrior.get(counter2) < 0) {
				Number2.myNumbPrior.set(counter2, Number2.myNumbPrior.get(counter2)*-1);
				for(int i = Number1.myNumbAfter.size()-1; i >= 0; i--) {
					Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)+Number2.myNumbAfter.get(i));
					if(Number1.myNumbAfter.get(i) >= 10 && i > 0) {
						Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)%10);
						Number1.myNumbAfter.set(i-1, Number1.myNumbAfter.get(i)+1);
					}
					else if(Number1.myNumbAfter.get(i) >= 10 && i == 0) {
						Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)%10);
						Number1.myNumbPrior.set(Number1.myNumbPrior.get(Number1.myNumbPrior.size()-1), Number1.myNumbPrior.get(Number1.myNumbPrior.size()-1)+1);
					}
				}
				for(int i = Number1.myNumbPrior.size()-1; i >= 0; i--) {
					Number1.myNumbPrior.set(i, Number1.myNumbPrior.get(i)+Number2.myNumbPrior.get(i));
					if(Number1.myNumbPrior.get(i) >= 10 && i > 0) {
						Number1.myNumbPrior.set(i, Number1.myNumbPrior.get(i)%10);
						Number1.myNumbPrior.set(i-1, Number1.myNumbPrior.get(i)+1);
					}
					else if(Number1.myNumbPrior.get(i) >= 10 && i == 0 ) {
						Number1.myNumbPrior.set(i, Number1.myNumbPrior.get(i)%10);
						Number1.myNumbPrior.add(0,1);
					}
				}
			}
			else if(Number1.myNumbPrior.get(counter) > 0 && Number2.myNumbPrior.get(counter2) > 0) {
				if(Number1.myNumbPrior.get(counter) > Number2.myNumbPrior.get(counter2)) {
					for(int i = Number1.myNumbAfter.size()-1; i >= 0; i--) {
						if(Number2.myNumbAfter.get(i) > Number1.myNumbAfter.get(i) && i > 0) {
							Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)+10);
							Number1.myNumbAfter.set(i-1, Number1.myNumbAfter.get(i-1)-1);
						}
						else if(Number2.myNumbAfter.get(i) > Number1.myNumbAfter.get(i) && i == 0) {
							Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)+10);
							Number1.myNumbPrior.set(Number1.myNumbPrior.size()-1, Number1.myNumbPrior.get(Number1.myNumbPrior.size()-1)-1);
						}
						Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)-Number2.myNumbAfter.get(i));
					}
					for(int i = Number1.myNumbPrior.size()-1; i >= 0; i--) {
						if(Number2.myNumbPrior.get(i) > Number1.myNumbPrior.get(i)) {
							Number1.myNumbPrior.set(i, Number1.myNumbPrior.get(i)+10);
							Number1.myNumbPrior.set(i-1, Number1.myNumbPrior.get(i-1)-1);
						}
						Number1.myNumbPrior.set(i, Number1.myNumbPrior.get(i) - Number2.myNumbPrior.get(i));
					}
				}
				else { 
					for(int i = Number1.myNumbAfter.size()-1; i >= 0; i--) {
						if(Number2.myNumbAfter.get(i) < Number1.myNumbAfter.get(i) && i > 0) {
							Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)+10);
							Number2.myNumbAfter.set(i-1, Number2.myNumbAfter.get(i-1)-1);
						}
						else if(Number2.myNumbAfter.get(i) < Number1.myNumbAfter.get(i) && i == 0) {
							Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)+10);
							Number2.myNumbPrior.set(Number2.myNumbPrior.size()-1, Number2.myNumbPrior.get(Number2.myNumbPrior.size()-1)-1);
						}
						Number1.myNumbAfter.set(i, Number2.myNumbAfter.get(i)-Number1.myNumbAfter.get(i));
					}
					for(int i = Number1.myNumbPrior.size()-1; i >= 0; i--) {
						if(Number2.myNumbPrior.get(i) > Number1.myNumbPrior.get(i)) {
							Number2.myNumbPrior.set(i, Number2.myNumbPrior.get(i)+10);
							Number2.myNumbPrior.set(i-1, Number2.myNumbPrior.get(i-1)-1);
						}
						Number1.myNumbPrior.set(i, Number2.myNumbPrior.get(i) - Number1.myNumbPrior.get(i));
					}
					}
				}
			else if(Number1.myNumbPrior.get(counter) < 0 && Number2.myNumbPrior.get(counter2) < 0) {
				Number1.myNumbPrior.set(counter, Number1.myNumbPrior.get(0)*-1);
				Number2.myNumbPrior.set(counter2, Number2.myNumbPrior.get(0)*-1);
				if(Number1.myNumbPrior.get(0) > Number2.myNumbPrior.get(0)) {
					for(int i = Number1.myNumbAfter.size()-1; i >= 0; i--) {
						if(Number2.myNumbAfter.get(i) > Number1.myNumbAfter.get(i) && i > 0) {
							Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)+10);
							Number1.myNumbAfter.set(i-1, Number1.myNumbAfter.get(i-1)-1);
						}
						else if(Number1.myNumbAfter.get(i) > Number1.myNumbAfter.get(i) && i == 0) {
							Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)+10);
							Number1.myNumbPrior.set(Number1.myNumbPrior.size()-1, Number1.myNumbPrior.get(Number1.myNumbPrior.size()-1)-1);
						}
						Number1.myNumbAfter.set(i, Number1.myNumbAfter.get(i)-Number2.myNumbAfter.get(i));	
				}
					for(int i = Number1.myNumbPrior.size()-1; i >= 0; i--) {
						if(Number2.myNumbPrior.get(i) > Number1.myNumbPrior.get(i)) {
							Number1.myNumbPrior.set(i, Number1.myNumbPrior.get(i)+10);
							Number1.myNumbPrior.set(i-1, Number1.myNumbPrior.get(i-1)-1);
						}
						Number1.myNumbPrior.set(i, Number1.myNumbPrior.get(i) - Number2.myNumbPrior.get(i));
					}
					Number1.myNumbPrior.set(0, Number1.myNumbPrior.get(0)*-1);
				}
				else {
					for(int i = Number1.myNumbAfter.size()-1; i >= 0; i--) {
						if(Number2.myNumbAfter.get(i) < Number1.myNumbAfter.get(i) && i > 0) {
							Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)+10);
							Number2.myNumbAfter.set(i-1, Number2.myNumbAfter.get(i-1)-1);
						}
						else if(Number2.myNumbAfter.get(i) < Number1.myNumbAfter.get(i) && i == 0) {
							Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)+10);
							Number2.myNumbPrior.set(Number2.myNumbPrior.size()-1, Number2.myNumbPrior.get(Number2.myNumbPrior.size()-1)-1);
						}
						Number1.myNumbAfter.set(i, Number2.myNumbAfter.get(i)-Number1.myNumbAfter.get(i));
					}
					for(int i = Number1.myNumbPrior.size()-1; i >= 0; i--) {
						if(Number2.myNumbPrior.get(i) > Number1.myNumbPrior.get(i)) {
							Number2.myNumbPrior.set(i, Number2.myNumbPrior.get(i)+10);
							Number2.myNumbPrior.set(i-1, Number2.myNumbPrior.get(i-1)-1);
						}
						Number1.myNumbPrior.set(i, Number2.myNumbPrior.get(i) - Number1.myNumbPrior.get(i));
					}
				}
			}
			return Number1;
		}
		/**
		 * Uses four test cases. When one number is negative and is subtracting a postiive number, this will result in a bigger negative number so 
		 * both numbers are changed to positive added and then made negative after the addition. A positive subtracting negative which will result in a positive number 
		 * so the negative number is made positive and then it is added. A positive subtracting a positive which will give a smaller number and then a negative subtraction a negative which results in a negative 
		 * number. I used the respective operation watching for carry out numbers in order to make sure that the out put for the opperation was right.
		 * Number2 - number used for the subtraction against our PreciseNumber
		 * @param numb The precise number to use during the subtraction
		 */
		public void subtract( AmusingPreciseNumber numb) {
			AmusingPreciseNumber Number2 = new AmusingPreciseNumber(numb);
			int counter = 0;
			int myCounter = 0;
			if(myNumbPrior.size() > Number2.myNumbPrior.size()) {
				while(Number2.myNumbPrior.size() != myNumbPrior.size()) {
					Number2.myNumbPrior.add(0, 0);
					counter++;
				}
			}
			if(myNumbPrior.size() < Number2.myNumbPrior.size()) {
				while(myNumbPrior.size() != Number2.myNumbPrior.size()) {
					myNumbPrior.add(0,0);
					myCounter++;
				}
			}
			if(myNumbAfter.size() > Number2.myNumbAfter.size()) {
				while(myNumbAfter.size() != Number2.myNumbAfter.size()) {
					Number2.myNumbAfter.add(0,0);
					counter++;
				}
			}
			if(myNumbAfter.size() < Number2.myNumbAfter.size()) {
				while(Number2.myNumbAfter.size() != myNumbAfter.size()) {
					myNumbAfter.add(0,0);
					myCounter++;
				}
			}
			//where the subtraction starts
			if(myNumbPrior.get(myCounter) < 0 && Number2.myNumbPrior.get(counter) > 0) {
				myNumbPrior.set(myCounter, myNumbPrior.get(myCounter)*-1);
				for(int i = myNumbAfter.size()-1; i >= 0; i--) {
					myNumbAfter.set(i, myNumbAfter.get(i)+Number2.myNumbAfter.get(i));
					if(myNumbAfter.get(i) >= 10 && i > 0) {
						myNumbAfter.set(i, myNumbAfter.get(i)%10);
						myNumbAfter.set(i-1, myNumbAfter.get(i)+1);
					}
					else if(myNumbAfter.get(i) >= 10 && i == 0) {
						myNumbAfter.set(i, myNumbAfter.get(i)%10);
						myNumbPrior.set(myNumbPrior.get(myNumbPrior.size()-1), myNumbPrior.get(myNumbPrior.size()-1)+1);
					}
				}
					for(int i = myNumbPrior.size()-1; i >= 0; i--) {
						myNumbPrior.set(i, myNumbPrior.get(i)+Number2.myNumbPrior.get(i));
						if(myNumbPrior.get(i) >= 10 && i > 0) {
							myNumbPrior.set(i, myNumbPrior.get(i)%10);
							myNumbPrior.set(i-1, myNumbPrior.get(i)+1);
						}
						else if(myNumbPrior.get(i) >= 10 && i == 0) {
							myNumbPrior.set(i, myNumbPrior.get(i)%10);
							myNumbPrior.add(0,1);
						}
					}
					myNumbPrior.set(0, myNumbPrior.get(0)*-1);
				}

			else if(myNumbPrior.get(myCounter) > 0 && Number2.myNumbPrior.get(counter) < 0) {
				Number2.myNumbPrior.set(myCounter, Number2.myNumbPrior.get(counter)*-1);
				for(int i = myNumbAfter.size()-1; i >= 0; i--) {
					myNumbAfter.set(i, myNumbAfter.get(i)+Number2.myNumbAfter.get(i));
					if(myNumbAfter.get(i) >= 10 && i > 0) {
						myNumbAfter.set(i, myNumbAfter.get(i)%10);
						myNumbAfter.set(i-1, myNumbAfter.get(i)+1);
					}
					else if(myNumbAfter.get(i) >= 10 && i == 0) {
						myNumbAfter.set(i, myNumbAfter.get(i)%10);
						myNumbPrior.set(myNumbPrior.get(myNumbPrior.size()-1), myNumbPrior.get(myNumbPrior.size()-1)+1);
					}
				}
				for(int i = myNumbPrior.size()-1; i >= 0; i--) {
					myNumbPrior.set(i, myNumbPrior.get(i)+Number2.myNumbPrior.get(i));
					if(myNumbPrior.get(i) >= 10 && i > 0) {
						myNumbPrior.set(i, myNumbPrior.get(i)%10);
						myNumbPrior.set(i-1, myNumbPrior.get(i)+1);
					}
					else if(myNumbPrior.get(i) >= 10 && i == 0 ) {
						myNumbPrior.set(i, myNumbPrior.get(i)%10);
						myNumbPrior.add(0,1);
					}
				}
			}
			else if(myNumbPrior.get(myCounter) > 0 && Number2.myNumbPrior.get(counter) > 0) {
				if(myNumbPrior.get(myCounter) > Number2.myNumbPrior.get(counter)) {
					for(int i = myNumbAfter.size()-1; i >= 0; i--) {
						if(Number2.myNumbAfter.get(i) > myNumbAfter.get(i) && i > 0) {
							myNumbAfter.set(i, myNumbAfter.get(i)+10);
							myNumbAfter.set(i-1, myNumbAfter.get(i-1)-1);
						}
						else if(Number2.myNumbAfter.get(i) > myNumbAfter.get(i) && i == 0) {
							myNumbAfter.set(i, myNumbAfter.get(i)+10);
							myNumbPrior.set(myNumbPrior.size()-1, myNumbPrior.get(myNumbPrior.size()-1)-1);
						}
						myNumbAfter.set(i, myNumbAfter.get(i)-Number2.myNumbAfter.get(i));
					}
					for(int i = myNumbPrior.size()-1; i >= 0; i--) {
						if(Number2.myNumbPrior.get(i) > myNumbPrior.get(i)) {
							myNumbPrior.set(i, myNumbPrior.get(i)+10);
							myNumbPrior.set(i-1, myNumbPrior.get(i-1)-1);
						}
						myNumbPrior.set(i, myNumbPrior.get(i) - Number2.myNumbPrior.get(i));
					}
				}
				else { 
					for(int i = myNumbAfter.size()-1; i >= 0; i--) {
						if(Number2.myNumbAfter.get(i) < myNumbAfter.get(i) && i > 0) {
							Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)+10);
							Number2.myNumbAfter.set(i-1, Number2.myNumbAfter.get(i-1)-1);
						}
						else if(Number2.myNumbAfter.get(i) < myNumbAfter.get(i) && i == 0) {
							Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)+10);
							Number2.myNumbPrior.set(Number2.myNumbPrior.size()-1, Number2.myNumbPrior.get(Number2.myNumbPrior.size()-1)-1);
						}
						myNumbAfter.set(i, Number2.myNumbAfter.get(i)-myNumbAfter.get(i));
					}
					for(int i = myNumbPrior.size()-1; i >= 0; i--) {
						if(Number2.myNumbPrior.get(i) > myNumbPrior.get(i)) {
							Number2.myNumbPrior.set(i, Number2.myNumbPrior.get(i)+10);
							Number2.myNumbPrior.set(i-1, Number2.myNumbPrior.get(i-1)-1);
						}
						myNumbPrior.set(i, Number2.myNumbPrior.get(i) - myNumbPrior.get(i));
					}
					}
				}
			else if(myNumbPrior.get(myCounter) < 0 && Number2.myNumbPrior.get(counter) < 0) {
				myNumbPrior.set(myCounter, myNumbPrior.get(myCounter)*-1);
				Number2.myNumbPrior.set(0, Number2.myNumbPrior.get(0)*-1);
				if(myNumbPrior.get(myCounter) > Number2.myNumbPrior.get(counter)) {
					for(int i = myNumbAfter.size()-1; i >= 0; i--) {
						if(Number2.myNumbAfter.get(i) > myNumbAfter.get(i) && i > 0) {
							myNumbAfter.set(i, myNumbAfter.get(i)+10);
							myNumbAfter.set(i-1, myNumbAfter.get(i-1)-1);
						}
						else if(myNumbAfter.get(i) > myNumbAfter.get(i) && i == 0) {
							myNumbAfter.set(i, myNumbAfter.get(i)+10);
							myNumbPrior.set(myNumbPrior.size()-1, myNumbPrior.get(myNumbPrior.size()-1)-1);
						}
						myNumbAfter.set(i, myNumbAfter.get(i)-Number2.myNumbAfter.get(i));	
				}
					for(int i = myNumbPrior.size()-1; i >= 0; i--) {
						if(Number2.myNumbPrior.get(i) > myNumbPrior.get(i)) {
							myNumbPrior.set(i, myNumbPrior.get(i)+10);
							myNumbPrior.set(i-1, myNumbPrior.get(i-1)-1);
						}
						myNumbPrior.set(i, myNumbPrior.get(i) - Number2.myNumbPrior.get(i));
					}
					myNumbPrior.set(0, myNumbPrior.get(0)*-1);
				}
				else {
					for(int i = myNumbAfter.size()-1; i >= 0; i--) {
						if(Number2.myNumbAfter.get(i) < myNumbAfter.get(i) && i > 0) {
							Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)+10);
							Number2.myNumbAfter.set(i-1, Number2.myNumbAfter.get(i-1)-1);
						}
						else if(Number2.myNumbAfter.get(i) < myNumbAfter.get(i) && i == 0) {
							Number2.myNumbAfter.set(i, Number2.myNumbAfter.get(i)+10);
							Number2.myNumbPrior.set(Number2.myNumbPrior.size()-1, Number2.myNumbPrior.get(Number2.myNumbPrior.size()-1)-1);
						}
						myNumbAfter.set(i, Number2.myNumbAfter.get(i)-myNumbAfter.get(i));
					}
					for(int i = myNumbPrior.size()-1; i >= 0; i--) {
						if(Number2.myNumbPrior.get(i) > myNumbPrior.get(i)) {
							Number2.myNumbPrior.set(i, Number2.myNumbPrior.get(i)+10);
							Number2.myNumbPrior.set(i-1, Number2.myNumbPrior.get(i-1)-1);
						}
						myNumbPrior.set(i, Number2.myNumbPrior.get(i) - myNumbPrior.get(i));
					}
				}
			}
		}
	}

	
