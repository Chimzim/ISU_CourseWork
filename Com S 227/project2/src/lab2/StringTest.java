/**
 * 
 */
package lab2;

/**
 * @author chimz
 *
 */
public class StringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String message;
		message = "Hello, World!";
		System.out.println(message);
		
		int theLength = message.length();
		System.out.println(theLength); 
		
		char theChar = message.charAt(0);
		System.out.println(theChar);

		theChar = message.charAt(1);
		System.out.println(theChar);
		//for checkpoint 1
		System.out.println(message.toUpperCase());
		System.out.println(message.substring(0, 5));
		message = message.replace('o', '*');
		System.out.println(message);
	}

}
