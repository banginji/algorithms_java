package com.abc.algorithms.leetcode.graph;

public class _2374_HighestScore {
    private static int highestScore(int[] edges) {
        long[] scores = new long[edges.length];
        for (int node = 0; node < edges.length; node++) scores[edges[node]] += node;
        long[] highest = new long[]{Integer.MIN_VALUE, -1};
        for (int node = 0; node < scores.length; node++) {
            if (highest[0] < scores[node])
                highest = new long[]{scores[node], node};
        }
        return (int)highest[1];
    }

    public static void main(String[] args) {
        System.out.println(
                highestScore(
                        new int[]{
                                1, 0, 0, 0, 0, 7, 7, 5
                        }
                )
        );
    }
}
