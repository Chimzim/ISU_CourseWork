package cs228hw1.stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Median<T extends Number> implements StatObject<T> {
	/**
	 * medianArr - Holds the data that will be used to find the median of the data
	 * description - Holds the description for the median class
	 */
	private ArrayList<T> medianArr = new ArrayList<T>();
	private String description;
	
	public Median() {
		
	}
	public Median(ArrayList<T> rawData) {
		medianArr = rawData;
		SetData(medianArr);
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
	 * temp - holds the data in an array so it can be sorted to find the median
	 * middle - holds the median for the weather data. Different depending on if it is odd or even
	 */
	@Override
	public ArrayList<Number> GetResult() throws RuntimeException {
		// TODO Auto-generated method stub
		ArrayList<Number> middle = new ArrayList<>();
		if(medianArr.equals(null)|| medianArr.size() == 0) {
			throw new RuntimeException();
		}
		for(int i = 0; i < medianArr.size(); i++) {
			if(medianArr.get(i) != null) {
				middle.add(medianArr.get(i));
			}
		}
		double[] temp = new double[middle.size()];
		for(int j = 0; j < middle.size(); j++) {
			temp[j] = middle.get(j).doubleValue();
		}
		Arrays.sort(temp);
		middle.clear();
		int half = temp.length/2;
		if(temp.length % 2 == 0) {
			middle.add((temp[half]+temp[half-1])/2);
			return middle;
		}
		else {
			middle.add(temp[half]);
			return middle;
		}
	}

	@Override
	public void SetData(ArrayList<T> data) {
		// TODO Auto-generated method stub
		if(medianArr.size()== 0 || medianArr.equals(null)) {
			throw new RuntimeException();
		}
	}

	@Override
	public ArrayList<T> GetData() {
		// TODO Auto-generated method stub
		return medianArr;
	}
}
