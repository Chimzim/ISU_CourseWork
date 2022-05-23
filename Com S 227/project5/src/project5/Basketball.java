package project5;

public class Basketball
{
	private double ballDiameter;
	private boolean isInflated;
  public Basketball(double givenDiameter)
  {
	  ballDiameter = givenDiameter;
	  isInflated = false;
  }

  public boolean isDribbleable()
  {
    return isInflated;
  }

  public double getDiameter()
  {
    return ballDiameter;
  }

  public double getCircumference()
  {
    double results = Math.PI * ballDiameter;
    return results;
  }

  public void inflate()
  {
	  isInflated = true;
  }
}