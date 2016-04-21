package controller;
import java.sql.*;

public class writeUser {

	// Database credentials
	static final String SQLUSER = "root";
	static final String SQLPASS = "Pa$$word";

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/Flights";

	public writeUser(String userName, String password, String email, String first_name, String last_name, int age) {
		
		try {
			// create a mysql database connection
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			// the mysql insert statement
			String query = 
			" INSERT INTO Booking_Users (idBooking_Users, user_name, password, email, first_name, last_name, age)"
			+ " values (?, ?, ?, ?, ?, ?, ?)";

			// create the mysql insert prepared statement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, null);
			preparedStmt.setString(2, userName);
			preparedStmt.setString(3, password);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, first_name);
			preparedStmt.setString(6, last_name);
			preparedStmt.setInt(7, age);

			// execute the prepared statement
			preparedStmt.execute();

			conn.close();
		} catch (Exception e) {
			System.err.println("I can't write to the data base!");
			System.err.println(e.getMessage());
		}
	}
}