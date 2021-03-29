package com.abc.algorithms.leetcode.binarysearch;

public class _1802_MaxIdxValueInArray {
    private static int maxIdxValue(int n, int index, int maxSum) {
        return bs(0, n - 1, n, index, maxSum);
    }

    private static int bs(int left, int right, int n, int idx, int maxSum) {
        int mid = (left + right) / 2;
        if (right - left <= 1) return checkNum(n, idx, maxSum, left) ? left : right;
        if (checkNum(n, idx, maxSum, mid))
            return bs(mid, right, n, idx, maxSum);
        else
            return bs(left, mid, n, idx, maxSum);
    }

    private static boolean checkNum(int n, int idx, int maxSum, int idxNum) {
        int[] nums = new int[n];
        nums[idx] = idxNum;

        int itrSum = 0;
        itrSum += nums[idx];

        int rIdx = idx + 1;
        while (rIdx < n) {
            nums[rIdx] = Math.max(nums[rIdx - 1] - 1, 1);
            itrSum += nums[rIdx];
            if (itrSum > maxSum) return false;
            rIdx++;
        }
        int lIdx = idx - 1;
        while (lIdx >= 0) {
            nums[lIdx] = Math.max(nums[lIdx + 1] - 1, 1);
            itrSum += nums[lIdx];
            if (itrSum > maxSum) return false;
            lIdx--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(
                maxIdxValue(4, 2, 6)
        );

        System.out.println(
                maxIdxValue(6, 1, 10)
        );
    }
}
