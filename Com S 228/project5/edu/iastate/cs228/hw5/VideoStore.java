package edu.iastate.cs228.hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

 

/**
 * 
 * @author Chimzim Ogbondah
 *
 */

public class VideoStore 
{
	protected SplayTree<Video> inventory;     // all the videos at the store
	String message; // help with printing out the error handling in the main method
	int order = 0; // help with the excption to throw
	
	// ------------
	// Constructors 
	// ------------
	
    /**
     * Default constructor sets inventory to an empty tree. 
     */
    public VideoStore()
    {
    	// no need to implement. 
    }
    
    
	/**
	 * Constructor accepts a video file to create its inventory.  Refer to Section 3.2 of  
	 * the project description for details regarding the format of a video file. 
	 * 
	 * Calls setUpInventory(). 
	 * 
	 * @param videoFile  no format checking on the file
	 * @throws FileNotFoundException
	 */
    public VideoStore(String videoFile) throws FileNotFoundException  
    {
    	inventory = new SplayTree<Video>();
    	setUpInventory(videoFile);
    }
    
    
   /**
     * Accepts a video file to initialize the splay tree inventory.  To be efficient, 
     * add videos to the inventory by calling the addBST() method, which does not splay. 
     * 
     * Refer to Section 3.2 for the format of video file. 
     * 
     * @param  videoFile  correctly formated if exists
     * @throws FileNotFoundException 
     */
    public void setUpInventory(String videoFile) throws FileNotFoundException
    {
    	File f = new File(videoFile);
    	if(!f.exists()) {
    		throw new FileNotFoundException();
    	}
    	Scanner in = new Scanner(f);
    	while(in.hasNextLine()) {
    		String eachLine = in.nextLine();
    		String movieTitle = parseFilmName(eachLine);
    		int copies = parseNumCopies(eachLine);
    		Video temp = new Video(movieTitle, copies);
    		//checks to see if the Movie is already in the store
    		if(findVideo(temp.getFilm()).compareTo(temp) == 0) {
    			inventory.add(temp);
    		}
    		else {
    			inventory.addBST(temp);
    		}
    	}
    	in.close();
    }
	
    
    // ------------------
    // Inventory Addition
    // ------------------
    
    /**
     * Find a Video object by film title. 
     * 
     * @param film
     * @return
     */
	public Video findVideo(String film) 
	{
		Video tofind = new Video(film);
		tofind = inventory.findEntry(tofind).data;
		return tofind; 
	}


	/**
	 * Updates the splay tree inventory by adding a number of video copies of the film.  
	 * (Splaying is justified as new videos are more likely to be rented.) 
	 * 
	 * Calls the add() method of SplayTree to add the video object.  
	 * 
	 *     a) If true is returned, the film was not on the inventory before, and has been added.  
	 *     b) If false is returned, the film is already on the inventory. 
	 *     
	 * The root of the splay tree must store the corresponding Video object for the film. Update 
	 * the number of copies for the film.  
	 * 
	 * @param film  title of the film
	 * @param n     number of video copies 
	 */
	public void addVideo(String film, int n)  
	{
		Video temp = new Video(film, n);
		inventory.add(temp);
		// TODO 
	}
	

	/**
	 * Add one video copy of the film. 
	 * 
	 * @param film  title of the film
	 */
	public void addVideo(String film)
	{
		Video temp = new Video(film);
		inventory.add(temp);
	}
	

	/**
     * Update the splay trees inventory by adding videos.  Perform binary search additions by 
     * calling addBST() without splaying. 
     * 
     * The videoFile format is given in Section 3.2 of the project description. 
     * 
     * @param videoFile  correctly formated if exists 
     * @throws FileNotFoundException
     */
    public void bulkImport(String videoFile) throws FileNotFoundException 
    {
    	 setUpInventory(videoFile);
    }

    
    // ----------------------------
    // Video Query, Rental & Return 
    // ----------------------------
    
	/**
	 * Search the splay tree inventory to determine if a video is available. 
	 * 
	 * @param  film
	 * @return true if available
	 */
	public boolean available(String film)
	{
		Video temp = new Video(film);
		return inventory.contains(temp); 
	}

	
	
