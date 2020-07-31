package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _743_NetworkDelay {
    private static int networkDelay(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> neighbors = new HashMap<>();

        for (int node = 1; node <= n; node++)
            neighbors.put(node, new ArrayList<>());

        for (int[] time : times)
            neighbors.computeIfPresent(time[0], (key, value) -> {
                value.add(new int[]{time[1], time[2]});
                return value;
            });

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(nodeAndWeight -> nodeAndWeight[1]));
        pq.offer(new int[]{k, 0});

        while (pq.size() > 0) {
            int[] currentNodeAndWeight = pq.poll();

            List<int[]> currentNodeNeighbors = neighbors.remove(currentNodeAndWeight[0]);

            if (neighbors.isEmpty()) return currentNodeAndWeight[1];

            if (currentNodeNeighbors == null) continue;

            for (int[] neighbor : currentNodeNeighbors)
                pq.offer(new int[]{neighbor[0], neighbor[1] + currentNodeAndWeight[1]});
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(
                networkDelay(
                        new int[][]{
                                new int[]{1, 2, 1},
                                new int[]{2, 3, 2},
                                new int[]{1, 3, 4}
                        },
                        3,
                        1
                )
        );
    }
}
