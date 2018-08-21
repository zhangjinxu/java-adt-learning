package com.zjx.learning;

/**
 *二叉查找树
 */
public class BinarySearchTree<T extends Comparable<? super T>>{

	private BinaryNode<T> root;


	private static class BinaryNode<T> {
		private T value;
		private BinaryNode<T> left;
		private BinaryNode<T> right;
	}
}
