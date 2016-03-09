package quiz;

import java.util.*;

public class MCQuestion extends SingleQuestion {
	private ArrayList<String> choices;
	
	public MCQuestion(String question, Answer answer, ArrayList<String> choices) {
		super(question, answer);
		this.choices = choices; 
	}
	
	public MCQuestion(String question,String answer, ArrayList<String> choices) {
		super(question, answer);
		this.choices = choices; 
	}
	
	@Override 
	public int getType() {
		return MULTIPLE_CHOICE; 
	}
	@Override
	public ArrayList<String> getChoices() {
		return choices; 
	}
}
