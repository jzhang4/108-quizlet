package administration;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class Achievements {
	private static int  numPossibleAchievements = 7;
	private static Connection achieveCon;
	public Achievements(Connection con){
		achieveCon = con;
	}
	private int mapGoalToInt(String goal){
		if (goal.equals("Lonely Bed")){
			return (1);
		} else if (goal.equals("Amateur Author")){
			return (2);
		} else if (goal.equals("Prolific Author")){
			return (3);
		} else if (goal.equals("Prodiguous Author")){
			return (4);
		} else if (goal.equals("No Sunlight")){
			return (7);
		}
		return (-1);
	}
	private void setAchieved(int achieveNum, String userName){
		String one = "1";
		String columnName = "achieve" + Integer.toString(achieveNum);
		String query = "UPDATE achievements SET " + columnName + "="+ "\""  + one + "\"" + " WHERE userName = \"" + userName + "\"";
		System.out.println(query);
		try {
			Statement stmt = achieveCon.createStatement();
			stmt.execute(query);
		} catch (Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public void doUpdate(String NameOfAchievement, String userName){
		ArrayList<Integer> currentAchieve= new ArrayList<Integer>();
		currentAchieve = fetchAchievemnt(userName);
		if (NameOfAchievement.equals("Lonely Bed")){	// Number 1 in our list of achievements
			System.out.println("WORKING WITH THE LONELY BED ACHIEVEMENT");
			if (currentAchieve.get(mapGoalToInt("Lonely Bed") - 1) == 0 ){
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				String hours = ((String)sdf.format(calendar.getTime())).substring(0, 2);
				System.out.println("CHECKING FOR LATE LOGIN TIME");
				if (hours.equals("02") || hours.equals("03") || hours.equals("04")){	
					setAchieved(mapGoalToInt("Lonely Bed"),userName);
				}		
			}	
		} else if (NameOfAchievement.equals("Amateur Author")||NameOfAchievement.equals("Prolific Author") || NameOfAchievement.equals("Prodiguous Author")){
			System.out.println("WORKING WITH THE QUIZ CREATION Achievements");
			if (currentAchieve.get(mapGoalToInt("Amateur Author") - 1) == 0 || currentAchieve.get(mapGoalToInt("Prolific Author") - 1) == 0 || currentAchieve.get(mapGoalToInt("Prodigous Author") - 1) == 0){
				String query = "COUNT * FROM quizzes WHERE username= \"" + userName + "\";";
				int numQuizzes = 0;
				try{
					Statement temp = achieveCon.createStatement();
					ResultSet results = temp.executeQuery(query);
					results.next();
					numQuizzes = results.getInt(1);
				} catch(Exception ex){}
				if (numQuizzes > 0 && numQuizzes < 5){
					if (currentAchieve.get(mapGoalToInt("Amateur Author") - 1) == 0) setAchieved(mapGoalToInt("Amateur Author"),userName);		
				} else if (numQuizzes > 0 && numQuizzes <= 5){
					if (currentAchieve.get(mapGoalToInt("Amateur Author") - 1) == 0) setAchieved(mapGoalToInt("Amateur Author"),userName);
					if (currentAchieve.get(mapGoalToInt("Prolific Author") - 1) == 0) setAchieved(mapGoalToInt("Prolific Author"),userName);
				} else if (numQuizzes > 0){
					if (currentAchieve.get(mapGoalToInt("Amateur Author") - 1) == 0) setAchieved(mapGoalToInt("Amateur Author"),userName);
					if (currentAchieve.get(mapGoalToInt("Prolific Author") - 1) == 0) setAchieved(mapGoalToInt("Prolific Author"),userName);
					if (currentAchieve.get(mapGoalToInt("Prodiguous Author") - 1) == 0) setAchieved(mapGoalToInt("Prodiguous Author"),userName);	
				}
			}
		} else if (NameOfAchievement.equals("No Sunlight")){
			System.out.println("WORKING WITH NO SUNLIGHT ACHIEVEMENT");
			if(currentAchieve.get(mapGoalToInt("No Sunlight") - 1) == 0){
				setAchieved(mapGoalToInt("No Sunlight"),userName);
			}
		}
		
	}
	public static ArrayList<Integer> fetchAchievemnt(String userName){
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
	public static void main(String[]args){
		 
	}
}
