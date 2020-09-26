package com.abc.algorithms.leetcode.tree;

import com.abc.algorithms.leetcode.tree.CreateBinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _1530_GoodLeafNodePairs {
    private static int sum = 0;

    private static int countPairs(TreeNode node, int distance) {
        traverseNodes(node, distance);
        return sum;
    }

    private static List<Integer> traverseNodes(TreeNode node, int maxDistance) {
        List<Integer> distances = new ArrayList<>();

        if (node == null) return distances;

        List<Integer> leftDistances = traverseNodes(node.left, maxDistance);
        List<Integer> rightDistances = traverseNodes(node.right, maxDistance);

        if (leftDistances.isEmpty() && rightDistances.isEmpty()) {
            distances.add(1);
            return distances;
        }

        if (!leftDistances.isEmpty() && !rightDistances.isEmpty())
            for (Integer leftDistance : leftDistances)
                for (Integer rightDistance : rightDistances)
                    if (leftDistance + rightDistance <= maxDistance) sum++;

        for (int leftDistance : leftDistances)
            distances.add(leftDistance + 1);

        for (int rightDistance : rightDistances)
            distances.add(rightDistance + 1);

        return distances;
    }

    public static void main(String[] args) {
        CreateBinaryTree createBinaryTree = new CreateBinaryTree();
        TreeNode root = createBinaryTree.createTree(new Integer[]{1, 2, 3, null, 4});
        System.out.println(countPairs(root, 3));
    }
}
