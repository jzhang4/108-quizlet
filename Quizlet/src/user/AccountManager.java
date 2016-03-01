package user;

import java.util.*;
import java.sql.*;

public class AccountManager {
	public HashSet<User> accounts;
	DBConnection dbc;
	
	public AccountManager() {
		accounts = new HashSet<User>();
		dbc = new DBConnection();
		
		try {
			dbc.stmt.executeQuery("SELECT * FROM users");
		} catch (SQLException e) {}
	}
	
	public void newAccount(String user, String password) {
		User toCheck = new User(user, password);
		if (!accountExists(toCheck)) {
			accounts.add(toCheck);
			try {
				dbc.stmt.executeUpdate("INSERT INTO users VALUES(\"" + user + "\", \"" + toCheck.getPasswordHash() + "\");");
			} catch (SQLException e) {}
		}
	}
	
	public boolean accountExists(User u) {
		return accounts.contains(u);
	}
	
	//only gets called if account exists already
//	public boolean passwordMatches(String user, String password) {
//		return password.equals(accounts.get(user));
//	}
}
