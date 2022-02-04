package document;

import document.element.*;

public class WordCountVisitor implements DocumentVisitor<Integer> {

  @Override
  public Integer visitBasicText(BasicText object) {
    return wordCountHelper(object.getText());
  }

  @Override
  public Integer visitBoldText(BoldText object) {
    return wordCountHelper(object.getText());
  }

  @Override
  public Integer visitHeading(Heading object) {
    return wordCountHelper(object.getText());
  }

  @Override
  public Integer visitHyperText(HyperText object) {
    return wordCountHelper(object.getText());
  }

  @Override
  public Integer visitItalicText(ItalicText object) {
    return wordCountHelper(object.getText());
  }

  @Override
  public Integer visitParagraph(Paragraph object) {
    return wordCountHelper(object.getText());
  }

  /**
   * Calculates the number of words in a given text.
   *
   * @param text the text to get the number of words from.
   * @return the number of words.
   */
  public Integer wordCountHelper(String text) {
    String[] words = text.split("\\s+");
    if (words[0].equals("")) {
      return 0;
    } else {
      return words.length;

    }
  }

}
