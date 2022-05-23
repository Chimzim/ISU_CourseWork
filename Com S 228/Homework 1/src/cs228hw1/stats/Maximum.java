package cs228hw1.stats;

import java.util.ArrayList;

public class Maximum<T extends Number> implements StatObject<T> {
	/**
	 * maximumArr - holds the data that will be used to find the max value of the weather data
	 * description - holds the description for what the maximum class
	 */
	private ArrayList<T> maximumArr = new ArrayList<T>();
	private String description;
	
	
	public Maximum() {
		
	}
	public Maximum(ArrayList<T> rawData) {
		maximumArr = rawData;
		SetData(maximumArr);
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
	 * temp - holds the temp to check and find the max value
	 */
	@Override
	public ArrayList<Number> GetResult() throws RuntimeException {
		// TODO Auto-generated method stub
		ArrayList<Number> max = new ArrayList<Number>();
		Number temp = maximumArr.get(0);
		if(maximumArr.size()== 0 || maximumArr.equals(null)) {
			throw new RuntimeException();
		}
		for(int i = 0; i < maximumArr.size(); i++) {
			if(maximumArr.get(i) != null) {
				if(maximumArr.get(i).doubleValue() > temp.doubleValue()) {
					temp = maximumArr.get(i);
				}
			}
			max.add(temp);
		}
		return max;
	}

	@Override
	public void SetData(ArrayList<T> data) {
		// TODO Auto-generated method stub
		for(int i = 0; i < data.size(); i++) {
			maximumArr.add(data.get(i));
		}
		
	}

	@Override
	public ArrayList<T> GetData() {
		// TODO Auto-generated method stub
		return maximumArr;
	}

}
