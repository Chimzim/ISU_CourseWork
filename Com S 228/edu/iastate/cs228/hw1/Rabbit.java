package edu.iastate.cs228.hw1;

/**
 *  
 * @author
 *
 */

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal 
{	
	/**
	 * Creates a Rabbit object.
	 * @param p: plain  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Rabbit (Plain p, int r, int c, int a) 
	{
		super(p,r,c,a);
		// TODO 
	}
		
	// Rabbit occupies the square.
	public State who()
	{
		// TODO  
		return State.RABBIT; 
	}
	
	/**
	 * A rabbit dies of old age or hunger. It may also be eaten by a badger or a fox.  
	 * @param pNew     plain of the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(Plain pNew)
	{
		int pop[] = new int[NUM_LIFE_FORMS];
		census(pop);
		
		if(age >= 3 || pop[GRASS] == 0) {
			return new Empty(pNew, row, column);
		}
		else if(pop[BADGER] + pop[FOX] >= pop[RABBIT] || (pop[BADGER] + pop[FOX] >= pop[RABBIT] && pop[FOX] > pop[BADGER])) {
			return new Fox(pNew, row, column, 0);
		}
		else if(pop[BADGER] > pop[RABBIT]) {
			return new Badger(pNew, row, column, 0);
		}
		else {
			return new Rabbit(pNew, row, column, age+1);
		}
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a rabbit. 
		
	}
}
