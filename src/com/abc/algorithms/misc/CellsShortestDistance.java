package com.abc.algorithms.misc;

public class CellsShortestDistance {
    private static boolean shortestDistance(char[][] grid) {
        int[][] sourceAndDestination = findSourceAndDestination(grid);

        int[] source = sourceAndDestination[0];
        int[] destination = sourceAndDestination[1];

        int sourceRow = source[0];
        int sourceCol = source[1];

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[sourceRow][sourceCol] = true;

        return findPath(sourceRow, sourceCol, destination, grid, visited);
    }

    private static boolean findPath(int sourceRow, int sourceCol, int[] destination, char[][] grid, boolean[][] visited) {

        // left
        if (sourceCol - 1 >= 0)
            if (grid[sourceRow][sourceCol - 1] == 'd')
                return true;

        // right
        if (sourceCol + 1 < grid[0].length)
            if (grid[sourceRow][sourceCol + 1] == 'd')
                return true;
        // up
        if (sourceRow - 1 >= 0)
            if (grid[sourceRow - 1][sourceCol] == 'd')
                return true;
        // down
        if (sourceRow + 1 < grid.length)
            if (grid[sourceRow + 1][sourceCol] == 'd')
                return true;

        // left neighbor
        if (sourceCol - 1 >= 0)
            if (!visited[sourceRow][sourceCol - 1] && grid[sourceRow][sourceCol - 1] == '*') {
                visited[sourceRow][sourceCol - 1] = true;
                System.out.print("{" + sourceRow + ", " + (sourceCol - 1) + "} ->");
                return findPath(sourceRow, sourceCol - 1, destination, grid, visited);
            }

        // right neighbor
        if (sourceCol + 1 < grid[0].length)
            if (!visited[sourceRow][sourceCol + 1] && grid[sourceRow][sourceCol + 1] == '*') {
                visited[sourceRow][sourceCol + 1] = true;
                System.out.print("{" + sourceRow + ", " + (sourceCol + 1) + "} ->");
                return findPath(sourceRow, sourceCol + 1, destination, grid, visited);
            }

        // up neighbor
        if (sourceRow - 1 >= 0)
            if (!visited[sourceRow - 1][sourceCol] && grid[sourceRow - 1][sourceCol] == '*') {
                visited[sourceRow - 1][sourceCol] = true;
                System.out.print("{" + (sourceRow - 1) + ", " + sourceCol + "} ->");
                return findPath(sourceRow - 1, sourceCol, destination, grid, visited);
            }

        // down neighbor
        if (sourceRow + 1 < grid.length)
            if (!visited[sourceRow + 1][sourceCol] && grid[sourceRow + 1][sourceCol] == '*') {
                visited[sourceRow + 1][sourceCol] = true;
                System.out.print("{" + (sourceRow + 1) + ", " + sourceCol + "} -> ");
                return findPath(sourceRow + 1, sourceCol, destination, grid, visited);
            }

        return false;
    }

    private static int[][] findSourceAndDestination(char[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        int[][] result = new int[2][2];

        for (int rowIdx = 0; rowIdx < numRows; rowIdx++)
            for (int colIdx = 0; colIdx < numCols; colIdx++) {
                if (grid[rowIdx][colIdx] == 's')
                    result[0] = new int[]{rowIdx, colIdx};
                if (grid[rowIdx][colIdx] == 'd')
                    result[1] = new int[]{rowIdx, colIdx};
            }

        return result;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                new char[]{'0', '*', '0', 's'},
                new char[]{'*', '0', '*', '*'},
                new char[]{'0', '*', '*', '*'},
                new char[]{'d', '*', '*', '*'},
        };

        System.out.println(shortestDistance(grid));

        System.out.println();
    }
}
