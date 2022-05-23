/**
 * 
 */
package lab2;

/**
 * @author chimz
 *
 */
public class BasketballTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Basketball b;
		b = new Basketball(4.0);
		
		System.out.println(b.getDiameter());
		System.out.println(b.isDribbleable());
		
		Basketball b2 = new Basketball(6.0);

	}

}
