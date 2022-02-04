package test;

import org.junit.Before;
import org.junit.Test;

import document.*;
import document.element.*;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test for DocumentVisitor interface.
 */
public class DocumentVisitorTest {
  private Document doc;
  private TextElement topHeading;
  private BasicText single;
  private Heading khoury;
  private HyperText link;
  private Heading summary;
  private BoldText bold;
  private Paragraph para;

  @Before
  public void setup() {
    topHeading = new Heading("Top Level Heading", 1);
    single = new BasicText("Just text is an important part of every document.");
    khoury = new Heading("Khoury College Website", 3);
    link = new HyperText("Link to Khoury College", "https://www.khoury.northeastern.edu/");
    summary = new Heading("Summary", 2);
    bold = new BoldText("Bold text so it's more important!");

    para = new Paragraph();
    BasicText para1 = new BoldText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
            + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
    BasicText para2 = new BasicText("Ut enim ad minim veniam, quis nostrud exercitation "
            + "ullamco laboris nisi ut aliquip ex ea commodo consequat.");
    BasicText para3 = new ItalicText("Duis aute irure dolor in reprehenderit in voluptate velit "
            + "esse cillum dolore eu fugiat nulla pariatur.");
    para.add(para1);
    para.add(para2);
    para.add(para3);

    doc = new Document();
  }

  @Test
  public void testMarkdownStringVisitor() {
    String expected = "";
    assertEquals("Failed with empty document", expected,
            doc.toText(new MarkdownStringVisitor()));

    expected += "# Top Level Heading";
    doc.add(topHeading);
    assertEquals("Failed after adding level 1 heading ...", expected,
            doc.toText(new MarkdownStringVisitor()));

    expected += "\nJust text is an important part of every document.";
    doc.add(single);
    assertEquals("Failed after adding basic text ...", expected,
            doc.toText(new MarkdownStringVisitor()));

    expected += "\n### Khoury College Website";
    doc.add(khoury);
    assertEquals("Failed after adding level 3 heading ...", expected,
            doc.toText(new MarkdownStringVisitor()));

    expected += "\n[Link to Khoury College](https://www.khoury.northeastern.edu/)";
    doc.add(link);
    assertEquals("Failed after adding hypertext ...", expected,
            doc.toText(new MarkdownStringVisitor()));

    expected += "\n## Summary";
    doc.add(summary);
    assertEquals("Failed after adding level 2 heading ...", expected,
            doc.toText(new MarkdownStringVisitor()));

    expected += "\n**Bold text so it's more important!**";
    doc.add(bold);
    assertEquals("Failed after adding bold text ...", expected,
            doc.toText(new MarkdownStringVisitor()));

    String expectedParagraph = "\n\n**Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
            + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.**\n"
            + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi "
            + "ut aliquip ex ea commodo consequat.\n*Duis aute irure dolor in reprehenderit "
            + "in voluptate velit esse cillum dolore eu fugiat nulla pariatur.*";

    expected += expectedParagraph;
    doc.add(para);
    assertEquals("Failed after adding paragraph...", expected,
            doc.toText(new MarkdownStringVisitor()));
  }

  @Test
  public void testBasicStringVisitor() {
    String expected = "";
    assertEquals("Failed with empty document", expected,
            doc.toText(new BasicStringVisitor()));

    expected += "Top Level Heading";
    doc.add(topHeading);
    assertEquals("Failed after adding level 1 heading ...", expected,
            doc.toText(new BasicStringVisitor()));

    expected += " Just text is an important part of every document.";
    doc.add(single);
    assertEquals("Failed after adding basic text ...", expected,
            doc.toText(new BasicStringVisitor()));

    expected += " Khoury College Website";
    doc.add(khoury);
    assertEquals("Failed after adding level 3 heading ...", expected,
            doc.toText(new BasicStringVisitor()));

    expected += " Link to Khoury College";
    doc.add(link);
    assertEquals("Failed after adding hypertext ...", expected,
            doc.toText(new BasicStringVisitor()));

    expected += " Summary";
    doc.add(summary);
    assertEquals("Failed after adding level 2 heading ...", expected,
            doc.toText(new BasicStringVisitor()));

    expected += " Bold text so it's more important!";
    doc.add(bold);
    assertEquals("Failed after adding bold text ...", expected,
            doc.toText(new BasicStringVisitor()));

    String expectedParagraph = " Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
            + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
            + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi "
            + "ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit "
            + "in voluptate velit esse cillum dolore eu fugiat nulla pariatur.";

    expected += expectedParagraph;
    doc.add(para);
    assertEquals("Failed after adding paragraph...", expected,
            doc.toText(new BasicStringVisitor()));
  }

  @Test
  public void testHtmlStringVisitor() {
    String expected = "";
    assertEquals("Failed with empty document", expected,
            doc.toText(new HtmlStringVisitor()));

    expected += "<h1>Top Level Heading</h1>";
    doc.add(topHeading);
    assertEquals("Failed after adding level 1 heading ...", expected,
            doc.toText(new HtmlStringVisitor()));

    expected += "\nJust text is an important part of every document.";
    doc.add(single);
    assertEquals("Failed after adding basic text ...", expected,
            doc.toText(new HtmlStringVisitor()));

    expected += "\n<h3>Khoury College Website</h3>";
    doc.add(khoury);
    assertEquals("Failed after adding level 3 heading ...", expected,
            doc.toText(new HtmlStringVisitor()));

    expected += "\n<a href=\"https://www.khoury.northeastern.edu/\">Link to Khoury College</a>";
    doc.add(link);
    assertEquals("Failed after adding hypertext ...", expected,
            doc.toText(new HtmlStringVisitor()));

    expected += "\n<h2>Summary</h2>";
    doc.add(summary);
    assertEquals("Failed after adding level 2 heading ...", expected,
            doc.toText(new HtmlStringVisitor()));

    expected += "\n<b>Bold text so it's more important!</b>";
    doc.add(bold);
    assertEquals("Failed after adding bold text ...", expected,
            doc.toText(new HtmlStringVisitor()));

    String expectedParagraph = "\n<p><b>Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
            + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</b>\n"
            + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi "
            + "ut aliquip ex ea commodo consequat.\n<i>Duis aute irure dolor in reprehenderit "
            + "in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</i>\n</p>";

    expected += expectedParagraph;
    doc.add(para);
    assertEquals("Failed after adding paragraph...", expected,
            doc.toText(new HtmlStringVisitor()));
  }
}