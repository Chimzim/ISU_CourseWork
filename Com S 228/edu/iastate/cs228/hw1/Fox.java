package edu.iastate.cs228.hw1;

/**
 *  
 * @author
 *
 */

/**
 * A fox eats rabbits and competes against a badger. 
 */
public class Fox extends Animal 
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Fox (Plain p, int r, int c, int a) 
	{
		super(p,r,c,a); 
	}
		
	/**
	 * A fox occupies the square. 	 
	 */
	public State who()
	{
		// TODO 
		return State.FOX; 
	}
	
	/**
	 * A fox dies of old age or hunger, or from attack by numerically superior badgers. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		int popArray[] = new int[NUM_LIFE_FORMS];
		census(popArray);
		if(age >= 6) {
			return new Empty(pNew, row, column);
		}
		else if(popArray[BADGER] > popArray[FOX]) {
			return new Badger(pNew, row, column, 0);
		}
		else if(popArray[RABBIT] < popArray[BADGER]+popArray[FOX]) {
			return new Empty(pNew, row, column);
		}
		else {
			return new Fox(pNew, row, column, age+1);
		}
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a fox. 	
	}
}
