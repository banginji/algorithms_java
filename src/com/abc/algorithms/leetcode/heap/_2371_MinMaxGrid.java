package com.abc.algorithms.leetcode.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _2371_MinMaxGrid {
    private static int[][] minMax(int[][] grid) {
        int[][] res = new int[grid.length][grid[0].length];
        int itrRowIdx = 0;
        int[] rowMax = new int[grid[0].length], colMax = new int[grid.length];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int[] g : grid) {
            for (int idx = 0; idx < g.length; idx++)
                pq.offer(new int[]{g[idx], itrRowIdx, idx});
            itrRowIdx++;
        }
        while (pq.size() > 0) {
            int[] polled = pq.poll();
            res[polled[1]][polled[2]] = Math.max(rowMax[polled[2]], colMax[polled[1]]) + 1;
            rowMax[polled[2]] = res[polled[1]][polled[2]];
            colMax[polled[1]] = res[polled[1]][polled[2]];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.deepEquals(
                        minMax(
                                new int[][]{
                                        new int[]{3, 1},
                                        new int[]{2, 5}
                                }
                        ), new int[][]{
                                new int[]{2, 1},
                                new int[]{1, 2}
                        }
                )
        );

        System.out.println(
                Arrays.deepEquals(
                        minMax(
                                new int[][]{
                                        new int[]{10}
                                }
                        ), new int[][]{
                                new int[]{1}
                        }
                )
        );

        System.out.println(
                Arrays.deepEquals(
                        minMax(
                                new int[][]{
                                        new int[]{4, 6, 233, 5},
                                        new int[]{81, 45, 7, 9},
                                        new int[]{2, 1, 80, 67},
                                        new int[]{3, 33, 12, 906},
                                        new int[]{345, 123, 769, 13414}
                                }
                        ),
                        new int[][]{
                                new int[]{4,6,9,5},
                                new int[]{9,8,1,6},
                                new int[]{2,1,8,7},
                                new int[]{3,7,4,8},
                                new int[]{10,9,11,12}
                        }
                )
        );

        // [[4, 6, 233, 5], [81, 45, 7, 9], [2, 1, 80, 67], [3, 33, 12, 906], [345, 123, 769, 13414]]
    }
}
