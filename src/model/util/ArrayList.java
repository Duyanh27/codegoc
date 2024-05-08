package model.util;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T>, Iterable<T> {
    private T[] items;
    private int size = 0;
    private static final int CAPACITY = 10;  // Initial capacity
    private int pointer = 0;  // For iteration within List methods

    public ArrayList() {
        items = (T[]) new Object[CAPACITY];
    }

    private void ensureCapacity() {
        if (size == items.length) {
            T[] newItems = (T[]) new Object[items.length * 2];
            java.lang.System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
    }

    public void add(T element) {
        ensureCapacity();
        items[size++] = element;
    }

    @Override
    public boolean insertAt(int index, T value) {
        if (index < 0 || index > size) {
            return false;
        }
        ensureCapacity();
        java.lang.System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = value;
        size++;
        return true;
    }

    @Override
    public boolean insertBefore(T searchValue, T value) {
        for (int i = 0; i < size; i++) {
            if (items[i] != null && items[i].equals(searchValue)) {
                return insertAt(i, value);
            }
        }
        return false;
    }

    @Override
    public boolean insertAfter(T searchValue, T value) {
        for (int i = 0; i < size; i++) {
            if (items[i] != null && items[i].equals(searchValue)) {
                return insertAt(i + 1, value);
            }
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            java.lang.System.arraycopy(items, index + 1, items, index, numMoved);
        }
        items[--size] = null;  // Clear to let GC do its work
        return true;
    }

    @Override
    public boolean remove(T value) {
        for (int i = 0; i < size; i++) {
            if (items[i] != null && items[i].equals(value)) {
                return removeAt(i);
            }
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        for (T item : this) {
            if (item != null && item.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean hasNext() {
        return pointer < size;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return items[pointer++];
    }

    @Override
    public void reset() {
        pointer = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            public boolean hasNext() {
                return currentIndex < size;
            }

            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return items[currentIndex++];
            }

            public void remove() {
                throw new UnsupportedOperationException("Remove not supported");
            }
        };
    }
}
