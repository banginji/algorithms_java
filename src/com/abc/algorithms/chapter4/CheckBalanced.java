package com.abc.algorithms.chapter4;

import static com.abc.algorithms.chapter4.BinaryTree.Node;
import static com.abc.algorithms.chapter4.BinaryTree.binaryTree;

public class CheckBalanced {
	public static void main(String[] args) {
		System.out.println("Checking if binary tree is balanced...");

		checkBalanced();
	}

	private static void checkBalanced() {
		Node<Integer> rootNode = binaryTree();

		System.out.println("Left height: " + height(rootNode.left));
		System.out.println("Right height: " + height(rootNode.right));
	}

	private static int height(Node node) {
		int height = 0;
		
		if (node.left != null) {
			height += height(node.left);
			height++;
		}

		if (node.right != null) {
			height += height(node.right);
			height++;
		}

		return height;
	}
}
