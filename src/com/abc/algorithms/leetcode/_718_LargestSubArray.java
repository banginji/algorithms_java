package com.abc.algorithms.leetcode;

public class _718_LargestSubArray {
    private static int largestSubArraySlow(int[] A, int[] B) {
        int[] longerArray = A.length >= B.length ? A : B;
        int[] shorterArray = A.length < B.length ? A : B;

        return largestSubArraySlow(longerArray, 0, shorterArray, 0, 0);
    }

    private static int largestSubArraySlow(int[] longer, int longerIdx, int[] shorter, int shorterIdx, int count) {
        if (longerIdx == longer.length || shorterIdx == shorter.length)
            return count;
        if (longer[longerIdx] == shorter[shorterIdx])
            return largestSubArraySlow(longer, longerIdx + 1, shorter, shorterIdx + 1, count + 1);
        return Math.max(
                largestSubArraySlow(longer, longerIdx + 1, shorter, shorterIdx, 0),
                largestSubArraySlow(longer, longerIdx, shorter, shorterIdx + 1, 0)
        );
    }

    private static int largestSubArray(int[] A, int[] B) {
        int result = 0;
        int[][] resultMatrix = new int[A.length + 1][B.length + 1];

        for (int aIdx = 1; aIdx <= A.length; aIdx++) {
            for (int bIdx = 1; bIdx <= B.length; bIdx++) {
                if (A[aIdx - 1] == B[bIdx - 1])
                    resultMatrix[aIdx][bIdx] = resultMatrix[aIdx - 1][bIdx - 1] + 1;
                result = Math.max(result, resultMatrix[aIdx][bIdx]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(largestSubArraySlow(new int[]{0, 1, 0, 1, 1}, new int[]{1, 0, 1, 0, 1}));
    }
}