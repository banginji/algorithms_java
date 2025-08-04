package com.abc.algorithms.leetcode.tree;

import java.util.*;

public class _3558_AssignEdgeWeights {
    private final int terminator = ((int)Math.pow(10, 9) + 7);
    public int assignEdgeWeights(int[][] edges) {

        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int num = 1; num < edges.length + 2; num++) adjMap.put(num, new ArrayList<>());

        for (int[] edge : edges) {
            int parent = Math.min(edge[0], edge[1]), child = Math.max(edge[0], edge[1]);
            adjMap.computeIfPresent(parent, (key, value) -> {
                value.add(child);
                return value;
            });
        }

        int maxDepth = maxDepth(1, 0, adjMap);

        int[] dp = new int[maxDepth + 1];
        Arrays.fill(dp, -1);

        return countEff(maxDepth, 0, dp) % terminator;
    }

    private int maxDepth(int node, int depth, Map<Integer, List<Integer>> adjacency) {
        if (adjacency.get(node).isEmpty()) return depth;
        int maxDepth = Integer.MIN_VALUE;
        for (int child : adjacency.get(node)) {
            maxDepth = Math.max(
                    maxDepth,
                    maxDepth(child, depth + 1, adjacency)
            );
        }
        return maxDepth;
    }

    private int count(int depth, int weightSum) {
        if (depth == 0) return weightSum % 2 == 0 ? 0 : 1;
        return count(depth - 1, weightSum + 1) + count(depth - 1, weightSum + 2);
    }

    private int countEff(int depth, int weightSum, int[] dp) {
        if (depth == 0) return weightSum % 2 == 0 ? 0 : 1;
        if (dp[depth] != -1) return dp[depth];
        return dp[depth] =
                (countEff(depth - 1, (weightSum + 1) % terminator, dp) +
                        countEff(depth - 1, (weightSum + 2) % terminator, dp)) % terminator;
    }

    public static void main(String[] args) {
        System.out.println(
                new _3558_AssignEdgeWeights().assignEdgeWeights(
                        new int[][]{{1, 2}}
                ) == 1
        );

        System.out.println(
                new _3558_AssignEdgeWeights().assignEdgeWeights(
                        new int[][]{{1, 2}, {1, 3}, {3, 4}, {3, 5}}
                ) == 2
        );

        System.out.println(
                new _3558_AssignEdgeWeights().assignEdgeWeights(
                        new int[][]{{2, 4}, {1, 5}, {1, 2}, {1, 3}}
                ) == 2
        );

        System.out.println(
                new _3558_AssignEdgeWeights().assignEdgeWeights(
                        new int[][]{{2, 4}, {1, 5}, {1, 2}, {1, 3}}
                ) == 2
        );

        System.out.println(
                new _3558_AssignEdgeWeights().assignEdgeWeights(
                        new int[][]{{1,2},{2,3},{3,4},{4,5},{5,6},{6,7},{7,8},{8,9},{9,10},{10,11},{11,12},{12,13},{13,14},{14,15},{15,16},{16,17},{17,18},{18,19},{19,20},{20,21},{21,22},{22,23},{23,24},{24,25},{25,26},{26,27},{27,28},{28,29},{29,30},{30,31},{31,32},{32,33},{33,34},{34,35},{35,36},{36,37},{37,38},{38,39},{39,40},{40,41}}
                ) == 755810045
        );

        System.out.println(
                new _3558_AssignEdgeWeights().assignEdgeWeights(
                        new int[][]{{3,2},{2,1}}
                ) == 2
        );
    }
}
