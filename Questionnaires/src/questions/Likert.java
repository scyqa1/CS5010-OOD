package questions;

public class Likert implements Question{
    
    private String text;
    // Constructor
    public Likert(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String answer(String answer) {
        int ans = Integer.parseInt(answer);
        if (ans < 1 || ans > 5) {
            return "Incorrect";
        }
        return "Correct";
    }

	// used for a question bank that stores questions and orders them 
	// so that they can be picked quickly to assemble a questionnaire
	private static final int order = 3;
	
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
