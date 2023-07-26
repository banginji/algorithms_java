package com.abc.algorithms.leetcode.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class _2333_MinSumSqDiff {
    private static long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        List<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int idx = 0; idx < nums1.length; idx++) list.add(Math.abs(nums1[idx] - nums2[idx]));
        for (Integer elem : list) pq.offer(elem);
        int totalDiff = k1 + k2;
        while (totalDiff-- > 0) pq.offer(pq.poll() - 1);
        int res = 0;
        while (pq.size() > 0) res += Math.pow(pq.poll(), 2);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(
                minSumSquareDiff(
                        new int[]{1, 2, 3, 4},
                        new int[]{2, 10, 20, 19},
                        0,
                        0
                ) == 579
        );

        System.out.println(
                minSumSquareDiff(
                        new int[]{1, 4, 10, 12},
                        new int[]{5, 8, 6, 9},
                        1,
                        1
                ) == 43
        );

        System.out.println(
                minSumSquareDiff(
                        new int[]{2, 4, 2, 24, 76, 22, 67, 1, 234, 2, 6, 1},
                        new int[]{66, 6, 12, 56, 96, 43, 7, 3, 4, 9, 13, 83},
                        2,
                        5
                ) == 66220
        );
    }
}
