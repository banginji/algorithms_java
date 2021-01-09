package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _1697_CheckEdgeExistence {
    private static boolean[] checkEdgeExistence(int n, int[][] edgeList, int[][] queries) {
        Map<Integer, List<int[]>> edgeWeightMap = new HashMap<>();
        for (int[] edge : edgeList) {
            edgeWeightMap.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
            edgeWeightMap.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(new int[]{edge[0], edge[2]});
        }

        boolean[] result = new boolean[queries.length];
        int itrIdx = 0;
        for (int[] query : queries) {
            Boolean[][] dp = new Boolean[n][n];
            boolean[] visited = new boolean[n];
            visited[query[0]] = true;
            result[itrIdx++] = dfs(query[0], query[1], query[2], edgeWeightMap, visited, dp);
        }
        return result;
    }

    private static boolean dfs(int startNode, int endNode, int weight, Map<Integer, List<int[]>> edgeWeightMap, boolean[] visited, Boolean[][] dp) {
        if (startNode == endNode) return true;
        if (dp[startNode][endNode] != null) return dp[startNode][endNode];
        for (int[] nodeWeight : edgeWeightMap.getOrDefault(startNode, Collections.emptyList()))
            if (nodeWeight[1] < weight && !visited[nodeWeight[0]]) {
                visited[nodeWeight[0]] = true;
                dp[startNode][endNode] = dfs(nodeWeight[0], endNode, weight, edgeWeightMap, visited, dp);
                if(dp[startNode][endNode]) return true;
            }
        return false;
    }

    /**
     * The idea is to sort queries and edges by their weights and perform union find on them
     * progressively. TLE for the question though. Should try with Union Find with rank
     */
    private static boolean[] edgeUf(int n, int[][] edgeList, int[][] queries) {
        int[] parents = new int[n];
        for (int node = 0; node < n; node++) parents[node] = node;
        int[][] itrQueries = new int[queries.length][4];
        for (int idx = 0; idx < queries.length; idx++) itrQueries[idx] = new int[]{queries[idx][0], queries[idx][1], queries[idx][2], idx};
        Arrays.sort(edgeList, Comparator.comparingInt(a -> a[2]));
        Arrays.sort(itrQueries, Comparator.comparingInt(a -> a[2]));
        int edgeListIdx = 0;
        boolean[] result = new boolean[queries.length];
        for (int[] query : itrQueries) {
            while (edgeListIdx < edgeList.length && edgeList[edgeListIdx][2] < query[2]) {
                union(edgeList[edgeListIdx][0], edgeList[edgeListIdx][1], parents);
                edgeListIdx++;
            }
            result[query[3]] = find(query[0], parents) == find(query[1], parents);
        }
        return result;
    }

    private static void union(int nodeOne, int nodeTwo, int[] parents) {
        int parentOne = find(nodeOne, parents);
        int parentTwo = find(nodeTwo, parents);
        if (parentOne != parentTwo) parents[parentOne] = parentTwo;
    }

    private static int find(int node, int[] parents) {
        return parents[node] == node ? node : find(parents[node], parents);
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.equals(
                        checkEdgeExistence(
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
                        checkEdgeExistence(
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
                        edgeUf(
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
                        edgeUf(
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
                        edgeUf(
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
