package questions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MultipleSelect implements Question{

    private String text;
    private String correctAnswer;

    private Set<String> opts = new HashSet<>();

    // Constructor with 3 opts
    public MultipleSelect(String text, String correctAnswer, String opt1, String opt2, String opt3) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        opts.add(opt1);
        opts.add(opt2);
        opts.add(opt3);
    }

    // Constructor with 4 opts
    public MultipleSelect(String text,
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
    public MultipleSelect(String text,
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
    public MultipleSelect(String text,
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
    public MultipleSelect(String text,
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
    public MultipleSelect(String text,
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
        String[] ansArr = answer.split(" ");
        for (String ans : ansArr) {
            int ansSelect = Integer.parseInt(ans);
            if (ansSelect < 1 || ansSelect > 5) {
                return "Incorrect";
            }
        }
        if (check(answer))
        	return "Correct";
        else
            return "Incorrect";
    }

    private boolean check(String answer) {
        char[] ans = answer.toCharArray();
        Arrays.sort(ans);
        char[] corrrect = correctAnswer.toCharArray();
        Arrays.sort(corrrect);
        return new String(ans).equals(new String(corrrect));
    }

	// used for a question bank that stores questions and orders them 
	// so that they can be picked quickly to assemble a questionnaire
	private static final int order = 2;
	
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
