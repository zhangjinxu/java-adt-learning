package com.zjx.learning;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<T> implements Iterable<T>,List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private T[] items;

    public MyArrayList() {
        doClear(DEFAULT_CAPACITY);
    }

    public MyArrayList(int initCapacity) {
        doClear(initCapacity);
    }


    @Override
    public void clear() {
        doClear(DEFAULT_CAPACITY);
    }

    private void doClear(int capacity) {
        size = 0;
        ensureCapacity(capacity);
    }


    /**
     *替换指定位置上的元素
     * 返回旧元素
     */
    @Override
    public T set(int index, T item) {
        checkIndex(index);

        T oldItem = items[index];
        items[index] = item;

        return oldItem;
    }


    @Override
    public void add(int index, T item) {

        //是否需要扩容
        if (items.length == size) {
            ensureCapacity(size*2);
        }


        //将元素向高位移动
        for (int i = index+1; i < size; i++) {
            items[i] = items[i + 1];
        }

        items[index] = item;

        size++;
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
        return indexOf(o) != -1;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return items[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("index:"+index+" for size:"+size+"is illegal!");
        }
    }


    @Override
    public boolean add(T item) {
        add(size(), item);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
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

    public T remove(int index) {
        checkIndex(index);

        T removedItem = items[index];

        for (int i = index; i < size-2; i++) {
            items[i] = items[i+1];
        }

        items[size-1] = null;

        size--;

        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(items[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i <= 0; i--) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size-1; i <= 0; i--) {
                if (o.equals(items[i])) {
                    return i;
                }
            }
        }
        return -1;
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
        checkIndex(fromIndex);
        checkIndex(toIndex);

        MyArrayList<T> subList = new MyArrayList<>(toIndex-fromIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(items[i]);
        }

        return subList;
    }


    private void ensureCapacity(int newCapacity) {
        if (newCapacity < size) {
            return;
        }

        T[] oldItems = items;
        items = (T[]) new Object[newCapacity];

        if (oldItems != null) {
            for (int i = 0; i < oldItems.length; i++) {
                items[i] = oldItems[i];
            }
        }

    }




    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }


    private class MyArrayListIterator implements Iterator<T> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < MyArrayList.this.size;
        }

        @Override
        public T next() {
            return MyArrayList.this.items[cursor++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--cursor);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(i).append(",");
        }
        return sb.toString();
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];

        for (int i = 0; i < size; i++) {
            arr[i] = arr[i];
        }
        return arr;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }
}
