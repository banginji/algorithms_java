package com.abc.algorithms.leetcode;

import com.abc.algorithms.datastructure.BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _606_StringBinaryTree {
    private static String tree2String(TreeNode<Integer> t) {
        StringBuilder stringBuilder = new StringBuilder();
        inOrderTraversal(t, stringBuilder);

        return stringBuilder.toString();
    }

    private static void inOrderTraversal(TreeNode<Integer> node, StringBuilder stringBuilder) {
        if (node != null && node.getData() != null)
            stringBuilder.append(node.getData());
        else {
            return;
        }
        stringBuilder.append("(");
        inOrderTraversal(node.getLeftChild(), stringBuilder);
        stringBuilder.append(")");
        stringBuilder.append("(");
        inOrderTraversal(node.getRightChild(), stringBuilder);
        stringBuilder.append(")");
    }

    private static TreeNode<Integer> binaryTree(Integer[] nums) {
        TreeNode<Integer> root = new TreeNode<>(nums[0]);

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);

        int idx = 1;
        while (idx < nums.length) {
            TreeNode<Integer> currentNode = queue.remove();

            if (nums[idx] != null)
                currentNode.setLeftChild(new TreeNode<>(nums[idx]));
            if (nums[idx + 1] != null && idx + 1 < nums.length)
                currentNode.setRightChild(new TreeNode<>(nums[idx + 1]));

            queue.add(currentNode.getLeftChild());
            queue.add(currentNode.getRightChild());

            idx += 2;
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = binaryTree(new Integer[]{1, 2, 3, null, 4});
        System.out.println(tree2String(root));
    }
}
