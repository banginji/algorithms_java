package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _1443_MinTimeApples {
    private static int minTime(int n, int[][] edges, List<Boolean> hasApples) {
        Map<Integer, List<Integer>> nodesMap = new HashMap<>();

        for (int[] edge : edges)
            nodesMap.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);

        int minTime = dfs(0, nodesMap, hasApples);

        return minTime * 2;
    }

    private static int dfs(Integer node, Map<Integer, List<Integer>> nodesMap, List<Boolean> hasApples) {
        int minTime = 0;

        for (int childNode : nodesMap.getOrDefault(node, Collections.emptyList()))
            minTime += dfs(childNode, nodesMap, hasApples);

        /**
         * If current node has an apple
         * If current node does NOT have an apple but some child has an apple
         * If current node is NOT the root node
         */
        if ((hasApples.get(node) || minTime > 0) && node != 0) minTime++;

        return minTime;
    }

    public static void main(String[] args) {
        System.out.println(
                minTime(
                        7,
                        new int[][]{
                                new int[]{0, 1},
                                new int[]{0, 2},
                                new int[]{1, 4},
                                new int[]{1, 5},
                                new int[]{2, 3},
                                new int[]{2, 6}
                        },
                        List.of(false, false, false, false, false, false, false)
                )
        );
    }
}
