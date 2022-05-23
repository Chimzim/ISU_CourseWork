package cs228hw1.stats;

import java.util.ArrayList;

/**
 * Performs a series of statistical calculations.
 * @param <T> The type to be used in interpreting data. Must be a number of some variety.
 * @author Don Nye
 */
public interface Statistics<T extends Number>
{
	/**
	 * Reads the weather data (of the specified variety) in the specified file into the active data set.
	 * The data in the file is treated as type T. Missing values should be read as null.
	 * @param d The set of data to be read from the file.
	 * @param path The path of the file.
	 * @return Returns true if the file is successfully read and false otherwise.
	 */
	boolean ReadFile(String path, DATA d);
	
	/**
	 * Adds a StatObject to this Statistics object to the end of the list of StatObjects currently in it.
	 * The data currently stored in this Statistics object will be assigned to the new StatObject.
	 */
	void AddStatObject(StatObject<T> so);
	
	/**
	 * Adds a StatObject to this Statistics object to the end of the list of StatObjects currently in it.
	 * A subset of the data currently stored in this Statistics object will be assigned to the new StatObject.
	 * @param so The new StatObject to add.
	 * @param first The index of the first piece of data to add to the provided StatObject.
	 * @param last The index of the last piece of data to add to the provided StatObject.
	 */
	void AddStatObject(StatObject<T> so, int first, int last);
	
	/**
	 * Obtains the [i]th StatObject in this Statistics object.
	 * @param i The index to return.
	 * @return The specified StatObject or null if no such index exists.
	 */
	StatObject<T> GetStatObject(int i);
	
	/**
	 * Removes a StatObject from this Statistics object.
	 * @param i The index to remove.
	 * @return Returns the StatObject removed or null if no such index exists.
	 */
	StatObject<T> RemoveStatObject(int i);
	
	/**
	 * Returns the number of StatObjects currently in this Statistics object.
	 */
	int Count();
	
	/**
	 * Returns a list containing the current data set stored in this Statistics object used for new StatObjects.
	 * The returned list should not allow modification of any values inside this Statistics object.
	 */
	ArrayList<T> GetDataSet();
	
	/**
	 * Performs each calculation in stored in this Statistics object in order.
	 * @return an ArrayList of results when GetResult is called in every StatObject in order.
	 */
	ArrayList<ArrayList<Number>> MapCar();
	
	/**
	 * A flag indicating what data should be read from the weather input file.
	 */
	public enum DATA
	{USAF, WBAN, YR_MO_DA_HR_MN /* Year, Month, Day, Hour, Minute */, DIR, SPD, GUS, CLG, SKC, L, M, H, VSB, MW_1, MW_2, MW_3, MW_4, AW_1, AW_2, AW_3, AW_4, W, TEMP, DEWP, SLP, ALT, STP, MAX, MIN, PCP01, PCP06, PCP24, PCPXX, SD}
}