package com.abc.algorithms.leetcode.tree;

public class _337_HouseRobberIII {
    private static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode(Integer val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private static int rob(TreeNode node) {
        int[] ans = dfs(node);

        return Math.max(ans[0], ans[1]);
    }

    private static int[] dfs(TreeNode node) {
        if (node == null || node.val == null)
            return new int[]{0, 0};

        int[] leftAmount = dfs(node.left);
        int[] rightAmount = dfs(node.right);

        int noRobMax = Math.max(leftAmount[0], leftAmount[1]) + Math.max(rightAmount[0], rightAmount[1]);
        int robMax = leftAmount[0] + rightAmount[0] + node.val;

        return new int[]{robMax, noRobMax};
    }

    private static TreeNode createTree(Integer[] nodes) {
        TreeNode[] bTreeArray = new TreeNode[nodes.length];

        TreeNode root = new TreeNode(nodes[0]);
        bTreeArray[0] = root;

        for (int idx = 1; idx < nodes.length; idx += 2) {
            TreeNode parent = bTreeArray[getBTreeParentPosition(idx)];
            parent.left = new TreeNode(nodes[idx]);
            bTreeArray[idx] = parent.left;
            if (idx + 1 < nodes.length) {
                parent.right = new TreeNode(nodes[idx + 1]);
                bTreeArray[idx + 1] = parent.right;
            }
        }

        return root;
    }

    private static int getBTreeParentPosition(int position) {
        if (position == 0)
            return -1;

        return (position - 1) / 2;
    }

    public static void main(String[] args) {
        TreeNode root = createTree(new Integer[]{3, 2, 3, null, 3, null, 1});
        System.out.println(rob(root));
    }
}
