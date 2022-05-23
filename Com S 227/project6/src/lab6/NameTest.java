package lab6;

import java.util.Scanner;
public class NameTest {



	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String blah = in.nextLine();
		Names g = new Names();
		System.out.println(g.test(blah));
		System.out.println(g.vowels(blah));
				
	}

}
