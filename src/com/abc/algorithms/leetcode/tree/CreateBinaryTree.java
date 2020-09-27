package com.abc.algorithms.leetcode.tree;

public class CreateBinaryTree {
    public static class TreeNode {
        public Integer val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(Integer val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public TreeNode createTree(Integer[] nodes) {
        TreeNode[] bTreeArray = new TreeNode[nodes.length];

        TreeNode root = new TreeNode(nodes[0]);
        bTreeArray[0] = root;

        int leftChildIdx = 1;

        while (leftChildIdx < nodes.length) {
            TreeNode parent = bTreeArray[getBTreeParentPosition(leftChildIdx)];

            int rightChildIdx = leftChildIdx + 1;

            parent.left = new TreeNode(nodes[leftChildIdx]);
            bTreeArray[leftChildIdx] = parent.left;

            if (rightChildIdx < nodes.length) {
                parent.right = new TreeNode(nodes[rightChildIdx]);
                bTreeArray[rightChildIdx] = parent.right;
            }

            leftChildIdx += 2;
        }

        return root;
    }

    private int getBTreeParentPosition(int position) {
        if (position == 0)
            return -1;

        return (position - 1) / 2;
    }

    public static boolean isLeafNode(TreeNode node) {
        boolean isLeafWithNoChildNodes = node.left == null && node.right == null;

        boolean leftChildNullCheck = node.left != null && node.left.val == null;
        boolean rightChildNullCheck = node.right != null && node.right.val == null;
        boolean isLeafWithNullChildNodes = leftChildNullCheck && rightChildNullCheck;

        return isLeafWithNoChildNodes || isLeafWithNullChildNodes;
    }
}
