package edu.iastate.cs228.hw2;

import java.io.File;

/**
 * 
 * @author 
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array by polar angle with respect to a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class RotationalPointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
	protected String outputFileName;   // "select.txt", "insert.txt", "merge.txt", or "quick.txt"
	
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[]. Set outputFileName. 
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public RotationalPointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if(pts == null ||pts.length == 0) {
			throw new IllegalArgumentException();
		}
		points = new Point[pts.length];
		points = Arrays.copyOf(pts, pts.length);
		sortingAlgorithm = algo;
	}

	
	/**
	 * This constructor reads points from a file. Set outputFileName. 
	 * It scans in each new line from the file, then after recieving each line another scanner comes in using a delimator
	 * to ensure negative numbers are scanned. it counts it up the first time to see if its odd or even to see if it should
	 * throw an exception. if it doesn't throw an exception then it goes through the file again putting all in ints into an array
	 * and finally takes that array of ints and puts it inside of the array of points with their respective x and y values
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected RotationalPointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		File f = new File(inputFileName);
		Scanner in = new Scanner(f);
		int size = 0;
		while(in.hasNextLine()) {
			Scanner in2 = new Scanner(in.nextLine());
			while(in2.hasNextInt()) {
				in.useDelimiter(",|\\s+");
				in.nextInt();
				size += 1; 
			}
			in2.close();
		}
		in.close();
		if(size % 2 != 0) {
			throw new InputMismatchException();
		}
		else {
			in = new Scanner(f);
			int[] numbers = new int[size];
			points = new Point[size/2];
			size = 0;
			while(in.hasNextLine()) {
				Scanner in2 = new Scanner(in.nextLine());
				while(in2.hasNextInt()) {
					in.useDelimiter(",|\\s+");
					int value = in2.nextInt();
					numbers[size] = value;
					size++;
				}
				in2.close();
			}
			in.close();
			size = 0;
			for(int i = 0; i < points.length; i++) {
				points[i] = new Point(numbers[size], numbers[size+1]);
				size += 2;
			}
		}
	}

	
	/**
	 * Carry out three rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates. 
	 *     d) Sort points[] again by the polar angle with respect to medianCoordinatePoint.
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting. Copy the sorting result back onto the array points[] by calling 
	 * the method getPoints() in AbstractSorter. 
	 *      
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		// TODO  
		AbstractSorter aSorter = null; 
		if(sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);
		}
		else if(sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
		}
		else if(sortingAlgorithm == Algorithm.QuickSort) {
			aSorter = new QuickSorter(points);
		}
		else {
			aSorter = new SelectionSorter(points);
		}
		
		aSorter.setComparator(0);
		long timer1 = System.nanoTime();
		aSorter.sort();
		scanTime += System.nanoTime() - timer1;
		Point median1 = aSorter.getMedian();
		
		aSorter.setComparator(1);
		timer1 = System.nanoTime();
		aSorter.sort();
		scanTime += System.nanoTime() - timer1;
		Point median2 = aSorter.getMedian();
		
		
		medianCoordinatePoint = new Point(median1.getX(), median2.getY());
		aSorter.setReferencePoint(medianCoordinatePoint);
		
		aSorter.setComparator(2);
		timer1 = System.nanoTime();
		aSorter.sort();
		scanTime += System.nanoTime() - timer1;
		
		// create an object to be referenced by aSorter according to sortingAlgorithm. for each of the three 
		// rounds of sorting, have aSorter do the following: 
		// 
		//     a) call setComparator() with an argument 0, 1, or 2. in case it is 2, must have made 
		//        the call setReferencePoint(medianCoordinatePoint) already. 
		//
		//     b) call sort(). 		
		// 
		// sum up the times spent on the three sorting rounds and set the instance variable scanTime. 
		
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String statistic = "";
		if(sortingAlgorithm == Algorithm.InsertionSort) {
			statistic += "Insertion sort " + points.length +" "+ scanTime;
		}
		else if(sortingAlgorithm == Algorithm.MergeSort) {
			statistic += "Merge sort " + points.length +" "+ scanTime;
		}
		else if(sortingAlgorithm == Algorithm.QuickSort) {
			statistic += "Quick sort " + points.length +" "+ scanTime;
		}
		else {
			statistic += "Selection sort " + points.length +" "+ scanTime;
		}
		return statistic; 
		// TODO 
	}
	
	
	/**
	 * Write points[] after a call to scan().  When printed, the points will appear 
	 * in order of polar angle with respect to medianCoordinatePoint with every point occupying a separate 
	 * line.  The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		//TODO remember to sort the array using the median coordinate point
		String plot = "";
		for(int i = 0; i < points.length; i++) {
			plot += points[i].toString();
			plot += "\n";
		}
		return plot; 
		// TODO
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writePointsToFile() throws FileNotFoundException
	{
		if(sortingAlgorithm.equals(Algorithm.MergeSort)) {
			outputFileName = "Merge.txt";
		}
		else if(sortingAlgorithm.equals(Algorithm.InsertionSort)) {
			outputFileName = "insert.txt";
		}
		else if (sortingAlgorithm.equals(Algorithm.SelectionSort)) {
			outputFileName = "select.txt";
		}
		else {
			outputFileName = "quick.txt";
		}
		
		File f = new File(outputFileName);
		PrintWriter p = new PrintWriter(f);
		for(int i = 0; i < points.length; i++) {
			p.println(points[i].toString());
		}
		p.close();
		// TODO 
	}	

	
	/**
	 * This method is called after each scan for visually check whether the result is correct.  You  
	 * just need to generate a list of points and a list of segments, depending on the value of 
	 * sortByAngle, as detailed in Section 4.1. Then create a Plot object to call the method myFrame().  
	 */
	public void draw()
	{		
		int numSegs = 0;  // number of segments to draw 
		for(int i = 0, j = 1; i < points.length-1; i++, j++) {
				if((points[j].getY() != points[i].getY() || points[i].getX() != points[j].getX()) && 
						(!points[i].equals(medianCoordinatePoint) && !points[j].equals(medianCoordinatePoint))) {
					numSegs += 2;
				}
			}
		
		if(points[points.length-1].getX() != points[0].getX() || points[points.length-1].getY() != points[0].getY()) {
			numSegs++;
		}
		// Based on Section 4.1, generate the line segments to draw for display of the sorting result.
		// Assign their number to numSegs, and store them in segments[] in the order. 
		Segment[] segments = new Segment[numSegs];
		int i = 0;
		while(i < segments.length-1) {
			for(int c = 0, d = 1; c < points.length-1; c++, d++) {
					if(points[d].getY() != points[c].getY() || points[c].getX() != points[d].getX()) {
						segments[i] = new Segment(points[c], points[d]);
						i += 1;
						if(i == 1) {
							segments[i] = new Segment(medianCoordinatePoint, points[c]);
							segments[i+1] = new Segment(medianCoordinatePoint, points[d]);
							i += 2;
						}
						else {
							segments[i] = new Segment(medianCoordinatePoint, points[d]);
							i++;
						}
					}
				}
			}
		
		// TODO 
		

		String sort = null; 
		
		switch(sortingAlgorithm)
		{
		case SelectionSort: 
			sort = "Selection Sort"; 
			break; 
		case InsertionSort: 
			sort = "Insertion Sort"; 
			break; 
		case MergeSort: 
			sort = "Mergesort"; 
			break; 
		case QuickSort: 
			sort = "Quicksort"; 
			break; 
		default: 
			break; 		
		}
		
		// The following statement creates a window to display the sorting result.
		Plot.myFrame(points, segments, sort);
		
	}
		
}
