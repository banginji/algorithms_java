package com.abc.algorithms.chapter8;

public class _8_3_MagicIndex {
    private static int getMagicIndexOnDistinctArray(int[] nums) {
        return getMagicIndexOnDistinctArray(nums, 0, nums.length - 1);
    }

    private static int getMagicIndexOnDistinctArray(int[] nums, int startIdx, int endIdx) {
        if (Math.abs(startIdx - endIdx) <= 1) {
            if (nums[startIdx] == startIdx)
                return startIdx;
            else if (nums[endIdx] == endIdx)
                return endIdx;
            else
                return -1;
        }

        int midPoint = Math.abs(startIdx + endIdx) / 2;

        if (nums[midPoint] == midPoint)
            return midPoint;

        if (nums[midPoint] < midPoint)
            return getMagicIndexOnDistinctArray(nums, midPoint + 1, endIdx);
        else
            return getMagicIndexOnDistinctArray(nums, startIdx, midPoint - 1);
    }

    private static int getMagicIndexOnNonDistinctArray(int[] nums) {
        return getMagicIndexOnNonDistinctArray(nums, 0, nums.length - 1);
    }

    private static int getMagicIndexOnNonDistinctArray(int[] nums, int startIdx, int endIdx) {
        if (Math.abs(startIdx - endIdx) <= 1) {
            if (nums[startIdx] == startIdx)
                return startIdx;
            else if (nums[endIdx] == endIdx)
                return endIdx;
            else
                return -1;
        }

        int midPoint = (startIdx + endIdx) / 2;

        if (nums[midPoint] == midPoint)
            return midPoint;

        int left = getMagicIndexOnNonDistinctArray(
                nums,
                startIdx,
                Math.min(
                        midPoint - 1,
                        nums[midPoint]
                )
        );

        if (left >= 0)
            return left;

        return getMagicIndexOnNonDistinctArray(
                nums,
                Math.max(
                        midPoint + 1,
                        nums[midPoint]
                ),
                endIdx
        );
    }

    public static void main(String[] args) {
        System.out.println(
                getMagicIndexOnDistinctArray(
                        new int[]{-12, -9, 2, 6, 8, 9, 14, 17, 19}
                )
        );

        System.out.println(
                getMagicIndexOnNonDistinctArray(
                        new int[]{-1234, -4242, -531, -531, -42, -42, 0, 1, 5, 7, 10, 44, 44, 44, 25}
                )
        );
    }
}
