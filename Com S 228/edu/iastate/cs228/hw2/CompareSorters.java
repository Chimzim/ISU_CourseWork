package edu.iastate.cs228.hw2;

/**
 *  
 * @author
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		Scanner in = new Scanner(System.in);
		int countOfTrials = 1;
		Point[] myPoints;
		Random rand = new Random();
		RotationalPointScanner[] scanners = new RotationalPointScanner[4]; 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       RotationalPointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		// 	
		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)");
		while(true) {
			System.out.println("Trial "+ countOfTrials+": ");
			
			int key = in.nextInt();
			
			if(key == 1) {
				String stats = "";
				System.out.println("Enter number of random points: ");
				int number = in.nextInt();
				myPoints = generateRandomPoints(number, rand);
				System.out.println("algorithm size time (ns)");
				System.out.println("----------------------------------");
				scanners[0] = new RotationalPointScanner(myPoints, Algorithm.SelectionSort);
				scanners[0].scan();
				scanners[0].draw();
				stats += scanners[0].stats() + "\n";
				scanners[1] = new RotationalPointScanner(myPoints, Algorithm.InsertionSort);
				scanners[1].scan();
				scanners[1].draw();
				stats += scanners[1].stats() + "\n";
				scanners[2] = new RotationalPointScanner(myPoints, Algorithm.MergeSort);
				scanners[2].scan();
				scanners[2].draw();
				stats += scanners[2].stats()+"\n";
				scanners[3] = new RotationalPointScanner(myPoints, Algorithm.QuickSort);
				scanners[3].scan();
				scanners[3].draw();
				stats += scanners[3].stats()+"\n";
				System.out.println(stats);
				System.out.println("----------------------------------");
				countOfTrials++;
			}
			else if(key == 2) {
				String stats = "";
				System.out.println("Points from a file ");
				System.out.println("File name: ");
				String myString = in.next();
				scanners[0] = new RotationalPointScanner(myString, Algorithm.SelectionSort);
				scanners[0].scan();
				scanners[0].draw();
				stats += scanners[0].stats()+"\n";
				scanners[1] = new RotationalPointScanner(myString, Algorithm.InsertionSort);
				scanners[1].scan();
				scanners[1].draw();
				stats += scanners[1].stats()+"\n";
				scanners[2] = new RotationalPointScanner(myString, Algorithm.MergeSort);
				scanners[2].scan();
				scanners[2].draw();
				stats += scanners[2].stats()+"\n";
				scanners[3] = new RotationalPointScanner(myString, Algorithm.QuickSort);
				scanners[3].scan();
				scanners[3].draw();
				stats += scanners[3].stats()+"\n";
				System.out.println(stats);
				System.out.println("----------------------------------");
				countOfTrials++;
			}
			else {
				System.exit(0);
			}
		}
		
		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() and draw() 
		//        methods in the RotationalPointScanner class.  You can visualize the result of each scan.  
		//        (Windows have to be closed manually before rerun.)  
		// 
		//     c) After all four scans are done for the input, print out the statistics table (cf. Section 2). 
		//
		// A sample scenario is given in Section 2 of the project description. 
		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] ? [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		if(numPts < 1) {
			throw new IllegalArgumentException();
		}
		Point[] pts = new Point[numPts];
		for(int i = 0; i < pts.length; i++) {
			int x = rand.nextInt(101) -50;
			int y = rand.nextInt(101) -50;
			pts[i] = new Point(x, y);
		}
		return pts; 
		// TODO 
	}
	
}
