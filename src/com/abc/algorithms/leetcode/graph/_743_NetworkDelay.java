package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _743_NetworkDelay {
    private static int networkDelay(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> neighborMap = new HashMap<>();

        for (int[] time: times)
            neighborMap.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new int[]{time[1], time[2]});

        PriorityQueue<int[]> pq = new PriorityQueue<>((a , b) -> b[1] - a[1]);
        pq.offer(new int[]{k, 0});

        int time = Integer.MIN_VALUE;

        Set<Integer> visited = new HashSet<>();

        while (pq.size() > 0) {
            /**
             * If all nodes are visited and pq is not empty it means that there is a cycle
             * Break the loop and return the time
             */
            if (visited.size() == n)
                break;

            int[] currentNodeAndTime = pq.poll();
            int currentNode = currentNodeAndTime[0];
            int currentTime = currentNodeAndTime[1];

            /**
             * If there is a smaller cycle with the source node, it means not all nodes are
             * reachable. Hence return -1 else add node to visited set
             */
            if (visited.contains(currentNode))
                return -1;
            else
                visited.add(currentNode);

            time = Math.max(time, currentTime);

            for (int[] neighbor: neighborMap.getOrDefault(currentNode, Collections.emptyList()))
                pq.offer(new int[]{neighbor[0], currentTime + neighbor[1]});
        }

        return visited.size() == n ? time : -1;
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
                ) == 4
        );

        System.out.println(
                networkDelay(
                        new int[][]{
                                new int[]{2, 1, 1},
                                new int[]{2, 3, 1},
                                new int[]{3, 4, 1}
                        },
                        4,
                        2
                ) == 2
        );

        // Node 4 is not reachable from 2
        System.out.println(
                networkDelay(
                        new int[][]{
                                new int[]{2, 1, 1},
                                new int[]{2, 3, 1},
                                new int[]{4, 3, 1}
                        },
                        4,
                        2
                ) == -1
        );

        // Cycle is present but all nodes were traversed
        System.out.println(
                networkDelay(
                        new int[][]{
                                new int[]{2, 1, 1},
                                new int[]{2, 3, 1},
                                new int[]{3, 4, 1},
                                new int[]{4, 2, 1}
                        },
                        4,
                        2
                ) == 2
        );

        // Cycle is present but NOT all nodes were traversed
        System.out.println(
                networkDelay(
                        new int[][]{
                                new int[]{2, 1, 1},
                                new int[]{1, 2, 1},
                                new int[]{2, 3, 1},
                                new int[]{3, 4, 1}
                        },
                        4,
                        2
                ) == -1
        );
    }
}
