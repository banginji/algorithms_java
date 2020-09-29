package com.abc.algorithms.leetcode.bfs;

import java.util.ArrayList;
import java.util.List;

public class _407_TrappingRainII {
    private static int trappingRain(int[][] heightMap) {
        int result = 0;
        int rowLength = heightMap.length, colLength = heightMap[0].length;

        for (int rowIdx = 1; rowIdx < rowLength - 1; rowIdx++)
            for (int colIdx = 1; colIdx < colLength - 1; colIdx++) {
                int minNeighbor = minInNeighbors(neighbors(rowIdx, colIdx, heightMap), heightMap);
                if (heightMap[rowIdx][colIdx] < minNeighbor) {
                    result += (minNeighbor - heightMap[rowIdx][colIdx]);
                    heightMap[rowIdx][colIdx] = minNeighbor;
                }
            }

        return result;
    }

    private static int minInNeighbors(List<int[]> neighbors, int[][] heightMap) {
        // return MIN_VALUE so that current node is not altered when no neighbors are less than current node
        if (neighbors.size() == 0)
            return Integer.MIN_VALUE;

        int min = Integer.MAX_VALUE;
        for (int[] neighbor : neighbors)
            min = Math.min(heightMap[neighbor[0]][neighbor[1]], min);

        return min;
    }

    private static List<int[]> neighbors(int rowIdx, int colIdx, int[][] heightMap) {
        List<int[]> neighbors = new ArrayList<>();
        int rowLength = heightMap.length, colLength = heightMap[0].length;
        int valueAtIdx = heightMap[rowIdx][colIdx];

        if (rowIdx - 1 >= 0 && heightMap[rowIdx - 1][colIdx] > valueAtIdx)
            neighbors.add(new int[]{rowIdx - 1, colIdx});

        if (rowIdx + 1 < rowLength && heightMap[rowIdx + 1][colIdx] > valueAtIdx)
            neighbors.add(new int[]{rowIdx + 1, colIdx});

        if (colIdx - 1 >= 0 && heightMap[rowIdx][colIdx - 1] > valueAtIdx)
            neighbors.add(new int[]{rowIdx, colIdx - 1});

        if (colIdx + 1 < colLength && heightMap[rowIdx][colIdx + 1] > valueAtIdx)
            neighbors.add(new int[]{rowIdx, colIdx + 1});

        return neighbors;
    }

    public static void main(String[] args) {
        System.out.println(
                trappingRain(
                        new int[][]{
                                new int[]{1, 4, 3, 1, 3, 2},
                                new int[]{3, 2, 1, 3, 2, 4},
                                new int[]{2, 3, 3, 2, 3, 1},
                        }
                ) == 4
        );
    }
}
