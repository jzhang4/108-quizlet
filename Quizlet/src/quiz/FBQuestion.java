package quiz;


public class FBQuestion extends SingleQuestion {
	
	public FBQuestion(String question, Answer answer) {
		super(question, answer);
	}
	
	public FBQuestion(String question,String answer) {
		super(question, answer);
	}
	
	@Override 
	public int getType() {
		return FILL_IN_BLANK; 
	}
	
}
