package edu.iastate.cs228.hw1;

/**
 *  
 * @author
 *
 */

/**
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{

	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (Plain p, int r, int c, int a) 
	{
		super(p,r,c,a);
		// TODO 
	}
	
	/**
	 * A badger occupies the square. 	 
	 */
	public State who()
	{
		// TODO 
		return State.BADGER; 
	}
	
	/**
	 * A badger dies of old age or hunger, or from isolation and attack by a group of foxes. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		int pop[] = new int[NUM_LIFE_FORMS];
		census(pop);
		
		if(age >= 4 || pop[BADGER]+pop[FOX] > pop[RABBIT]) {
			return new Empty(pNew, row, column);
		}
		else if(pop[FOX] > 1 && pop[BADGER] <= 1) {
			return new Fox(pNew, row, column, 0);
		}
		else {
			return new Badger(pNew, row, column, age+1);
		}
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a badger. 
	}
}
