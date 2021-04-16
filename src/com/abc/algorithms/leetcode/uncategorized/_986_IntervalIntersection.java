package com.abc.algorithms.leetcode.uncategorized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _986_IntervalIntersection {
    private static int[][] intersections(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<>();
        for (int i = 0, j = 0; i < A.length && j < B.length; ) {
            int start = Math.max(A[i][0], B[j][0]);
            int end = Math.min(A[i][1], B[j][1]);
            if (start <= end) res.add(new int[]{start, end});
            if (A[i][1] < B[j][1]) i++; else j++;
        }
        return res.toArray(new int[res.size()][res.size() > 0 ? res.get(0).length : 0]);
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.deepEquals(
                        intersections(
                                new int[][]{
                                        new int[]{0, 2},
                                        new int[]{5, 10},
                                        new int[]{13, 23},
                                        new int[]{24, 25}
                                },
                                new int[][]{
                                        new int[]{1, 5},
                                        new int[]{8, 12},
                                        new int[]{15, 24},
                                        new int[]{25, 26}
                                }
                        ),
                        new int[][]{
                                new int[]{1, 2},
                                new int[]{5, 5},
                                new int[]{8, 10},
                                new int[]{15, 23},
                                new int[]{24, 24},
                                new int[]{25, 25}
                        }
                )
        );
    }
}
