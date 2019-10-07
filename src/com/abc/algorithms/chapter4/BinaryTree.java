package com.abc.algorithms.chapter4;

import java.util.Random;

public class BinaryTree {
	static class Node<U> {
		U data;
		Node<U> left;
		Node<U> right;

		Node(U data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

		U getData() {
			return this.data;
		}

		Node<U> getLeft() {
			return this.left;
		}

		Node<U> getRight() {
			return this.right;
		}
	}

	static Node<Integer> binaryTree() {
		Node<Integer> zero = new Node<>(0);
		Node<Integer> one = new Node<>(1);
		Node<Integer> two = new Node<>(2);
		Node<Integer> three = new Node<>(3);
		Node<Integer> four = new Node<>(4);
		Node<Integer> five = new Node<>(5);
		Node<Integer> six = new Node<>(6);
		Node<Integer> seven = new Node<>(7);
		Node<Integer> eight = new Node<>(8);

		zero.left = one;
		zero.right = two;

		one.left = three;
		one.right = four;

		three.right = five;

		four.left = six;
		four.right = seven;

		seven.left = eight;

		return zero;
	}

	static Node<Integer> randomGenerated() {
		Random random = new Random();
		
		Node<Integer> node = new Node<>(random.nextInt(20));

		if (random.nextBoolean()) {
			node.left = binaryTree();
		} else {
			node.right = binaryTree();
		}

		return node;
	}
}
