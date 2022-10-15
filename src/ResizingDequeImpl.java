import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingDequeImpl<E> implements ResizingDeque<E> {
    
    private E[] deque;
    private int size;
    private int capacity;
    private int head;
    private int tail;
    
    public ResizingDequeImpl() {
        this.deque = (E[]) new Object[2];
        this.size = 0;
        this.capacity = 2;
        this.head = 0;
        this.tail = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E[] getArray() {
        E[] arr = (E[]) new Object[capacity];
        for (int i = 0; i < deque.length; i++) {
            arr[i] = deque[i];
        }
        return arr;
    }

    @Override
    public void addFirst(E e) {
        if (this.size == capacity) {
            resizeUp(true);
            deque[0] = e;
            this.head = 0;
        } else {
            if (this.size == 0) {
                deque[0] = e;
            } else if (head == 0) {
                deque[capacity - 1] = e;
                this.head = this.capacity - 1;
            } else {
                deque[head - 1] = e;
                this.head--;
            }
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        if (this.size == capacity) {
            resizeUp(false);
        }
        
        if (this.size == 0) {
            deque[0] = e;
        } else if (tail == capacity - 1) {
            deque[0] = e;
            this.tail = 0;
        } else {
            deque[tail + 1] = e;
            this.tail++;
        }
        
        size++;
    }

    @Override
    public E pollFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        
        if (this.size - 1 < deque.length / 4) {
            resizeDown(true);
        }
        
        E removed = deque[head];
        deque[head] = null;
        if (head == this.capacity - 1) {
            head = 0;
        } else {
            head++;
        }
        this.size--;
        if (this.size == 0) {
            this.head = 0;
            this.tail = 0;
        }
        
        return removed;
    }

    @Override
    public E pollLast() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        
        if (this.size - 1 < deque.length / 4) {
            resizeDown(false);
        }
        
        E removed = deque[tail];
        deque[tail] = null;
        if (tail == 0) {
            tail = this.capacity - 1;
        } else {
            tail--;
        }
        this.size--;
        if (this.size == 0) {
            this.head = 0;
            this.tail = 0;
        }
        return removed;
    }

    @Override
    public E peekFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        
        return deque[head];
    }

    @Override
    public E peekLast() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        
        return deque[tail];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int curr = head;
            
            @Override
            public boolean hasNext() {
                return curr != tail;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    if (curr == capacity) {
                        curr = 0;
                        return deque[0];
                    }
                    return deque[++curr];
                } else {
                    throw new NoSuchElementException();
                }
                
            }
            
        };
    }
    
    void resizeUp(boolean addingToFront) {
        E[] newDeque = (E[]) new Object[this.capacity * 2];
        
        int location = this.head;
        int startPoint;
        if (addingToFront) {
            startPoint = 1;
        } else {
            startPoint = 0;
        }
        
        for (int i = startPoint; i < this.size + startPoint; i++) {
            newDeque[i] = deque[location];
            if (location == this.capacity - 1) {
                location = 0;
            } else {
                location++;
            }
        }
        
        this.head = 0;
        this.tail = this.size - 1 + startPoint;
        this.capacity *= 2;
        
        deque = newDeque;
    }
    
    void resizeDown(boolean pollingFromFront) {
        E[] newDeque = (E[]) new Object[this.capacity / 2];
        
        int location = this.head;
        int deduction = 0;
        if (pollingFromFront) {
            newDeque[(capacity / 2) - 1] = deque[head];
            if (location == this.capacity - 1) {
                location = 0;
            } else {
                location++;
            }
            deduction = 1;
        }
       
        for (int i = 0; i < this.size - deduction; i++) {
            newDeque[i] = deque[location];
            if (location == this.capacity - 1) {
                location = 0;
            } else {
                location++;
            }
        }
        
        if (pollingFromFront) {
            this.head = (capacity / 2) - 1;
        } else {
            this.head = 0;
        }
        
        this.tail = this.size - 1 - deduction; 
        this.capacity /= 2;
        
        deque = newDeque;
        
    }
    
    Boolean isEmpty() {
        return this.size == 0;
    }

}
