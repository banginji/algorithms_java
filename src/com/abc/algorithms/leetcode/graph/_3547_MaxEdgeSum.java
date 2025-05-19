package com.abc.algorithms.leetcode.graph;

public class _3547_MaxEdgeSum {
    // 8*7+8*6+7*5+6*4+5*3+4*2+3*1 2-4-6-8-7-5-3-1
    // 7*6+7*5+5*3+4*2+3*1 1-3-5-7-6-4-2
    // 6*5+6*4+5*3+4*2+3*1 2-4-6-5-3-1
    // 4*3+4*2+3*1
    // n*(n-1)+n*(n-2)+(n-1)*(n-3)+(n-2)*(n-4)+(n-3)*(n-5)
    public long maxScore(int n, int[][] edges) {
        if (n == 2) return 2;
        int[] edgeCount = new int[n + 1];

        for (int[] edge : edges) {
            edgeCount[edge[0]]++;
            edgeCount[edge[1]]++;
        }

        boolean shouldAdd = true;

        for (int count : edgeCount) {
            if (count == 1) {
                shouldAdd = false;
                break;
            }
        }

        return ((long) n * (n - 1)) + total(n) + (shouldAdd ? 2 : 0);
    }

    private long total(long n) {
        if (n == 3) return 3;
        return (n * (n - 2)) + total(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(
                new _3547_MaxEdgeSum()
                        .maxScore(
                                5,
                                new int[][]{
                                        {0, 1},
                                        {1, 2},
                                        {2, 3},
                                        {3, 4}
                                }
                        ) == 46
        );

        System.out.println(
                new _3547_MaxEdgeSum()
                        .maxScore(
                                2,
                                new int[][]{
                                        {0, 1},
                                }
                        ) == 2
        );
    }
}
