package com.abc.algorithms.leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _329_LongestIncPath {
    private static int longestPathLength(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        List<int[]> maxPositions = new ArrayList<>();

        // Find the largest number
        for (int rowIdx = 0; rowIdx < matrix.length; rowIdx++)
            for (int colIdx = 0; colIdx < matrix[0].length; colIdx++)
                max = Math.max(max, matrix[rowIdx][colIdx]);

        // Find the positions for the largest number in case of duplicates
        for (int rowIdx = 0; rowIdx < matrix.length; rowIdx++)
            for (int colIdx = 0; colIdx < matrix[0].length; colIdx++)
                if (matrix[rowIdx][colIdx] == max)
                    maxPositions.add(new int[]{rowIdx, colIdx});

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        final List<List<int[]>> paths = new ArrayList<>();

        for (int[] maxPosition : maxPositions) {
            for (boolean[] booleans : visited) Arrays.fill(booleans, false);
            paths.addAll(dfs(maxPosition[0], maxPosition[1], matrix, visited, new ArrayList<>()));
        }

        return paths.stream().mapToInt(List::size).max().orElse(-1);
    }

    private static List<List<int[]>> dfs(int rowIdx, int colIdx, int[][] matrix, boolean[][] visited, List<int[]> incomingPath) {
        List<List<int[]>> paths = new ArrayList<>();

        if (rowIdx - 1 >= 0)
            if (!visited[rowIdx - 1][colIdx] && matrix[rowIdx - 1][colIdx] < matrix[rowIdx][colIdx]) {
                visited[rowIdx - 1][colIdx] = true;
                List<int[]> thisPath = new ArrayList<>(incomingPath);
                thisPath.add(new int[]{rowIdx, colIdx});
                paths.addAll(dfs(rowIdx - 1, colIdx, matrix, visited, thisPath));
            }

        if (colIdx - 1 >= 0)
            if (!visited[rowIdx][colIdx - 1] && matrix[rowIdx][colIdx - 1] < matrix[rowIdx][colIdx]) {
                visited[rowIdx][colIdx - 1] = true;
                List<int[]> thisPath = new ArrayList<>(incomingPath);
                thisPath.add(new int[]{rowIdx, colIdx});
                paths.addAll(dfs(rowIdx, colIdx - 1, matrix, visited, thisPath));
            }

        if (rowIdx + 1 < matrix.length)
            if (!visited[rowIdx + 1][colIdx] && matrix[rowIdx + 1][colIdx] < matrix[rowIdx][colIdx]) {
                visited[rowIdx + 1][colIdx] = true;
                List<int[]> thisPath = new ArrayList<>(incomingPath);
                thisPath.add(new int[]{rowIdx, colIdx});
                paths.addAll(dfs(rowIdx + 1, colIdx, matrix, visited, thisPath));
            }

        if (colIdx + 1 < matrix[0].length)
            if (!visited[rowIdx][colIdx + 1] && matrix[rowIdx][colIdx + 1] < matrix[rowIdx][colIdx]) {
                visited[rowIdx][colIdx + 1] = true;
                List<int[]> thisPath = new ArrayList<>(incomingPath);
                thisPath.add(new int[]{rowIdx, colIdx});
                paths.addAll(dfs(rowIdx, colIdx + 1, matrix, visited, thisPath));
            }

        /**
         * If no neighbors are eligible then add current idx to incoming path and return
         * for fulfilling the terminating condition
         */
        if (paths.size() == 0) {
            incomingPath.add(new int[]{rowIdx, colIdx});
            paths.add(incomingPath);
        }

        return paths;
    }

    public static void main(String[] args) {
        System.out.println(
                longestPathLength(
                        new int[][]{
                                new int[]{9, 9, 4},
                                new int[]{6, 6, 8},
                                new int[]{2, 1, 1}
                        }
                )
        );

        System.out.println(
                longestPathLength(
                        new int[][]{
                                new int[]{3, 4, 5},
                                new int[]{3, 2, 6},
                                new int[]{2, 2, 1}
                        }
                )
        );
    }
}
