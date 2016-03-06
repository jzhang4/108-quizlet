package quiz;
import java.util.*;

public class Answer {
	//private ivars
	private ArrayList<String> answers; 


	//Constructor
	public Answer(ArrayList<String> answers) {
		this.answers = answers; 
	}
	//Simplified constructor to take in single String
	public Answer(String answer) {
		this.answers = new ArrayList<String>(); 
		this.answers.add(answer);
	}
	
	public Answer() {
		this.answers = new ArrayList<String>();
	}
	
	public void addAnswer(String answer) {
		this.answers.add(answer);
	}

	public ArrayList<String> getAnswer() {
		return answers;
	}

}
