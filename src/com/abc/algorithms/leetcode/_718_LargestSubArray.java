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
        int resultA = Math.max(
                count,
                largestSubArraySlow(longer, longerIdx + 1, shorter, shorterIdx, 0)
        );
        int resultB = Math.max(
                count,
                largestSubArraySlow(longer, longerIdx, shorter, shorterIdx + 1, 0)
        );

        return Math.max(resultA, resultB);
    }

    private static int largestSubArrayFast(int[] A, int[] B) {
        int[] longer = A.length >= B.length ? A : B;
        int[] shorter = A.length < B.length ? A : B;

        Integer[][] memo = new Integer[longer.length][shorter.length];

        return largestSubArrayFast(longer, 0, shorter, 0, memo);
    }

    private static int largestSubArrayFast(int[] longer, int longerIdx, int[] shorter, int shorterIdx, Integer[][] memo) {
        if (longerIdx == longer.length || shorterIdx == shorter.length)
            return 0;

        if (memo[longerIdx][shorterIdx] != null)
            return memo[longerIdx][shorterIdx];

        if (longer[longerIdx] == shorter[shorterIdx]) {
            memo[longerIdx][shorterIdx] = 1 + largestSubArrayFast(longer, longerIdx + 1, shorter, shorterIdx + 1, memo);
            return memo[longerIdx][shorterIdx];
        }

        return Math.max(
                largestSubArrayFast(longer, longerIdx + 1, shorter, shorterIdx, memo),
                largestSubArrayFast(longer, longerIdx, shorter, shorterIdx + 1, memo)
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
        System.out.println(
                largestSubArrayFast(
                        new int[]{0, 1, 1, 1, 1},
                        new int[]{1, 0, 1, 0, 1}
                )
        );
    }
}
