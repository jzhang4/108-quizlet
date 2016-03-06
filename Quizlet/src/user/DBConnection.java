package user;
import java.sql.*;

public class DBConnection {
	private Connection con;
	Statement stmt;
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection( "jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD);
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
	
	public Statement getStatement() {
		return stmt;
	}
	
	public void closeConnection() {
//		try {
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
	
	
	
}