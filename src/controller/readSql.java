package controller;
//STEP 1. Import required packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Flight;
import model.FlightModel;

public class readSql {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/Flights";

	// Database credentials
	static final String SQLUSER = "root";
	static final String SQLPASS = "Pa$$word";

	//search the table and make sure the username or email does not already exist
	public boolean validateNewUser(String userName, String email) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT idBooking_Users, user_name, email FROM Booking_Users";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("idBooking_Users");
				String user_name = rs.getString("user_name");
				String read_email = rs.getString("email");

				if(userName.equals(user_name) || email.equals(read_email)){
					return false;
				}
				// Display values
				System.out.print("ID: " + id);
				System.out.print(", User name: " + user_name);
				System.out.println(", email: " + email);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
		return true;
	}// end main
	
	//returns the user's int id from the table if they exist, return 0 if they aren't available
	public int validateLogin(String userName, String password) {
		Connection conn = null;
		Statement stmt = null;
		int numToReturn=0;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT idBooking_Users, user_name, password FROM Booking_Users";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("idBooking_Users");
				String read_user_name = rs.getString("user_name");
				String read_password = rs.getString("password");

				if(userName.equals(read_user_name)){
					 if(password.equals(read_password)){
						 numToReturn=id;	//user exists and password matches, id passed to work with other tables
					 }else{
						 numToReturn=-1;	//user password incorrect but username exists
					 }
				}
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
		return numToReturn;
	}// end main
	
	public String getFirstName(int id) {
		Connection conn = null;
		Statement stmt = null;
		String stringToReturn = "n/a";
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT idBooking_Users, first_name FROM Booking_Users";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int read_id = rs.getInt("idBooking_Users");
				String read_first_name = rs.getString("first_name");
				if(read_id==id){
					stringToReturn = read_first_name;
				}
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println(stringToReturn);
		return stringToReturn;
	}// end main
	
	//read all flights from the All_Flights table and convert it all into a string array to eventually display on a panel
	public String[] getAllFlightsAsStringArray() {
		Connection conn = null;
		Statement stmt = null;
		String[] array=null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT idAll_Flights, Leaving_From, Going_To, Departure_Date FROM All_Flights";
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList<String> temp = new ArrayList<String>();
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("idAll_Flights");
				String leaving_from = rs.getString("Leaving_From");
				String going_to = rs.getString("Going_To");
				String departure_date = rs.getString("Departure_Date");
				String flightStr = "Departing From: ("+leaving_from + ") " + "Landing In: ("+going_to + ") " + "Leaves on: ("+departure_date+")";
				temp.add(flightStr);
			}
			array = temp.toArray(new String[temp.size()]);
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
		return array;
	}// end main
	
	//the string size is the number of seats on the flight, x if taken and o if open
	public Flight getSeatInfoFromCorrectFlight(String s, FlightModel fm){
		//parse the selected flight's destinations and date, compare to the model's arraylist of flights
		for(int i=0; i<fm.getFlightsList().size();i++){
			if(s.contains(fm.getAFlight(i).getDestinationTo()) && 
					s.contains(fm.getAFlight(i).getDestinationFrom()) &&
					s.contains(fm.getAFlight(i).getDepartureDate())){
				return fm.getAFlight(i);
			}
		}
		
		return null;
	}
}