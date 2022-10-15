import java.util.Set;
import java.util.NoSuchElementException;


public interface BinaryMinHeap<Key extends Comparable<Key>, V> {

    /**
     * Runtime: O(1)
     *
     * @return the number of elements in the min-heap
     */
    int size();

    /**
     * Runtime: O(1)
     *
     * @return true if the heap is empty
     */
    boolean isEmpty();

    /**
     * Runtime: expected O(1)
     *
     * @param value the value to check, may be null
     * @return true if the min-heap contains the specified value
     */
    boolean containsValue(V value);

    /**
     * Runtime: expected O(log n)
     *
     * @param value the value to insert into the heap,
     *              may be null
     * @param key   the priority key to associate with the
     *              value, must be non-null
     * @throws IllegalArgumentException if key is null
     * @throws IllegalArgumentException if value is already in the min-heap
     */
    void add(Key key, V value);

    /**
     * Updates the key of a particular value in the min-heap
     * to a smaller key.
     * <p>
     * Runtime: expected O(log n)
     *
     * @param value  the value whose associated key to update
     * @param newKey the key to update value with
     * @throws NoSuchElementException   if value is not in the heap
     * @throws IllegalArgumentException if newKey is null or newKey > key(value)
     */
    void decreaseKey(V value, Key newKey);

    /**
     * Runtime: O(1)
     *
     * @return the entry corresponding to the smallest key in the min-heap
     * @throws NoSuchElementException if the heap is empty
     */
    Entry<Key, V> peek();

    /**
     * Removes the value with the smallest key in the min-heap.
     * Ties broken arbitrarily
     * <p>
     * Runtime: expected O(log n)
     *
     * @return any entry corresponding to the smallest key in the min-heap
     * @throws NoSuchElementException if the min-heap is empty
     */
    Entry<Key, V> extractMin();

    /**
     * Runtime: O(n)
     *
     * @return an unordered Set containing all the values in the heap
     */
    Set<V> values();

    /**
     * Helper entry class for maintaining value-key pairs.
     */
    class Entry<Key, V> {
        public final Key key;
        public final V value;

        public Entry(Key k, V v) {
            key = k;
            value = v;
        }
    }
}
