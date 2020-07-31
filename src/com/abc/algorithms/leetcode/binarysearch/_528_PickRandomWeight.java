package com.abc.algorithms.leetcode.binarysearch;

import java.util.Random;

public class _528_PickRandomWeight {
    private static Integer pickIndex(int[] w) {

        int[] prefixSum = new int[w.length];

        prefixSum[0] = w[0];

        for (int idx = 1; idx < w.length; idx++)
            prefixSum[idx] = prefixSum[idx - 1] + w[idx];

        int randomIdx = new Random().nextInt(prefixSum[prefixSum.length - 1]);

        return w[binarySearch(prefixSum, randomIdx, 0, prefixSum.length - 1)];
//        return pickIdx(new Random(), prefixSum);
    }

    private static int pickIdx(Random random, int[] prefixSum) {
        int target = random.nextInt(prefixSum[prefixSum.length - 1]);
        int low = 0;
        int high = prefixSum.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (prefixSum[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private static int binarySearch(int[] nums, int target, int startIdx, int endIdx) {
        int midPoint = (endIdx + startIdx) / 2;

        if (Math.abs(startIdx - endIdx) <= 1) {
            if (nums[startIdx] == target)
                return startIdx;
            if (nums[endIdx] == target)
                return endIdx;
            return endIdx;
        }

        if (nums[midPoint] > target)
            return binarySearch(nums, target, startIdx, midPoint);
        else
            return binarySearch(nums, target, midPoint, endIdx);
    }

    public static void main(String[] args) {
        System.out.println(pickIndex(new int[]{3, 14, 1, 7}));
    }
}
