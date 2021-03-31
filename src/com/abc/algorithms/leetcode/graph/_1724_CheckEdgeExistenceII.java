package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _1724_CheckEdgeExistenceII {
    private static boolean[] checkEdgeExistenceII(int n, int[][] edges, int[][] queries) {
        // Union find to determine if connected
        int[] parent = new int[n];
        for (int node = 0; node < n; node++) parent[node] = node;
        for (int[] edge : edges) union(edge[0], edge[1], parent);

        Map<Integer, List<int[]>> nMap = new HashMap<>();
        for (int[] edge : edges) {
            nMap.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
            nMap.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(new int[]{edge[0], edge[2]});
        }
        boolean[] res = new boolean[queries.length];
        for (int idx = 0; idx < queries.length; idx++) {
            int start = queries[idx][0], end = queries[idx][1], nWeight = queries[idx][2];
            if (find(parent[start], parent) != find(parent[end], parent)) {
                res[idx] = false;
                continue;
            }
            boolean[] visited = new boolean[n];
            int resWeight = dfs(start, end, nWeight, nMap, visited, 0);
            res[idx] = resWeight != Integer.MAX_VALUE;
        }
        return res;
    }

    private static int dfs(int start, int end, int limit, Map<Integer, List<int[]>> nMap, boolean[] visited, int itrWeight) {
        if (start == end) return itrWeight >= limit ? Integer.MAX_VALUE : itrWeight;
        if (visited[start]) return Integer.MAX_VALUE;
        if (itrWeight >= limit) return Integer.MAX_VALUE;
        visited[start] = true;
        for (int[] neighbor : nMap.getOrDefault(start, Collections.emptyList())) {
            int neighborIdx = neighbor[0], neighborWeight = neighbor[1];
            int calcWeight = dfs(neighborIdx, end, limit, nMap, visited, neighborWeight);
            if (calcWeight != Integer.MAX_VALUE) return calcWeight;
        }
        return Integer.MAX_VALUE;
    }

    private static void union(int nodeOne, int nodeTwo, int[] parent) {
        parent[find(nodeOne, parent)] = parent[find(nodeTwo, parent)];
    }

    private static int find(int node, int[] parent) {
        return parent[node] == node ? node : find(parent[node], parent);
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.equals(
                        checkEdgeExistenceII(
                                6,
                                new int[][]{
                                        new int[]{0, 2, 4},
                                        new int[]{0, 3, 2},
                                        new int[]{1, 2, 3},
                                        new int[]{2, 3, 1},
                                        new int[]{4, 5, 5}
                                },
                                new int[][]{
                                        new int[]{2, 3, 2},
                                        new int[]{1, 3, 3},
                                        new int[]{2, 0, 3},
                                        new int[]{0, 5, 6}
                                }
                        ),
                        new boolean[]{true, false, true, false}
                )
        );

        System.out.println(
                Arrays.equals(
                        checkEdgeExistenceII(
                                3,
                                new int[][]{
                                        new int[]{0, 1, 2},
                                        new int[]{1, 2, 4},
                                        new int[]{2, 0, 8},
                                        new int[]{1, 0, 16}
                                },
                                new int[][]{
                                        new int[]{0, 1, 2},
                                        new int[]{0, 2, 5}
                                }
                        ),
                        new boolean[]{false, true}
                )
        );

        System.out.println(
                Arrays.equals(
                        checkEdgeExistenceII(
                                5,
                                new int[][]{
                                        new int[]{0, 1, 10},
                                        new int[]{1, 2, 5},
                                        new int[]{2, 3, 9},
                                        new int[]{3, 4, 13}
                                },
                                new int[][]{
                                        new int[]{0, 4, 14},
                                        new int[]{1, 4, 13}
                                }
                        ),
                        new boolean[]{true, false}
                )
        );

        System.out.println(
                Arrays.equals(
                        checkEdgeExistenceII(
                                3,
                                new int[][]{
                                        new int[]{0, 1, 2},
                                        new int[]{1, 2, 4},
                                        new int[]{2, 0, 8},
                                        new int[]{1, 0, 16}
                                },
                                new int[][]{
                                        new int[]{0, 1, 2},
                                        new int[]{0, 2, 5}
                                }
                        ),
                        new boolean[]{false, true}
                )
        );

        System.out.println(
                Arrays.equals(
                        checkEdgeExistenceII(
                                5,
                                new int[][]{
                                        new int[]{0, 1, 10},
                                        new int[]{1, 2, 5},
                                        new int[]{2, 3, 9},
                                        new int[]{3, 4, 13}
                                },
                                new int[][]{
                                        new int[]{0, 4, 14},
                                        new int[]{1, 4, 13}
                                }
                        ),
                        new boolean[]{true, false}
                )
        );

        System.out.println(
                Arrays.equals(
                        checkEdgeExistenceII(
                                13,
                                new int[][]{
                                        new int[]{9, 1, 53},
                                        new int[]{3, 2, 66},
                                        new int[]{12, 5, 99},
                                        new int[]{9, 7, 26},
                                        new int[]{1, 4, 78},
                                        new int[]{11, 1, 62},
                                        new int[]{3, 10, 50},
                                        new int[]{12, 1, 71},
                                        new int[]{12, 6, 63},
                                        new int[]{1, 10, 63},
                                        new int[]{9, 10, 88},
                                        new int[]{9, 11, 59},
                                        new int[]{1, 4, 37},
                                        new int[]{4, 2, 63},
                                        new int[]{0, 2, 26},
                                        new int[]{6, 12, 98},
                                        new int[]{9, 11, 99},
                                        new int[]{4, 5, 40},
                                        new int[]{2, 8, 25},
                                        new int[]{4, 2, 35},
                                        new int[]{8, 10, 9},
                                        new int[]{11, 9, 25},
                                        new int[]{10, 11, 11},
                                        new int[]{7, 6, 89},
                                        new int[]{2, 4, 99},
                                        new int[]{10, 4, 63}
                                },
                                new int[][]{
                                        new int[]{9, 7, 65},
                                        new int[]{9, 6, 1},
                                        new int[]{4, 5, 34},
                                        new int[]{10, 8, 43},
                                        new int[]{3, 7, 76},
                                        new int[]{4, 2, 15},
                                        new int[]{7, 6, 52},
                                        new int[]{2, 0, 50},
                                        new int[]{7, 6, 62},
                                        new int[]{1, 0, 81},
                                        new int[]{4, 5, 35},
                                        new int[]{0, 11, 86},
                                        new int[]{12, 5, 50},
                                        new int[]{11, 2, 2},
                                        new int[]{9, 5, 6},
                                        new int[]{12, 0, 95},
                                        new int[]{10, 6, 9},
                                        new int[]{9, 4, 73},
                                        new int[]{6, 10, 48},
                                        new int[]{12, 0, 91},
                                        new int[]{9, 10, 58},
                                        new int[]{9, 8, 73},
                                        new int[]{2, 3, 44},
                                        new int[]{7, 11, 83},
                                        new int[]{5, 3, 14},
                                        new int[]{6, 2, 33}
                                }
                        ),
                        new boolean[]{true, false, false, true, true, false, false, true, false, true, false, true, false, false, false, true, false, true, false, true, true, true, false, true, false, false}
                )
        );
    }
}
