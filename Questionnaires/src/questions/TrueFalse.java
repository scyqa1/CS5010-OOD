package questions;

public class TrueFalse implements Question{
    private String text;
    private String correctAnswer;

    // Constructor
    public TrueFalse(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String answer(String answer) {
        if (!answer.equals("True") && !answer.equals("False")) {
            return "Incorrect";
        }
        else if (answer.equals(correctAnswer))
        	return "Correct";
        else
            return "Incorrect";
    }

	// used for a question bank that stores questions and orders them 
	// so that they can be picked quickly to assemble a questionnaire
	private static final int order = 0;
	
    @Override
    public int getOrder() {
        return order;
    }
    
    @Override
    public int compareTo(Question o) {
        if (getOrder() != o.getOrder()) {
            return Integer.compare(getOrder(), o.getOrder());
        } else {
            return getText().compareTo(o.getText());
        }
    }

}
