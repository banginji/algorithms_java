package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _1514_PathWithMaxProb {
    private static double maxProb(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Double, List<double[]>> neighborMap = new HashMap<>();

        for (int idx = 0; idx < edges.length; idx++) {
            neighborMap.computeIfAbsent((double) edges[idx][0], x -> new ArrayList<>()).add(new double[]{edges[idx][1], succProb[idx]});
            neighborMap.computeIfAbsent((double) edges[idx][1], x -> new ArrayList<>()).add(new double[]{edges[idx][0], succProb[idx]});
        }

        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));
        pq.add(new double[]{start, 1});

        Set<Double> visitedNodes = new HashSet<>();

        while (pq.size() > 0) {
            double[] currentNodeAndProb = pq.poll();
            double currentNode = currentNodeAndProb[0];
            double currentProb = currentNodeAndProb[1];

            visitedNodes.add(currentNode);

            if (currentNode == end) return currentProb;

            for (double[] neighborNodeAndProb : neighborMap.getOrDefault(currentNode, Collections.emptyList())) {
                if (visitedNodes.contains(neighborNodeAndProb[0])) continue;
                pq.offer(new double[]{neighborNodeAndProb[0], currentProb * neighborNodeAndProb[1]});
            }

        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(
                maxProb(
                        3,
                        new int[][]{
                                new int[]{0, 1},
                                new int[]{1, 2},
                                new int[]{0, 2}
                        },
                        new double[]{0.5, 0.5, 0.3},
                        0, 2
                )
                        == 0.3
        );

        System.out.println(
                maxProb(
                        3,
                        new int[][]{
                                new int[]{0, 1},
                                new int[]{1, 2},
                                new int[]{0, 2}
                        },
                        new double[]{0.5, 0.5, 0.2},
                        0, 2
                )
                        == 0.25
        );

        System.out.println(
                maxProb(
                        3,
                        new int[][]{
                                new int[]{0, 1}
                        },
                        new double[]{0.5},
                        0, 2
                )
                        == 0.0
        );
    }
}
