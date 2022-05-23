package cs228hw1.stats;

import java.util.ArrayList;

public class StdDeviation<T extends Number> implements StatObject<T> {
	/**
	 * standardDev - holds the weather data that will be used to calculate standard devation
	 * description - Tells how the data will be used for the standard devation class
	 */
	private ArrayList<T> standardDev = new ArrayList<T>();
	private String description;
	@Override
	public void SetDescription(String d) {
		// TODO Auto-generated method stub
		description = d;
	}

	@Override
	public String GetDescription() {
		// TODO Auto-generated method stub
		return description;
	}
	/**
	 * mean - holds the mean of the weather data.
	 * summation - calculates the summation of the weather data.
	 * Devation - returns the standard devation of the weather data
	 */
	@Override
	public ArrayList<Number> GetResult() throws RuntimeException {
		// TODO Auto-generated method stub
		ArrayList<Number> Devation = new ArrayList<Number>();
		double mean = 0.0;
		double summation = 0.0;
		double size = 0.0;
		if(standardDev.equals(null) || standardDev.size() == 0) {
			throw new RuntimeException();
		}
		for(int i = 0; i < standardDev.size(); i++) {
			if(standardDev.equals(null)) {
				mean += standardDev.get(i).doubleValue();
				size += 1;
			}
		}
		for(int j = 0; j < standardDev.size(); j++) {
			if(standardDev.equals(null)) {
				summation += Math.sqrt(standardDev.get(j).doubleValue() - mean);
			}
		}
		summation /= size-1;
		Devation.add(summation);
		
		return Devation;
	}

	@Override
	public void SetData(ArrayList<T> data) {
		// TODO Auto-generated method stub
		for(int i = 0; i < data.size(); i++) {
			standardDev.add(data.get(i));
		}
	}

	@Override
	public ArrayList<T> GetData() {
		// TODO Auto-generated method stub
		return standardDev;
	}

}
