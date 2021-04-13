package com.abc.algorithms.leetcode.array;

import java.util.Arrays;

public class _1150_MajorityElement {
    private static boolean majorityElement(int[] nums, int target) {
        int someIdx = Arrays.binarySearch(nums, 0, nums.length, target);
        if (someIdx < 0) return false;
        int lowItrIdx = someIdx, highItrIdx = someIdx;
        while (lowItrIdx - 1 >= 0 && nums[lowItrIdx - 1] == target) lowItrIdx--;
        while (highItrIdx + 1 < nums.length && nums[highItrIdx + 1] == target) highItrIdx++;
        return highItrIdx - lowItrIdx + 1 > nums.length / 2;
    }

    public static void main(String[] args) {
        System.out.println(
                majorityElement(
                        new int[]{438885258, 438885258},
                        438885258
                )
        );

        System.out.println(
                !majorityElement(
                        new int[]{10, 100, 101, 101},
                        101
                )
        );

        System.out.println(
                !majorityElement(
                        new int[]{438885258},
                        786460391
                )
        );
    }
}