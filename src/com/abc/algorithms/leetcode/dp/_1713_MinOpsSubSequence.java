package com.abc.algorithms.leetcode.dp;

import java.util.*;

public class _1713_MinOpsSubSequence {
    // MLE; O(ta)
    private static int lcs(int[] target, int[] arr) {
        Integer[][] dp = new Integer[target.length][arr.length];
        return target.length - lcs(target, 0, arr, 0, dp);
    }

    private static int lcs(int[] target, int targetIdx, int[] arr, int arrIdx, Integer[][] dp) {
        if (targetIdx > target.length - 1 || arrIdx > arr.length - 1) return 0;
        if (dp[targetIdx][arrIdx] != null) return dp[targetIdx][arrIdx];
        if (target[targetIdx] == arr[arrIdx]) return dp[targetIdx][arrIdx] = 1 + lcs(target, targetIdx + 1, arr, arrIdx + 1, dp);
        return dp[targetIdx][arrIdx] = Math.max(lcs(target, targetIdx + 1, arr, arrIdx, dp), lcs(target, targetIdx, arr, arrIdx + 1, dp));
    }

    // --------------------------------------------------------------------------------------

    // MLE; O(ta)
    private static int lcsDpBu(int[] target, int[] arr) {
        int[][] dp = new int[arr.length + 1][target.length + 1];

        for (int arrIdx = 1; arrIdx <= arr.length; arrIdx++)
            for (int targetIdx = 1; targetIdx <= target.length; targetIdx++) {
                if (target[targetIdx - 1] == arr[arrIdx - 1]) dp[arrIdx][targetIdx] = dp[arrIdx - 1][targetIdx - 1] + 1;
                else dp[arrIdx][targetIdx] = Math.max(dp[arrIdx - 1][targetIdx], dp[arrIdx][targetIdx - 1]);
            }

        return target.length - dp[arr.length][target.length];
    }

    // --------------------------------------------------------------------------------------

    // Memory efficient but still TLE; O(ta)
    private static int lcsDpBuMe(int[] target, int[] arr) {
        int[][] dp = new int[arr.length + 1][2];

        int itrTarget = 0;
        while (itrTarget++ + 1 <= target.length) {
            for (int arrIdx = 1; arrIdx <= arr.length; arrIdx++)
                if (itrTarget % 2 == 0)
                    if (target[itrTarget - 1] == arr[arrIdx - 1]) dp[arrIdx][1] = dp[arrIdx - 1][0] + 1;
                    else dp[arrIdx][1] = Math.max(dp[arrIdx - 1][1], dp[arrIdx][0]);
                else
                    if (target[itrTarget - 1] == arr[arrIdx - 1]) dp[arrIdx][0] = dp[arrIdx - 1][1] + 1;
                    else dp[arrIdx][0] = Math.max(dp[arrIdx - 1][0], dp[arrIdx][1]);
        }

        return target.length - dp[arr.length][target.length % 2 == 0 ? 1 : 0];
    }

    // --------------------------------------------------------------------------------------

    // MLE for very large input; O(ta)
    private static int lis(int[] target, int[] arr) {
        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int targetIdx = 0; targetIdx < target.length; targetIdx++) targetMap.put(target[targetIdx], targetIdx);
        List<Integer> rn = new ArrayList<>();
        for (int an : arr) if (targetMap.containsKey(an)) rn.add(targetMap.get(an));
        return target.length - lis(rn.stream().mapToInt(Integer::intValue).toArray());
    }

    private static int lis(int[] nums) {
        int[] itrNums = new int[nums.length + 1];
        itrNums[0] = Integer.MIN_VALUE;
        System.arraycopy(nums, 0, itrNums, 1, nums.length);

        int[][] dp = new int[nums.length + 1][nums.length + 1];
        for (int[] d : dp) Arrays.fill(d, Integer.MIN_VALUE);
        return lis(itrNums, 1, 0, dp);
    }

    private static int lis(int[] nums, int idx, int prevIdx, int[][] dp) {
        if (idx > nums.length - 1 || prevIdx > nums.length - 1) return 0;
        if (dp[idx][prevIdx] != Integer.MIN_VALUE) return dp[idx][prevIdx];
        if (nums[prevIdx] < nums[idx]) return dp[idx][prevIdx] = Math.max(1 + lis(nums, idx + 1, idx, dp), lis(nums, idx + 1, prevIdx, dp));
        return dp[idx][prevIdx] = lis(nums, idx + 1, prevIdx, dp);
    }

    // --------------------------------------------------------------------------------------

    // O(alog(a))
    private static int lisBs(int[] target, int[] arr) {
        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int targetIdx = 0; targetIdx < target.length; targetIdx++) targetMap.put(target[targetIdx], targetIdx);
        List<Integer> arrList = new ArrayList<>();
        for (int an : arr) if (targetMap.containsKey(an)) arrList.add(targetMap.get(an));
        int[] nums = arrList.stream().mapToInt(Integer::intValue).toArray();
        int size = 0, tail[] = new int[nums.length];
        for (int num : nums) {
            int idx = Arrays.binarySearch(tail, 0, size, num);
            if (-1 * idx - 1 == size) size++;
            if (idx < 0) tail[-1 * idx - 1] = num;
        }
        return target.length - size;
    }

    public static void main(String[] args) {
        System.out.println(
                lcs(
                        new int[]{5, 1, 3},
                        new int[]{9, 4, 2, 3, 4}
                ) == 2
        );

        System.out.println(
                lcs(
                        new int[]{6, 4, 8, 1, 3, 2},
                        new int[]{4, 7, 6, 2, 3, 8, 6, 1}
                ) == 3
        );

        System.out.println(
                lcsDpBu(
                        new int[]{5, 1, 3},
                        new int[]{9, 4, 2, 3, 4}
                ) == 2
        );

        System.out.println(
                lcsDpBu(
                        new int[]{6, 4, 8, 1, 3, 2},
                        new int[]{4, 7, 6, 2, 3, 8, 6, 1}
                ) == 3
        );

        System.out.println(
                lcsDpBuMe(
                        new int[]{5, 1, 3},
                        new int[]{9, 4, 2, 3, 4}
                ) == 2
        );

        System.out.println(
                lcsDpBuMe(
                        new int[]{6, 4, 8, 1, 3, 2},
                        new int[]{4, 7, 6, 2, 3, 8, 6, 1}
                ) == 3
        );

        System.out.println(
                lis(
                        new int[]{5, 1, 3},
                        new int[]{9, 4, 2, 3, 4}
                ) == 2
        );

        System.out.println(
                lis(
                        new int[]{6, 4, 8, 1, 3, 2},
                        new int[]{4, 7, 6, 2, 3, 8, 6, 1}
                ) == 3
        );

        System.out.println(
                lisBs(
                        new int[]{5, 1, 3},
                        new int[]{9, 4, 2, 3, 4}
                ) == 2
        );

        System.out.println(
                lisBs(
                        new int[]{6, 4, 8, 1, 3, 2},
                        new int[]{4, 7, 6, 2, 3, 8, 6, 1}
                ) == 3
        );
    }
}
