package quiz;

import java.util.*;

import org.json.simple.JSONObject;

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
	
	
}
