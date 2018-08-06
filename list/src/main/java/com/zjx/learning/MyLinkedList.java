package com.zjx.learning;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<T> implements Iterable<T>,List<T> {

    private int size;

    private Node head;

    private Node tail;

    private class Node{

        T data;

        Node next;

        Node previous;

        Node() {}

        Node(T data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }

    public MyLinkedList() {
        clear();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (indexOf(o) != -1) {
            return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        add(size,t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.previous = head;
    }

    @Override
    public T get(int index) {

        Node node = null;

        if (index < size / 2) {
            node = getNodeFromHead(index);
        }else{
            node = getNodeFromTail(index);
        }

        return node.data;
    }

    private Node getNode(int index) {
        checkIndex(index);
        if (index < size / 2) {
            return getNodeFromHead(index);
        }
        return getNodeFromTail(index);
    }

    private Node getNodeFromHead(int index) {

        checkIndex(index);

        Node node = head.next;

        for (int i = 0; i <= index; i++) {
            node = node.next;
        }
        return node;
    }

    private Node getNodeFromTail(int index) {
        checkIndex(index);

        Node node = tail.previous;

        for (int i = size - 1; i <= index; i++) {
            node = node.previous;
        }
        return node;
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);

        Node node = null;

        node = getNode(index);

        node.data = element;

        return element;
    }

    private void checkIndex(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void add(int index, T element) {

        Node node = getNode(index);

        Node newNode = new Node();
        newNode.data = element;


        newNode.next = node;
        newNode.previous = node.previous;

        node.previous.next = newNode;
        node.previous = newNode;

    }

    @Override
    public T remove(int index) {

        Node node = getNode(index);

        node.previous.next = node.next;
        node.next.previous = node.previous;

        return node.data;
    }

    @Override
    public int indexOf(Object o) {

        if (size == 0) {
            return -1;
        }

        Node node = head;

        if (o == null) {
            for (int i = 0; i < size; i++) {
                node = node.next;
                if (node.data == null) {
                    return i;
                }
            }
        }else{
            for (int i = 0; i < size; i++) {
                node = node.next;
                if (o.equals(node.data)) {
                    return i;
                }
            }
        }


        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
