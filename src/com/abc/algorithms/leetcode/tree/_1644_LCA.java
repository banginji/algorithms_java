package com.abc.algorithms.leetcode.tree;

import com.abc.algorithms.leetcode.tree.CreateBinaryTree.TreeNode;

import java.util.*;

public class _1644_LCA {
    private static Integer lca(TreeNode root, TreeNode p, TreeNode q) {
        Set<TreeNode> foundNodes = new HashSet<>();
        TreeNode res = dfs(root, p, q, foundNodes);
        return foundNodes.size() == 2 ? res.val : null;
    }

    private static TreeNode dfs(TreeNode node, TreeNode p, TreeNode q, Set<TreeNode> foundNodes) {
        if (node == null || node.val == null) return null;
        if (node == p || node == q) foundNodes.add(node);
        TreeNode left = dfs(node.left, p, q, foundNodes);
        TreeNode right = dfs(node.right, p, q, foundNodes);
        if (node == p || node == q) return node;
        if (left != null && right != null) return node;
        if (left == null) return right;
        else if (right == null) return left;
        else return node;
    }

    public static void main(String[] args) {
        CreateBinaryTree cbt = new CreateBinaryTree();
        TreeNode root = cbt.createTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        for (int val : new int[]{5, 1}) nodeMap.put(val, cbt.getNodeMap().get(val));
        System.out.println(lca(root, nodeMap.get(5), nodeMap.get(1)));
    }
}
