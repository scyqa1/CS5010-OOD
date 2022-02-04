package document;

import java.util.ArrayList;
import java.util.List;

import document.element.TextElement;

public class Document {
  
  
  private final List<TextElement> content;
  
  /*public <R> R accept(DocumentVisitor<R> visitor) {
	  String s = "";
	  for (TextElement element : content) {
		  s += element.getText();
		}
	  return null;
  }
  */

  /** Default constructor initializes the fields of the class. */
  public Document() {
    content = new ArrayList<>();
  }

  /**
   * Add an element to the document. Elements are added in order.
   *
   * @param e the element to add
   */
  public void add(TextElement e) {
    content.add(e);
  }

  /**
   * Counts the number of words in the document.
   *
   * @return the number of words in the document.
   */
  public int countWords() {
    WordCountVisitor countVisitor = new WordCountVisitor();
    int words = 0;
    for (TextElement element : content) {
      words = words + (int)countVisitor.wordCountHelper(element.getText());
    }
    return words;
  }

  /**
   * Converts document to a text format.
   * @param visitor the text format to convert document to.
   * @return the converted text format.
   */
  public String toText(DocumentVisitor<String> visitor) {
	for (TextElement element : content) {
	  element.accept(visitor);
	}

	return visitor.toString().trim();
  }
	
}
