package document;

import java.util.List;

import document.element.*;

public class HtmlStringVisitor implements DocumentVisitor<String> {
  private String text;

  /** Default constructor. */
  public HtmlStringVisitor() {
    text = "";
  }

  @Override
  public String visitBasicText(BasicText object) {
    text = text + object.getText() + "\n";
    return text;
  }

  @Override
  public String visitBoldText(BoldText object) {
    text = text + "<b>" + object.getText() + "</b>" + "\n";
    return text;
  }

  @Override
  public String visitHeading(Heading object) {
    String level = String.valueOf(object.getLevel());
    text =
        text + "<h" + level + ">" + object.getText() + "</h" + level + ">" + "\n";
    return text;
  }

  @Override
  public String visitHyperText(HyperText object) {
    String url = String.valueOf(object.getUrl());
    text = text + "<a href=\"" + url + "\">" + object.getText() + "</a>" + "\n";
    return text;
  }

  @Override
  public String visitItalicText(ItalicText object) {
    text = text + "<i>" + object.getText() + "</i>" + "\n";
    return text;
  }

  @Override
  public String visitParagraph(Paragraph object) {
	DocumentVisitor<String> html = new HtmlStringVisitor();
    List<BasicText> content = object.getContent();
    for (BasicText text : content) {
    	text.accept(html);
    }
    text = text + "<p>" + html.toString() + "</p>" + "\n";
    return text;
  }

  @Override
  public String toString() {
    String answer = text;
    text = "";
    return answer;
  }
}
