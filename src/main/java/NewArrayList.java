import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NewArrayList<T> implements List {
    private static final Logger log = Logger.getLogger(NewArrayList.class);


    private int capacity;
    private int defaultCapacity = 16;
    private int size = 0;
    private T array[];


    public NewArrayList(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];

    }

    public NewArrayList() {
        this.capacity = defaultCapacity;
        this.array = (T[]) new Object[defaultCapacity];

    }

    @Override
    public boolean add(Object o) {

        if (size < capacity) {
            this.array[this.size++] = (T) o;
            return true;
        } else {
            T tempArray[];
            tempArray = (T[]) new Object[capacity];
            this.capacity = (capacity * 3) / 2 + 1;
            tempArray = this.array;
            this.array = (T[]) new Object[capacity];
            this.array = tempArray;
            this.array[this.size++] = (T) o;

        }
        log.error("Object " + o.toString() + " didn't add");
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && array[currentIndex] != null;
            }

            @Override
            public T next() {
                return array[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
        return it;
    }

    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(array[i])) {
                for (int j = i; j < size; j++) {
                    array[i] = array[i + 1];
                }
                size--;
                return true;
            } else {
                log.info("Object for remove  " + o.toString() + " not found");
                return true;

            }
        }
        log.error("Removing object " + o.toString() + " finished incorrect");
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        if (c.size() <= freeSpace(this.capacity, this.size)) { //  more free space than added items

            for (Object o : c) {
                array[size++] = (T) o;

            }

            log.info("Objects added  successfully");
            return true;
        } else {
            T tempArray = (T) this.array;

            array = (T[]) new Object[(c.size() + this.size) + 1];//
            log.info("Capacity increased");
            array = (T[]) tempArray;
            addAll(c);

        }
        log.error("addAll(Collection c) -  runtime error");
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {

        if ((freeSpace(this.capacity, this.size)) >= (numberOfItemsAdded(index, c.size()))) {  //  more free space than added items
            int temp = 0;
            for (Object o : c) {
                temp++;
                if (temp >= index) {
                    array[size++] = (T) o;
                }
                log.info("Objects added  successfully");
                return true;
            }
        } else {
            T tempArray = (T) this.array;
            array = (T[]) new Object[(c.size() + numberOfItemsAdded(index, c.size())) + 1];//
            log.info("Capacity increased");
            array = (T[]) tempArray;
            addAll(index, c);
        }
        log.error("addAll(int index, Collection c) -  runtime error");
        return false;
    }

    private int numberOfItemsAdded(int index, int size) {
        return size - index;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            this.array[i] = null;

        this.size = 0;

    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    private int freeSpace(int p, int p2) {
        return p - p2;
    }
}
