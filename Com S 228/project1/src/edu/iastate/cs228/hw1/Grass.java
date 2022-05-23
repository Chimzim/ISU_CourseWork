package edu.iastate.cs228.hw1;

/**
 *  
 * @author
 *
 */

/**
 * Grass remains if more than rabbits in the neighborhood; otherwise, it is eaten. 
 *
 */
public class Grass extends Living 
{
	
	public Grass (Plain p, int r, int c) 
	{
		super(p,r,c);
	}
	
	public State who()
	{
		// TODO  
		return State.GRASS; 
	}
	
	/**
	 * Grass can be eaten out by too many rabbits. Rabbits may also multiply fast enough to take over Grass.
	 */
	public Living next(Plain pNew)
	{
		int pop[] = new int[NUM_LIFE_FORMS];
		census(pop);
		
		if(pop[RABBIT] > pop[GRASS]*3) {
			return new Empty(pNew, row, column);
		} 
		else if(pop[RABBIT] >= 3) {
			return new Rabbit(pNew, row, column, 0);
		}
		else {
			return new Grass(pNew, row, column);
		}
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for grass. 
		
	}
}
