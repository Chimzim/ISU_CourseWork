package lab2;

public class AtomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Atom a;
		a = new Atom(92, 146, 92);
		System.out.println(a.getAtomicCharge());
		System.out.println(a.getAtomicMass());
		a.decay();
		System.out.println(a.getAtomicCharge());
		System.out.println(a.getAtomicMass());
	}

}
