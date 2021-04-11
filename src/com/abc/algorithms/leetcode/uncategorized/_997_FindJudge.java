package com.abc.algorithms.leetcode.uncategorized;

public class _997_FindJudge {
    private static int findJudge(int n, int[][] trust) {
        int[] trustCount = new int[n + 1];

        for (int[] t : trust) {
            trustCount[t[0]]--;
            trustCount[t[1]]++;
        }

        for (int individual = 1; individual < trustCount.length; individual++) {
            if (trustCount[individual] == n - 1)
                return individual;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Find the Town Judge");
        System.out.println(
                findJudge(
                        2,
                        new int[][]{{1, 2}}
                )
        );
    }
}
