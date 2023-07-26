package com.abc.algorithms.leetcode.dp;

import java.util.Arrays;

public class _2320_WaysToPlaceHouses {
    // TODO
    private static int count(int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 0);
        count(dp, 0, true);
        count(dp, 0, false);
        return Arrays.stream(dp).filter(num -> num != Integer.MIN_VALUE).sum();
    }

    private static int count(int[] dp, int n, boolean select) {
        if (n >= dp.length) return 0;
        if (n == (dp.length - 1)) return 1;
//        if (dp[n] != 0) return dp[n];
        return dp[n] += select ?
                count(dp, n + 1, !select) :
                (count(dp, n + 1, !select) + count(dp, n + 1, select));
    }

    public static void main(String[] args) {
        System.out.println(
                count(3)
        );
    }
}
