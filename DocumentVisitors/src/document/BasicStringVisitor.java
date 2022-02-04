package document;

import document.element.*;

public class BasicStringVisitor implements DocumentVisitor<String> {
  private String text;

  /** constructor. */
  public BasicStringVisitor() {
    text = "";
  }

  @Override
  public String visitBasicText(BasicText object) {
    return toText(object.getText());
  }

  @Override
  public String visitBoldText(BoldText object) {
    return toText(object.getText());
  }

  @Override
  public String visitHeading(Heading object) {
    return toText(object.getText());
  }

  @Override
  public String visitHyperText(HyperText object) {
    return toText(object.getText());
  }

  @Override
  public String visitItalicText(ItalicText object) {
    return toText(object.getText());
  }

  @Override
  public String visitParagraph(Paragraph object) {
    return toText(object.getText());
  }

  @Override
  public String toString() {
    String str = text;
    text = "";
    return str;
  }

  /**
   * Gets the string text of elements.
   *
   * @param text the text to accumulate.
   * @return the accumulated text.
   */
  private String toText(String str) {
    text +=  str.trim() + " ";
    return text;
  }
}
