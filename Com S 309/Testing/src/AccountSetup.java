import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public abstract class AccountSetup {
	private String password, username, securityAnswer, securityQuestion;
	private SecretKey userSecretKey;
	private String SecurityQuestionAwnser;
	Scanner in = new Scanner(System.in);
	private ArrayList<String> a = new ArrayList<String>();
	private Cipher c;
	
	public boolean passwordStrength(String testing) {
		boolean containsDigit = false, containsUpper = false;
		for(int i = 0; i < testing.length()-2; i++) {
			String currentChar = testing.substring(i, i+1);
			if(testing.length() < 8) {
				return false;
			}
			try {
				if(Integer.parseInt(currentChar) == i) {
					containsDigit = true;
				}
			}catch (NumberFormatException e) {
				if(currentChar == currentChar.toUpperCase()) {
					containsUpper = true;
				}
			}
			
		}
		return (containsDigit && containsUpper) == true;
	}
	
	public void changePassword(String response) {
		if(response == securityAnswer) {
			System.out.println("Please enter a new Password meeting the following criteria:\n");
		}
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setSecurityQuestion() {
		System.out.println("Please enter the number corresponding to the question,\n"
				+ "1.) What is the street you grew up on?\n2.) What is the name of your childhood bestfriend?\n"
				+ "3.) What is your favorite sport?\n 4.) What is your favorite color?\n5.) What is your favorite animial?");
		int num = in.nextInt();
		switch (num) {
		case 1:
			securityQuestion = "What is the street you grew up on";
			System.out.println("Please enter your answer.");
			securityAnswer = in.next();
			break;
		case 2:
			securityQuestion = "What is the name of your childhood bestfriend";
			System.out.println("Please enter your answer.");
			securityAnswer = in.next();
			break;
		case 3:
			securityQuestion = "What is your favorite sport";
			System.out.println("Please enter your answer.");
			securityAnswer = in.next();
			break;
		case 4:
			securityQuestion = "What is your favorite color";
			System.out.println("Please enter your answer.");
			securityAnswer = in.next();
			break;
		case 5: 
			securityQuestion = "What is your favorite animial";
			System.out.println("Please enter your answer.");
			securityAnswer = in.next();
			break;
		}
	}
	
}
