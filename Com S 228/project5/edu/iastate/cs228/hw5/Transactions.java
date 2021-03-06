package edu.iastate.cs228.hw5;


import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author Chimzim Ogbondah
 *
 */

/**
 * 
 * The Transactions class simulates video transactions at a video store. 
 *
 */
public class Transactions 
{
	
	/**
	 * The main method generates a simulation of rental and return activities.  
	 *  
	 * @param args
	 * @throws FileNotFoundException
	 * @throws AllCopiesRentedOutException 
	 * @throws FilmNotInInventoryException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException, FilmNotInInventoryException, AllCopiesRentedOutException
	{	
		// TODO 
		// 
		// 1. Construct a VideoStore object.
		// 2. Simulate transactions as in the example given in Section 4 of the 
		//    the project description. 
		VideoStore myStore = new VideoStore("AmazonPrime.txt");
		int key = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("Transcations at a Video Store");
		System.out.print("Keys: 1 (rent)    2 (bulk rent)\n");
		System.out.print("      3 (return)  4 (bulk return)\n");
		System.out.print("      5 (summary) 6 (exit)\n");
		while(true) {
			System.out.print("Transactions: ");
			key = in.nextInt();
			if(key == 1) {
				System.out.println("Film to rent: ");
				String film = in.nextLine();
				String movie = myStore.parseFilmName(film);
				int copies = myStore.parseNumCopies(film);
				myStore.videoRent(movie, copies);
				
			}
			else if(key == 2) {
				System.out.println("Video file (rent): ");
				String fileName = in.next();
				myStore.bulkRent(fileName);
				if(myStore.order == 1) {
					throw new FileNotFoundException();
				}
				else if(myStore.order == 2) {
					throw new IllegalArgumentException();
				}
				else if(myStore.order == 3) {
					throw new FilmNotInInventoryException();
				}
				System.out.println(myStore.message);
			}
			else if(key == 3) {
				System.out.println("Film to return: ");
				String toReturn = in.nextLine();
				String movie = myStore.parseFilmName(toReturn);
				int copies = myStore.parseNumCopies(toReturn);
				myStore.videoReturn(movie, copies);
			}
			else if(key == 4) {
				System.out.println("Video file (return): ");
				String fileName = in.next();
				myStore.bulkReturn(fileName);
				if(myStore.order == 1) {
					throw new FileNotFoundException();
				}
				else if(myStore.order == 2) {
					throw new IllegalArgumentException();
				}
				else if(myStore.order == 3) {
					throw new FilmNotInInventoryException();
				}
				else if(myStore.order == 4) {
					throw new AllCopiesRentedOutException();
				}
				System.out.println(myStore.message);
				
			}
			else if(key == 5) {
				System.out.println(myStore.transactionsSummary());
			}
			else {
				System.exit(key);
			}
		}
	}
}
