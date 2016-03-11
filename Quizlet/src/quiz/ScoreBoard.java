package quiz;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ScoreBoard {
	private static final int MINUTES_PASSED = 15; 
	
	
	ArrayList<Score> users; 
		
	public class Score implements Comparable<Score>{
		public String user; 
		public long score; 
		public long timescore; 
		public long timetaken; 
		
		public Score(String user, long score, long timescore, long timetaken) {
			this.user = user; 
			this.score = score; 
			this.timescore = timescore; 
			this.timetaken = timetaken; 
		}
		
		public String timeToString() {
			long millis = timetaken;
			long second = (millis / 1000) % 60;
			long minute = (millis / (1000 * 60)) % 60;
			long hour = (millis / (1000 * 60 * 60)) % 24;
				
			return String.format("%02d:%02d:%02d", hour, minute, second);
		}
		
		@Override
		public int compareTo(Score sc) {
			int dif = (int)(sc.score - this.score);
			if (dif == 0) {
				return (int)(this.timescore - sc.timescore);
			} else return dif; 
		}


		
	}
	
	public ScoreBoard() {
		this.users = new ArrayList<Score>(); 
	}
	
	public ArrayList<Score> getUsers() {
		return this.users; 
	}
	
	public ArrayList<Score> getTopPerformers() {
		ArrayList<Score> top = new ArrayList<Score>();
		Collections.sort(users);
		for (int i = 0; i < 3; i++) {
			if (i < users.size()) top.add(users.get(i));
		}
		return top;
	}
	public ArrayList<Score> getTopRecentPerformers() {
		ArrayList<Score> top = new ArrayList<Score>();
		Collections.sort(users);
		int index = 0;
		long curtime = System.currentTimeMillis();
		
		for (int i = 0; i < 3; i++) {
			while(index < users.size()) {
				long timepassed = curtime - users.get(index).timetaken;
				long minutes = (timepassed / (1000 * 60)) % 60;
				if (minutes < MINUTES_PASSED) {
					top.add(users.get(index));
					index++;
					break;
				}
				else index++;
			}
		}
		return top;
	}
	public ArrayList<Score> getRecentPerformers() {
		ArrayList<Score> recent = new ArrayList<Score>();
		Collections.sort(users);
		int index = 0;
		long curtime = System.currentTimeMillis();

		while(index < users.size()) {			
			long timepassed = curtime - users.get(index).timetaken;				
			long minutes = (timepassed / (1000 * 60)) % 60;
				
			if (minutes < MINUTES_PASSED) {				
				recent.add(users.get(index));										
			}			
			index++;	
		}
		return recent;
	}
	
	public ScoreBoard(Blob blob) {
		JSONArray jarr = null; 
		this.users = new ArrayList<Score>(); 
		try {
			byte[] bdata = blob.getBytes(1, (int)blob.length());
			String boardstr = new String(bdata);
			
			JSONParser parser = new JSONParser(); 
			Object obj = parser.parse(boardstr);
			jarr = (JSONArray)obj;
			
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Object obj: jarr) {
			JSONObject jobj = (JSONObject)obj; 
			String user = (String)jobj.get("user");
			long score = (long)jobj.get("score");
			long timescore = (long)jobj.get("timescore");
			long timetaken = (long)jobj.get("timetaken");
			Score sc = new Score(user, score, timescore, timetaken);
			this.users.add(sc);
		}
	}
	
	public void addScore(String user, int score, long timescore, long timetaken) {
		Score sc = new Score(user, score, timescore, timetaken);
		users.add(sc);
	}
	
	public byte[] boardToBytes() {
		JSONArray jarr = new JSONArray(); 
		for (Score sc : users) {
			JSONObject obj = new JSONObject();
			obj.put("user", sc.user);
			obj.put("score", sc.score);
			obj.put("timescore", sc.timescore);
			obj.put("timetaken", sc.timetaken);
			jarr.add(obj);
		}
		StringWriter out = new StringWriter(); 
		try {
			jarr.writeJSONString(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String jsonText = out.toString();
		return jsonText.getBytes();
	}
	
	public String toString() {
		JSONObject obj = new JSONObject();
		for (Score sc : users) {
			obj.put(sc.user, sc.score);
		}
		StringWriter out = new StringWriter(); 
		try {
			obj.writeJSONString(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String jsonText = out.toString();
		return jsonText;
	}
	
}
