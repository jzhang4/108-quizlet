package administration;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import quiz.ScoreBoard;
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
		nameOfMonths.add("Sunday");
		nameOfMonths.add("Monday");
		nameOfMonths.add("Tuesday");
		nameOfMonths.add("Wednesday");
		nameOfMonths.add("Thursday");
		nameOfMonths.add("Friday");
		nameOfMonths.add("Saturday");
		
		String query = "Select * from siteVisits";
		try {
			Statement temp = con.createStatement();
			ResultSet results = temp.executeQuery(query);
			extractSearchResults(results);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	// extracting the search results and storing them in a table
		public void extractSearchResults(ResultSet results){
			if (results != null){
				try{
					while (results.next()){
						PageInfo temp = new PageInfo(results.getString(1),results.getInt(2),results.getInt(3),results.getInt(4),results.getInt(5),results.getInt(6),results.getInt(7),results.getInt(8));
						pageStats.add(temp);
					}
				} catch (Exception ex){}
			}
		}
	public ArrayList<PageInfo> getPages(){
		return pageStats;
	}
	private void tryToClear(){
		String query = "SELECT * FROM siteVisits WHERE pageName = \"Home Page\";";
		int numLastLogged = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery(query);
			results.next();
			numLastLogged = results.getInt(8);
		} catch (Exception ex){}
		if (numLastLogged != 0){
			for (int i = 2; i < 8; i++ ){
				String updateQuery = "UPDATE siteVisits SET day" + Integer.toString(i) + "=" + "\"0\";";
				try {
					Statement stmt = con.createStatement();
					stmt.execute(updateQuery);		
				} catch (Exception ex){
					ex.printStackTrace();	
				}
			}
		}
	}
	public void incrementCell(String pageName, int dayOfWeek){
		String query = "SELECT * FROM siteVisits WHERE pageName = \"" + pageName + "\";";
		int currentNumber = 0;
		try {
			Statement stmt = con.createStatement();			// statement created and result set fetched
			ResultSet results = stmt.executeQuery(query);	// allowing us to execute the query  and pull out
			results.next();									// the visit value stored in the SQL table.
			currentNumber = results.getInt(dayOfWeek + 1);
		} catch (Exception ex){
			ex.printStackTrace();
		}
		currentNumber += 1;	// incrementing the value
		try {
			String insertQuery = "UPDATE siteVisits SET day" + Integer.toString(dayOfWeek) + " = \"" + Integer.toString(currentNumber) + "\" WHERE pageName = \"" + pageName + "\";";
			Statement insertStmt = con.createStatement();
			insertStmt.execute(insertQuery);
			if (dayOfWeek == 1){		// we always try to clear on a Sunday!
				tryToClear();
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	public void incrementCount(String page){
		Calendar c = Calendar.getInstance();			// Fetching the day of the week so that 
		c.setTimeInMillis(System.currentTimeMillis());	// we know which cell in the table we should
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);	// increment!
		if (page.equals("index.jsp")){
			incrementCell("Home Page",dayOfWeek);
		} else if (page.equals("TheTeam.jsp")){			// incrementing the counter on the about us page
			incrementCell("About Us",dayOfWeek);
		} else {
			incrementCell("Terms and Conditions",dayOfWeek);
		}
	}
	
	public int getNumUsers(){
		return countNumElements("users");
	}
	
	public int getNumQuizzes(){
		return countNumElements("quizzes");
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
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();		// We time stamp each announcement so we need to get the current date 
		String countQuery = "Select COUNT(*) FROM announcements;";
		int numPresent = 0;
		try{
			Statement temp = con.createStatement();
			ResultSet results = temp.executeQuery(countQuery);
			results.next();
			numPresent = results.getInt(1);
		}catch(Exception ex){}
		if (numPresent > 4){		// if we have more than 5 elements before we 
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
		//System.out.println(deleteQuery);
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
	public int removeQuiz(String quizName){
		int origNumElements = countNumElements("quizzes");
		//System.out.println(origNumElements);
		String deleteQuery = "DELETE FROM quizzes WHERE name =" + "\"" + quizName + "\"" + ";";
		//System.out.println(deleteQuery);
		try {
			Statement deleteStmt = con.createStatement();
			deleteStmt.execute(deleteQuery);			
		} catch (Exception ex){}
		if (origNumElements != countNumElements("quizzes")){
			return 1;
		}
		return 0;
	}
	public int removeQuizHistory(String quizName){
		ScoreBoard sb = new ScoreBoard(); 
		byte[] sbytes = sb.boardToBytes();
		
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
	public ArrayList<Announcement> getAnnounce(){
		ArrayList<Announcement> notices = new ArrayList<Announcement>();
		String query = "SELECT * FROM announcements;";
		try{
			Statement temp = con.createStatement();
			ResultSet results = temp.executeQuery(query);
			while (results.next()){
				Announcement temp2 = new Announcement(results.getString(1),results.getString(2));
				notices.add(temp2);
			}
		} catch (Exception ex){}
		return notices;	
	}
	
	
	// This is just for testing to make sure that everything is working correctly!
	public static void main (String[] args){
		Administrator adminLink = new Administrator();
		adminLink.incrementCount("index.jsp");
	}
	
}
