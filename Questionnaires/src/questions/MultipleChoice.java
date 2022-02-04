package questions;

import java.util.HashSet;
import java.util.Set;

public class MultipleChoice implements Question{

    private String text;
    private String correctAnswer;

    private Set<String> opts = new HashSet<>();

    // Constructor with 3 opts
    public MultipleChoice(String text, String correctAnswer, String opt1, String opt2, String opt3) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        opts.add(opt1);
        opts.add(opt2);
        opts.add(opt3);
    }
    
    // Constructor with 4 opts
    public MultipleChoice(String text,
                          String correctAnswer,
                          String opt1,
                          String opt2,
                          String opt3,
                          String opt4) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        opts.add(opt1);
        opts.add(opt2);
        opts.add(opt3);
        opts.add(opt4);
    }

    // Constructor with 5 opts
    public MultipleChoice(String text,
                          String correctAnswer,
                          String opt1,
                          String opt2,
                          String opt3,
                          String opt4,
                          String opt5) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        opts.add(opt1);
        opts.add(opt2);
        opts.add(opt3);
        opts.add(opt4);
        opts.add(opt5);
    }

    // Constructor with 6 opts
    public MultipleChoice(String text,
                          String correctAnswer,
                          String opt1,
                          String opt2,
                          String opt3,
                          String opt4,
                          String opt5,
                          String opt6) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        opts.add(opt1);
        opts.add(opt2);
        opts.add(opt3);
        opts.add(opt4);
        opts.add(opt5);
        opts.add(opt6);
    }

    // Constructor with 7 opts
    public MultipleChoice(String text,
                          String correctAnswer,
                          String opt1,
                          String opt2,
                          String opt3,
                          String opt4,
                          String opt5,
                          String opt6,
                          String opt7) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        opts.add(opt1);
        opts.add(opt2);
        opts.add(opt3);
        opts.add(opt4);
        opts.add(opt5);
        opts.add(opt6);
        opts.add(opt7);
    }

    // Constructor with 8 opts
    public MultipleChoice(String text,
                          String correctAnswer,
                          String opt1,
                          String opt2,
                          String opt3,
                          String opt4,
                          String opt5,
                          String opt6,
                          String opt7,
                          String opt8) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        opts.add(opt1);
        opts.add(opt2);
        opts.add(opt3);
        opts.add(opt4);
        opts.add(opt5);
        opts.add(opt6);
        opts.add(opt7);
        opts.add(opt8);
    }


    @Override
    public String getText() {
        return text;
    }

    @Override
    public String answer(String answer) {
        int ans = Integer.parseInt(answer);
        if (ans < 1 || ans > opts.size()) {
            return "Incorrect";
        }
        else if (answer.equals(correctAnswer))
        	return "Correct";
        else
            return "Incorrect";
    }

	// used for a question bank that stores questions and orders them 
	// so that they can be picked quickly to assemble a questionnaire
	private static final int order = 1;
	
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
