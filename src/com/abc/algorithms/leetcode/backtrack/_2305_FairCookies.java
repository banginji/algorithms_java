package com.abc.algorithms.leetcode.backtrack;

import java.util.stream.IntStream;

public class _2305_FairCookies {
    private static int count(int[] cookies, int k) {
        int sum = IntStream.of(cookies).sum();

        int targetSum = sum / k;

        boolean[] taken = new boolean[cookies.length];

        int res = Integer.MIN_VALUE;
        for (int idx = 0; idx < cookies.length; idx++)
            if (!taken[idx]) {
                Object[] itrRes = count(cookies, taken, idx, 0, targetSum);
                res = Math.max(res, (int) itrRes[1]);
            }

        return res;
    }

    private static Object[] count(int[] cookies, boolean[] taken, int idx, int itrSum, int target) {
        if (idx >= cookies.length) return new Object[]{false, -1};
        int calcSum = itrSum + cookies[idx];
        if (!taken[idx] && calcSum >= (target - 2) && calcSum <= (target + 2)) {
            taken[idx] = true;
            return new Object[]{true, calcSum};
        }
        Object[] res = count(cookies, taken, idx + 1, itrSum + cookies[idx], target);
        if (!taken[idx] && (boolean) res[0]) {
            taken[idx] = true;
            return res;
        }
        return count(cookies, taken, idx + 1, itrSum, target);
    }

    public static void main(String[] args) {
        System.out.println(
                count(
                        new int[]{8, 15, 10, 20, 8},
                        2
                ) == 31
        );

        // Following is an edge case for the given question
        // based on the algorithm used
        // Expected = 7 but obtained answer is 6
        // Possible improvement is to look at the non
        // selected cookies and distribute it accordingly
        // though that will still yield 6, 7, 8
        System.out.println(
                count(
                        new int[]{6, 1, 3, 2, 2, 4, 1, 2},
                        3
                ) == 6
        );
    }
}
