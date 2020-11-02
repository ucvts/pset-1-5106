import java.util.List;

public class SimpleArrayList {
    
    private final static int DEFAULT_CAPACITY = 10;
    
    private int size;
    private int capacity;
    private String[] data;
    
    public SimpleArrayList() {
        this(DEFAULT_CAPACITY);
    }
    
    public SimpleArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        
        this.size = 0;
        this.capacity = initialCapacity;
        this.data = new String[initialCapacity];
    }
    
    public SimpleArrayList(List<String> list) {
        if (list == null) {
            throw new NullPointerException();
        }
        
        this.size = list.size();
        this.capacity = list.size();
        this.data = new String[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            this.data[i] = list.get(i);
        }
    }
    
    public void add(int index, String s) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else if (size == capacity) {
            increaseCapacity(10);
        }
        
        for (int i = size - 1; i > index - 1; i--) {
            data[i + 1] = data[i];
        }
        
        data[index] = s;
        size++;
    }
    
    public boolean add(String s) {
        add(size, s);
        
        return true;
    }
    
    public void clear() {
        size = 0;
        capacity = 0;
        data = new String[10];
    }
    
    public boolean contains(String s) {
        if (s == null) {
            return false;
        }
        
        for (int i = 0; i < size; i++) {
            if (data[i].equals(s)) {
                return true;
            }
        }
        
        return false;
    }
    
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        
        return data[index];
    }
    
    public int indexOf(String s) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(s)) {
                return i;
            }
        }
        
        return -1;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        
        String s = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null;
        
        if (capacity != size) {
            trimToSize();
        }
        
        return s;
    }
    
    public boolean remove(String s) {
        int index = indexOf(s);
        
        if (index == -1) {
            return false;
        }
        remove(index);
        
        return true;
    }
    
    public String set(int index, String s) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        
        String orig = get(index);
        remove(index);
        add(index, s);
        
        return orig;
    }
    
    public int size() {
        return size;
    }
    
    public void trimToSize() {      
        capacity = size;
        
        String[] data = this.data;
        this.data = new String[size];
        
        for (int i = 0; i < size; i++) {
            this.data[i] = data[i];
        }       
    }
        
    @Override
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                s = s + data[i];
            } else {
                s = s + data[i] + ", ";
            }
        }
        
        return s + "]";
    }
            
    private void increaseCapacity(int amount) {
        String[] data = new String[capacity + amount];
        
        for (int i = 0; i < size; i++) {
            data[i] = this.data[i];
        }
        
        this.data = data;
        capacity = data.length;
    }
}
