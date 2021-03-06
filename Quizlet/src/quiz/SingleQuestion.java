package quiz;

import java.util.*;

public class SingleQuestion extends Question {
	
	public SingleQuestion(String question, Answer answer) {
		this.question = question; 
		this.answer = answer; 
	}
	
	public SingleQuestion(String question,String answer) {
		this.question = question; 
		this.answer = new Answer(answer); 
	}
	
	public void addAnswer(String answer) {
		this.answer.addAnswer(answer);
	}
	
	@Override
	public int checkAnswer(String[] answer) {
		ArrayList<String> answers = this.answer.getAnswer();
		for (String ans : answers) {
			if (answer[0].equals(ans)) return 1; 
		}
		return 0; 
	}
	
}
