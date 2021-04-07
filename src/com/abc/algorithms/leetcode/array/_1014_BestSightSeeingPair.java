package com.abc.algorithms.leetcode.array;

public class _1014_BestSightSeeingPair {
    private static int bestPair(int[] values) {
        int max = 0, res = 0;
        for (int value : values) {
            // max is kept track of so as to maximize the result
            res = Math.max(res, max + value);
            // decrementing because max reduces by 1 since 1 idx position is moved by moving forward
            max = Math.max(max, value) - 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(
                bestPair(
                        new int[]{8, 1, 5, 2, 6}
                ) == 11
        );

        System.out.println(
                bestPair(
                        new int[]{1, 2}
                ) == 2
        );
    }
}
