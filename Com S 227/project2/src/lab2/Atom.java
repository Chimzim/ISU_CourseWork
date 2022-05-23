package lab2;

/**
 * The atom class represent an atom
 * @author chimz
 *
 */
public class Atom {
	/**
	 * Protons, neutrons, and electrons are the variables that correspond with an actual atom 
	 */
	private int protons;
	private int neutrons;
	private int electrons;
	
	
	/**
	 * This is the constructor for the atom class
	 * @param givenProtons - This is the user given protons
	 * @param givenNeutrons - This is the user given neutrons
	 * @param givenElectrons - This is the user given electrons
	 */
	public Atom(int givenProtons, int givenNeutrons, int givenElectrons) {
		protons = givenProtons;
		neutrons = givenNeutrons;
		electrons = givenElectrons;
		// insert code to assign given values to instance variables
	}
	/**
	 * Provides the atomic mass for the atom by adding the protons and neutrons together
	 * @return The sum of the protons and neutrons 
	 */
	public int getAtomicMass() {
		return protons + neutrons;
	}
	/**
	 * Provides the atomic charge by subtracting the protons from the electrons
	 * @return The difference of protons and electrons
	 */
	public int getAtomicCharge() {
		return protons - electrons;
	}
	/**
	 * Decrements the protons and neutrons by two
	 */
	public void decay() {
		protons -= 2;
		neutrons -= 2;
	}
}
