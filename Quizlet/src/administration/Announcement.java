package administration;

public class Announcement {
	private String announce;
	private String date;
	// creates the announcement, requires the text of the announcement 
	// and the date announcement. 
	public Announcement(String firstArg, String secondArg){
		announce = firstArg;
		date = secondArg;
	}
	// returns the text associated with the announcement 
	public String getText(){
		return announce;
	}
	// returns the date of the announcement 
	public String getDate(){
		return date;
	}
	
}
