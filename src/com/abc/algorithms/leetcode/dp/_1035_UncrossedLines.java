package com.abc.algorithms.leetcode.dp;

import java.util.Arrays;

public class _1035_UncrossedLines {
    private static int uncrossedLines(int[] A, int[] B) {
        int[][] dp = new int[A.length][B.length];

        for (int[] d : dp) Arrays.fill(d, Integer.MIN_VALUE);

        return lcs(A, 0, B, 0, dp);
    }

    private static int lcs(int[] A, int idxOne, int[] B, int idxTwo, int[][] dp) {
        if (idxOne >= A.length || idxTwo >= B.length) return 0;

        if (dp[idxOne][idxTwo] != Integer.MIN_VALUE) return dp[idxOne][idxTwo];

        if (A[idxOne] == B[idxTwo]) return dp[idxOne][idxTwo] = 1 + lcs(A, idxOne + 1, B, idxTwo + 1, dp);

        return dp[idxOne][idxTwo] = Math.max(
                lcs(A, idxOne + 1, B, idxTwo, dp),
                lcs(A, idxOne, B, idxTwo + 1, dp)
        );
    }

    public static void main(String[] args) {
        System.out.println(
                uncrossedLines(
                        new int[]{1, 4, 2},
                        new int[]{1, 2, 4}
                ) == 2
        );

        System.out.println(
                uncrossedLines(
                        new int[]{2, 5, 1, 2, 5},
                        new int[]{10, 5, 2, 1, 5, 2}
                ) == 3
        );

        System.out.println(
                uncrossedLines(
                        new int[]{1, 3, 7, 1, 7, 5},
                        new int[]{1, 9, 2, 5, 1}
                ) == 2
        );

        System.out.println(
                uncrossedLines(
                        new int[]{2, 3, 4, 1, 3, 3, 2, 4, 2, 2, 1, 5, 2, 4, 3, 4, 4, 5, 1, 5, 1, 5, 4, 3, 1, 2, 5, 2, 4, 4},
                        new int[]{2, 2, 4, 2, 4, 1, 1, 5, 5, 3, 2, 1, 1, 1, 3, 1, 2, 5, 2, 4, 3, 4, 5, 5, 3, 3, 5, 1, 4, 3}
                ) == 16
        );
    }
}
