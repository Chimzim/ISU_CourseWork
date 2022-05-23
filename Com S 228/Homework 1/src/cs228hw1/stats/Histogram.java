package cs228hw1.stats;

import java.util.ArrayList;

public class Histogram<T extends Number> implements StatObject<T> {
	/**
	 * histogramArr - holds the weather data that will be used to make a histogram
	 * description - tells how the data will be used for the Histogram class
	 * bunNum - the number of different categories to be used for in the chart
	 * maxRange - max value for the histogram
	 * minRange - min value for the histogram
	 */
	private ArrayList<T> histogramArr = new ArrayList<T>();
	private String description;
	private Number binNum = 10;
	private Number maxRange = Integer.MAX_VALUE;
	private Number minRange = Integer.MIN_VALUE;
	
	
	public Histogram() {
		
	}
	public Histogram(ArrayList<T> rawData) {
		histogramArr = rawData;
		SetData(histogramArr);
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

	@Override
	public ArrayList<Number> GetResult() throws RuntimeException {
		// TODO Auto-generated method stub
		double binWidth = (maxRange.doubleValue()-minRange.doubleValue())/binNum.doubleValue();
		ArrayList<Number> temp = new ArrayList<Number>();
		double counter = 0.0;
		if(minRange.doubleValue() > maxRange.doubleValue() || histogramArr.equals(null)) {
			throw new RuntimeException();
		}
		else {
			for(int i = (int) minRange.doubleValue(); i < (int) maxRange.doubleValue(); i+= (int) binWidth) {
				if(histogramArr.get(i).doubleValue() > maxRange.doubleValue() ||
						histogramArr.get(i).doubleValue() < minRange.doubleValue()) {
					throw new RuntimeException();
				}
				else {
					for(int j = 0; j < histogramArr.size(); j++) {
						if(histogramArr.get(j).doubleValue() >= i && histogramArr.get(j).doubleValue() < binWidth+i) {
							counter++;
						}
					}
				}
				temp.add(counter);
				counter = 0;
			}
		}
		return temp;
	}

	@Override
	public void SetData(ArrayList<T> data) {
		// TODO Auto-generated method stub
		for(int i = 0; i < data.size(); i++) {
			histogramArr.add(data.get(i)); 
		}
	}

	@Override
	public ArrayList<T> GetData() {
		// TODO Auto-generated method stub
		return histogramArr;
	}
	/**
	 * sets the number of bins
	 * @param amount - number of bins
	 */
	public void SetNumberBins(Number amount) {
		binNum = amount;
	}
	/**
	 * Returns the number of bins
	 * @return number of bins for this histogram
	 */
	public Number GetNumberBins() {
		return binNum;
	}
	/**
	 * Sets the minimum number for the histogram
	 * @param min - minimum range inside the array
	 */
	public void SetMinRange(Number min) {
		minRange = min;
	}
	/**
	 * sets the max number for the histogram
	 * @param max maximum number for the histogram
	 */
	public void SetMaxRange(Number max) {
		maxRange = max;
	}
	/**
	 * returns the min range
	 * @return the min
	 */
	public Number GetMinRange() {
		return minRange;
	}
	
	public Number GetMaxRange() {
		return maxRange;
	}

}
