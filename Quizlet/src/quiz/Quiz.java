package quiz;

import java.io.File;
import java.util.*;

import org.json.simple.JSONObject;



public class Quiz {
	
	private ArrayList<Question> questions; 
	
	private String name; 
	
	private String description; 
	
	private boolean random; 
	
	private boolean multipage; 
	
	private boolean quickcorrect;
	
	private boolean practicemode; 
	
	public static Quiz readXML(String XMLfile) {
		Quiz quiz = null; 
		
		File fXmlFile = new File(XMLfile);
		
		return quiz;
	}
	
	public Quiz(String name, String description) {
		questions = new ArrayList<Question>(); 
		this.name = name; 
		this.description = description; 
		this.random = false; 
	}
	
	public void setRandom(boolean bool) {
		this.random = bool; 
	}
	
	public void shuffleQuiz() {
		Collections.shuffle(questions);
	}
	
	public boolean isRandom() {
		return this.random; 
	}
	
	public void setPage(boolean bool) {
		this.multipage = bool; 
	}
	public boolean isMulti() {
		return this.multipage; 
	}
	
	public void setCorrect(boolean bool) {
		this.quickcorrect = bool; 
	}
	public boolean isQuickCorrect() {
		return this.quickcorrect; 
	}
	
	public void setPractice(boolean bool) {
		this.practicemode = bool; 
	}
	public boolean isPractice() {
		return this.practicemode; 
	}
	
	public int getSize() {
		return questions.size();
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	public void addQuestion(Question question) {
		questions.add(question);
	}
	public void removeQuestion(Question question) {
		questions.remove(question);
	}
	public Question getQuestion(int index) {
		return questions.get(index-1);
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Iterator<Question> iterator() {
		return new QuizIterator(); 
	}
	
	//Nested Inner class to iterate through quiz question
	private class QuizIterator implements Iterator<Question> {
		int index; 
		
		public QuizIterator() {
			index = 0; 
		}
		
		@Override
		public boolean hasNext() {
			if (index < questions.size()) return true; 
			return false;
		}

		@Override
		public Question next() {
			return questions.get(index++);
		}
		
	}
	
	public void printStr() {
		System.out.println(this.getName());
		System.out.println(this.getDescription());
		Iterator<Question> it = this.iterator();
		while (it.hasNext()) {
			Question q = it.next();
			String ques = q.getQuestion();
			System.out.println(ques);
			ArrayList<String> answers = q.getAnswer().getAnswer();
			for (String str : answers) {
				System.out.println(str);
			}
		}
	}

	
	
	public static void main(String[] args) {
		Quiz quiz = new Quiz("Best Quiz", "This is a really cool quiz."); 
		String question = "Who is the most amazing person in the world?";
		String answer = "Jaimie";
		QRQuestion qr = new QRQuestion(question, answer);
		quiz.addQuestion(qr);
		
		String question2 = "________ is the most amazing person in the world.";
		String answer2 = "Jaimie";
		FBQuestion fb = new FBQuestion(question2, answer2);
		fb.addAnswer("Jaimie Xie");
		quiz.addQuestion(fb);
		
		System.out.println(quiz.getName());
		System.out.println(quiz.getDescription());
		Iterator<Question> it = quiz.iterator();
		while (it.hasNext()) {
			Question q = it.next();
			String ques = q.getQuestion();
			System.out.println(ques);
			ArrayList<String> answers = q.getAnswer().getAnswer();
			for (String str : answers) {
				System.out.println(str);
			}
		}
		JSONObject obj = JSONCreator.jsonQuiz(quiz);
		System.out.println(obj);
	}
}
