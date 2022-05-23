package lab3;

public class RabbitModel3 {
	private int population;
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
	    population = population / 2;
	  }
	  
	  /**
	   * Sets or resets the state of the model to the 
	   * initial conditions.
	   */
	  public void reset()
	  {
	    population = 500;
	  }
}
