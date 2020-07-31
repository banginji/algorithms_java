package com.abc.algorithms.leetcode.greedy;

import java.util.Arrays;

import static java.util.Comparator.comparingInt;

public class _1029_TwoCityScheduling {
    private static int twoCityScheduleCost(int[][] costs) {
        Arrays.sort(costs, comparingInt(a -> a[0] - a[1]));

        int minCost = 0;

        for (int idx = 0; idx < costs.length; idx++) {
            if (idx < costs.length / 2)
                minCost += costs[idx][0];
            else
                minCost += costs[idx][1];
        }
        return minCost;
    }

    public static void main(String[] args) {
        System.out.println(
                twoCityScheduleCost(
                        new int[][]{
                                new int[]{10, 20},
                                new int[]{30, 200},
                                new int[]{400, 50},
                                new int[]{30, 20}
                        }
                )
        );
    }
}
