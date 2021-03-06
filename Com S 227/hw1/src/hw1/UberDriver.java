package hw1;
/**
 * This will mimic a the simplistic aspects of an Uber driver through the drivers given per minute rate and per mile rate 
 * @author ogbondah
 *
 */
public class UberDriver {
	 /**
	 * Maximum number of passengers allowed in the vehicle at one time.
	 */
	 public static final int MAX_PASSENGERS = 4;

	 /**
	 * Cost to operate the vehicle per mile.
	 */
	 public static final double OPERATING_COST = 0.5;
	 private double perMileRate, perMinRate, exactMins, sum, profit, workingMins;
	 private int totalMiles, totalMinutes, passengers, roundedMins, workingMiles;
	 /**
	  * Constructs an UberDriver with the given per-mile rate and per-minute rate.
	  * @param givenPerMileRate - Rate of the Uber driver per mile.
	  * @param givenPerMinuteRate - rate of the Uber driver per minute.
	  * perMilesRate - The rate of the Uber driver per mile.
	  * perMinRate - The rate of the Uber driver per minutes.
	  * totalMinutes - Holds the total minutes the Uber driver has driven.
	  * passengers - Number of passengers in the vehicle (can't exceed 4).
	  * roundedMins - Rounded number taken average speed equation.
	  * exactMins - Exact minutes taken from average speed equation.
	  * sum - The total amount of credits earned by the Uber driver.
	  * profit - How much the Uber driver made after the Operating cost for every mile
	  * workingMiles - Miles driven with a passenger in the car.
	  * workingMins - Minutes driven with a passenger in the car.
	  */
	 public UberDriver(double givenPerMileRate, double givenPerMinuteRate) {
		 perMileRate = givenPerMileRate;
		 perMinRate = givenPerMinuteRate;
		 totalMiles = 0; 
		 totalMinutes= 0; 
		 passengers = 0;
		 roundedMins = 0;
		 exactMins = 0.0;
		 sum = 0.0;
		 profit = 0.0;
		 workingMiles = 0;
		 workingMins = 0.0;
		 
	 }
	 /**
	  * Returns the total miles driven since this UberDriver was constructed
	  * @return Total miles the Uber driver has gone
	  */
	 public int getTotalMiles() {
		 return totalMiles;
	 }
	 /**
	  * Returns the total minutes driven since this UberDriver was constructed.
	  * @return Total minutes the Uber driver has driven 
	  */
	 public int getTotalMinutes() {
		 return totalMinutes;
	 }
	 /**
	  * Drives the vehicle for the given number of miles over the given number of minutes.
	  * @param miles - User given number of miles
	  * @param minutes - User given number of minutes
	  */
	 public void drive(int miles, int minutes) { 
		 totalMiles += miles;
		 totalMinutes += minutes;
		 workingMins = minutes;
		 workingMiles = miles;
		 sum += (workingMiles * perMileRate) * passengers + (workingMins * perMinRate) * passengers;
		 
	 }
	 /**
	  * Simulates sitting in the vehicle without moving for the given number of minutes.
	  * @param minutes - How long in minutes the vehicle was stopped for.
	  */
	 public void waitAround(int minutes) {
		 drive(0, minutes);
	 }
	 /**
	  * Drives the vehicle for the given number of miles at the given speed.
	  * @param miles - Number of miles the uber driver has driven. 
	  * @param averageSpeed - Speed of the uber drivers car.
	  */
	 public void driveAtSpeed(int miles, double averageSpeed) {
		 exactMins = (miles/averageSpeed) * 60;
		 roundedMins = (int) Math.round(exactMins);
		 drive(miles, roundedMins); 
	 }
	 /**
	  * Returns the number of passengers currently in the vehicle.
	  * @return Total number of passengers in the Uber vehicle.
	  */
	 public int getPassengerCount() {
		 return passengers;
	 }
	 /**
	  * Increases the passenger count by 1 without exceeding the maximum capacity 
	  */
	 public void pickUp() {
		 if(passengers < 4) {
			 passengers++; 
		 }
	 } 
	 /**
	  * Decreases the passenger count by 1, not going below zero.
	  */
	 public void dropOff() {
		 if(passengers > 0) {
			 passengers--;
		 }
	 }
	 /**
	  * Returns this UberDriver's total credits (money earned before deducting operating costs)
	  * @return Total amount of money earned by the Uber driver before the operating costs.
	  */
	 public double getTotalCredits() {
		 return sum;
	 }
	 /**
	  * Returns this UberDriver's profit (total credits, less operating costs).
	  * @return Total amount of money that the Uber driver made.
	  */
	 public double getProfit() {
		 return sum - (OPERATING_COST * totalMiles);
	 }
	 /**
	  * Returns this UberDriver's average profit per hour worked.
	  * @return The hourly rate the Uber driver made while working.
	  */
	 public double getAverageProfitPerHour() {
		 profit = sum - (OPERATING_COST * totalMiles);
		 return (profit / totalMinutes) * 60;
	 }
	 
	 
}
