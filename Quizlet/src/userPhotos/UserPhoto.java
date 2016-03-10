package userPhotos;

import java.sql.*;

public class UserPhoto {
	private static Connection con;
	public UserPhoto(Connection cn) {
		con = cn;
	}
	public Integer getPhotoName(String userName){
		int value = 0;
		String query = "SELECT * FROM users WHERE userName = " + "\"" + userName+ "\"" + ";";
		try {
			Statement action = con.createStatement();
			ResultSet result = action.executeQuery(query);
			result.next();
			value = result.getInt(5);	
		}catch (Exception ex){
			System.err.println("Error fetching the name of the photo");
			ex.printStackTrace();
		}
		return value;
	}
}
