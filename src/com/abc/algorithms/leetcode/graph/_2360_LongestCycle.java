package com.abc.algorithms.leetcode.graph;

import java.util.HashMap;
import java.util.Map;

// TODO
public class _2360_LongestCycle {
    private static int longest(int[] edges) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxLength = Integer.MIN_VALUE;
        for (int node = 0; node < edges.length && !map.containsKey(node); node++) {
            map.put(node, 0);
            maxLength = Math.max(maxLength, longest(edges[node], 0, edges, map));
        }
        return maxLength;
    }
    private static int longest(int node, int dist, int[] edges, Map<Integer, Integer> map) {
        if (node < 0) return -1;
        if (map.containsKey(node)) return dist - map.get(node);
        map.put(node, dist + 1);
        return longest(edges[node], dist + 1, edges, map);
    }

    public static void main(String[] args) {
//        System.out.println(
//                longest(new int[]{3, 3, 4, 2, 3}) == 3
//        );
//
//        System.out.println(
//                longest(new int[]{2, -1, 3, 1}) == -1
//        );
//
//        System.out.println(
//                longest(new int[]{1, 0}) == 2
//        );

        System.out.println(
                longest(new int[]{-1, 4, -1, 2, 0, 4})
        );
    }
}
