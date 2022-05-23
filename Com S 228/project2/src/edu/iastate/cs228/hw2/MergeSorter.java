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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		// TODO  
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		sorting(points, 0, points.length-1);
		// TODO 
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		int a = 0, b = 0, c = 0;
		int size1 = points.length/2, size2 = points.length - size1;
		Point[] left = new Point[size1], right = new Point[size2];
		
		for(int i = 0; i < size1; i++) {
			left[i] = new Point(points[i].getX(), points[i].getY());
		}
		for(int i = 0, j = size1; i < size2; i++, j++) {
			right[i] = new Point(points[j].getX(), points[j].getY());
		}
		
		//mergeSortRec(left);
		//mergeSortRec(right);
		b = size1;
		while(a < size1 && b < size2) {
			if(left[a].compareTo(right[b]) <= 0) {
				pts[c] = left[a];
				a += 1;
			}
			else {
				pts[c] = right[b];
				b += 1;
			}
			c++;
		}
		while(a < size1) {
			pts[c] = left[a];
			a += 1;
			c += 1;
		}
		while(b < size2) {
			pts[c] = right[b];
			b += 1;
			c += 1;
		}
	}
		

	private void sorting(Point[] Pts, int left, int right) {
		
		if(left < right) {
			int middle = left+right;
			middle /= 2;
			
			sorting(Pts, left, middle);
			sorting(Pts, middle+1, right);
			
			mergeSortRec(Pts);
		}
	}

	// Other private methods in case you need ...

}
