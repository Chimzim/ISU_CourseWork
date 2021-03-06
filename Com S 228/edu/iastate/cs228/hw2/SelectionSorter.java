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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	private long runTime = 0;
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		// TODO 
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		Point smallest = points[0];
		int index = 0;
		for(int i = 0; i < points.length; i++) {
			for(int j = 0; j < points.length; j++) {
				if(pointComparator == null) {
					if(points[j].compareTo(points[i]) == -1) {
						if(points[j].compareTo(smallest) == -1) {
							smallest = points[j];
							index = j;
						}
					}
				}
				else {
					if(pointComparator.compare(points[j],points[i]) == -1) {
						if(pointComparator.compare(points[j],smallest) == -1) {
							smallest = points[j];
							index = j;
						}
					}
				}
			}
			Point temp = points[i];
			points[i] = smallest;
			points[index] = temp;
		}	
	}
}
