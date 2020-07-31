package com.abc.algorithms.misc;

import java.util.Random;

public class FindPathInGrid {

    /*    Given a matrix with only 0s and 1s, where 0 represents a wall and 1 represents a path, return if there is a path from first to last row.

                Case 1:
                0 0 0 0 1 0
                1 1 1 1 1 1
                1 0 0 0 1 0
                0 0 1 1 1 0
                1 0 1 0 0 0	return true

        Case 2:
                0 0 0 0 1 0
                1 1 1 1 1 1
                1 0 0 0 1 0
                0 0 1 0 1 0
                1 0 1 0 0 0	return false


                0 0 1 1 0
                0 0 0 0 0
    */

    private static int[][] createGrid(int numberOfRows, int numberOfColumns) {
        int[][] grid = new int[numberOfRows][numberOfColumns];

        for (int rowIdx = 0; rowIdx < numberOfRows; rowIdx++) {
            for (int colIdx = 0; colIdx < numberOfColumns; colIdx++) {
                grid[rowIdx][colIdx] = new Random().nextInt(2);
            }
        }

        return grid;
    }

    private static boolean findPath(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return false;

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int colIdx = 0; colIdx < grid[0].length; colIdx++) {
            visited[0][colIdx] = true;
            if (grid[0][colIdx] == 1)
                if (path(0, colIdx, grid, visited))
                    return true;
        }

        return false;
    }

    private static boolean path(int row, int col, int[][] grid, boolean[][] visited) {
        if (row == grid.length - 1)
            return true;

        // find neighbors
        // right neighbor
        if (row + 1 < grid.length)
            if (!visited[row + 1][col] && grid[row + 1][col] == 1) {
                visited[row + 1][col] = true;
                if (path(row + 1, col, grid, visited))
                    return true;
            }

        // left neighbor
        if (row - 1 >= 0)
            if (!visited[row - 1][col] && grid[row - 1][col] == 1) {
                visited[row - 1][col] = true;
                if (path(row - 1, col, grid, visited))
                    return true;
            }

        // down neighbor
        if (col + 1 < grid[0].length)
            if (!visited[row][col + 1] && grid[row][col + 1] == 1) {
                visited[row][col + 1] = true;
                if (path(row, col + 1, grid, visited))
                    return true;
            }


        // up neighbor
        if (col - 1 >= 0)
            if (!visited[row][col - 1] && grid[row][col - 1] == 1) {
                visited[row][col - 1] = true;
                return path(row, col - 1, grid, visited);
            }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(findPath(createGrid(3, 3)));
    }
}
