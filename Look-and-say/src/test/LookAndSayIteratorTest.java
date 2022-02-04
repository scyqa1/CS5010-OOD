package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;


import lookandsay.*;


public class LookAndSayIteratorTest {

	  private RIterator<BigInteger> iterator1;
	  private RIterator<BigInteger> iterator2;
	  private RIterator<BigInteger> iterator3;



	  /**
	   * Set up methods before test. 
	   */
	  @Before
	    public void setUp() {
		  iterator1 = new LookAndSayIterator();
		  
		  BigInteger a = new BigInteger("3");
		  iterator2 = new LookAndSayIterator(a);
		  
		  BigInteger b = new BigInteger("3");
		  BigInteger c = new BigInteger("369");
		  iterator3 = new LookAndSayIterator(b,c);
	  }
	  
	  /**
	   * test iterator1
	   */
	  @Test
	  public void testIterator1() {
	    assertEquals("1", iterator1.next().toString());
	    assertEquals("11", iterator1.next().toString());
	    assertEquals("21", iterator1.next().toString());
	    assertEquals("1211", iterator1.next().toString());
	    assertEquals("111221", iterator1.next().toString());
	    
	    assertEquals("312211", iterator1.prev().toString());
	    assertEquals("111221", iterator1.prev().toString());
	    assertEquals("1211", iterator1.prev().toString());
	    assertEquals("21", iterator1.prev().toString());
	  }
	  
	  /**
	   * test iterator2
	   */
	  @Test
	  public void testIterator2() {
	    assertEquals("3", iterator2.next().toString());
	    assertEquals("13", iterator2.next().toString());
	    assertEquals("1113", iterator2.next().toString());
	    assertEquals("3113", iterator2.next().toString());
	    assertEquals("132113", iterator2.next().toString());
	    
	    assertEquals("1113122113", iterator2.prev().toString());
	    assertEquals("132113", iterator2.prev().toString());
	    assertEquals("3113", iterator2.prev().toString());
	    assertEquals("1113", iterator2.prev().toString());
	  }
	  
	  /**
	   * test iterator3
	   */
	  @Test
	  public void testIterator3() {
	    assertEquals("3", iterator3.next().toString());
	    assertEquals("13", iterator3.next().toString());
	    assertEquals("1113", iterator3.next().toString());
	    assertEquals("1113", iterator3.next().toString());
	    assertEquals("1113", iterator3.next().toString());
	    
	    assertEquals("1113", iterator3.prev().toString());
	    assertEquals("13", iterator3.prev().toString());
	    assertEquals("3", iterator3.prev().toString());
	    assertEquals("3", iterator3.prev().toString());
	  }
	  
	  /**
	   * test invalid arguments for iterator2
	   */
	  @Test(expected = IllegalArgumentException.class)
	  public void testIfInvaliditerator2One() {
		  BigInteger a = new BigInteger("-3");
		  iterator2 = new LookAndSayIterator(a);
	  }
	  
	  /**
	   * test invalid arguments for iterator2
	   */
	  @Test(expected = IllegalArgumentException.class)
	  public void testIfInvaliditerator2Two() {
		  BigInteger a = new BigInteger("100");
		  iterator2 = new LookAndSayIterator(a);
	  }
	  
	  /**
	   * test invalid arguments for iterator2
	   */
	  @Test(expected = IllegalArgumentException.class)
	  public void testIfInvaliditerator2Three() {
		  BigInteger a = new BigInteger("99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		  iterator2 = new LookAndSayIterator(a);
	  }
	  
	  /**
	   * test invalid arguments for iterator3
	   */
	  @Test(expected = IllegalArgumentException.class)
	  public void testIfInvaliditerator3One() {
		  BigInteger a = new BigInteger("-3");
		  BigInteger b = new BigInteger("123");
		  iterator3 = new LookAndSayIterator(a,b);
	  }
	  
	  /**
	   * test invalid arguments for iterator3
	   */
	  @Test(expected = IllegalArgumentException.class)
	  public void testIfInvaliditerator3Two() {
		  BigInteger a = new BigInteger("100");
		  BigInteger b = new BigInteger("123");
		  iterator3 = new LookAndSayIterator(a,b);
	  }
	  
	  /**
	   * test invalid arguments for iterator3
	   */
	  @Test(expected = IllegalArgumentException.class)
	  public void testIfInvaliditerator3Three() {
		  BigInteger a = new BigInteger("99999");
		  BigInteger b = new BigInteger("123");
		  iterator3 = new LookAndSayIterator(a,b);
	  }
	  
	  
	  

}
