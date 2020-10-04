package com.abc.algorithms.leetcode.hashtable;

import java.util.HashSet;
import java.util.Set;

public class _560_SubArraySumCt {
    private static int subArraySumCt(int[] nums, int k) {
        int count = 0, sum = 0;
        Set<Integer> prefixSums = new HashSet<>();

        for (int num : nums) {
            sum += num;

            if (sum == k || prefixSums.contains(sum - k))
                count++;

            prefixSums.add(sum);
        }

        return count;
    }

    public static void main(String[] args) {
        // [0, 1], [1, 2]
        System.out.println(
                subArraySumCt(
                        new int[]{1, 1, 1},
                        2
                ) == 2
        );

        // [3, 4, 5]
        System.out.println(
                subArraySumCt(
                        new int[]{10, 20, 30, 40, -10, 80},
                        110
                ) == 1
        );

        // [1, 2], [2, 3], [3, 4], [4, 5]
        System.out.println(
                subArraySumCt(
                        new int[]{-14, 1, 1, 1, 1, 1}, 2
                ) == 4
        );

        // [1, 2, 3, 4], [4, 5], [6, 7]
        System.out.println(
                subArraySumCt(
                        new int[]{8, -1, 3, -1, 5, 1, 4, 2, -9}, 6
                ) == 3
        );

        // [0, 1, 2], [2, 3, 4], [5, 6, 7], [6, 7, 8]
        System.out.println(
                subArraySumCt(
                        new int[]{6, 6, -2, 3, 5, 4, 1, 1, 8}, 10
                ) == 4
        );

        // [0], [0, 1], [0, 1, 2]
        System.out.println(
                subArraySumCt(
                        new int[]{0, 0, 0}, 0
                ) == 3
        );
    }
}
