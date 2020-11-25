package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _1631_MinEffortPath {
    private record Cell(int rowIdx, int colIdx) {}

    private static int minEffortPath(int[][] heights) {
        Map<Cell, List<int[]>> nMap = new HashMap<>();
        int rows = heights.length, cols = heights[0].length;

        // Initialize neighbor indices and absolute height difference between current index and neighbor
        for (int rowIdx = 0; rowIdx < rows; rowIdx++)
            for (int colIdx = 0; colIdx < cols; colIdx++)
                nMap.put(new Cell(rowIdx, colIdx), nWeights(rowIdx, rows, colIdx, cols, heights));

        // Set to keep track of visited indices
        Set<Cell> visited = new HashSet<>();

        // Min Heap based on height difference of neighbors
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        // Initialize start node with weight 0
        pq.offer(new int[]{0, 0, 0});

        // Initialize distance map for all indices; start index should be 0 and rest INF
        Map<Cell, Integer> dMap = new HashMap<>();

        for (int rowIdx = 0; rowIdx < rows; rowIdx++)
            for (int colIdx = 0; colIdx < cols; colIdx++)
                if (rowIdx == 0 && colIdx == 0)
                    dMap.put(new Cell(rowIdx, colIdx), 0);
                else
                    dMap.put(new Cell(rowIdx, colIdx), Integer.MAX_VALUE);

        while (pq.size() > 0) {
            // Current Neighbor Weight
            int[] cnw = pq.poll();
            int rowIdx = cnw[0];
            int colIdx = cnw[1];
            int wt = cnw[2];

            // If current index is last cell then return cell's calculated distance
            if (rowIdx == rows - 1 && colIdx == cols - 1) return dMap.get(new Cell(rowIdx, colIdx));

            // Add current index to visited set
            visited.add(new Cell(rowIdx, colIdx));

            // Iterate over current index's neighbors and add them to min heap after modification of its 'distance'
            for (int[] nw : nMap.getOrDefault(new Cell(rowIdx, colIdx), Collections.emptyList())) {
                int nRowIdx = nw[0];
                int nColIdx = nw[1];

                // If visited set contains current neighbor, skip subsequent processing
                if (visited.contains(new Cell(nRowIdx, nColIdx))) continue;

                // Process neighbor by getting the neighbor map of the current node
                List<int[]> ncs = nMap.get(new Cell(rowIdx, colIdx));

                // Iterator to find neighbor
                for (int[] nc : ncs)
                    if (nc[0] == nRowIdx && nc[1] == nColIdx) {
                        // Weight of node from distance map which is transient
                        Integer nwt = dMap.get(new Cell(nRowIdx, nColIdx));
                        // Max between current node's weight and current neighbor's weight
                        int maxDiff = Math.max(wt, nc[2]);
                        if (maxDiff < nwt) {
                            dMap.put(new Cell(nRowIdx, nColIdx), maxDiff);
                            pq.offer(new int[]{nw[0], nw[1], maxDiff});
                        }
                        break;
                    }
            }
        }

        return 0;
    }

    private static List<int[]> nWeights(int rowIdx, int rows, int colIdx, int cols, int[][] heights) {
        List<int[]> neighbors = new ArrayList<>();

        // left elem
        if (colIdx - 1 >= 0)
            neighbors.add(new int[]{rowIdx, colIdx - 1, Math.abs(heights[rowIdx][colIdx - 1] - heights[rowIdx][colIdx])});
        // right elem
        if (colIdx + 1 < cols)
            neighbors.add(new int[]{rowIdx, colIdx + 1, Math.abs(heights[rowIdx][colIdx + 1] - heights[rowIdx][colIdx])});
        // up elem
        if (rowIdx - 1 >= 0)
            neighbors.add(new int[]{rowIdx - 1, colIdx, Math.abs(heights[rowIdx - 1][colIdx] - heights[rowIdx][colIdx])});
        // down elem
        if (rowIdx + 1 < rows)
            neighbors.add(new int[]{rowIdx + 1, colIdx, Math.abs(heights[rowIdx + 1][colIdx] - heights[rowIdx][colIdx])});

        return neighbors;
    }

    public static void main(String[] args) {
        System.out.println(
                minEffortPath(
                        new int[][]{
                                new int[]{1, 2, 2},
                                new int[]{3, 8, 2},
                                new int[]{5, 3, 5}
                        }
                ) == 2
        );

        System.out.println(
                minEffortPath(
                        new int[][]{
                                new int[]{1, 10, 6, 7, 9, 10, 4, 9},
                        }
                ) == 9
        );
    }
}
