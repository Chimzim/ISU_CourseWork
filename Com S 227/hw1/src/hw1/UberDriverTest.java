package hw1;

public class UberDriverTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UberDriver a = new UberDriver(1.0, 0.2);
		a.waitAround(13);
		System.out.println(a.getTotalCredits());
		a.pickUp();
		a.drive(5, 10);
		System.out.println(a.getTotalCredits());
		a.pickUp();
		a.drive(5, 10);
		System.out.println(a.getTotalCredits());
		System.out.println(a.getProfit());
		System.out.println(a.getAverageProfitPerHour());
		a.waitAround(3);
		System.out.println(a.getTotalCredits());
	}

}
