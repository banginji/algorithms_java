package com.abc.algorithms.leetcode.heap;

import java.util.*;

public class _973_KPointsCloseToOrigin {
    private static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0] ^ 2 + a[1] ^ 2));

        for (int[] point : points)
            pq.offer(point);

        List<int[]> result = new ArrayList<>();

        int iterator = k;

        while (iterator-- > 0)
            result.add(pq.poll());

        return result.toArray(new int[k][2]);
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.deepToString(
                        kClosest(
                                new int[][]{
                                        new int[]{3, 3},
                                        new int[]{5, -1},
                                        new int[]{-2, 4}
                                }
                                , 2
                        )
                )
        );
    }
}
