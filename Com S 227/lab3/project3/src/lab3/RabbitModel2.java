package lab3;

public class RabbitModel2 {
	 private int population = 0;
	  /**
	   * Constructs a new RabbitModel.
	   */
	  public RabbitModel()
	  {
	    population = 0;
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
	    population++;
	    population %= 5;
	  }
	  
	  /**
	   * Sets or resets the state of the model to the 
	   * initial conditions.
	   */
	  public void reset()
	  {
	    population = 0;
	  }
}
