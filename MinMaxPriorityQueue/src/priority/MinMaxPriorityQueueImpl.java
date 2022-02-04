package priority;

import java.util.*;

public class MinMaxPriorityQueueImpl<T> implements MinMaxPriorityQueue<T>{
    
	private final TreeMap<Integer, Queue<T>> orderdMap;
    
	/**
	 * Constructor
	 */
	public MinMaxPriorityQueueImpl() {
        this.orderdMap = new TreeMap<>();
    }

    @Override
    public void add(T item, int priority) {
        if (!this.orderdMap.containsKey(priority)) {
            this.orderdMap.put(priority, new LinkedList<>());
        }
        this.orderdMap.get(priority).add(item);
    }

    @Override
    public T minPriorityItem() {
        if (this.orderdMap.isEmpty()) {
            return null;
        }
        
        Integer minKey = this.orderdMap.firstKey();
        T result = this.orderdMap.get(minKey).element();
        this.orderdMap.get(minKey).remove();
        
        if (this.orderdMap.get(minKey).size() == 0) {
            this.orderdMap.remove(minKey);
        }
        
        return result;
    }

    @Override
    public T maxPriorityItem() {
        if (this.orderdMap.isEmpty()) {
            return null;
        }
        
        Integer maxKey = this.orderdMap.lastKey();
        T result = this.orderdMap.get(maxKey).element();
        this.orderdMap.get(maxKey).remove();
        
        if (this.orderdMap.get(maxKey).isEmpty()) {
            this.orderdMap.remove(maxKey);
        }
        
        return result;
    }
}