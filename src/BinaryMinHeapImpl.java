import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @param <V>   {@inheritDoc}
 * @param <Key> {@inheritDoc}
 */
public class BinaryMinHeapImpl<Key extends Comparable<Key>, V> implements BinaryMinHeap<Key, V> {
    
    
    private ArrayList<Entry<Key, V>> contents;
    private HashMap<V, Integer> heap;
    
    BinaryMinHeapImpl() {
        this.heap = new HashMap<V, Integer>();
        this.contents = new ArrayList<>();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return contents.size();
    }

    @Override
    public boolean isEmpty() {
        return contents.size() == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(V value) {
        return heap.containsKey(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Key key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null.");
        }
        if (this.containsValue(value)) {
            throw new IllegalArgumentException("Value already in min-heap.");
        }
        
        Entry<Key, V> placeholder = new Entry<Key, V>(key, value);
        
        contents.add(placeholder);
        heap.put(value, contents.size() - 1);
        
        decreaseKey(value, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseKey(V value, Key newKey) {
        if (!this.containsValue(value)) {
            throw new NoSuchElementException("Value is not in the heap.");
        }
        if (newKey == null) {
            throw new IllegalArgumentException("NewKey is null.");
        }
        
        int i = this.heap.get(value);        
        if (contents.get(i).key.compareTo(newKey) < 0) {
            throw new IllegalArgumentException("newKey > key(value).");
        }
        
        Entry<Key, V> element = new Entry<Key, V>(newKey, value);
        
        heap.put(value, i);
        contents.set(i, element);
        int parent = Math.floorDiv(i - 1, 2);

        while ((i > 0) && contents.get(i).key.compareTo(contents.get(parent).key) < 0) {
            this.exchange(i, parent);
            i = Math.floorDiv(i - 1, 2);
            parent = Math.floorDiv(i - 1, 2);
        }
        
        
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<Key, V> peek() { 
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return contents.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<Key, V> extractMin() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        
        Entry<Key, V> min = contents.get(0);
        
        this.heap.remove(contents.get(0).value);
        
        if (this.size() == 1) {
            return contents.remove(0);
        }
        
        this.heap.put(contents.get(this.size() - 1).value, 0);
        contents.set(0, contents.remove(this.size() - 1));
 
        
        if (this.size() > 0) {
            minHeapify(0);
        }
        
        return min;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<V> values() {
        return heap.keySet();
    }
    
    void minHeapify(int i) {
        int left = (2 * i) + 1;
        int right = (2 * i) + 2;
        
        int min = i;
        
        if (left < contents.size() && 
                (contents.get(left).key.compareTo(contents.get(min).key) < 0)) {
            min = left;
        } 
        if (right < contents.size() && 
                (contents.get(right).key.compareTo(contents.get(min).key) < 0)) {
            min = right;
        }
        
        if (min != i) {
            exchange(i, min);
            minHeapify(min);
        }
        
    }
    
    void exchange(int first, int sec) {
        this.heap.put(contents.get(first).value, sec);
        this.heap.put(contents.get(sec).value, first);
        
        Collections.swap(contents, first, sec);
    }
}