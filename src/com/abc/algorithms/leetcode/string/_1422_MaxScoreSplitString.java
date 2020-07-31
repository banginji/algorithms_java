package com.abc.algorithms.leetcode.string;

public class _1422_MaxScoreSplitString {
    public static int maxScore(String s) {
        char[] c = s.toCharArray();

        int result = Integer.MIN_VALUE;

        int rightOne = onesCount(c, 0, c.length);

        int leftZero = 0;

        for (int idx = 0; idx < c.length - 1; idx++) {
            if (c[idx] == '0') leftZero++;
            if (c[idx] == '1') rightOne--;
            result = Math.max(leftZero + rightOne, result);
        }

        return result;
    }

    private static int onesCount(char[] c, int startIdx, int endIdx) {
        int numberOfOnes = 0;

        for (int idx = startIdx; idx < endIdx; idx++) {
            if (c[idx] == '1') numberOfOnes++;
        }

        return numberOfOnes;
    }

    public static void main(String[] args) {
        System.out.println(maxScore("00"));
    }
}
