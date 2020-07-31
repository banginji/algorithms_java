package com.abc.algorithms.leetcode.graph;

public class _1267_CountServers {
    private static int countServers(int[][] grid) {
        int count = 0;

        boolean[][] result = new boolean[grid.length][grid[0].length];

        int numRows = grid.length;
        int numCols = grid[0].length;

        for (int rowIdx = 0; rowIdx < grid.length; rowIdx++)
            for (int colIdx = 0; colIdx < grid[0].length; colIdx++) {
                if (grid[rowIdx][colIdx] != 1)
                    continue;

                // Down neighbor
                int currentRow = rowIdx;
                while (++currentRow < numRows)
                    if (grid[currentRow][colIdx] == 1) {
                        result[currentRow][colIdx] = true;
                        break;
                    }

                // Up neighbor
                currentRow = rowIdx;
                while (--currentRow >= 0)
                    if (grid[currentRow][colIdx] == 1) {
                        result[currentRow][colIdx] = true;
                        break;
                    }

                // Left neighbor
                int currentCol = colIdx;
                while (--currentCol >= 0)
                    if (grid[rowIdx][currentCol] == 1) {
                        result[rowIdx][currentCol] = true;
                        break;
                    }

                // Right neighbor
                currentCol = colIdx;
                while (++currentCol < numCols)
                    if (grid[rowIdx][currentCol] == 1) {
                        result[rowIdx][currentCol] = true;
                        break;
                    }
            }

        for (boolean[] computers: result)
            for (int colIdx = 0; colIdx < result[0].length; colIdx++)
                if (computers[colIdx]) count++;

        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                countServers(
                        new int[][]{
                                new int[]{1, 0, 0, 1, 0},
                                new int[]{0, 0, 0, 0, 0},
                                new int[]{0, 0, 0, 1, 0}
                        }
                )
        );
    }
}
