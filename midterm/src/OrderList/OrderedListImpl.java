package OrderList;

public class OrderedListImpl <E extends Comparable<E>> implements OrderedList <E>{
    private int size;
    private int capacity;
    private OrderedListNode<E> root;

    public OrderedListNode<E> getRoot(){
        return this.root;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public OrderedListImpl(int cap)
    {
        if (cap < 0 ) {
            throw new IllegalArgumentException("cap must be positive. \\n");
        }
        this.capacity = cap;


        root = null;
        size = 0;
    }



    public String remove(E val){
        if(root == null){
            return "Element not found";
        } else {
            if (val.compareTo(root.val) == 0) {
                root=root.next;
                return "Element Succesfully removed";
            } else {
                return root.remove(val);
            }
        }
    }


    @Override
    public String toString()
    {
        String s = "";
        int times = 0;

        OrderedListNode current = root;

        while (current != null)
        {
            s += current.val.toString();

            if (current.next != null)
            {
                s += " ";
            }
            times++;
            current = current.next;
        }

        for (int i=0; i<capacity-times; i++) {
            s += " ?";
        }

        return s;
    }



    @Override
    public E getMax() {
        if (root == null) {
            return null;
        } else {
            OrderedListNode current = root;
            while (current != null && current.next != null) {
                current = current.next;
            }
            return (E) current.getVal();
        }
    }


    @Override
    public OrderedList merge(OrderedList other) {
        int newCapacity = this.capacity + other.getCapacity();
        OrderedList newList = new OrderedListImpl(newCapacity);

        OrderedListNode current1 = this.root;
        while (current1 != null) {
            newList.add(current1.val);
            current1 = current1.next;
        }

        //System.out.println(newList.toString() + 11);

        OrderedListNode current2 = other.getRoot();
        while (current2 != null) {
            newList.add(current2.val);
            current2 = current2.next;
        }

        //System.out.println(newList.toString());

        return newList;
    }

    @Override
    public void resize(int newCapacity) {
        capacity = newCapacity;
        OrderedList newList = new OrderedListImpl(newCapacity);
        OrderedListNode current = root;
        while (current != null) {
            newList.add(current.val);
            current = current.next;
        }
        //System.out.println(newList.toString());
        this.root = newList.getRoot();

    }


    @Override
    public void add(E val) {
        boolean full = (size >= capacity);

        if (root == null) {
            root = new OrderedListNode(val);
        } else {
            OrderedListNode<E> newNode = new OrderedListNode<>(val);
            if (val.compareTo(root.val) < 0) {
                newNode.next = root;
                root = newNode;
            } else {
                root.insert(newNode);
            }
        }


        if (full){
            this.remove(root.val);
        }
        size ++;

    }

    /*
    public static void main(String[] args) {
        OrderedList list = new OrderedListImpl(5);
        list.add(5);
        list.add(2);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(5);



        OrderedList list1 = new OrderedListImpl(5);
        list1.add(3);
        list1.add(6);
        list1.add(1);
        System.out.println(list1.toString());

        OrderedList list2 = list1.merge(list);

        //System.out.println(list2.getMax());
        //list2.resize(3);

    }

     */


}
