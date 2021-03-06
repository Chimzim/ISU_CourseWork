package lab3;

import java.util.Random;

public class RabbitModel4 {
	 // TODO - add instance variables as needed
	 private int population = 1;
	 private int lastYear = 1;
	 private int prevYear = 0;
	 
	 Random rand = new Random();
	  /**
	   * Constructs a new RabbitModel.
	   */
	  public RabbitModel()
	  {
	    reset();
	  }  
	 
	  /**
	   * Returns the current number of rabbits.
	   * @return
	   *   current rabbit population
	   */
	  public int getPopulation()
	  {
	    // TODO - returns a dummy value so code will compile
	    return population;
	  }
	  
	  /**
	   * Updates the population to simulate the
	   * passing of one year.
	   */
	  public void simulateYear()
	  {
	    population = lastYear + prevYear;
	    prevYear = lastYear;
	    lastYear = population;
	  }
	  
	  /**
	   * Sets or resets the state of the model to the 
	   * initial conditions.
	   */
	  public void reset()
	  {
	    population = 1;
	    int lastYear = 1;
	    int prevYear = 0;
	  }
}
