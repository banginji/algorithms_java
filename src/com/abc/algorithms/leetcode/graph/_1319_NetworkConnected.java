package com.abc.algorithms.leetcode.graph;

public class _1319_NetworkConnected {
    private static int makeConnected(int n, int[][] connections) {
        int[] parents = new int[n];

        for (int idx = 0; idx < parents.length; idx++)
            parents[idx] = idx;

        for (int[] conn : connections)
            union(conn, parents);

        int count = 0;

        for (int idx = 0; idx < parents.length; idx++)
            if (parents[idx] == idx) count++;

        return count - 1;
    }

    private static void union(int[] conn, int[] parents) {
        int parentOne = find(conn[0], parents);
        int parentTwo = find(conn[1], parents);

        if (parentOne != parentTwo)
            parents[parentOne] = parentTwo;
    }

    private static int find(int node, int[] parents) {
        if (parents[node] == node) return node;

        return find(parents[node], parents);
    }

    public static void main(String[] args) {
        System.out.println(
                makeConnected(
                        6,
                        new int[][]{
                                new int[]{0, 1},
                                new int[]{0, 2},
                                new int[]{0, 3},
                                new int[]{1, 2},
                                new int[]{1, 3}
                        }
                )
        );
    }
}
