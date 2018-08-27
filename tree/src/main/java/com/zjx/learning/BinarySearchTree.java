package com.zjx.learning;

public class BinarySearchTree<T extends Comparable<? super T>> {

    private BinaryNode<T> root;

    public BinarySearchTree() {}

    public boolean contains(T data) {
        return contains(data, root);
    }

    public T getMin() {
        return getMin(root).data;
    }

    public T getMax() {
        return getMax(root).data;
    }

    public void insert(T data) {
        root = insert(data, root);
    }

    public void remove(T data) {
        root = remove(data, root);
    }

    private BinaryNode<T> remove(T data, BinaryNode<T> node) {

        if (node == null) {
            return null;
        }

        int compareResult = data.compareTo(node.data);

        if (compareResult < 0) {
            node.left = remove(data, node.left);
        } else if (compareResult > 0) {
            node.right = remove(data, node.right);
        } else if (node.left != null && node.right != null) {//有两个儿子

            BinaryNode<T> min = getMin(node.right);//找到右子树最小节点
            node.right = remove(min.data, node.right);


        } else {//有一个儿子
            node = (node.left != null) ? node.left : node.right;
        }
        return node;


    }

    private BinaryNode<T> insert(T data, BinaryNode<T> node) {

        if (node == null) {
            return new BinaryNode<>(data,null,null);
        }

        int compareResult = data.compareTo(node.data);

        if (compareResult < 0) {
            node.left = insert(data, node.left);
        } else if (compareResult > 0) {
            node.right = insert(data, node.right);
        }

        return node;
    }

    private BinaryNode<T> getMax(BinaryNode<T> node) {

        if (node != null) {
            while (node.right != null) {
                node = node.right;
            }
        }
        return node;

    }

    private BinaryNode<T> getMin(BinaryNode<T> node) {

        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node;
        } else {
            return getMin(node.left);
        }

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

        public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }


}
