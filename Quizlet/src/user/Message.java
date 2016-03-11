package user;

import java.util.*;
import java.sql.*;

public class Message {
	String type;
	String sender;
	String recipient;
	String message;
	String subject;
	boolean recipientRead;
	int id;
	String quiz;
	long score;
	
	public Message(String type, String sender, String recipient, String message, String subject, int id, Boolean read, String quiz, long score) {
		this.type = type;
		this.sender = sender;
		this.recipient = recipient;
		this.id = id;
		
		if (type.equals("Request")) {
			this.message = "Please accept my friend request!";
			this.subject = "Friend Request from " + sender;
		} else if (type.equals("Challenge")) {
			this.message = "Try to beat my score: " + score + "!";
			this.subject = "Challenge from " + sender;
			this.quiz = quiz;
			this.score = score;
		} else {
			this.message = message;	
			this.subject = subject;
		}
		
		if (read != null) {
			this.recipientRead = read;
		} else {
			this.recipientRead = false;
		}
	}
	
	public void setRead() {
		recipientRead = true;
	}
	
	public boolean isRead() {
		return recipientRead;
	}
	
	public long getScore() {
		return score;
	}

	public void setMessage(String s) {
		message = s;
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
	
	public String getType() {
		return type;
	}
	
	public String getQuiz() {
		return quiz;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setID(int id) {
		this.id = id;
	}
}
