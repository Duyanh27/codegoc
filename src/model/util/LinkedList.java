package src.model.util;

import model.util.List;

public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> current; // Pointer for iteration
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public LinkedList() {
        head = null;
        current = null;
        size = 0;
    }

    @Override
    public boolean insertAt(int index, T value) {
        if (index < 0 || index > size) {
            return false;
        }
        Node<T> newNode = new Node<>(value);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> prev = getNode(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
        return true;
    }

    private Node<T> getNode(int index) {
        Node<T> x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    @Override
    public boolean insertBefore(T searchValue, T value) {
        if (head == null) return false;
        if (head.data.equals(searchValue)) {
            return insertAt(0, value);
        }
        Node<T> prev = null, current = this.head;
        while (current != null && !current.data.equals(searchValue)) {
            prev = current;
            current = current.next;
        }
        if (current == null) return false;
        Node<T> newNode = new Node<>(value);
        newNode.next = current;
        if (prev != null) prev.next = newNode;
        size++;
        return true;
    }

    @Override
    public boolean insertAfter(T searchValue, T value) {
        Node<T> current = head;
        while (current != null && !current.data.equals(searchValue)) {
            current = current.next;
        }
        if (current == null) return false;
        Node<T> newNode = new Node<>(value);
        newNode.next = current.next;
        current.next = newNode;
        size++;
        return true;
    }

    @Override
    public boolean removeAt(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> prev = getNode(index - 1);
            prev.next = prev.next.next;
        }
        size--;
        return true;
    }

    @Override
    public boolean remove(T value) {
        if (head == null) return false;
        if (head.data.equals(value)) {
            head = head.next;
            size--;
            return true;
        }
        Node<T> current = head, prev = null;
        while (current != null && !current.data.equals(value)) {
            prev = current;
            current = current.next;
        }
        if (current == null) return false;
        prev.next = current.next;
        size--;
        return true;
    }

    @Override
    public boolean contains(T value) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            return null;
        }
        T data = current.data;
        current = current.next;
        return data;
    }

    @Override
    public void reset() {
        current = head;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> node = getNode(index);
        return node.data;
    }
}
