package quiz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MAQuestion extends Question{
	
	private int numanswers; 
	private boolean ordered;
	
	public MAQuestion(String question, int numanswers, boolean ordered) {
		this.question = question;
		this.numanswers = numanswers; 
		this.ordered = ordered; 
		this.answer = new Answer(); 
	}
	public void addAnswer(String answer) {
		this.answer.addAnswer(answer);
	}
	

	@Override
	public int checkAnswer(String[] answers) {
		if (answers.length != numanswers) return -1;
		int numcorrect = 0; 
		if (!ordered){
			Set<String> correct = new HashSet<String>(this.answer.getAnswer());
			
			for (String ans : answers) {
				if (correct.contains(ans)) {
					correct.remove(ans);
					numcorrect++;
				} 
			}
		} else {
			for (int i = 0; i < numanswers; i++) {
				if (this.answer.getAnswer().get(i).equals(answers[i])) numcorrect++; 
			}
		}
		return numcorrect;
	}
	
	@Override
	public int getNumAnswers() {
		return numanswers; 
	}
	
	public void setNumAnswers(int num) {
		numanswers = num; 
	}
	
	public boolean ordered() {
		return ordered; 
	}
	
	@Override 
	public int getType() {
		return MULTIPLE_ANSWER; 
	}
	
	//Test Sequence
	public static void main(String[] args) {
		MAQuestion q = new MAQuestion("Name 3 things", 3, false);
		q.addAnswer("a");
		q.addAnswer("b");
		q.addAnswer("c");
		q.addAnswer("d");
		String[] answers = new String[3];
		answers[0] = "a";
		answers[1] = "c";
		answers[2] = "d";
		System.out.println(q.checkAnswer(answers));
	}
}
