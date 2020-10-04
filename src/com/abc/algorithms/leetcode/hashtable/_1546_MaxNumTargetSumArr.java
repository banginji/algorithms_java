package com.abc.algorithms.leetcode.hashtable;

import java.util.HashSet;
import java.util.Set;

public class _1546_MaxNumTargetSumArr {
    private static int maxNonOverlapping(int[] nums, int target) {
        int count = 0, sum = 0;
        Set<Integer> prefixSums = new HashSet<>();

        for (int num: nums) {
            sum += num;

            if (sum == target || prefixSums.contains(sum - target)) {
                count++;
                sum = 0;
                prefixSums.clear();
            } else
                prefixSums.add(sum);
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                maxNonOverlapping(
                        new int[]{1, 1, 1, 1, 1}, 2
                ) == 2
        );

        System.out.println(
                maxNonOverlapping(
                        new int[]{-1, 3, 5, 1, 4, 2, -9}, 6
                ) == 2
        );

        System.out.println(
                maxNonOverlapping(
                        new int[]{-2, 6, 6, 3, 5, 4, 2, 2, 8}, 10
                ) == 2
        );

        System.out.println(
                maxNonOverlapping(
                        new int[]{0, 0, 0}, 0
                ) == 3
        );
    }
}
