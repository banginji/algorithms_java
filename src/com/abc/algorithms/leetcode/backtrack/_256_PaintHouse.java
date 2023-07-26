package com.abc.algorithms.leetcode.backtrack;

public class _256_PaintHouse {
    private static int minCost(int[][] cost) {
        Integer[][][] dp = new Integer[cost.length][cost.length][3];
        int res = minCost(cost, dp, 0, 0, 0, 0);
        return res;
    }

    private static int minCost(int[][] cost, Integer[][][] dp, int currHouse, int prevHouse, int itrCost, int prevColor) {
        if (currHouse >= cost.length || prevHouse >= cost.length) return itrCost;
        if (dp[currHouse][prevHouse][prevColor] != null) return dp[currHouse][prevHouse][prevColor];
        int minCost = Integer.MAX_VALUE;
        for (int color = 0; color <= 2; color++) {
            if (color == prevColor) continue;
            minCost = Math.min(
                    minCost,
                    minCost(cost, dp, currHouse + 1, currHouse, itrCost + cost[currHouse][color], color)
            );
        }
        return dp[currHouse][prevHouse][prevColor] = minCost;
    }

    public static void main(String[] args) {
        System.out.println(
                minCost(
                        new int[][]{
                                new int[]{17, 2, 17},
                                new int[]{16, 16, 5},
                                new int[]{14, 3, 19}
                        }
                ) == 10
        );

        System.out.println(
                minCost(
                        new int[][]{
                                new int[]{7, 6, 2}
                        }
                ) == 2
        );
    }
}
