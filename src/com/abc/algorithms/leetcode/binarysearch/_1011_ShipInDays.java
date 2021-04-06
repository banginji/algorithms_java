package com.abc.algorithms.leetcode.binarysearch;

import java.util.Arrays;

public class _1011_ShipInDays {
    private static int capacity(int[] weights, int D) {
        int min = Arrays.stream(weights).max().orElse(-1);
        int max = Arrays.stream(weights).sum();
        return bs(min, max, weights, D);
    }

    private static int bs(int min, int max, int[] weights, int D) {
        int mid = (max + min) / 2;
        if (max - min <= 1) return checker(min, weights, D) ? min : max;
        if (checker(mid, weights, D))
            return bs(min, mid, weights, D);
        else
            return bs(mid, max, weights, D);
    }

    private static boolean checker(int capacity, int[] weights, int D) {
        int itrDays = 1, itrWeight = 0;
        for (int weight : weights) {
            itrWeight += weight;
            if (itrWeight > capacity) {
                itrWeight = weight;
                itrDays++;
                if (itrDays > D) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(
                capacity(
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                        5
                ) == 15
        );

        System.out.println(
                capacity(
                        new int[]{3, 2, 2, 4, 1, 4},
                        3
                ) == 6
        );

        System.out.println(
                capacity(
                        new int[]{1, 2, 3, 1, 1},
                        4
                ) == 3
        );
    }
}
