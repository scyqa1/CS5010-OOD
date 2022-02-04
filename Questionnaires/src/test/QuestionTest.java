package test;

import org.junit.Before;
import org.junit.Test;

import questions.*;
import static org.junit.Assert.*;


public class QuestionTest {

	


    // test likert
	Likert likert;
    @Before
    public void LikertSetUp() {
    	likert = new Likert("I am a question");
    }

    @Test
    public void TestLikert() {
        assertEquals("Correct", likert.answer("1"));
        assertEquals("Incorrect", likert.answer("0"));
        assertEquals("Incorrect", likert.answer("6"));

    }
    
    // test MultChoice
    MultipleChoice MultChoice1;
    MultipleChoice MultChoice2;
    MultipleChoice MultChoice3;
    MultipleChoice MultChoice4;
    MultipleChoice MultChoice5;
    MultipleChoice MultChoice6;
    
    public void setUp() throws Exception {
    	MultChoice1 = new MultipleChoice("I am a question", "1 3", "1", "2", "3");
    }
    @Before
    public void MultChoiceSetUp() {
    	MultChoice1 = new MultipleChoice("I am a question", "1", "12", "abc", "b b");
    	MultChoice2 = new MultipleChoice("I am a question", "1", "12", "abc", "b b", "a1");
    	MultChoice3 = new MultipleChoice("I am a question", "1", "12", "abc", "b b", "a1", "321");
    	MultChoice4 = new MultipleChoice("I am a question", "1", "12", "abc", "b b", "a1", "321", "11111");
    	MultChoice5 = new MultipleChoice("I am a question", "1", "12", "abc", "b b", "a1", "321", "11111", "acca");
    	MultChoice6 = new MultipleChoice("I am a question", "1", "12", "abc", "b b", "a1", "321", "11111", "acca", "a 1");
    }

    @Test
    public void TestMultChoice() {
        assertEquals("Correct", MultChoice1.answer("1"));
        assertEquals("Incorrect", MultChoice1.answer("0"));
        assertEquals("Incorrect", MultChoice1.answer("4"));

    }

    // test MultSelect
    MultipleSelect MultSelect1;
    MultipleSelect MultSelect2;
    MultipleSelect MultSelect3;
    MultipleSelect MultSelect4;
    MultipleSelect MultSelect5;
    MultipleSelect MultSelect6;
    @Before
    public void MultSelectSetUp() {
    	MultSelect1 = new MultipleSelect("I am a question", "1 4", "12", "abc", "b b");
    	MultSelect2 = new MultipleSelect("I am a question", "1 4", "12", "abc", "b b", "a1");
    	MultSelect3 = new MultipleSelect("I am a question", "1 4", "12", "abc", "b b", "a1", "321");
    	MultSelect4 = new MultipleSelect("I am a question", "1 4", "12", "abc", "b b", "a1", "321", "11111");
    	MultSelect5 = new MultipleSelect("I am a question", "1 4", "12", "abc", "b b", "a1", "321", "11111", "acca");
    	MultSelect6 = new MultipleSelect("I am a question", "1 4", "12", "abc", "b b", "a1", "321", "11111", "acca", "a 1");
    }

    @Test
    public void TestMultSelect() {
        assertEquals("Correct", MultSelect1.answer("1 4"));
        assertEquals("Correct", MultSelect1.answer("4 1"));
        assertEquals("Incorrect", MultSelect1.answer("0"));
        assertEquals("Incorrect", MultSelect1.answer("4"));
        

    }
    
    // test TrueFalse
    TrueFalse TrueFalse;
    @Before
    public void TureFalseSetUp() {
    	TrueFalse = new TrueFalse("I am a question", "True");
    }

    @Test
    public void TestTureFalse() {
        assertEquals("Correct", TrueFalse.answer("True"));
        assertEquals("Incorrect", TrueFalse.answer("6"));

    }
    
    // test order
    @Test
    public void TestOrder() {
        assertEquals(-1, TrueFalse.compareTo(likert));
        assertEquals(1, MultSelect1.compareTo(MultChoice1));
        assertEquals(0, likert.compareTo(likert));
        assertEquals(0, TrueFalse.compareTo(TrueFalse));
        assertEquals(0, MultSelect1.compareTo(MultSelect1));
        assertEquals(0, MultChoice1.compareTo(MultChoice1));

    }
    
}