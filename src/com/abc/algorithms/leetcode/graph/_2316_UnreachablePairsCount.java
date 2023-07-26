package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _2316_UnreachablePairsCount {
    private static int count(int n, int[][] edges) {
        int[] parents = new int[n];
        for (int idx = 0; idx < parents.length; idx++) parents[idx] = idx;
        for (int[] edge : edges) union(edge, parents);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int idx = 0; idx < parents.length; idx++)
            map.computeIfAbsent(parents[idx], x -> new ArrayList<>()).add(idx);
        if (map.entrySet().size() == 1) return 0;
        /**
         * L_NOTES: Pairwise multiplication in O(n)
         * (a + b)^2 = (a^2 + b^2) + 2*a*b
         * a*b = [(a + b)^2 - (a^2 + b^2)] / 2
         *
         * (a + b + c)^2 = (a^2 + b^2 + c^2) + 2*(a*b + b*c + c*a)
         * a*b + b*c + c*a = [(a + b + c)^2 - (a^2 + b^2 + c^2)] / 2
         *
         * Generic formula:
         * pairwise multiplication = [(sum) ^ 2 - (sum of squares)] / 2
         */
        int sumOfSizes = 0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) sumOfSizes += entry.getValue().size();
        int sumOfSquares = 0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet())
            sumOfSizes += Math.pow(entry.getValue().size(), 2);
        return (sumOfSizes + sumOfSquares) / 2;
    }

    private static void union(int[] edge, int[] parents) {
        int parentOne = find(edge[0], parents);
        int parentTwo = find(edge[1], parents);

        if (parentOne != parentTwo)
            parents[parentTwo] = parentOne;
    }

    private static int find(int node, int[] parents) {
        return parents[node] == node ? node : find(parents[node], parents);
    }

    public static void main(String[] args) {
        System.out.println(
                count(
                        3,
                        new int[][]{
                                new int[]{0, 1},
                                new int[]{0, 2},
                                new int[]{1, 2}
                        }
                ) == 0
        );

        System.out.println(
                count(
                        7,
                        new int[][]{
                                new int[]{0, 2},
                                new int[]{0, 5},
                                new int[]{2, 4},
                                new int[]{1, 6},
                                new int[]{5, 4}
                        }
                ) == 14
        );
    }
}
