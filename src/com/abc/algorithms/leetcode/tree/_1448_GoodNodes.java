package com.abc.algorithms.leetcode.tree;

import com.abc.algorithms.leetcode.tree.CreateBinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _1448_GoodNodes {
    private static void nodesCount(TreeNode node) {
        dfs(node, new ArrayList<>());
    }

    private static int count = 0;

    private static void dfs(TreeNode node, List<TreeNode> nodesInPath) {
        if (node == null || node.val == null)
            return;

        boolean isGoodNode = true;
        for (TreeNode nodeInPath : nodesInPath)
            if (nodeInPath.val > node.val) {
                isGoodNode = false;
                break;
            }

        if (isGoodNode) count++;

        nodesInPath.add(node);
        dfs(node.left, nodesInPath);
        dfs(node.right, nodesInPath);
        nodesInPath.remove(node);
    }

    public static void main(String[] args) {
        CreateBinaryTree createBinaryTree = new CreateBinaryTree();
        TreeNode root = createBinaryTree.createTree(new Integer[]{3, 1, 4, 3, null, 1, 5});
        nodesCount(root);
        System.out.println(count);
    }
}
