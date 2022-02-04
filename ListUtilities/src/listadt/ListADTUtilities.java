package listadt;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * In order to complete an implementation of any collection, 
 * a class library will usually create a utility class 
 * consisting exclusively of  methods that operate on that collection. 
 */
public final class ListADTUtilities<T> extends ListADTImpl<T> {

  /**
   * creates a new instance of the ListADT
   * that contains all of the specified elements in the order they appeared in elements.
   */
  public static <T> ListADT<T> toList(T[] elements) throws IllegalArgumentException {
    ListADT<T> listadt = new ListADTImpl<T>();

    // throw a IllegalArgumentException if elements contains one or more null values.
    for (T element : elements) {
      if (element == null) {
        throw new IllegalArgumentException();
      } else {
    	listadt.addBack(element);
      }
    }

    return listadt;
  }

  /**
   * This adds the specified elements to the end of the specified list.
   * Elements should be added in the same order they appear in elements.
   * 
   */
  public static <T> void addAll(ListADT<T> listadt, T... elements) throws IllegalArgumentException {
	  //throw a IllegalArgumentException If elements contains one more more null values.
	  for (T element : elements) {
      if (element == null) {
        throw new IllegalArgumentException();
      } else {
    	  listadt.addBack(element);
      }
    }
  }

  /**
   * returns the number of elements in the specified list equal to the specified element.
   * More formally, it should return the number of elements in the list 
   * such that (o == null) ? e == null : o.equals(e))
   *
   */
  public static <T> int frequency(ListADT<T> listadt, T element) {
    int counter = 0;

    for (int i = 0; i < listadt.getSize(); i++) {
      if (listadt.get(i) == element) {
        counter += 1;
      }
    }

    return counter;
  }

  /**
   * returns true if the two lists have no elements in common.
   *
   */
  public static<T> boolean disjoint(ListADT<? extends T> first, ListADT<? extends T> second) throws IllegalArgumentException {
	//throw a IllegalArgumentException If either list is null or if either list contains a null element.
	if (first == null || second == null) {
        throw new IllegalArgumentException();
    }
	  
    Set<T> tempSet = new HashSet<>();

    int first_size = first.getSize();
    int second_size = second.getSize();

    for (int i = 0; i < first_size; i++) {
      T oneNode = first.get(0);
      
      if (oneNode == null) {
        throw new IllegalArgumentException();
      }

      tempSet.add(oneNode);
      first.getNext();
    }

    
    for (int i = 0; i < second_size; i++) {
      T twoNode = second.get(0);
      if (twoNode == null) {
        throw new IllegalArgumentException();
      }
      
      if (tempSet.contains(twoNode)) {
          return false;
        }
      second.getNext();
    }

    return true;
  }


  /**
   * returns true if the two lists are equal.
   * Two lists are equal if they have the same elements in the same order.
   *
   */
  public static boolean equals(ListADT<?> first, ListADT<?> second) throws IllegalArgumentException {
	String firstStr = "";
	String secondStr = "";

	 //throw a IllegalArgumentException if either list is null or if either list contains a null element.
	if (first == null || second == null) {
	  throw new IllegalArgumentException();
	}

    for (int i = 0; i < first.getSize(); i++) {
      if (first.get(i) == null) {
        throw new IllegalArgumentException();
      }

      firstStr += first.get(i).toString();
    }

    for (int i = 0; i < second.getSize(); i++) {
      if (second.get(i) == null) {
        throw new IllegalArgumentException();
      }

      secondStr += second.get(i).toString();
    }


    return firstStr.equals(secondStr);
  }


  /**
   * swaps the elements at the specified position in the specified list.
   *
   */
  public void swap(ListADT<?> listadt, int i, int j) throws IndexOutOfBoundsException {
	//throw an IndexOutOfBoundsException If either i or j are out of range.
	if (i < 0 || j < 0 || i >= listadt.getSize() || j >= listadt.getSize()) {
      throw new IndexOutOfBoundsException();
    }

    T left = this.get(i);
    T right = this.get(j);
    this.remove(this.get(i));
    this.remove(this.get(j));

    this.add(j, left);
    this.add(i, right);

  }
}