package com.abc.algorithms.leetcode.dp;

import java.util.HashSet;
import java.util.Set;

public class _1695_MaxErasureValue {
    private static int maxErasureValue(int[] nums) {
        int leftIdx = 0, rightIdx = 0, sum = 0, result = 0;

        Set<Integer> seenNums = new HashSet<>();

        while (rightIdx < nums.length) {
            if (seenNums.contains(nums[rightIdx])) {
                sum -= nums[leftIdx];
                seenNums.remove(nums[leftIdx++]);
            } else {
                sum += nums[rightIdx];
                result = Math.max(result, sum);
                seenNums.add(nums[rightIdx++]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                maxErasureValue(
                        new int[]{4, 2, 4, 5, 6}
                )
        );

        System.out.println(
                maxErasureValue(
                        new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5}
                )
        );
    }
}
