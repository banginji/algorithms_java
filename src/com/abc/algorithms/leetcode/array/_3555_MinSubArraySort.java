package com.abc.algorithms.leetcode.array;

import java.util.Arrays;

public class _3555_MinSubArraySort {
    public int[] minSubarraySort(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int resultIdx = 0;
        for (int idx = 0; idx < nums.length - k + 1; idx++) {
            int[] itrArray = Arrays.copyOfRange(nums, idx, idx + k);
            int[] sorted = Arrays.copyOf(itrArray, k);
            Arrays.sort(sorted);
            int leftIdx = 0, rightIdx = k - 1;
            while (leftIdx < k && itrArray[leftIdx] == sorted[leftIdx]) leftIdx++;
            while (rightIdx >= 0 && itrArray[rightIdx] == sorted[rightIdx]) rightIdx--;
            result[resultIdx++] = leftIdx > rightIdx ? 0 : rightIdx - leftIdx + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        new _3555_MinSubArraySort()
                                .minSubarraySort(
                                        new int[]{1, 3, 2, 4, 5},
                                        3
                                )
                )
        );

        System.out.println(
                Arrays.toString(
                        new _3555_MinSubArraySort()
                                .minSubarraySort(
                                        new int[]{5, 4, 3, 2, 1},
                                        4
                                )
                )
        );

        System.out.println(
                Arrays.toString(
                        new _3555_MinSubArraySort()
                                .minSubarraySort(
                                        new int[]{15,6,7},
                                        3
                                )
                )
        );
    }
}
