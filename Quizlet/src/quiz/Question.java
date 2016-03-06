package quiz;

import java.util.ArrayList;

//ABSTRACT CLASS: DO NOT CREATE NEW INSTANCE OF QUESTION OBJECT
public abstract class Question {
	
	//Type Constants 
	public static final int QUESTION_RESPONSE = 1; 
	public static final int FILL_IN_BLANK = 2; 
	public static final int MULTIPLE_CHOICE = 3; 
	public static final int PICTURE_RESPONSE = 4; 
	public static final int MULTIPLE_ANSWER = 5; 
	
	protected String question; 
	protected Answer answer; 
	
	//getType and getQuestion must be supported by all Question subclasses
	public int getType() {
		return 0; 
	}
	
	public String getQuestion() {
		return question;
	}
	
	public Answer getAnswer() {
		return answer; 
	}
	
	public String stringType(int type) {
		switch(type) {
			case 1: return "Question Response"; 
			case 2: return "Fill in the Blank"; 
			case 3: return "Multiple Choice"; 
			case 4: return "Picture Response"; 
			case 5: return "Multiple Answer"; 
			default: return ""; 
		}
	}
	
	public ArrayList<String> getChoices() {
		return null; 
	}
	
	public int getNumAnswers() {
		return 1; 
	}
	
	public int checkAnswer(String[] answer) {
		return 0;
	}
}
