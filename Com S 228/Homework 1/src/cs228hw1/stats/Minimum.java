package cs228hw1.stats;

import java.util.ArrayList;

public class Minimum<T extends Number> implements StatObject<T> {
	/**
	 * minimumArr - holds the weather data that will be used to find the min value
	 * description - Tells what the minimum class returns 
	 */
	private ArrayList<T> minimumArr = new ArrayList<T>();
	private String description;
	
	public Minimum() {
		
	}
	public Minimum(ArrayList<T> rawData) {
		minimumArr = rawData;
		SetData(minimumArr);
	}
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
	 * temp - Checks and finds the smallest value 
	 */
	@Override
	public ArrayList<Number> GetResult() throws RuntimeException {
		// TODO Auto-generated method stub
		ArrayList<Number> min = new ArrayList<Number>();
		Number temp = minimumArr.get(0);
		if(minimumArr.size() == 0 || minimumArr.equals(null)) {
			throw new RuntimeException();
		}
		for(int i = 0; i < minimumArr.size(); i++) {
			if(minimumArr.get(i) != null) {
				if(minimumArr.get(i).doubleValue() < temp.doubleValue()) {
					temp = minimumArr.get(i);
				}
			}
			min.add(temp);
		}
		return min;
	}

	@Override
	public void SetData(ArrayList<T> data) {
		// TODO Auto-generated method stub
		for(int i = 0; i < data.size(); i++) {
			minimumArr.add(data.get(i)); 
		}
		
	}

	@Override
	public ArrayList<T> GetData() {
		// TODO Auto-generated method stub
		return minimumArr;
	}

}
