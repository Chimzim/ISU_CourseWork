package cs228hw1.stats;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.html.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;

public class StatisticsShell<T extends Number> implements Statistics<T> {
	/**
	 * rawData - holds the data for the statobject class
	 * parser - variable used to parse the weather data
	 * numData - number type that will be used to put the write type of data into the arraylist 
	 */
	private ArrayList<StatObject <T>> rawData = new ArrayList<StatObject <T>>();
	private IParser parser;
	private ArrayList<Number> numData = new ArrayList<Number>();
	
	public StatisticsShell(IParser<T> anything) {
		parser = anything;
	}
	/**
	 * in - scanner used to read the data file
	 * f - file that reads in the data
	 * lines - holds each line from the scanner
	 * s - the array of strings to parse the data
	 * location - gives the location of the correct data set
	 */
	@Override
	public boolean ReadFile(String path, DATA d) {
		// TODO Auto-generated method stub
	try {
		File f = new File(path);
		Scanner in = new Scanner(f);
		String lines = in.nextLine();
		ArrayList<String> s = new ArrayList<String>();
		String[] tempStrings = lines.split(" ");
		int location = d.ordinal();
		
		while(in.hasNext()) {
			String nextLine = in.nextLine();
			//String[] tempStrings2 = nextLine.split(" ");
			//int location2 = d.ordinal();
			for(int i = 0; i < location; i++) {
				in.next();
			}
			parser.parse(in.next());
			numData.add((Number) parser);
		}
		return true;
	}
	catch (FileNotFoundException e) {
		return false;
		}
	}

	@Override
	public void AddStatObject(StatObject<T> so) {
		// TODO Auto-generated method stub
		rawData.add(so);
	}

	@Override
	public void AddStatObject(StatObject<T> so, int first, int last) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StatObject<T> GetStatObject(int i) {
		// TODO Auto-generated method stub
		return rawData.get(i);
	}

	@Override
	public StatObject<T> RemoveStatObject(int i) {
		// TODO Auto-generated method stub
		return rawData.remove(i);
	}

	@Override
	public int Count() {
		// TODO Auto-generated method stub
		return rawData.size();
	}

	@Override
	public ArrayList<T> GetDataSet() {
		// TODO Auto-generated method stub
		return (ArrayList<T>) rawData;
	}

	@Override
	public ArrayList<ArrayList<Number>> MapCar() {
		// TODO Auto-generated method stub
		return null;
	}

}
