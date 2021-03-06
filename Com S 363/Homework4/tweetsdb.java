package coms363;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * 
 * @author Chimzim Ogbondah
 *
 */
public class tweetsdb {
	/**
	 * This Method creates a new JPanel that prompts the user for information to create a new tweet
	 * @return returns the users input to create a new tweet
	 */
	public static String[] newTweetDialog() {
		String newTweetResults[] = new String[7];
		JPanel newTweetPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		JLabel tid = new JLabel("Tweet ID:");
		JLabel postDate = new JLabel("Tweet post date:");
		JLabel postMonth = new JLabel("Tweet post month (1-12):");
		JLabel postYear = new JLabel("Tweet post year (xxxx):");
		JLabel texts = new JLabel("Tweet text:");
		JLabel retweetCount = new JLabel("Tweet retweet count:");
		JLabel username = new JLabel("Tweet username:");
		
		JTextField tidField = new JTextField(20);
		JTextField dateField = new JTextField(20);
		JTextField monthField = new JTextField(20);
		JTextField yearField = new JTextField(20);
		JTextField textField = new JTextField(20);
		JTextField retweetField = new JTextField(20);
		JTextField usernameField = new JTextField(20);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		newTweetPanel.add(tid, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		newTweetPanel.add(tidField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		newTweetPanel.add(postDate, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		newTweetPanel.add(dateField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		newTweetPanel.add(postMonth, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		newTweetPanel.add(monthField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		newTweetPanel.add(postYear, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		newTweetPanel.add(yearField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		newTweetPanel.add(texts, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		newTweetPanel.add(textField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		newTweetPanel.add(retweetCount, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		newTweetPanel.add(retweetField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		newTweetPanel.add(username, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		newTweetPanel.add(usernameField, constraints);
		
		String[] options = new String[] { "OK", "Cancel" };
		int ioption = JOptionPane.showOptionDialog(null, newTweetPanel, "New Tweet Data", JOptionPane.OK_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		
		if (ioption == 0) // pressing OK button
		{
			newTweetResults[0] = tidField.getText();
			newTweetResults[1] = dateField.getText();
			newTweetResults[2] = monthField.getText();
			newTweetResults[3] = yearField.getText();
			newTweetResults[4] = textField.getText();
			newTweetResults[5] = retweetField.getText();
			newTweetResults[6] = usernameField.getText();
		}
		
		return newTweetResults;
	}
	/**
	 * This method creates a JPanel that prompts the user for information to get the Top 5 users who used a hashtag based on month, year and user state
	 * @return the users input for the desired call
	 */
	public static String[] newUserHashtagDialog() {
		String newHashtagResults[] = new String[4];
		JPanel newHashtagPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		JLabel hashtag = new JLabel("Hashtag:");
		JLabel postMonth = new JLabel("Tweet post month:");
		JLabel postYear = new JLabel("Tweet post year (xxxx):");
		JLabel postState = new JLabel("State:");
		
		JTextField hashtagField = new JTextField(20);
		JTextField monthField = new JTextField(20);
		JTextField yearField = new JTextField(20);
		JTextField stateField = new JTextField(20);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		newHashtagPanel.add(hashtag, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		newHashtagPanel.add(hashtagField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		newHashtagPanel.add(postMonth, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		newHashtagPanel.add(monthField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		newHashtagPanel.add(postYear, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		newHashtagPanel.add(yearField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		newHashtagPanel.add(postState, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		newHashtagPanel.add(stateField, constraints);
		
		
		String[] options = new String[] { "OK", "Cancel" };
		int ioption = JOptionPane.showOptionDialog(null, newHashtagPanel, "New Hashtag Data", JOptionPane.OK_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		
		if (ioption == 0) // pressing OK button
		{
			newHashtagResults[0] = hashtagField.getText();
			newHashtagResults[1] = monthField.getText();
			newHashtagResults[2] = yearField.getText();
			newHashtagResults[3] = stateField.getText();
		}
		
		return newHashtagResults;
	}
	
	public static String[] loginDialog() {
		// asking for a username and password to access the database.
		
		String loginResults[] = new String[2];
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		cs.fill = GridBagConstraints.HORIZONTAL;

		JLabel lbUsername = new JLabel("Database Username: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(lbUsername, cs);

		JTextField textFieldUsername = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(textFieldUsername, cs);

		JLabel lbPassword = new JLabel("Database Password: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(lbPassword, cs);

		JPasswordField passfieldPassword = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(passfieldPassword, cs);
		panel.setBorder(new LineBorder(Color.ORANGE));

		String[] options = new String[] { "OK", "Cancel" };
		int ioption = JOptionPane.showOptionDialog(null, panel, "Login", JOptionPane.OK_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		
		// store the database username in the first slot.
		// store the database password in the second slot.
		
		if (ioption == 0) // pressing OK button
		{
			loginResults[0] = textFieldUsername.getText();
			loginResults[1] = new String(passfieldPassword.getPassword());
		}
		return loginResults;
	}
	
	
	/**
	 * Transaction example that adds a new tweet into the tweet table
	 * @param conn - connection to the database
	 * @param tid - ID of the tweet
	 * @param post_day - the date of the tweet
	 * @param post_month - the month of the tweet
	 * @param post_year - the year of the tweet
	 * @param text - the text of the tweet
	 * @param retweetCt - the number of retweets for the tweet
	 * @param screen_name - username of the posting tweet
	 */
	private static void insertNewTweet(Connection conn, long tid, int post_day, int post_month, int post_year,
			String text, int retweetCt,String screen_name) {
		if (conn==null || screen_name == null) throw new NullPointerException();
		try {
			/* we want to make sure that all SQL statements for insertion 
			   of a new food are considered as one unit.
			   That is all SQL statements between the commit and previous commit 
			   get stored permanently in the DBMS or  all the SQL statements 
			   in the same transaction are rolled back.
			
			   By default, the isolation level is TRANSACTION_REPEATABLE_READ
			   By default, each SQL statement is one transaction
			
			   conn.setAutoCommit(false) is to 
			   specify what SQL statements are in the same transaction 
			   by a developer.
			   Several SQL statements can be put in one transaction.
			*/ 
			
			conn.setAutoCommit(false);
			// full protection against interference from other transaction
			// prevent dirty read
			// prevent unrepeatable reads
			// prevent phantom reads
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE );
			
			// once done, close the DBMS resources
			
			/* Example of a parameterized query, which is
			  that have ? in them where ? is 
			  replaced by a value obtained from a user
			*/
			PreparedStatement inststmt = conn.prepareStatement(
	                " insert into tweets (tid,post_day,post_month,post_year,texts,retweetCt,user_screen_name) values(?,?,?,?,?,?,?) ");
			
			// Set the first ?  to have the new tweet id
			inststmt.setLong(1, tid);
			// Set the second ? to have the new tweet day
			inststmt.setInt(2, post_day);
			// Set the third ? to have the new tweet month
			inststmt.setInt(3, post_month);
			// Set the fourth ? to have the new tweet year
			inststmt.setInt(4, post_year);
			// Set the fifth ? to have the new tweet text
			inststmt.setString(5, text);
			// Set the sixth ? to have the new tweet retweet count
			inststmt.setInt(6, retweetCt);
			// Set the seventh ? to have the food name
			inststmt.setString(7, screen_name);
			// tell DBMS to insert the tweet into the table
			
			int rowcount = inststmt.executeUpdate();
			// show how many rows are impacted, should be one row if 
			// successful
			// if not successful, SQLException occurs.
			System.out.println("Number of rows updated:" + rowcount);
			inststmt.close();
			
			// Tell DBMS to make sure all the changes you made from 
			// the prior commit is saved to the database
			conn.commit();
			
			// Reset the autocommit to commit per SQL statement
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	/**
	 * This method Returns the top 5 users in descending order based on the hashtag, tweet month tweet year and user state
	 * @param conn - Database connection 
	 * @param hashtag - the Hashtag used in the posted tweets
	 * @param postMonth - The month the tweet with the hashtag was posted
	 * @param postYear - The year the tweet with the hashtag was posted
	 * @param state - the state of the users who posted the tweet using the hashtag
	 */
private static void findUserPostingHashtag(Connection conn, String hashtag, int postMonth, int postYear, String state) {
	if(conn == null || hashtag == null || state == null) throw new NullPointerException();
	try {
		conn.setAutoCommit(false);
		// full protection against interference from other transaction
		// prevent dirty read
		// prevent unrepeatable reads
		// prevent phantom reads
		conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE );
		
		ResultSet rs;
		ResultSetMetaData rsMetaData;
		String toShow = "tweet_count | screen_name | category |\n";
		
		CallableStatement callstmt = conn.prepareCall(" {call findUserPostingHashtag(?,?,?,?)} ");
		
		//sets the first ? to be the defined hashtag
		callstmt.setString(1, hashtag);
		//sets the second ? to be the defined tweet month
		callstmt.setInt(2, postMonth);
		//sets the third ? to be the defined tweet year
		callstmt.setInt(3, postYear);
		//sets the fourth ? to be the defined user state
		callstmt.setString(4, state);
		
	    //callstmt.registerOutParameter(1, Types.INTEGER);
	    //callstmt.registerOutParameter(2, Types.VARCHAR);
	   // callstmt.registerOutParameter(3, Types.VARCHAR);
		
		
		rs = callstmt.executeQuery();
		rsMetaData = rs.getMetaData();
		
		// iterate through each item in the returned result
		while (rs.next()) {
			// concatenate the columns in each row
			for (int i = 0; i < rsMetaData.getColumnCount(); i++) {
				toShow += rs.getString(i + 1) + " | ";
			}
			toShow += "\n";
		}
		// show the dialog box with the returned result by DBMS
		JOptionPane.showMessageDialog(null, toShow);
		rs.close();
		
		callstmt.close();
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
}
	
	
private static void deleteUser(Connection conn, String username) {
		
		if (conn==null || username==null) throw new NullPointerException();
		try {
			/* we want to make sure that all SQL statements for insertion 
			   of a new food are considered as one unit.
			   That is all SQL statements between the commit and previous commit 
			   get stored permanently in the DBMS or  all the SQL statements 
			   in the same transaction are rolled back.
			
			   By default, the isolation level is TRANSACTION_REPEATABLE_READ
			   By default, each SQL statement is one transaction
			
			   conn.setAutoCommit(false) is to 
			   specify what SQL statements are in the same transaction 
			   by a developer.
			   Several SQL statements can be put in one transaction.
			*/ 
			
			conn.setAutoCommit(false);
			// full protection against interference from other transaction
			// prevent dirty read
			// prevent unrepeatable reads
			// prevent phantom reads
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE );
			
			
			// once done, close the DBMS resources
			
			/* Example of a parameterized query, which is
			  that have ? in them where ? is 
			  replaced by a value obtained from a user
			*/
			PreparedStatement inststmt1 = conn.prepareStatement(
	                " delete from mentions where screen_name=? ");
			
			// Set the first ?  to have the users screen_name to be deleted
			inststmt1.setString(1, username);
			// tell DBMS to delete the user from the table
			int rowcount = inststmt1.executeUpdate();
			inststmt1.close();
			
			PreparedStatement inststmt2 = conn.prepareStatement(
	                " delete from tweets where user_screen_name=? ");
			
			// Set the first ?  to have the users screen_name to be deleted
			inststmt2.setString(1, username);
			// tell DBMS to delete the user from the table
			 rowcount += inststmt2.executeUpdate();
			 inststmt2.close();
			 
			 PreparedStatement inststmt3 = conn.prepareStatement(
		                " delete from users where screen_name=? ");
				
			// Set the first ?  to have the users screen_name to be deleted
			inststmt3.setString(1, username);
			// tell DBMS to delete the user from the table
			rowcount += inststmt3.executeUpdate();
			
			// show how many rows are impacted, should be one row if 
			// successful
			// if not successful, SQLException occurs.
			System.out.println("Number of rows updated:" + rowcount);
			inststmt3.close();
			
			
			// Tell DBMS to make sure all the changes you made from 
			// the prior commit is saved to the database
			conn.commit();
			
			// Reset the autocommit to commit per SQL statement
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			System.out.println(e);
		}

	}
	
	
	/*-----------------------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	
	public static void main(String[] args) {
		// useSSL=false means plain text allowed
		//String dbServer = "jdbc:mysql://localhost:3306/fooddb?useSSL=false";
		// useSSL=true; data are encrypted when sending between DBMS and 
		// this program
		
		String dbServer = "jdbc:mysql://localhost:3306/project?useSSL=true";
		String userName = "";
		String password = "";

		String result[] = loginDialog();
		userName = result[0];
		password = result[1];

		Connection conn=null;
		Statement stmt=null;
		if (result[0]==null || result[1]==null) {
			System.out.println("Terminating: No username nor password is given");
			return;
		}
		try {
			// load JDBC driver
			// must be in the try-catch-block
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbServer, userName, password);
			
			stmt = conn.createStatement();
			String sqlQuery = "";
			String option = "";
			String instruction = "Enter a: Enter a new tweet." + "\n"
					+ "Enter b: Deleting a user based on the users screen name."
					+ "\n" + "Enter c: Top 5 users based on given hashtag." + "\n"
					+ "Enter e: Quit Program";

			while (true) {
				option = JOptionPane.showInputDialog(instruction);
				if (option.equals("a")) {
					String tweetResults[] = newTweetDialog();
					if(!tweetResults[0].equals("") && !tweetResults[6].equals("")) {
						if(Long.parseLong(tweetResults[0]) > 0) {
						insertNewTweet(conn, Long.parseLong(tweetResults[0]), Integer.parseInt(tweetResults[1]),Integer.parseInt(tweetResults[2]),Integer.parseInt(tweetResults[3]),
							tweetResults[4], Integer.parseInt(tweetResults[5]), tweetResults[6]);
						}
					}
				} else if (option.equals("b")) {
					String username = JOptionPane.showInputDialog("Enter user's screen_name to be deleted:");
					String answer = JOptionPane.showInputDialog("Warning: All tweets posted by: "+username+" will be deleted.\n"
							+"Enter 'y' to continue and 'n' to cancel");
					if(answer.equals("y") && !username.equals("")) {
						deleteUser(conn, username);
					}
				} else if (option.equals("c")) {
					String hashtagResults[] = newUserHashtagDialog();
					if(!hashtagResults[0].contentEquals("") && !hashtagResults[1].contentEquals("") && !hashtagResults[2].contentEquals("")
							&& !hashtagResults[3].contentEquals("")) {
						findUserPostingHashtag(conn, hashtagResults[0], Integer.parseInt(hashtagResults[1]),
								Integer.parseInt(hashtagResults[2]), hashtagResults[3]);
					}
				}
				else {
					break;
				}
			}
			// close the statement
			if (stmt != null) stmt.close();
			// close the connection
			if (conn != null) conn.close();
		} catch (Exception e) {
			
			System.out.println("Program terminates due to errors or user cancelation");
			e.printStackTrace(); // for debugging; 
		}
	}

}
