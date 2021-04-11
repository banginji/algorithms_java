package com.abc.algorithms.leetcode.uncategorized;

public class _704_BinarySearch {
    private static int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    private static int search(int[] nums, int target, int startIdx, int endIdx) {
        int midPoint = (startIdx + endIdx) / 2;

        if (Math.abs(startIdx - endIdx) <= 1) {
            if (nums[startIdx] == target)
                return startIdx;
            else if (nums[endIdx] == target)
                return endIdx;
            else
                return -1;
        }

        if (target < midPoint)
            return search(nums, target, startIdx, midPoint);
        else
            return search(nums, target, midPoint, endIdx);
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{-1, 0, 3, 5, 9, 12}, 0));
    }
}
