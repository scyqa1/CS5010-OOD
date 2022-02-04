package test;

import priority.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MinMaxPriorityQueueImplTest {

	  private MinMaxPriorityQueue<String> strQueue;

	  @Before
	  public void setUp() {
	    strQueue = new MinMaxPriorityQueueImpl<>();
	    strQueue.add((String) "c", 2);
	    strQueue.add((String) "a", 1);
	    strQueue.add((String) "d", 1);
	    strQueue.add((String) "b", 3);
	  }

	  @Test
	  public void testAdd() {
	    strQueue.add((String) "a", 5);
	    assertEquals("a", strQueue.maxPriorityItem());
	  }
	  

	  @Test
	  public void testMinPriorityItem() {
	    assertEquals("a", strQueue.minPriorityItem());
	  }

	  @Test
	  public void testMaxPriorityItem() {
	    assertEquals("b", strQueue.maxPriorityItem());
	  }
	}
