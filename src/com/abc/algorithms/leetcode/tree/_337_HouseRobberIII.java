package com.abc.algorithms.leetcode.tree;

import com.abc.algorithms.leetcode.tree.CreateBinaryTree.TreeNode;

public class _337_HouseRobberIII {
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

    public static void main(String[] args) {
        CreateBinaryTree createBinaryTree = new CreateBinaryTree();
        TreeNode root = createBinaryTree.createTree(new Integer[]{3, 2, 3, null, 3, null, 1});
        System.out.println(rob(root));
    }
}
