package cs228hw1.stats;


import java.util.ArrayList;

public class Average<T extends Number> implements StatObject<T> {
			/**
			 * Data - holds the average for the weather data
			 * description - holds the description of the average class
			 */
			private ArrayList<T> Data = new ArrayList<T>();;
			private String description;	 
		
		public Average() {
			
		}
		public Average(ArrayList<T> rawData) {
			Data = rawData;
			SetData(Data);
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
		 * sumArr - Temporary array that will average out the weather data
		 */
		@Override
		public ArrayList<Number> GetResult() throws RuntimeException {
			// TODO Auto-generated method stub
			if(Data.size()== 0 || Data.equals(null)) {
				throw new RuntimeException();
			}
			ArrayList<Number> sumArr = new ArrayList<Number>();
			Double sum = 0.0;
			for(int i =0; i < Data.size(); i++) {
				if(Data.get(i) != null) {
				sumArr.add(Data.get(i));
				sum += sumArr.get(i).doubleValue();
				}
			}
			sum /= Data.size();
			sumArr.clear();
			sumArr.add(sum);
			return sumArr;
		}

		@Override
		public void SetData(ArrayList<T> data) {
			// TODO Auto-generated method stub
			for(int i = 0; i < data.size(); i++) {
				Data.add(data.get(i));
			}
			
			
		}

		@Override
		public ArrayList<T> GetData() {
			return Data;
			// TODO Auto-generated method stub
		}
		

	}
	