	/**
     * Update inventory. 
     * 
     * Search if the film is in inventory by calling findElement(new Video(film, 1)). 
     * 
     * If the film is not in inventory, prints the message "Film <film> is not 
     * in inventory", where <film> shall be replaced with the string that is the value 
     * of the parameter film.  If the film is in inventory with no copy left, prints
     * the message "Film <film> has been rented out".
     * 
     * If there is at least one available copy but n is greater than the number of 
     * such copies, rent all available copies. In this case, no AllCopiesRentedOutException
     * is thrown.  
     * 
     * @param film   
     * @param n 
     * @throws IllegalArgumentException      if n <= 0 or film == null or film.isEmpty()
	 * @throws FilmNotInInventoryException   if film is not in the inventory
	 * @throws AllCopiesRentedOutException   if there is zero available copy for the film.
	 */
	public void videoRent(String film, int n) throws IllegalArgumentException, FilmNotInInventoryException,  
									     			 AllCopiesRentedOutException 
	{
		Video temp = new Video(film, n);
		if(inventory.findElement(temp) == null) {
			System.out.println("Film "+film+" is not in inventory");
			throw new FilmNotInInventoryException();
		}
		
		if(n <= 0 ) {
			throw new IllegalArgumentException();
		}
		
		if(inventory.findElement(temp).compareTo(temp) == 0) {
			if(inventory.findElement(temp).getNumAvailableCopies() >= 1) {
				inventory.findElement(temp).rentCopies(Math.min(n,inventory.findElement(temp).getNumAvailableCopies()));
			}
			else {
				System.out.println("Film "+film+" has been rented out");
				throw new AllCopiesRentedOutException();
			}
		}
	}

	
	/**
	 * Update inventory.
	 * 
	 *    1. Calls videoRent() repeatedly for every video listed in the file.  
	 *    2. For each requested video, do the following: 
	 *       a) If it is not in inventory or is rented out, an exception will be 
	 *          thrown from videoRent().  Based on the exception, prints out the following 
	 *          message: "Film <film> is not in inventory" or "Film <film> 
	 *          has been rented out." In the message, <film> shall be replaced with 
	 *          the name of the video. 
	 *       b) Otherwise, update the video record in the inventory.
	 * 
	 * For details on handling of multiple exceptions and message printing, please read Section 3.4 
	 * of the project description. 
	 *       
	 * @param videoFile  correctly formatted if exists
	 * @throws FileNotFoundException
     * @throws IllegalArgumentException     if the number of copies of any film is <= 0
	 * @throws FilmNotInInventoryException  if any film from the videoFile is not in the inventory 
	 * @throws AllCopiesRentedOutException  if there is zero available copy for some film in videoFile
	 */
	public void bulkRent(String videoFile) throws FileNotFoundException, IllegalArgumentException, 
												  FilmNotInInventoryException, AllCopiesRentedOutException 
	{
		File f = new File(videoFile);
		if(!f.exists()) {
			order = 1;
    		throw new FileNotFoundException();
    	}
		Scanner in = new Scanner(f);
		message = "";
		order = 0;
		while(in.hasNextLine()) {
			String scanning = in.nextLine();
			String movieTitle = parseFilmName(scanning);
			int copies = parseNumCopies(scanning);
			try {
			videoRent(movieTitle, copies);
			}
			catch(IllegalArgumentException e) {
				if(order > 2) {
					order = 2;
				}      
				message += "Film "+movieTitle+" has an invaild request\n";
			}
			catch(FilmNotInInventoryException e) {
				if(order > 3) {
					order = 3;
				}
				message += "Film "+movieTitle+ " is not in the inventory\n";
			}
			catch(AllCopiesRentedOutException e) {
				if(order == 0) {
					order = 4;
				}
				message += "Film "+movieTitle+ " has be rented out\n";
			}
		}
		in.close();
	}

	
	/**
	 * Update inventory.
	 * 
	 * If n exceeds the number of rented video copies, accepts up to that number of rented copies
	 * while ignoring the extra copies. 
	 * 
	 * @param film
	 * @param n
	 * @throws IllegalArgumentException     if n <= 0 or film == null or film.isEmpty()
	 * @throws FilmNotInInventoryException  if film is not in the inventory
	 */
	public void videoReturn(String film, int n) throws IllegalArgumentException, FilmNotInInventoryException 
	{
		if(n <= 0 || film == null || film.isEmpty()) {
			throw new IllegalArgumentException();
		}
		Video temp = new Video(film, n);
		if(inventory.findElement(temp) == null){
			throw new FilmNotInInventoryException();
		}
		else {
			inventory.findElement(temp).returnCopies(Math.min(n, inventory.findElement(temp).getNumCopies()));
			}
		}
	

	
	
