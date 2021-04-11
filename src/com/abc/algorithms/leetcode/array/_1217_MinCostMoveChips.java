package com.abc.algorithms.leetcode.array;

public class _1217_MinCostMoveChips {
    private static int minCost(int[] positions) {
        int oddNum = 0, evenNum = 0;
        for (int idx : positions)
            if (idx % 2 == 0) evenNum++;
            else oddNum++;
        return Math.min(evenNum, oddNum);
    }

    public static void main(String[] args) {
        System.out.println(
                minCost(
                        new int[]{1, 2, 3}
                ) == 1
        );

        System.out.println(
                minCost(
                        new int[]{2, 2, 2, 3, 3}
                ) == 2
        );

        System.out.println(
                minCost(
                        new int[]{1, 1_000_000_000}
                ) == 1
        );
    }
}
