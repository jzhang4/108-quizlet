package quiz;

import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import quiz.*;

public class JSONCreator {
	
	
	public static JSONObject jsonQuestion(Question question) {
		int type = question.getType();
		String name = question.getQuestion();
		ArrayList<String> answers = question.getAnswer().getAnswer();
		JSONObject obj = new JSONObject(); 
		
		obj.put("type", type);
		obj.put("name", name);
		obj.put("answers", answers);
		
		if (question.getType() == Question.MULTIPLE_CHOICE) {
			ArrayList<String> choices = question.getChoices();
			obj.put("choices", choices);
		}
		if (question.getType() == Question.MULTIPLE_ANSWER) {
			int numanswers = question.getNumAnswers();
			obj.put("numanswers", numanswers);
			MAQuestion maq = (MAQuestion)question; 
			boolean ordered = maq.ordered();
			obj.put("ordered", ordered);
		}
		
		return obj; 
	}
	
	public static JSONObject jsonQuiz(Quiz quiz) {
		String name = quiz.getName();
		String description = quiz.getDescription();
		boolean random = quiz.isRandom();
		boolean multipage = quiz.isMulti();
		boolean quickcorrect = quiz.isQuickCorrect();
		boolean practicemode = quiz.isPractice();
		ArrayList<JSONObject> questions = new ArrayList<JSONObject>();
		for (Question q : quiz.getQuestions()) {
			JSONObject obj = jsonQuestion(q);
			questions.add(obj);
		}
		JSONObject obj = new JSONObject(); 
		obj.put("name", name);
		obj.put("description", description);
		obj.put("random", random);
		obj.put("multipage", multipage);
		obj.put("quickcorrect", quickcorrect);
		obj.put("practicemode", practicemode);
		obj.put("question", questions);
		
		return obj; 
	}
	
	public static Question getQuestion(JSONObject obj) {
		String name = (String)obj.get("name");
		int type = Math.toIntExact((long) obj.get("type"));

		ArrayList<String> answers = (ArrayList<String>)obj.get("answers");
		Answer answerobj = new Answer(answers);
		Question ques = null; 
		
		switch (type) {
		case Question.MULTIPLE_ANSWER: 
			int numanswers = Math.toIntExact((long) obj.get("numanswers"));
			boolean ordered = (boolean)obj.get("ordered");
			ques = new MAQuestion(name, numanswers, ordered);
			for (String ans : answers) {
				ques.addAnswer(ans);
			}
			break;
		case Question.MULTIPLE_CHOICE:
			ArrayList<String> choices = (ArrayList<String>)obj.get("choices");
			ques = new MCQuestion(name, answers.get(0), choices);
			break;
		case Question.FILL_IN_BLANK:
			ques = new FBQuestion(name, answerobj); 
			break;
		case Question.PICTURE_RESPONSE:
			ques = new PRQuestion(name, answerobj); 
			break;
		case Question.QUESTION_RESPONSE:
			ques = new QRQuestion(name, answerobj);
			break;
		} 
		

		return ques; 
	}
	
	public static Quiz getQuiz(JSONObject jobj) {
        Quiz quiz; 

		String quizname = (String)jobj.get("name");
		String description = (String)jobj.get("description");
			
		quiz = new Quiz(quizname, description);
		quiz.setCorrect((boolean)jobj.get("quickcorrect"));
		quiz.setRandom((boolean)jobj.get("random"));
		quiz.setPractice((boolean)jobj.get("practicemode"));
		quiz.setPage((boolean)jobj.get("multipage"));
		
		ArrayList<JSONObject> array = (ArrayList<JSONObject>)jobj.get("question");
		for (JSONObject qobj : array) {
			Question q = getQuestion(qobj);
			quiz.addQuestion(q);
		}	
		return quiz; 
	}
	
}
