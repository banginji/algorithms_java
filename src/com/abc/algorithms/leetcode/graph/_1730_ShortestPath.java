package com.abc.algorithms.leetcode.graph;

public class _1730_ShortestPath {
    private static int shortestPath(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int startRowIdx = 0, startColIdx = 0;
        for (int rowIdx = 0; rowIdx < grid.length; rowIdx++)
            for (int colIdx = 0; colIdx < grid[0].length; colIdx++)
                if (grid[rowIdx][colIdx] == '*') {
                    startRowIdx = rowIdx;
                    startColIdx = colIdx;
                }

        int res = traverse(startRowIdx, startColIdx, grid, visited, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static int traverse(int rowIdx, int colIdx, char[][] grid, boolean[][] visited, int count) {
        if (rowIdx >= grid.length || colIdx >= grid[0].length) return Integer.MAX_VALUE;

        if (grid[rowIdx][colIdx] == '#') return count;

        if (grid[rowIdx][colIdx] == 'X') return Integer.MAX_VALUE;

        visited[rowIdx][colIdx] = true;

        int resOne = Integer.MAX_VALUE;
        if (rowIdx + 1 < grid.length && !visited[rowIdx + 1][colIdx])
            resOne = traverse(rowIdx + 1, colIdx, grid, visited, count + 1);
        int resTwo = Integer.MAX_VALUE;
        if (colIdx + 1 < grid[0].length && !visited[rowIdx][colIdx + 1])
            resTwo = traverse(rowIdx, colIdx + 1, grid, visited, count + 1);
        int resThree = Integer.MAX_VALUE;
        if (rowIdx - 1 >= 0 && !visited[rowIdx - 1][colIdx])
            resThree = traverse(rowIdx - 1, colIdx, grid, visited, count + 1);
        int resFour = Integer.MAX_VALUE;
        if (colIdx - 1 >= 0 && !visited[rowIdx][colIdx - 1])
            resFour = traverse(rowIdx, colIdx - 1, grid, visited, count + 1);

        visited[rowIdx][colIdx] = false;

        return Math.min(Math.min(resOne, resTwo), Math.min(resThree, resFour));
    }

    public static void main(String[] args) {
        System.out.println(
                shortestPath(
                        new char[][]{
                                new char[]{'X', 'X', 'X', 'X', 'X', 'X'},
                                new char[]{'X', '*', 'O', 'O', 'O', 'X'},
                                new char[]{'X', 'O', 'O', '#', 'O', 'X'},
                                new char[]{'X', 'X', 'X', 'X', 'X', 'X'}
                        }
                ) == 3
        );

        System.out.println(
                shortestPath(
                        new char[][]{
                                new char[]{'X', 'X', 'X', 'X', 'X'},
                                new char[]{'X', '*', 'X', 'O', 'X'},
                                new char[]{'X', 'O', 'X', '#', 'X'},
                                new char[]{'X', 'X', 'X', 'X', 'X'}
                        }
                ) == -1
        );

        System.out.println(
                shortestPath(
                        new char[][]{
                                new char[]{'X','X','X','X','X','X','X','X'},
                                new char[]{'X','*','O','X','O','#','O','X'},
                                new char[]{'X','O','O','X','O','O','X','X'},
                                new char[]{'X','O','O','O','O','#','O','X'},
                                new char[]{'X','X','X','X','X','X','X','X'}
                        }
                ) == 6
        );

        System.out.println(
                shortestPath(
                        new char[][]{
                                new char[]{'O','*'},
                                new char[]{'#','O'}
                        }
                ) == 2
        );

        System.out.println(
                shortestPath(
                        new char[][]{
                                new char[]{'X','*'},
                                new char[]{'#','X'}
                        }
                ) == -1
        );
    }
}
