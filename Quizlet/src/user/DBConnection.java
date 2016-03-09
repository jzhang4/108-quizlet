package user;
import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
	private Connection con;
	private PreparedStatement pstmt; 
	Statement stmt;
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD);
			stmt = con.createStatement();
			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public PreparedStatement getPreparedStatement() {
		try {
			pstmt = con.prepareStatement("INSERT INTO quizzes VALUES (?, ?, ?, ?, ?, ?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pstmt;
	}
	
	public Statement getStatement() {
		return stmt;
	}
	
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}