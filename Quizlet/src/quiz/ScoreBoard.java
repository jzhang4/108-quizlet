package quiz;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ScoreBoard {
	
	ArrayList<Score> users; 
		
	public class Score {
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
		
	}
	
	public ScoreBoard() {
		this.users = new ArrayList<Score>(); 
	}
	
	public ArrayList<Score> getUsers() {
		return this.users; 
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
