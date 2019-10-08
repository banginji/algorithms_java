package com.abc.algorithms.chapter4;

import static com.abc.algorithms.chapter4.BinaryTree.Node;
import static com.abc.algorithms.chapter4.BinaryTree.binaryTree;

public class CheckBalanced {
	public static void main(String[] args) {
		System.out.println("Checking if binary tree is balanced...");

		if(checkBalanced()) {
			System.out.println("The binary tree is balanced");
		} else {
			System.out.println("The binary tree is NOT balanced");
		}
	}

	private static boolean checkBalanced() {
		Node<Integer> rootNode = binaryTree();

		System.out.println("Left height: " + height(rootNode.left));
		System.out.println("Right height: " + height(rootNode.right));

		return Math.abs(height(rootNode.left) - height(rootNode.right)) <= 1;
	}

	private static int height(Node node) {
		int leftHeight = -1, rightHeight = -1;
		
		if (node.left != null) {
			leftHeight = height(node.left);
			leftHeight++;
		} else {
			leftHeight++;
		}

		if (node.right != null) {
			rightHeight = height(node.right);
			rightHeight++;
		} else {
			rightHeight++;
		}

		return Math.max(leftHeight, rightHeight);
	}
}
