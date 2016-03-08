package user;

import java.util.*;
import java.sql.*;

public class Message {
	String type;
	String sender;
	String recipient;
	String message;
	String subject;
	int id;
	
	public Message(String type, String sender, String recipient, String message, String subject, int id) {
		this.type = type;
		this.sender = sender;
		this.recipient = recipient;
		this.id = id;
		if (type.equals("Request")) {
			this.message = "Please accept my friend request!";
			this.subject = "Friend Request from " + sender;
		} else if (type.equals("Challenge")) {
			this.message = "Try to beat my score!";
			this.subject = "Challenge from " + sender;
		} else {
			this.message = message;	
			this.subject = subject;
		}
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getSender() {
		return sender;
	}
	
	public String getRecipient() {
		return recipient;
	}
	
	public int getID() {
		return id;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setID(int id) {
		this.id = id;
	}
}
