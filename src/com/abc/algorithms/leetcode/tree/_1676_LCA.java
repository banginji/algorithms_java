package com.abc.algorithms.leetcode.tree;

import com.abc.algorithms.leetcode.tree.CreateBinaryTree.TreeNode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _1676_LCA {
    private static TreeNode lca(TreeNode root, TreeNode[] nodes) {
        return dfs(root, Stream.of(nodes).collect(Collectors.toSet()));
    }

    private static TreeNode dfs(TreeNode node, Set<TreeNode> nodes) {
        if (node == null || node.val == null) return null;
        if (nodes.contains(node)) return node;
        TreeNode left = dfs(node.left, nodes);
        TreeNode right = dfs(node.right, nodes);
        if (left != null && right != null) return node;
        return left == null ? right : left;
    }

    public static void main(String[] args) {
        CreateBinaryTree cbt = new CreateBinaryTree();
        TreeNode root = cbt.createTree(new Integer[]{12795, 1982, null, 3798, null, 430, null, 5481, null, 15224, null, 12970, null, 18652, null, 5137, null, 13230, null, 8433, null, 19989, null, 6921});
        List<TreeNode> nodes = new ArrayList<>();
        for (int val : new int[]{5481, 13230, 18652}) nodes.add(cbt.getNodeMap().get(val));
        TreeNode[] na = new TreeNode[nodes.size()];
        int idx = 0;
        for (TreeNode node : nodes) na[idx++] = node;
        System.out.println(lca(root, na).val);
    }
}
