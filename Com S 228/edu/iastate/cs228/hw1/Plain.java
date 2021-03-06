package edu.iastate.cs228.hw1;

/**
 *  
 * @author
 *
 */

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner; 
import java.util.Random; 

/**
 * 
 * The plain is represented as a square grid of size width x width. 
 *
 */
public class Plain 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid;
	
	
	/**
	 *  Default constructor reads from a file 
	 */
	public Plain(String inputFileName) throws FileNotFoundException
	{		
		File f = new File(inputFileName);
		Scanner in = new Scanner(f);
		width = in.nextInt();
		grid = new Living[width][width];
		int i = 0, j = 0;
		
		while(in.hasNext()) {
			if(in.next() == "E") {
				grid[i][j] = new Empty(null, i , j);
				j++;
			}
			else if(in.next() == "G") {
				grid[i][j] = new Grass(null, i , j);
				j++;
			}
			else if(in.next() == "R") {
				grid[i][j] = new Rabbit(null, i, j, in.nextInt());
				j++;
			}
			else if(in.next() == "B") {
				grid[i][j] = new Badger(null, i , j, in.nextInt());
				j++;
			}
			else if(in.next() == "F") {
				grid[i][j] = new Fox(null, i, j, in.nextInt());
				j++;
			}
			
			if(j >= width - 1) {
				j = 0;
				i++;
			}
			 
		}
		
		in.close();
        // TODO 
		// 
		// Assumption: The input file is in correct format. 
		// 
		// You may create the grid plain in the following steps: 
		// 
		// 1) Reads the first line to determine the width of the grid.
		// 
		// 2) Creates a grid object. 
		// 
		// 3) Fills in the grid according to the input file. 
		// 
		// Be sure to close the input file when you are done. 
	}
	
	/**
	 * Constructor that builds a w x w grid without initializing it. 
	 * @param width  the grid 
	 */
	public Plain(int w)
	{
		grid = new Living[w][w];
		width = w;
	}
	
	
	public int getWidth()
	{
		// TODO  
		return width;  // to be modified 
	}
	
	/**
	 * Initialize the plain by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		Random generator = new Random(); 
		 for(int i = 0; i < width; i++) {
			 for(int j = 0; j < width; i++) {
				 int occupant = generator.nextInt(5);
				 
				if(occupant == Living.RABBIT) {
					grid[i][j] = new Rabbit(this,i,j,0);
				}
				else if(occupant == Living.GRASS) {
					grid[i][j] = new Grass(this, i, j);
				}
				else if(occupant == Living.FOX) {
					grid[i][j] = new Fox(this, i, j, 0);
				}
				else if(occupant == Living.EMPTY) {
					grid[i][j] = new Empty(this, i, j);
				}
				else {
					grid[i][j] = new Badger(this, i, j, 0);
				}
			 }
		 }
		// TODO 
	}
	
	
	/**
	 * Output the plain grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		String screenGrid = "";
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < width; j++) {
				
				if(grid[i][j].who() == State.FOX) {
					screenGrid += "F";
					screenGrid += Integer.toString((((Animal) grid[i][j]).myAge()));
					screenGrid += " ";
				}
				else if(grid[i][j].who() == State.BADGER) {
					screenGrid += "B";
					screenGrid += Integer.toString((((Animal) grid[i][j]).myAge()));
					screenGrid += " ";
				}
				else if(grid[i][j].who() == State.RABBIT) {
					screenGrid += "R";
					screenGrid += Integer.toString((((Animal) grid[i][j]).myAge()));
					screenGrid += " ";
				}
				else if(grid[i][j].who() == State.EMPTY) {
					screenGrid += "E  ";
				}
				else { 
					screenGrid += "G  ";
				}
			}
			screenGrid += "\n";
		}
		
		return screenGrid; 
	}
	

	/**
	 * Write the plain grid to an output file.  Also useful for saving a randomly 
	 * generated plain for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException
	{
		// TODO 
		// 1. Open the file. 
		// 2. Write to the file. The five life forms are represented by characters 
		//    B, E, F, G, R. Leave one blank space in between. Examples are given in
		//    the project description. 
		// 3. Close the file. 
	}			
}
