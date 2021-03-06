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
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	// Other private instance variables if you need ... 
	Point smallerValues, largerValues;
	int pivioting = 0;
		
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super(pts);
		// TODO 
	}
		

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{
		quickSortRec(0, points.length-1);
		// TODO 
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if(last < first) {
			pivioting = partition(first, last);
			
			quickSortRec(first, pivioting-1);
			quickSortRec(pivioting+1, last);
		}
		// TODO
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		// TODO 
		Point pivot = points[last];
		int j = first;
		for(int i = first; i < last; i++) {
			if(points[i].compareTo(pivot) == -1) {
				j += 1;
				Point temp = points[j];
				points[j] = points[i];
				points[i] = temp;
			}
		}
		Point temp = points[j+1];
		points[j+1] = points[last];
		points[last] = temp;
		
		pivioting = j+1;
		return j+1; 
	}	
	// Other private methods in case you need ...
}
