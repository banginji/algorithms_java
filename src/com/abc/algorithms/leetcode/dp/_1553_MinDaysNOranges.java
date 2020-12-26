package com.abc.algorithms.leetcode.dp;

import com.abc.algorithms.leetcode.TimeIt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _1553_MinDaysNOranges {
    private static int minDays(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int[] d : dp) Arrays.fill(d, Integer.MAX_VALUE);
        return dp(n, 0, dp);
    }

    private static int dp(int n, int days, int[][] dp) {
        if (n == 0) return days;

        if (dp[n][days] != Integer.MAX_VALUE) return dp[n][days];

        return dp[n][days] = Math.min(
                dp(n - 1, days + 1, dp),
                Math.min(
                        n % 2 == 0 ? dp(n - n / 2, days + 1, dp) : Integer.MAX_VALUE,
                        n % 3 == 0 ? dp(n - 2 * n / 3, days + 1, dp) : Integer.MAX_VALUE
                )
        );
    }

    private static int minDaysDp(int n) {
        Map<Integer, Integer> dp = new HashMap<>();
        // 0 and 1 are base cases that should not be handled in the recursion
        dp.put(0, 0);
        dp.put(1, 1);
        return minDaysDp(n, dp);
    }

    private static int minDaysDp(int n, Map<Integer, Integer> dp) {
        if (n <= 1) return n;
        if (dp.containsKey(n)) return dp.get(n);
        dp.put(
                n,
                /**
                 * Normally n in n/2 or n/3 should subtracted by n%2 or n%3 but Integer division
                 * takes care of it automatically and it is enough to add n%2 or n%3 at the end
                 *
                 * The `1` is added at the end to increment the sub-problem's result
                 * Ex:
                 * minDaysDp(10, {0:0, 1:1, `2:2`, `5:3`, `3:2`, `10:4`}) {         (12)
                 *     Math.min(
                 *          minDaysDp(5, {0:0, 1:1}) {
                 *              Math.min(
                 *                  minDaysDp(2, {0:0, 1:1}) {
                 *                      Math.min(
                 *                          minDaysDp(1, {0:0, 1:1}) [1] + 0,       (01)
                 *                          minDaysDp(0, {0:0, 1:1}) [0] + 2        (02)
                 *                      )
                 *                  } [1] + 1,
                 *                  minDaysDp(1, {0:0, 1:1, `2:2`}) [1] + 2         (03)
                 *              ) [2] + 1                                           (04)
                 *          } [3] + 0,                                              (05)
                 *          minDaysDp(3, {0:0, 1:1, 2:2, `5:3`}) {                  (06)
                 *              Math.min(
                 *                  minDaysDp(1, {0:0, 1:1, 2:2, 5:3}) [1] + 1,     (07)
                 *                  minDaysDp(1, {0:0, 1:1, 2:2, 5:3}) [1] + 0      (08)
                 *              ) [1] + 1                                           (09)
                 *          } [2] + 1,                                              (10)
                 *     ) [3] + 1                                                    (11)
                 * }
                  */
                Math.min(
                        minDaysDp(n / 2, dp) + n % 2,
                        minDaysDp(n / 3, dp) + n % 3
                ) + 1
        );
        return dp.get(n);
    }

    /**
     *  5     4 + 1    3         1 + 1
     * 13 -> 12 + 1 -> 6 -> 3 -> 2 + 1 -> 0 -> 0
     *
     *        4 + 1         3 + 1         1 + 1
     * 13 -> 12 + 1 -> 4 -> 3 + 1 -> 1 -> 0 + 1
     *
     *
     * 6 -> 3 -> 1 -> 0 + 1 -> 0
     *
     * @param args
     */

    public static void main(String[] args) {
        System.out.println("-".repeat(10) + "minDays" + "-".repeat(10));
        System.out.println(minDays(1) == 1);
        System.out.println(minDays(2) == 2);
        System.out.println(minDays(3) == 2);
        System.out.println(minDays(10) == 4);
        System.out.println(minDays(6) == 3);
        System.out.println(minDays(1) == 1);
        System.out.println(minDays(56) == 6);
        System.out.println(minDays(673) == 10);
        System.out.println(minDays(9314) == 15);

        System.out.println("-".repeat(10) + "minDaysDp" + "-".repeat(10));
        System.out.println(minDaysDp(1) == 1);
        System.out.println(minDaysDp(2) == 2);
        System.out.println(minDaysDp(3) == 2);
        System.out.println(minDaysDp(10) == 4);
        System.out.println(minDaysDp(6) == 3);
        System.out.println(minDaysDp(1) == 1);
        System.out.println(minDaysDp(56) == 6);
        System.out.println(minDaysDp(673) == 10);
        System.out.println(minDaysDp(9314) == 15);

        System.out.println("-".repeat(10) + "minDays(673)" + "-".repeat(10));
        int itrOne = 0;
        while (itrOne++ < 10)
            TimeIt.timeTaken(() -> minDays(673));
        System.out.println("-".repeat(10) + "minDays(9314)" + "-".repeat(10));
        int itrTwo = 0;
        while (itrTwo++ < 10)
            TimeIt.timeTaken(() -> minDays(9314));
        System.out.println("-".repeat(10) + "minDaysDp(673)" + "-".repeat(10));
        int itrThree = 0;
        while (itrThree++ < 10)
            TimeIt.timeTaken(() -> minDaysDp(673));
        System.out.println("-".repeat(10) + "minDaysDp(9314)" + "-".repeat(10));
        int itrFour = 0;
        while (itrFour++ < 10)
            TimeIt.timeTaken(() -> minDaysDp(9314));
    }
}
