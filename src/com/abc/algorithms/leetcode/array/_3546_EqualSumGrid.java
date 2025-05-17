package com.abc.algorithms.leetcode.array;

public class _3546_EqualSumGrid {
    public boolean canPartitionGrid(int[][] grid) {
        int[][] rowTraverser = new int[grid.length][grid[0].length];
        int[][] colTraverser = new int[grid.length][grid[0].length];

        for (int jIdx = 0; jIdx < grid[0].length; jIdx++) {
            for (int iIdx = 0; iIdx < grid.length; iIdx++) {
                if (iIdx == 0) {
                    rowTraverser[iIdx][jIdx] = grid[iIdx][jIdx];
                    continue;
                }
                rowTraverser[iIdx][jIdx] = rowTraverser[iIdx-1][jIdx] + grid[iIdx][jIdx];
            }
        }

        for (int iIdx = 0; iIdx < grid.length; iIdx++) {
            for (int jIdx = 0; jIdx < grid[0].length; jIdx++) {
                if (jIdx == 0) {
                    colTraverser[iIdx][jIdx] = grid[iIdx][jIdx];
                    continue;
                }
                colTraverser[iIdx][jIdx] = colTraverser[iIdx][jIdx-1] + grid[iIdx][jIdx];
            }
        }

        boolean result;

        // Check for vertical cuts - comparing left and right parts
        for (int jIdx = 0; jIdx < grid[0].length - 1; jIdx++) {
            result = true;
            int leftSum = colTraverser[grid.length - 1][jIdx];
            int rightSum = colTraverser[grid.length - 1][grid[0].length - 1] - leftSum;
            if (leftSum == rightSum) {
                return true;
            }
        }

        // Check for horizontal cuts - comparing top and bottom parts
        for (int iIdx = 0; iIdx < grid.length - 1; iIdx++) {
            result = true;
            int topSum = rowTraverser[iIdx][grid[0].length - 1];
            int bottomSum = rowTraverser[grid.length - 1][grid[0].length - 1] - topSum;
            if (topSum == bottomSum) {
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
//
//        System.out.println(
//                new _3546_EqualSumGrid().canPartitionGrid(
//                        new int[][]{
//                                {1, 4, 5},
//                                {6, 3, 2},
//                                {2, 3, 7},
//                        }
//                )
//        );

//        System.out.println(
//                new _3546_EqualSumGrid().canPartitionGrid(
//                        new int[][]{
//                                {28443},
//                                {33959}
//                        }
//                )
//        );

//        System.out.println(
//                new _3546_EqualSumGrid().canPartitionGrid(
//                        new int[][]{
//                                {28443, 33959}
//                        }
//                )
//        );
    }
}
