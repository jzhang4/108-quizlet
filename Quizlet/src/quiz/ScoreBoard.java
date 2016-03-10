package quiz;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ScoreBoard {
	
	ArrayList<UserScorePair> users; 
		
	public class UserScorePair {
		public String user; 
		public int score; 
		
		public UserScorePair(String user, int score) {
			this.user = user; 
			this.score = score; 
		}
		
	}
	
	public ScoreBoard() {
		this.users = new ArrayList<UserScorePair>(); 
	}
	
	public ScoreBoard(Blob blob) {
		JSONObject jobj = null; 
		this.users = new ArrayList<UserScorePair>(); 
		try {
			byte[] bdata = blob.getBytes(1, (int)blob.length());
			String boardstr = new String(bdata);
			
			JSONParser parser = new JSONParser(); 
			Object obj = parser.parse(boardstr);
			jobj = (JSONObject)obj;
			
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Object key: jobj.keySet()) {
			long score1 = (long)jobj.get(key);
			int score = (int)score1;
			UserScorePair pair = new UserScorePair((String)key, score);
			this.users.add(pair);
		}
	}
	
	public void addScore(String user, int score) {
		UserScorePair pair = new UserScorePair(user, score);
		users.add(pair);
	}
	
	public byte[] boardToBytes() {
		JSONObject obj = new JSONObject();
		for (UserScorePair pair : users) {
			obj.put(pair.user, pair.score);
		}
		StringWriter out = new StringWriter(); 
		try {
			obj.writeJSONString(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String jsonText = out.toString();
		return jsonText.getBytes();
	}
	public String toString() {
		JSONObject obj = new JSONObject();
		for (UserScorePair pair : users) {
			obj.put(pair.user, pair.score);
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
