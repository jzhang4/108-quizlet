package quiz;

public class QRQuestion extends SingleQuestion {
	
	public QRQuestion(String question, Answer answer) {
		super(question, answer);
	}
	
	public QRQuestion(String question,String answer) {
		super(question, answer);
	}
	
	@Override 
	public int getType() {
		return QUESTION_RESPONSE; 
	}
}
