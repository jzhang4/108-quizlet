package administration;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class Administrator {
	static String account = MyDBInfo.MYSQL_USERNAME;	// Variables taken from the MyDBInfo 
	static String password = MyDBInfo.MYSQL_PASSWORD;	// storage class.
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	private static Connection con;		// This is our connection to the SQL server
	private ArrayList<PageInfo> pageStats; 
	private ArrayList<String> nameOfMonths;
	
	
	public Connection passConnection(){
		return con;
	}
	
	
	
	public Administrator(){
		pageStats = new ArrayList<PageInfo>();
		nameOfMonths = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + server, account ,password);
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
		} catch (ClassNotFoundException | SQLException ex){
			System.out.println("Error opening connection with or using SQL Database");
			ex.printStackTrace();
		}
	}
	// Getting the site visit frequency, we must first make a SQL query!
	public void getVisitFreq(){
		pageStats.clear();
		nameOfMonths.clear();
		nameOfMonths.add("September");
		nameOfMonths.add("October");
		nameOfMonths.add("Novemeber");
		nameOfMonths.add("December");
		nameOfMonths.add("January");
		
		String query = "Select * from siteVisits";
		try {
			Statement temp = con.createStatement();
			ResultSet results = temp.executeQuery(query);
			extractSearchResults(results);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	public int getNumUsers(){
		return countNumElements("users");
	}
	public int getNumQuizzes(){
		return countNumElements("quizzes");
	}
	// extracting the search results and storing them in a table
	public void extractSearchResults(ResultSet results){
		if (results != null){
			try{
				while (results.next()){
					PageInfo temp = new PageInfo(results.getString(1),results.getInt(2),results.getInt(3),results.getInt(4),results.getInt(5),results.getInt(6));
					pageStats.add(temp);
				}
			} catch (Exception ex){}
		}
	}
	public int makeAdminUpdate(String userName){
		String query = "Select COUNT(*) FROM users WHERE userName= \""+ userName + "\"";
		int numPresent = 0;
		try{
			Statement temp = con.createStatement();
			ResultSet results = temp.executeQuery(query);
			results.next();
			numPresent = results.getInt(1);
		}catch(Exception ex){}
		if (numPresent == 1){		// Update the entry if our count is one
			String updateQuery = "UPDATE users SET admin= 1 WHERE userName = \""+userName + "\"";
			try {
				Statement temp = con.createStatement();
				temp.execute(updateQuery);
				return 1;
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return 0;
	}
	public int logAnnounce(String text){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();		// We time stamp each announcement so we need to get the current date 
		String countQuery = "Select COUNT(*) FROM announcements;";
		int numPresent = 0;
		try{
			Statement temp = con.createStatement();
			ResultSet results = temp.executeQuery(countQuery);
			results.next();
			numPresent = results.getInt(1);
		}catch(Exception ex){}
		if (numPresent > 5){		// if we have more than 5 elements before we 
			try {					// add then clear everything!
			Statement deleteStmt = con.createStatement();
			deleteStmt.execute("DELETE FROM announcements;");
			} catch (Exception ex){}
		}							// At this point we are now inserting into the table
		String insertQuery = "INSERT INTO announcements VALUES(\""+text +"\""+ "," + "\"" + dateFormat.format(date) + "\""+");";
		try {
			Statement temp = con.createStatement();
			temp.execute(insertQuery);	// Execute the insert query and make sure everything is okay
			return 1;					// return 1 for successful insertion into the table
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return 0;						// return 0 if insertion failed at any point in time
	}
	public int removeUser(String userName){
		int origNumElements = countNumElements("users");
		String deleteQuery = "DELETE FROM users WHERE userName=" + "\"" + userName + "\"" + ";";
		String deleteQuery2 ="DELETE FROM achievements WHERE userName=" + "\"" + userName + "\"" + ";";
		System.out.println(deleteQuery);
		try{
			Statement deleteStmt = con.createStatement();
			deleteStmt.execute(deleteQuery);
			deleteStmt.execute(deleteQuery2);
		} catch (Exception ex){
			System.err.println("NOT WORKING");
			ex.printStackTrace();
		}
		if (origNumElements != countNumElements("users")){
			return 1;
		}
		return 0;
	}
	public ArrayList<String> getNameOfMonths(){
		return nameOfMonths;
	}
	
	// Counts the number of elements in a MYSQL table by querying 
	// the table with 'SELECT COUNT(*) FROM'
	public int countNumElements(String table){
		String countQuery = "Select COUNT(*) FROM " + table +";";	// Query that we use on the SQL table
		int numPresent = 0;					// allows us to return 0 in the case that the query 
		try{								// fails
			Statement temp = con.createStatement();
			ResultSet results = temp.executeQuery(countQuery);
			results.next();
			numPresent = results.getInt(1);	// First row in the result set is the number that was
		}catch(Exception ex){}				// counted
		return numPresent;					// just return this number to the user. 
	}
	
	
	// This code closes our connection the SQL database
	public void closeConnection(){
		try {
			con.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	public ArrayList<PageInfo> getPages(){
		return pageStats;
	}
	
	// This is just for testing to make sure that everything is working correctly!
	public static void main (String[] args){
		Administrator newStats = new Administrator();
		//newStats.getVisitFreq();
		//newStats.closeConnection();  // Don't forget to close the connection
		System.out.println(newStats.getNumUsers());
		newStats.makeAdminUpdate("jessicaZhang");
	}
	
}
