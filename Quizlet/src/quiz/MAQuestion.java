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
	
	public boolean checkAnswers(ArrayList<String> answers) {
		if (answers.size() != numanswers) return false; 
		if (!ordered){
			Set<String> correct = new HashSet<String>(this.answer.getAnswer());
			
			for (String ans : answers) {
				if (correct.contains(ans)) {
					correct.remove(ans);
				} else return false; 
			}
			return true; 
		} else {
			for (int i = 0; i < numanswers; i++) {
				if (!this.answer.getAnswer().get(i).equals(answers.get(i))) return false; 
			}
			return true; 
		}
	}
	
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
		MAQuestion q = new MAQuestion("Name 3 things", 3, true);
		q.addAnswer("a");
		q.addAnswer("b");
		q.addAnswer("c");
		q.addAnswer("d");
		ArrayList<String> answers = new ArrayList<String>(); 
		answers.add("a");
		answers.add("b");
		answers.add("c");
		System.out.println(q.checkAnswers(answers));
	}
}
