package questions;

public interface Question extends Comparable<Question> {
    String getText();
    String answer(String answer);
    int getOrder();
}