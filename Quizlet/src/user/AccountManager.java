 package user;

import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class AccountManager {
	public HashSet<User> accounts;
	
	public AccountManager(Statement stmt) {
		accounts = new HashSet<User>();
		loadUsers(stmt);
	}
	
	private void loadUsers(Statement stmt) {
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM users");
			while(rs.next()) {
				String username = rs.getString("username");
				String strPasswordHash = rs.getString("passwordHash");
				boolean privacyOn = (rs.getInt("privacy") == 0) ? false : true;
				User toAdd = new User(username, hexToArray(strPasswordHash), stmt, privacyOn);
				int ID = rs.getInt("id");
				toAdd.setID(ID);
				accounts.add(toAdd);
			}
			rs.close();
			for (User u : accounts) {
				u.loadRequests(stmt);
				u.loadFriends(stmt);
				u.loadMessages(stmt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] hexToArray(String hex) {
		byte[] result = new byte[hex.length()/2];
		for (int i=0; i<hex.length(); i+=2) {
			result[i/2] = (byte) Integer.parseInt(hex.substring(i, i+2), 16);
		}
		return result;
	}
	
	public void newAccount(String user, String password, Statement stmt) {
		int ID = -1;
		if (!accountExists(user)) {
			User toCheck = new User(user, generate(password), stmt, false);
			accounts.add(toCheck);
			try {
				stmt.executeUpdate("INSERT INTO achievements (username,achieve1,achieve2,achieve3,achieve4,achieve5,achieve6,achieve7) VALUES (\"" + user + "\"," +"0,0,0,0,0,0,0);");
				stmt.executeUpdate("INSERT INTO users (username, passwordHash, admin,picKey) VALUES(\"" + user + "\", \"" + toCheck.getPasswordHash() + "\", 0,0);");
				ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username = \"" + user + "\";");
				while (rs.next()) {
					ID = rs.getInt("id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			toCheck.setID(ID);
		}
	}
	
	private byte[] generate(String toHash) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(toHash.getBytes());
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();	
			return null;
		}
	}
	
	public boolean accountExists(String username) {
		for (User u : accounts) {
			if (u.getUserName().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public User getAccount(String username) {
		for (User u : accounts) {
			if (u.getUserName().equals(username)) {
				return u;
			}
		}
		return null;
	}
	
	public User getAccount(int ID) {
		for (User u : accounts) {
			if (u.getID()==ID) {
				return u;
			}
		}
		return null;
	}
	
	//only gets called if account exists already
	public boolean passwordMatches(String username, String password) {
		for (User u : accounts) {
			if (u.getUserName().equals(username)) {
				return u.hexToString(generate(password)).equals(u.getPasswordHash());
			}
		}
		return false;
	}
}
