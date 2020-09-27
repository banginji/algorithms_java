package com.abc.algorithms.leetcode.dfs;

import com.abc.algorithms.leetcode.tree.CreateBinaryTree;
import com.abc.algorithms.leetcode.tree.CreateBinaryTree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _1161_MaxLvlSum {
    private static Integer maxLvlSum(TreeNode node) {
        Map<Integer, Integer> lvlSum = new HashMap<>();

        dfs(node, 1, lvlSum);

        return lvlSum.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(Integer.MIN_VALUE);
    }

    private static void dfs(TreeNode node, int lvl, Map<Integer, Integer> lvlSum) {
        lvlSum.putIfAbsent(lvl, 0);
        if (node != null && node.val != null)
            lvlSum.computeIfPresent(lvl, (key, value) -> value + node.val);

        if (node == null || (node.left == null && node.right == null))
            return;

        dfs(node.left, lvl + 1, lvlSum);
        dfs(node.right, lvl + 1, lvlSum);
    }

    public static void main(String[] args) {
        CreateBinaryTree cbt = new CreateBinaryTree();
        TreeNode root = cbt.createTree(new Integer[]{1, 7, 0, 7, -8, null, null});
        System.out.println(maxLvlSum(root));
    }
}
