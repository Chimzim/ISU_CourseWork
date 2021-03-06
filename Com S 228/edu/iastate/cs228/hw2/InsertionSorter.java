package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author
 *
 */

/**
 * 
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		// TODO 
	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 */
	@Override 
	public void sort()
	{
		Point temp;
		if(pointComparator == null) {
			for(int i = 1; i < points.length; i++) {
				temp = points[i];
				int hole = i -1;
				while(hole >= 0 && points[hole].compareTo(temp) != -1) {
					points[hole+1] = points[hole];
					hole -= 1;
				}
				points[hole+1] = temp;
			}
		}
		else {
			for(int i = 1; i < points.length; i++) {
				temp = points[i];
				int hole = i -1;
				while(hole >= 0 && pointComparator.compare(points[hole], temp) != -1) {
					points[hole+1] = points[hole];
					hole -= 1;
				}
				points[hole+1] = temp;
			}
		}
		// TODO 
	}
}
