package com.abc.algorithms.leetcode.binarysearch;

public class _852_PeakInMtArray {
    private static int peak(int[] nums) {
        int leftIdx = 0, rightIdx = nums.length - 1;

        while (leftIdx < rightIdx) {
            int midPoint = (leftIdx + rightIdx) / 2;

            if (nums[midPoint] < nums[midPoint + 1])
                leftIdx = midPoint + 1;
            else
                rightIdx = midPoint;
        }

        return leftIdx;
    }

    private static int peakAlt(int[] nums) {
        int idx = 0;
        while (nums[idx] < nums[idx + 1]) idx++;
        return idx;
    }

    public static void main(String[] args) {
        System.out.println(
                peak(
                        new int[]{1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1}
                ) == 5
        );

        System.out.println(
                peak(
                        new int[]{4, 12, 22, 32, 67, 43, 1}
                ) == 4
        );

        System.out.println(
                peak(
                        new int[]{1, 5, 2}
                ) == 1
        );

        System.out.println(
                peakAlt(
                        new int[]{1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1}
                ) == 5
        );

        System.out.println(
                peakAlt(
                        new int[]{4, 12, 22, 32, 67, 43, 1}
                ) == 4
        );

        System.out.println(
                peakAlt(
                        new int[]{1, 5, 2}
                ) == 1
        );
    }
}
