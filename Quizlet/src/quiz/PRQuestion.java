package quiz;

public class PRQuestion extends SingleQuestion {
	public PRQuestion(String question, Answer answer) {
		super(question, answer);
	}
	
	public PRQuestion(String question,String answer) {
		super(question, answer);
	}
	
	@Override 
	public int getType() {
		return PICTURE_RESPONSE; 
	}
}
