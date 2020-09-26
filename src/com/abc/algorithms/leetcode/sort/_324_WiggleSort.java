package com.abc.algorithms.leetcode.sort;

import java.util.Arrays;

public class _324_WiggleSort {
    private static void wiggleSort(int[] nums) {
        Arrays.sort(nums);

        int[] result = new int[nums.length];

        int firstHalfIdx = 0, secondHalfIdx = nums.length / 2;

        for (int idx = 0; idx < nums.length; idx++)
            if (idx % 2 == 0)
                result[idx] = nums[firstHalfIdx++];
            else
                result[idx] = nums[secondHalfIdx++];

        System.out.println(Arrays.toString(result));
    }

    public static void main(String[] args) {
        wiggleSort(new int[]{1, 3, 2, 2, 3, 1});
    }
}
