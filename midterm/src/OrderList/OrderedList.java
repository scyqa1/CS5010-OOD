package OrderList;
public interface OrderedList<E extends Comparable<E>> {
    // Return the maximum element in the list.
    // It returns null if the list is empty.
    E getMax();

    // Add `val` to the ordered list.
    void add(E val);

    // Merge `other` and this list.
    // Returns a new list.
    OrderedList merge(OrderedList other);

    // Reset the capacity to `newCapacity`.
    // Truncate the smallest elements if length is larger than `newCapacity`.
    void resize(int newCapacity);

    <E extends Comparable<E>> OrderedListNode<E> getRoot();

    int getCapacity();
}