	/**
	 * Update inventory. 
	 * 
	 * Handles excessive returned copies of a film in the same way as videoReturn() does.  See Section 
	 * 3.4 of the project description on how to handle multiple exceptions. 
	 * 
	 * @param videoFile
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException    if the number of return copies of any film is <= 0
	 * @throws FilmNotInInventoryException if a film from videoFile is not in inventory
	 */
	public void bulkReturn(String videoFile) throws FileNotFoundException, IllegalArgumentException,
													FilmNotInInventoryException												
	{
		File f = new File(videoFile);
		if(!f.exists()) {
			order = 1;
    		throw new FileNotFoundException();
    	}
		Scanner in = new Scanner(f);
		message = "";
		order = 0;
		while(in.hasNextLine()) {
			String scanning = in.nextLine();
			try {
				videoReturn(parseFilmName(scanning), parseNumCopies(scanning));
			}
			catch(IllegalArgumentException e) {
				if(order > 2) {
					order = 2;
				}
				message += "Film"+parseFilmName(scanning)+" has an invalid request\n";
			}
			catch(FilmNotInInventoryException e) {
				if(order == 0) {
					order = 3;
				}
				message += "Film "+parseFilmName(scanning)+ " is not in the inventory\n";
			}
			
		}
		in.close();
	}
		
	

	// ------------------------
	// Methods without Splaying
	// ------------------------
		
	/**
	 * Performs inorder traversal on the splay tree inventory to list all the videos by film 
	 * title, whether rented or not.  Below is a sample string if printed out: 
	 * 
	 * 
	 * Films in inventory: 
	 * 
	 * A Streetcar Named Desire (1) 
	 * Brokeback Mountain (1) 
	 * Forrest Gump (1)
	 * Psycho (1) 
	 * Singin' in the Rain (2)
	 * Slumdog Millionaire (5) 
	 * Taxi Driver (1) 
	 * The Godfather (1) 
	 * 
	 * 
	 * @return
	 */
	public String inventoryList()
	{
		Iterator<Video> list = inventory.iterator();
		String inventoryList = "Films in inventory: \n\n";
		while(list.hasNext()) {
			Video temp = list.next();
			inventoryList += temp.getFilm()+ " ("+temp.getNumCopies()+")\n";
		}
		return inventoryList; 
	}

	
	/**
	 * Calls rentedVideosList() and unrentedVideosList() sequentially.  For the string format, 
	 * see Transaction 5 in the sample simulation in Section 4 of the project description. 
	 *   
	 * @return 
	 */
	public String transactionsSummary()
	{
		String toReturn = rentedVideosList()+ unrentedVideosList();
		return toReturn; 
	}	
	
	/**
	 * Performs inorder traversal on the splay tree inventory.  Use a splay tree iterator.
	 * 
	 * Below is a sample return string when printed out:
	 * 
	 * Rented films: 
	 * 
	 * Brokeback Mountain (1)
	 * Forrest Gump (1) 
	 * Singin' in the Rain (2)
	 * The Godfather (1)
	 * 
	 * 
	 * @return
	 */
	private String rentedVideosList()
	{
		String rented ="Rented films: \n\n";
		Iterator<Video> temp = inventory.iterator();
		while(temp.hasNext()) {
			Video scan = temp.next();
			if(scan.getNumRentedCopies() > 0) {
				rented += scan.getFilm()+ " ("+ scan.getNumRentedCopies()+")\n";
			}
		}
		return rented; 
	}

	
	/**
	 * Performs inorder traversal on the splay tree inventory.  Use a splay tree iterator.
	 * Prints only the films that have unrented copies. 
	 * 
	 * Below is a sample return string when printed out:
	 * 
	 * 
	 * Films remaining in inventory:
	 * 
	 * A Streetcar Named Desire (1) 
	 * Forrest Gump (1)
	 * Psycho (1) 
	 * Slumdog Millionaire (4) 
	 * Taxi Driver (1) 
	 * 
	 * 
	 * @return
	 */
	private String unrentedVideosList()
	{
		String leftInInventory = "Films remaining in inventory";
		Iterator<Video> temp = inventory.iterator();
		while(temp.hasNext()) {
			Video list = temp.next();
			if(list.getNumAvailableCopies() > 0) {
				leftInInventory += list.getFilm()+ " ("+list.getNumAvailableCopies()+")\n";
			}
		}
		return leftInInventory; 
	}	

	
	/**
	 * Parse the film name from an input line. 
	 * 
	 * @param line
	 * @return
	 */
	public static String parseFilmName(String line) 
	{
		String myString = "";
		Scanner in = new Scanner(line);
		while(in.hasNext()) {
			String temp = in.next();
			if(temp == "(") {
				myString = myString.substring(0, myString.length()-1);
				break;
			}
			else {
				myString += temp+ " ";
			}
		}
		in.close();
		return myString; 
	}
	
	
	/**
	 * Parse the number of copies from an input line. 
	 * 
	 * @param line
	 * @return
	 */
	public static int parseNumCopies(String line) 
	{
		int myNumber = 1;
		Scanner in = new Scanner(line);
		while(in.hasNext()) {
			String temp = in.next();
			if(temp == "(") {
				myNumber = in.nextInt();
				if(myNumber <= 0) {
					myNumber = 0;
				}
			}
		}
		in.close();
		return myNumber; 
	}
	
}
