package com.abc.algorithms.leetcode.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class _1557_MinNumOfVertices {
    private static List<Integer> minNumberOfVertices(int n, List<List<Integer>> edges) {
        Map<Integer, Integer> inDegree = new HashMap<>();

        for (int edge = 0; edge < n; edge++)
            inDegree.put(edge, 0);

        for (List<Integer> edge : edges)
            inDegree.computeIfPresent(edge.get(1), (k, v) -> v + 1);

        return inDegree.entrySet().stream()
                .filter(entry -> entry.getValue() == 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(
                minNumberOfVertices(
                        6,
                        List.of(
                                List.of(0, 1),
                                List.of(0, 2),
                                List.of(2, 5),
                                List.of(3, 4),
                                List.of(4, 2)
                        )
                )
        );
    }
}
