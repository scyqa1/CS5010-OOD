package OrderList;
public class OrderedListNode <E extends Comparable<E>>{

    public E val;
    public OrderedListNode next;
    public OrderedListNode pre;


    public E getVal() {
        return this.val;
    }

    public OrderedListNode(E val){
        this.val = val;
        this.next = null;
    }

    public void insert(OrderedListNode<E> tNode) {
        if (next == null) {
            next = tNode;
        } else if (next.val.compareTo(tNode.val) < 0) {
            next.insert(tNode);
        } else {
            tNode.next = next;
            next = tNode;
        }
    }

    public String remove(E val){
        if (next != null && next.val.compareTo(val)==0){
            next=next.next;
            return "Element succesfully removed";
        } else {
            if (next==null) {
                return "Element not found";
            }
            else{
                return next.remove(val);
            }
        }
    }


    @Override
    public String toString() {
        return val.toString();
    }
}
