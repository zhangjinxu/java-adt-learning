package com.zjx.learning;

public class BinarySreachTree<T extends Comparable<? super T>> {

    private BinaryNode<T> root;

    public BinarySreachTree() {
        root = null;
    }


    public boolean contains(T data) {
        return contains(data, root);
    }

    private boolean contains(T data, BinaryNode<T> node) {

        if (node == null) {
            return false;
        }

        int i = data.compareTo(node.data);

        if (i < 0) {
            return contains(data, node.left);
        } else if (i > 0) {
            return contains(data, node.right);
        } else {
            return true;
        }

    }


    private static class BinaryNode<T>{

        private T data;

        private BinaryNode<T> left;

        private BinaryNode<T> right;

    }


}
