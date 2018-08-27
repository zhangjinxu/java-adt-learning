package com.zjx.learning;

import org.junit.Test;

public class TestBinarySearchTree {

    @Test
    public void testInsert() {
        getTestTree();
    }



    private BinarySearchTree<Integer> getTestTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(9);
        tree.insert(6);
        tree.insert(22);
        return tree;
    }
}
