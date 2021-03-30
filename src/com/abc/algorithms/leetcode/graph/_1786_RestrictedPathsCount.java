package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _1786_RestrictedPathsCount {
    private static int rpc(int n, int[][] edges) {
        Map<Integer, List<int[]>> nMap = new HashMap<>();
        for (int[] edge : edges) {
            nMap.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
            nMap.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(new int[]{edge[0], edge[2]});
        }
        int[] dist = dijkstra(n, nMap);
        return dfs(1, n, 0, nMap, dist);
    }

    private static int[] dijkstra(int n, Map<Integer, List<int[]>> nMap) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{n, 0});

        boolean[] visited = new boolean[n + 1];
        visited[n] = true;

        while (pq.size() > 0) {
            int[] curr = pq.poll();
            int currentNode = curr[0], currentDistance = curr[1];
            for (int[] neighbor : nMap.getOrDefault(currentNode, Collections.emptyList())) {
                int neighborIdx = neighbor[0], neighborDistance = neighbor[1];
                if (neighborDistance + currentDistance < dist[neighborIdx]) {
                    dist[neighborIdx] = neighborDistance + currentDistance;
                    if (!visited[neighborIdx]) {
                        visited[neighborIdx] = true;
                        pq.offer(new int[]{neighborIdx, dist[neighborIdx]});
                    }
                }
            }
            System.out.println(Arrays.toString(dist));
        }
        return dist;
    }

    private static int dfs(int start, int end, int count, Map<Integer, List<int[]>> nMap, int[] dist) {
        if (start == end) return count + 1;
        for (int[] neighbor : nMap.getOrDefault(start, Collections.emptyList())) {
            int neighborIdx = neighbor[0];
            if (dist[neighborIdx] < dist[start]) count = dfs(neighborIdx, end, count, nMap, dist);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                rpc(
                        5,
                        new int[][]{
                                new int[]{1, 2, 3},
                                new int[]{1, 3, 3},
                                new int[]{2, 3, 1},
                                new int[]{1, 4, 2},
                                new int[]{5, 2, 2},
                                new int[]{3, 5, 1},
                                new int[]{5, 4, 10}
                        }
                ) == 3
        );

        System.out.println(
                rpc(
                        7,
                        new int[][]{
                                new int[]{1, 3, 1},
                                new int[]{4, 1, 2},
                                new int[]{7, 3, 4},
                                new int[]{2, 5, 3},
                                new int[]{5, 6, 1},
                                new int[]{6, 7, 2},
                                new int[]{7, 5, 3},
                                new int[]{2, 6, 4}
                        }
                ) == 1
        );

        System.out.println(
                rpc(
                        5,
                        new int[][]{
                                new int[]{1, 5, 9},
                                new int[]{1, 2, 9},
                                new int[]{1, 4, 3},
                                new int[]{1, 3, 4},
                                new int[]{2, 5, 6},
                                new int[]{2, 4, 5},
                                new int[]{2, 3, 5},
                                new int[]{3, 4, 2},
                                new int[]{3, 5, 2},
                                new int[]{4, 5, 1}
                        }
                ) == 4
        );
    }
}
