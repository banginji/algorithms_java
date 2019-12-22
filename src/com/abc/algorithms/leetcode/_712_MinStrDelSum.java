package com.abc.algorithms.leetcode;

import java.util.Arrays;

public class _712_MinStrDelSum {
    //     a c d e f
    //   0 1 2 3 4 5
    // a 1 0 1 2 3 4
    // b 2 1 2 3 4 5
    // c 3 2 1 2 3 4

    //     s e a
    //   0 1 2 3
    // e 1 2 1 2
    // a 2 3 2 1
    // t 3 4 3 2
    private static int minSum(String s1, String s2) {
        char[] s1CharArray = s1.toCharArray();
        char[] s2CharArray = s2.toCharArray();

        int s1Length = s1.length() + 1;
        int s2Length = s2.length() + 1;

        int[][] resultMatrix = new int[s1Length][s2Length];

        for (int s1Idx = 1; s1Idx < s1Length; s1Idx++)
            resultMatrix[s1Idx][0] = resultMatrix[s1Idx - 1][0] + s1CharArray[s1Idx - 1];
        for (int s2Idx = 1; s2Idx < s2Length; s2Idx++)
            resultMatrix[0][s2Idx] = resultMatrix[0][s2Idx - 1] + s2CharArray[s2Idx - 1];

        System.out.println(Arrays.deepToString(resultMatrix));

        for (int s1Idx = 1; s1Idx < s1Length; s1Idx++) {
            for (int s2Idx = 1; s2Idx < s2Length; s2Idx++) {
                if (s1CharArray[s1Idx - 1] == s2CharArray[s2Idx - 1]) {
                    resultMatrix[s1Idx][s2Idx] = resultMatrix[s1Idx - 1][s2Idx - 1];
                } else {
                    resultMatrix[s1Idx][s2Idx] = Math.min(
                            resultMatrix[s1Idx - 1][s2Idx] + s1CharArray[s1Idx - 1],
                            resultMatrix[s1Idx][s2Idx - 1] + s2CharArray[s2Idx - 1]
                    );
                }
            }
        }

        return resultMatrix[s1Length - 1][s2Length - 1];
    }

    public static void main(String[] args) {
        System.out.println(minSum("delete", "leet"));
    }
}
