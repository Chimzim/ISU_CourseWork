package coms363;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
/*
 * Author: ComS 363 Teaching Staff
 * Examples of static queries, parameterized queries, and 
 * transactions
 * You can use this example to build your queries upon
 * 
 */
public class JDBCTransactionTester {
	public static String[] loginDialog() {
		// asking for a username and password to access the database.
		
		String result[] = new String[2];
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		cs.fill = GridBagConstraints.HORIZONTAL;

		JLabel lbUsername = new JLabel("Username: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(lbUsername, cs);

		JTextField tfUsername = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(tfUsername, cs);

		JLabel lbPassword = new JLabel("Password: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(lbPassword, cs);

		JPasswordField pfPassword = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(pfPassword, cs);
		panel.setBorder(new LineBorder(Color.GRAY));

		String[] options = new String[] { "OK", "Cancel" };
		int ioption = JOptionPane.showOptionDialog(null, panel, "Login", JOptionPane.OK_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		
		// store the username in the first slot.
		// store the password in the second slot.
		
		if (ioption == 0) // pressing OK button
		{
			result[0] = tfUsername.getText();
			result[1] = new String(pfPassword.getPassword());
		}
		return result;
	}

	/**
	 * @param stmt
	 * @param sqlQuery
	 * @throws SQLException
	 */
	// run a static SQL query
	private static void runQuery(Statement stmt, String sqlQuery) throws SQLException {
		// ResultSet is used to store the data returned by DBMS when issuing a static query
		ResultSet rs;
		
		// ResultSetMetaData is used to find meta data about the data returned
		ResultSetMetaData rsMetaData;
		String toShow;
		
		// Send the SQL query to the DBMS
		rs = stmt.executeQuery(sqlQuery);
		
		// get information about the returned result.
		rsMetaData = rs.getMetaData();
		System.out.println(sqlQuery);
		toShow = "";
		
		// iterate through each item in the returned result
		while (rs.next()) {
			// concatenate the columns in each row
			for (int i = 0; i < rsMetaData.getColumnCount(); i++) {
			
				toShow += rs.getString(i + 1) + ", ";
			}
			toShow += "\n";
		}
		// show the dialog box with the returned result by DBMS
		JOptionPane.showMessageDialog(null, toShow);
		rs.close();
	}
	
	/**
	 * Show an example of a transaction
	 * @param conn Valid database connection
	 * 		  fname: Name of a food to check
	 */
	private static void insertFood(Connection conn, String fname) {
		
		if (conn==null || fname==null) throw new NullPointerException();
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
			
			Statement stmt = conn.createStatement();
			ResultSet rs;
			int id=0;
			
			// get the maximum id from the food table
			rs = stmt.executeQuery("select max(fid) from food");
			while (rs.next()) {
				// 1 indicates the position of the returned result we want to get
				id = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			// once done, close the DBMS resources
			
			/* Example of a parameterized query, which is
			  that have ? in them where ? is 
			  replaced by a value obtained from a user
			*/
			PreparedStatement inststmt = conn.prepareStatement(
	                " insert into food (fid,fname) values(?,?) ");
			
			// Set the first ?  to have the new food id
			inststmt.setInt(1, id+1);
			// Set the second ? to have the food name
			inststmt.setString(2, fname);
			
			// tell DBMS to insert the food into the table
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
			
		} catch (SQLException e) {}

	}
	
	/* this example shows how to use a parameterized SQL query
	 @param conn: Valid connection to a dbms
	        iname: the name of the ingredient to check
	*/
	
	private static void checkIngredient(Connection conn, String iname) {
		
		if (conn==null || iname==null) throw new NullPointerException();
		try {
			
			ResultSet rs =null;
			String toShow ="";
			
			/* Another example of a parameterized query
			   Notice the use of PreparedStatement instead of Statement 
			   used in a static query.
			 * 
			 */
			PreparedStatement lstmt = conn.prepareStatement(
	           "select count(*) from ingredient where iname= ?");
			
			// clear previous parameter values
			lstmt.clearParameters();
			
			// Replace the first question mark with the value of iname
			lstmt.setString(1, iname);
			
			// execute the query
			rs=lstmt.executeQuery();
			
			// advance the cursor to the first record
			rs.next();
			int count = rs.getInt(1);
			
			
			System.out.println("count=" + count);
			
			if (count > 0) {
				toShow = "The ingredient " + iname + " exists";
			}
			else toShow = "The ingredient " + iname + " does not exist";
			
			JOptionPane.showMessageDialog(null, toShow);
			lstmt.close();
			rs.close();
			
		} catch (SQLException e) {}

	}

	public static void main(String[] args) {
		// useSSL=false means plain text allowed
		//String dbServer = "jdbc:mysql://localhost:3306/fooddb?useSSL=false";
		// useSSL=true; data are encrypted when sending between DBMS and 
		// this program
		
		String dbServer = "jdbc:mysql://localhost:3306/fooddb?useSSL=true";
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
			String instruction = "Enter 1: Find all food with chicken as ingredient." + "\n"
					+ "Enter 2: For each food, list food name, total number of ingredients, and total amount of ingredients (gram)."
					+ "\n" + "Enter 3: Find all food without green onion as ingredient." + "\n"
					+ "Enter 4: Find all ingredients and amount of each ingredient of BBQ Chicken" + "\n"
					+ "Enter 5: Enter new food" + "\n"
					+ "Enter 6: Check whether an ingredient exists" + "\n"
					+ "Enter 7: Quit Program";

			while (true) {
				option = JOptionPane.showInputDialog(instruction);
				if (option.equals("1")) {
					sqlQuery = "select distinct f.fname from food f inner join recipe r on r.fid = f.fid inner join ingredient i on i.iid = r.iid where i.iname = 'Chicken'";
					runQuery(stmt, sqlQuery);
				} else if (option.equals("2")) {
					sqlQuery = "select f.fname, count(r.iid), sum(r.amount) from food f inner join recipe r on r.fid = f.fid inner join ingredient i on i.iid = r.iid group by f.fname";
					runQuery(stmt, sqlQuery);
				} else if (option.equals("3")) {
					sqlQuery = "select f.fname from food f where f.fid not in (select r.fid from recipe r inner join ingredient i on i.iid = r.iid where i.iname = 'Green Onion');";
					runQuery(stmt, sqlQuery);
				} else if (option.equals("4")) {
					sqlQuery = "select i.iname, r.amount from food f inner join recipe r on r.fid = f.fid inner join ingredient i on i.iid = r.iid where f.fname = 'BBQ Chicken'";
					runQuery(stmt, sqlQuery);
				} else if (option.equals("5")) {
					String fname=JOptionPane.showInputDialog("Enter foodname:");
					insertFood(conn, fname);
				} else if (option.equals("6")) {
					String iname=JOptionPane.showInputDialog("Enter exact name of the ingredient to check:");
					checkIngredient(conn, iname);
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
