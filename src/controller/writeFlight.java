package controller;
import java.sql.*;

public class writeFlight {

	// Database credentials
	static final String SQLUSER = "root";
	static final String SQLPASS = "Pa$$word";

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/Flights";

	public writeFlight(String from, String to, String date) {
		
		try {
			// create a mysql database connection
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			// the mysql insert statement
			String query = 
			" INSERT INTO All_Flights (idAll_Flights, Leaving_From, Going_To, Departure_Date)"
			+ " values (?, ?, ?, ?)";

			// create the mysql insert prepared statement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, null);
			preparedStmt.setString(2, from);
			preparedStmt.setString(3, to);
			preparedStmt.setString(4, date);

			// execute the prepared statement
			preparedStmt.execute();

			conn.close();
		} catch (Exception e) {
			System.err.println("I can't write to the data base!");
			System.err.println(e.getMessage());
		}
	}
}