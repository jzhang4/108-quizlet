package administration;

import java.sql.*;
import java.util.*;


public class Achievements {
	private int numPossibleAchievements = 10;
	private static Connection achieveCon;

	
	public Achievements(Connection con){
		achieveCon = con;
	}
	
	public ArrayList<Integer> fetchAchievemnt(String userName){
		ArrayList<Integer> achievements = new ArrayList<Integer>();
		String query = "SELECT * FROM achievements WHERE userName = " + "\"" + userName + "\"" + ";";
		try{
			Statement temp = achieveCon.createStatement();
			ResultSet results = temp.executeQuery(query);
			while(results.next()){
				for (int i = 2; i <= numPossibleAchievements + 1; i++){
					achievements.add(results.getInt(i));
				}		
			}
		} catch (Exception ex){}
		return achievements;
	}
}
