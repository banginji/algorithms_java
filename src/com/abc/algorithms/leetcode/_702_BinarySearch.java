package com.abc.algorithms.leetcode;

import com.abc.algorithms.misc.Tuple;

public class _702_BinarySearch {
    private static int search(int[] nums, int target) {
        Tuple<Integer, Integer> range = getRange(nums, 0, 2, target);

        return binarySearch(nums, range.first(), range.second(), target);
    }

    private static int binarySearch(int[] nums, int startIdx, int endIdx, int target) {
        int midPoint = (startIdx + endIdx) / 2;

        if (Math.abs(startIdx - endIdx) <= 1) {
            if (nums[startIdx] == target)
                return startIdx;
            else if (nums[endIdx] == target)
                return endIdx;
            else return -1;
        }

        if (target < nums[midPoint])
            return binarySearch(nums, startIdx, midPoint, target);
        else
            return binarySearch(nums, midPoint, endIdx, target);
    }

    private static Tuple<Integer, Integer> getRange(int[] nums, int startIdx, int endIdx, int target) {
        try {
            if (nums[endIdx] < target)
                return getRange(nums, endIdx, 2 * endIdx, target);
        } catch (ArrayIndexOutOfBoundsException e) {
            return getRange(nums, startIdx, nums.length - 1, target);
        }
        return new Tuple<>(startIdx, endIdx);
    }

    public static void main(String[] args) {
        System.out.println(
                search(
                        new int[]{3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170},
                        42
                )
        );
    }
}
