package com.abc.algorithms.leetcode.graph;

import java.util.Arrays;

public class _684_RedundantConnection {
    private static int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[edges.length + 1];
        for (int idx = 0; idx < parent.length; idx++)
            parent[idx] = idx;

        int[] edgeCausingCycle = new int[]{0, 0};

        for (int[] edge : edges)
            if (union(edge[0], edge[1], parent)) edgeCausingCycle = edge;

        return edgeCausingCycle;
    }

    private static boolean union(int edgeOne, int edgeTwo, int[] parent) {
        int parentOne = find(edgeOne, parent);
        int parentTwo = find(edgeTwo, parent);

        if (parentOne != parentTwo) {
            parent[parentOne] = parentTwo;
            return false;
        }

        return true;
    }

    private static int find(int edge, int[] parent) {
        if (parent[edge] == edge) return edge;

        return find(parent[edge], parent);
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        findRedundantConnection(
                                new int[][]{
                                        new int[]{1, 2},
                                        new int[]{2, 3},
                                        new int[]{3, 4},
                                        new int[]{1, 4},
                                        new int[]{1, 5}
                                }
                        )
                )
        );
    }
}
