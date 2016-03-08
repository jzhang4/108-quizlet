package user;

public class Request {
	private boolean accepted;
	private int senderID;
	private int recipientID;
	
	public Request(int senderID, int recipientID) {
		accepted = false;
		this.senderID = senderID;
		this.recipientID = recipientID;
	}
	
	public void acceptRequest() {
		accepted = true;
	}
	
	public int getSenderID() {
		return senderID;
	}
	
	public int getRecipientID() {
		return recipientID;
	}

}