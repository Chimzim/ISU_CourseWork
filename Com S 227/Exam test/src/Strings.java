import java.util.Scanner;
public class Strings {

	public static String LongestWord(String a) {
		Scanner in = new Scanner(a);
		String longest = in.next();
		while(in.hasNext()) {
			String check = in.next();
			if(longest.length() < check.length()) {
				longest = check;
			}
		}
		return longest;
		
	}
	
	public static String NewWord(String a) {
		String newWord = "";
		for(int i = 0; i < a.length(); i++) {
			if(!Character.isAlphabetic(a.charAt(i))) {
				newWord += "#";
			}
			else {
				newWord += a.charAt(i);
			}
		}
		return newWord;
	}
	
	public static int DoubleTime(double rate, double bal) {
		int time = 0;
		double addon = 0;
		double end = bal * 2;
		double actrate = rate / 100;
		double balance = bal;
		while(end >= balance) {
			addon = balance * actrate;
			balance += addon;
			time += 1;
		}
		return time * 12;
	}
	
	public static int vowelIndex(String s) {
		int idx = -1;
		for(int i = 0; i < s.length(); i++) {
			String check = s.toLowerCase();
			if(check.charAt(i) == 'a' || check.charAt(i) == 'e' || check.charAt(i) == 'i' || check.charAt(i) == 'o'|| check.charAt(i) == 'u') {
				idx = i;
				break;
			}
		}
		return idx;
	}
	
	public static void ReverseArray(int [] a) {
		for(int i = 0, j = a.length -1; i < a.length; i++, j--) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
			if(i >= j) {
				break;
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(LongestWord("The longest word happens to be IowaStateUniversity"));
		System.out.println(NewWord("Iowa State is a bunch of bullshit!"));
		System.out.println(DoubleTime(5, 1000));
		System.out.println(vowelIndex("Fruit"));
		int [] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		ReverseArray(a);
		for(int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		
	}

}
