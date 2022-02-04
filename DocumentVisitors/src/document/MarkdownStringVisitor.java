package document;

import java.util.List;

import document.element.*;

public class MarkdownStringVisitor implements DocumentVisitor<String> {
  private String text;

  /** constructor. */
  public MarkdownStringVisitor() {
    text = "";
  }

  @Override
  public String visitBasicText(BasicText object) {
    text = text + object.getText() + "\n";
    return text;
  }

  @Override
  public String visitBoldText(BoldText object) {
    text = text + "**" + object.getText() + "**" + "\n";
    return text;
  }

  @Override
  public String visitHeading(Heading object) {
    text =
        text + "#".repeat(Math.max(0, object.getLevel())) + " " + object.getText() + "\n";
    return text;
  }

  @Override
  public String visitHyperText(HyperText object) {
    text =
        text + "[" + object.getText() + "]" + "(" + object.getUrl() + ")" + "\n";
    return text;
  }

  @Override
  public String visitItalicText(ItalicText object) {
    text = text + "*" + object.getText() + "*" + "\n";
    return text;
  }

  @Override
  public String visitParagraph(Paragraph object) {
    DocumentVisitor<String> markdown = new MarkdownStringVisitor();
    List<BasicText> content = object.getContent();
    for (BasicText text : content) {
      text.accept(markdown);
    }
    text = text + "\n" + markdown.toString() + "\n";
    return text;
  }

  @Override
  public String toString() {
    String str = text;
    text = "";
    return str;
  }
}
