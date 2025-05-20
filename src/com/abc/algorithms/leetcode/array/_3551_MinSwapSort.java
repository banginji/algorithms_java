package com.abc.algorithms.leetcode.array;

import java.util.*;

public class _3551_MinSwapSort {
    public int minSwaps(int[] nums) {
        // arrive at finding the two arrays that needs to be equated and then perform cycle detection
        int[][] sorted = new int[nums.length][1];
        for (int idx = 0; idx < nums.length; idx++) sorted[idx] = new int[]{nums[idx], idx};
        Arrays.sort(sorted, (a, b) -> {
            int bSum = digSum(b[0]), aSum = digSum(a[0]);
            if (bSum == aSum) return a[0] - b[0];
            return aSum - bSum;
        });

        int[] target = new int[sorted.length];
        for (int idx = 0; idx < sorted.length; idx++) target[idx] = sorted[idx][1];

        boolean[] visited = new boolean[sorted.length];

        int swaps = 0;

        for (int idx = 0; idx < target.length; idx++) {
            if (visited[idx]) continue;
            int itrIdx = idx, cycle = 0;
            while (!visited[itrIdx]) {
                cycle++;
                visited[itrIdx] = true;
                itrIdx = target[itrIdx];
            }
            swaps += (cycle - 1);
        }

        return swaps;
    }

    private int digSum(int num) {
        if (num / 10 == 0) return num % 10;
        return (num % 10) + digSum(num / 10);
    }

    public static void main(String[] args) {
        System.out.println(
                new _3551_MinSwapSort().minSwaps(new int[]{37, 100}) == 1
        );
        System.out.println(
                new _3551_MinSwapSort().minSwaps(new int[]{22, 14, 33, 7}) == 0
        );
        System.out.println(
                new _3551_MinSwapSort().minSwaps(new int[]{18, 43, 34, 16}) == 2
        );
        System.out.println(
                new _3551_MinSwapSort().minSwaps(new int[]{268835996, 65052660, 415128775}) == 2
        );
    }
}
