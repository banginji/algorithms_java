package com.abc.algorithms.leetcode.tree;

import com.abc.algorithms.leetcode.tree.CreateBinaryTree.TreeNode;

public class _938_RangeSumBst {
    private static int rangeSum(TreeNode root, int low, int high) {
        return traversal(root, low, high);
    }

    static int sum = 0;
    private static int traversal(TreeNode node, int low, int high) {
        if (node == null || node.val == null) return 0;
        if (low < node.val)
            traversal(node.left, low, high);
        if (low <= node.val && node.val <= high)
            sum += node.val;
        if (node.val < high)
            traversal(node.right, low, high);
        return sum;
    }

    public static void main(String[] args) {
        CreateBinaryTree cbt = new CreateBinaryTree();
        TreeNode root = cbt.createTree(new Integer[]{10, 5, 15, 3, 7, null, 18});
        System.out.println(rangeSum(root, 7, 15));
    }
}
