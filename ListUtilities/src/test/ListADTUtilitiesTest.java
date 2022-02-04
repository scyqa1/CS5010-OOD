package test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import listadt.ListADT;
import listadt.ListADTImpl;
import listadt.ListADTUtilities;


/**
 * Tests the ListAdtUtilities for correctness.
 */
public class ListADTUtilitiesTest {
  ListADT<String> list;
  ListADT<Integer> integerList;

  @Before
  public void setUp() {
    list = new ListADTImpl<String>();
    integerList = new ListADTImpl<Integer>();
  }

  @Test
  public void toList() {
    Integer[] arr = new Integer[]{1, 2, 3, 4};
    Integer[] emptyArr = new Integer[]{};

    ListADT<Integer> listAdt = ListADTUtilities.toList(arr);
    assertEquals("(1 2 3 4)", listAdt.toString());

    listAdt = ListADTUtilities.toList(emptyArr);
    assertEquals("()", listAdt.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalToList() {
    Integer[] arr = new Integer[]{1, 2, null, 4};
    ListADTUtilities.toList(arr);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalMoreToList() {
    Integer[] arr = new Integer[]{1, 2, null, null};
    ListADTUtilities.toList(arr);
  }

  @Test
  public void addAll() {
    list.addBack("dddd");
    list.addBack("coll");
    list.addBack("chad");

    ListADTUtilities.addAll(list, "dest", "gdse");
    assertEquals("(dddd coll chad dest gdse)", list.toString());
    ListADTUtilities.addAll(list, "blue");
    assertEquals("(dddd coll chad dest gdse blue)", list.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addAllNull() {
    list.addBack("dddd");
    list.addBack("coll");
    ListADTUtilities.addAll(list, "dest", "gdse", null, null);
  }

  @Test
  public void frequency() {
    list.addBack("dddd");
    list.addBack("coll");
    assertEquals(1, ListADTUtilities.frequency(list, "coll"));
    assertEquals(1, ListADTUtilities.frequency(list, "dddd"));
    assertEquals(0, ListADTUtilities.frequency(list, "gdse"));
    assertEquals(0, ListADTUtilities.frequency(list, null));
    list.addBack("coll");
    list.addBack("coll");
    assertEquals(3, ListADTUtilities.frequency(list, "coll"));

    ListADT<String> emptyList = new ListADTImpl<String>();
    assertEquals(0, ListADTUtilities.frequency(emptyList, "coll"));
    emptyList.addBack(null);
    emptyList.addBack("yo yo");
    emptyList.addBack("kit");
    emptyList.addBack("kit");
    emptyList.addBack("hand");
    assertEquals(2, ListADTUtilities.frequency(emptyList, "kit"));
    assertEquals(1, ListADTUtilities.frequency(emptyList, null));
    emptyList.addBack(null);
    emptyList.addBack(null);
    assertEquals(3, ListADTUtilities.frequency(emptyList, null));
  }

  @Test(expected = NullPointerException.class)
  public void testNullFrequency() {
    ListADTUtilities.frequency(null, "blue");
  }

  @Test
  public void disjoint() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack("coll");
    list.addBack("dddd");
    list.addBack("coll");
    assertFalse(ListADTUtilities.disjoint(list, newList));
  }

  @Test
  public void disjointTrue() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack("helo");
    list.addBack("dddd");
    list.addBack("coll");
    assertTrue(ListADTUtilities.disjoint(list, newList));
  }

  @Test
  public void disjointEmpty() {
    ListADT<String> newList = new ListADTImpl<String>();
    assertTrue(ListADTUtilities.disjoint(list, newList));
  }

  @Test
  public void disjointOneEmpty() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack("helo");
    assertTrue(ListADTUtilities.disjoint(list, newList));
  }

  @Test
  public void disjointTwoEmpty() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack("helo");
    assertTrue(ListADTUtilities.disjoint(list, newList));
  }

  @Test(expected = IllegalArgumentException.class)
  public void disjointIllegal() {
    assertTrue(ListADTUtilities.disjoint(list, null));
  }

  @Test(expected = IllegalArgumentException.class)
  public void disjointIllegalNull() {
    assertTrue(ListADTUtilities.disjoint(null, list));
  }

  @Test(expected = IllegalArgumentException.class)
  public void disjointNullElement() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack("helo");
    newList.addBack(null);
    list.addBack("dddd");
    list.addBack("coll");
    assertTrue(ListADTUtilities.disjoint(list, newList));
  }

  @Test(expected = IllegalArgumentException.class)
  public void disjointNullElementReversed() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack("helo");
    newList.addBack(null);
    list.addBack("dddd");
    list.addBack("coll");
    assertTrue(ListADTUtilities.disjoint(newList, list));
  }

  @Test(expected = IllegalArgumentException.class)
  public void disjointNullElementTwo() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack("helo");
    newList.addBack("dddd");
    list.addBack(null);
    list.addBack("coll");
    assertTrue(ListADTUtilities.disjoint(list, newList));
  }

  @Test(expected = IllegalArgumentException.class)
  public void disjointNullElementTwoReversed() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack("helo");
    newList.addBack("dddd");
    list.addBack(null);
    list.addBack("coll");
    assertTrue(ListADTUtilities.disjoint(newList, list));
  }

  @Test
  public void testEquals() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack("helo");
    list.addBack("gdse");
    list.addBack("helo");
    assertTrue(ListADTUtilities.equals(list, newList));
    assertTrue(ListADTUtilities.equals(newList, list));
  }

  @Test
  public void testEqualsDifferentOrder() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack("helo");
    list.addBack("uber");
    list.addBack("blue");
    assertFalse(ListADTUtilities.equals(list, newList));
  }

  @Test
  public void testEqualsEmpty() {
    ListADT<String> newList = new ListADTImpl<String>();
    list.addBack("uber");
    list.addBack("qcccc");
    assertFalse(ListADTUtilities.equals(list, newList));
  }

  @Test
  public void testEqualsTwoEmpty() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack("helo");
    assertFalse(ListADTUtilities.equals(list, newList));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEqualsNull() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack("helo");
    assertFalse(ListADTUtilities.equals(null, newList));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEqualsNullTwo() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack("helo");
    assertFalse(ListADTUtilities.equals(list, null));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEqualsNullElement() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack("helo");
    list.addBack("gdse");
    list.addBack(null);
    assertFalse(ListADTUtilities.equals(list, newList));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEqualsNullElementTwo() {
    ListADT<String> newList = new ListADTImpl<String>();
    newList.addBack("gdse");
    newList.addBack(null);
    list.addBack("gdse");
    list.addBack("helo");
    assertFalse(ListADTUtilities.equals(list, newList));
  }
}
