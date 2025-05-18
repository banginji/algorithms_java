package com.abc.algorithms.leetcode.array;

public class _3546_EqualSumGrid {
    public boolean canPartitionGrid(int[][] grid) {
        long totalSum = 0;
        for (int rowIdx = 0; rowIdx < grid.length; rowIdx++) {
            for (int colIdx = 0; colIdx < grid[rowIdx].length; colIdx++) {
                totalSum += grid[rowIdx][colIdx];
            }
        }
        if (totalSum % 2 != 0) {
            return false;
        }

        long target = totalSum / 2;

        // horizontal cut
        long sum = 0;
        for (int rowIdx = 0; rowIdx < grid.length; rowIdx++) {
            for (int colIdx = 0; colIdx < grid[0].length; colIdx++) {
                sum += grid[rowIdx][colIdx];
            }
            if (sum == target) {
                return true;
            }
        }

        // vertical cut
        sum = 0;
        for (int colIdx = 0; colIdx < grid[0].length; colIdx++) {
            for (int rowIdx = 0; rowIdx < grid.length; rowIdx++) {
                sum += grid[rowIdx][colIdx];
            }
            if (sum == target) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(
                new _3546_EqualSumGrid().canPartitionGrid(
                        new int[][]{
                                {1, 4},
                                {2, 3},
                        }
                )
        );

        System.out.println(
                !new _3546_EqualSumGrid().canPartitionGrid(
                        new int[][]{
                                {1, 4, 5},
                                {6, 3, 2},
                                {2, 3, 7},
                        }
                )
        );

        System.out.println(
                !new _3546_EqualSumGrid().canPartitionGrid(
                        new int[][]{
                                {28443},
                                {33959}
                        }
                )
        );

        System.out.println(
                !new _3546_EqualSumGrid().canPartitionGrid(
                        new int[][]{
                                {28443, 33959}
                        }
                )
        );

        System.out.println(
                new _3546_EqualSumGrid().canPartitionGrid(
                        new int[][]{
                                {54756, 54756}
                        }
                )
        );

        System.out.println(
                new _3546_EqualSumGrid().canPartitionGrid(
                        new int[][]{
                                {42047},
                                {57775},
                                {99822}
                        }
                )
        );

        System.out.println(
                !new _3546_EqualSumGrid().canPartitionGrid(
                        new int[][]{
                                {100000, 10247},
                                {1, 1},
                        }
                )
        );
    }
}
