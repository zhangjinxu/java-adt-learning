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

        Object data;

        Node next;

        Node previous;

    }

    public MyLinkedList() {
        size = 0;
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.previous = head;
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

    }

    @Override
    public T get(int index) {

        Node node = null;

        if (index < size / 2) {
            node = getNodeFromHead(index);
        }else{
            node = getNodeFromTail(index);
        }

        return (T) node.data;
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

        Node node = head.next;

        for (int i = 0; i <= index; i++) {
            node = node.next;
        }

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
        checkIndex(index);

        Node newNode = new Node();
        newNode.data = element;

        if (index < size/2) {
            Node node = getNodeFromHead(index);

            newNode.next = node;
            newNode.previous = node.previous;

            node.previous.next = newNode;
            node.previous = newNode;

        }else if(index>size/2&&index<size){
            tail.previous.next = newNode;
            tail.previous = newNode;
        }
    }

    @Override
    public T remove(int index) {
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
