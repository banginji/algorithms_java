package com.abc.algorithms.leetcode.graph;

import java.util.HashSet;
import java.util.Set;

public class _2368_ReachableNodes {
    private static int count(int n, int[][] edges, int[] restricted) {
        int[] parent = new int[n];
        Set<Integer> restrictedSet = new HashSet<>();
        for (int num : restricted) restrictedSet.add(num);
        for (int node = 0; node < n; node++) parent[node] = node;
        for (int[] edge : edges) {
            if (restrictedSet.contains(edge[0]) || restrictedSet.contains(edge[1])) continue;
            union(edge[0], edge[1], parent);
        }
        int[] res = new int[n];
        for (int node = 0; node < n; node++) res[node] = find(node, parent);
        int count = 1;
        for (int node = 1; node < res.length; node++)
            if (res[node] == res[0]) count++;
        return count;
    }

    private static void union(int nodeOne, int nodeTwo, int[] parent) {
        int parentOne = find(nodeOne, parent);
        int parentTwo = find(nodeTwo, parent);
        if (parentOne != parentTwo) parent[parentTwo] = parentOne;
    }

    private static int find(int node, int[] parent) {
        return parent[node] == node ? node : find(parent[node], parent);
    }

    public static void main(String[] args) {
        System.out.println(
                count(
                        7,
                        new int[][]{
                                new int[]{0, 1},
                                new int[]{1, 2},
                                new int[]{3, 1},
                                new int[]{4, 0},
                                new int[]{0, 5},
                                new int[]{5, 6}
                        },
                        new int[]{4, 5}
                ) == 4
        );
    }
}
