package com.abc.algorithms.leetcode.tree;

import com.abc.algorithms.leetcode.tree.CreateBinaryTree.TreeNode;

import java.util.*;

public class _1302_DeepestLeavesSum {
    private static int deepestLeavesSum(TreeNode node) {
        // Traverse Tree and collect all leaf nodes along with their height
        List<int[]> leafNodes = dfs(node, 0);

        int heightOfTree = 0;

        Map<Integer, Integer> sameHeightLeftNodesSum = new HashMap<>();

        for (int[] leafNode : leafNodes) {
            if (heightOfTree < leafNode[1])
                heightOfTree = leafNode[1];

            sameHeightLeftNodesSum.putIfAbsent(leafNode[1], 0);
            sameHeightLeftNodesSum.computeIfPresent(leafNode[1], (key, value) -> value + leafNode[0]);
        }

        return sameHeightLeftNodesSum.get(heightOfTree);
    }

    private static List<int[]> dfs(TreeNode node, int heightFromRoot) {
        if (node == null || node.val == null)
            return Collections.emptyList();

        if (CreateBinaryTree.isLeafNode(node))
            return Collections.singletonList(new int[]{node.val, heightFromRoot + 1});

        List<int[]> leafNodes = new ArrayList<>();

        leafNodes.addAll(dfs(node.left, heightFromRoot + 1));
        leafNodes.addAll(dfs(node.right, heightFromRoot + 1));

        return leafNodes;
    }

    public static void main(String[] args) {
        CreateBinaryTree cbt = new CreateBinaryTree();
        TreeNode root = cbt.createTree(new Integer[]{1, 2, 3, 4, 5, null, 6, 7, null, null, null, null, null, null, 8});
        System.out.println(deepestLeavesSum(root));
    }
}
