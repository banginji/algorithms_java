package com.abc.algorithms.leetcode.graph;

import java.util.Arrays;

public class _1361_ValidateBinaryTree {
    private static boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] inDegree = new int[n];
        for (int node = 0; node < n; node++) {
            if (leftChild[node] != -1) inDegree[leftChild[node]]++;
            if (rightChild[node] != -1) inDegree[rightChild[node]]++;
        }
        int infractions = 1;
        int startNode = 0;
        for (int node = 0; node < n; node++) {
            if (inDegree[node] == 0) {
                startNode = node;
                infractions--;
            }
            if (infractions < 0 || inDegree[node] > 1) return false;
        }

        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited, false);
        visited[startNode] = true;
        boolean result = dfs(startNode, leftChild, rightChild, visited);
        return result & Arrays.stream(visited).allMatch(Boolean::booleanValue);
    }

    private static boolean dfs(int node, int[] leftChild, int[] rightChild, Boolean[] visited) {
        boolean leftResult = true;
        if (leftChild[node] != -1)
            if (!visited[leftChild[node]]) {
                visited[leftChild[node]] = true;
                leftResult = dfs(leftChild[node], leftChild, rightChild, visited);
            } else
                leftResult = false;
        boolean rightResult = true;
        if (rightChild[node] != -1)
            if (!visited[rightChild[node]]) {
                visited[rightChild[node]] = true;
                rightResult = dfs(rightChild[node], leftChild, rightChild, visited);
            } else
                rightResult = false;
        return leftResult && rightResult;
    }

    public static void main(String[] args) {
        System.out.println(
                validateBinaryTreeNodes(
                        4,
                        new int[]{1, -1, 3, -1},
                        new int[]{2, -1, -1, -1}
                )
        );

        System.out.println(
                !validateBinaryTreeNodes(
                        4,
                        new int[]{1, -1, 3, -1},
                        new int[]{2, 3, -1, -1}
                )
        );

        System.out.println(
                !validateBinaryTreeNodes(
                        2,
                        new int[]{1, 0},
                        new int[]{-1, -1}
                )
        );

        System.out.println(
                !validateBinaryTreeNodes(
                        6,
                        new int[]{1, -1, -1, 4, -1, -1},
                        new int[]{2, -1, -1, 5, -1, -1}
                )
        );
    }
}
