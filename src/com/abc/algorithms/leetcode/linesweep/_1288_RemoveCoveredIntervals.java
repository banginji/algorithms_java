package com.abc.algorithms.leetcode.linesweep;

import java.util.PriorityQueue;

public class _1288_RemoveCoveredIntervals {
    private static int removeCoveredIntervals(int[][] intervals) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];

            return b[1] - a[1];
        });

        for (int[] interval : intervals)
            pq.offer(interval);

        int largestRight = 0;

        int intervalCount = 0;

        while (pq.size() > 0) {
            int[] currentInterval = pq.poll();
            if (largestRight < currentInterval[1]) {
                largestRight = currentInterval[1];
                intervalCount++;
            }
        }

        return intervalCount;
    }

    public static void main(String[] args) {
        System.out.println(
                removeCoveredIntervals(
                        new int[][]{
                                new int[]{1, 4},
                                new int[]{3, 6},
                                new int[]{2, 8},
                        }
                ) == 2
        );

        System.out.println(
                removeCoveredIntervals(
                        new int[][]{
                                new int[]{1, 4},
                                new int[]{2, 3}
                        }
                ) == 1
        );

        System.out.println(
                removeCoveredIntervals(
                        new int[][]{
                                new int[]{0, 10},
                                new int[]{5, 12}
                        }
                ) == 2
        );

        System.out.println(
                removeCoveredIntervals(
                        new int[][]{
                                new int[]{1, 2},
                                new int[]{1, 4},
                                new int[]{3, 4},
                        }
                ) == 1
        );

        System.out.println(
                removeCoveredIntervals(
                        new int[][]{
                                new int[]{1, 4},
                                new int[]{1, 2},
                                new int[]{3, 4},
                        }
                ) == 1
        );
    }
}
