package com.abc.algorithms.leetcode.dp;

import java.util.Arrays;

public class _583_NumDeleteOpsTwoStrings {
    private static int numOfOps(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];

        for (int[] d : dp) Arrays.fill(d, Integer.MIN_VALUE);

        int lenOne = word1.length(), lenTwo = word2.length();

        return lenOne + lenTwo - 2 * lcs(word1, 0, word2, 0, dp);
    }

    private static int lcs(String wordOne, int idxOne, String wordTwo, int idxTwo, int[][] dp) {
        if (idxOne >= wordOne.length() || idxTwo >= wordTwo.length()) return 0;

        if (dp[idxOne][idxTwo] != Integer.MIN_VALUE) return dp[idxOne][idxTwo];

        if (wordOne.charAt(idxOne) == wordTwo.charAt(idxTwo))
            return dp[idxOne][idxTwo] = 1 + lcs(wordOne, idxOne + 1, wordTwo, idxTwo + 1, dp);

        return dp[idxOne][idxTwo] = Math.max(
                lcs(wordOne, idxOne + 1, wordTwo, idxTwo, dp),
                lcs(wordOne, idxOne, wordTwo, idxTwo + 1, dp)
        );
    }

    public static void main(String[] args) {
        System.out.println(
                numOfOps("sea", "eat") == 2
        );

        System.out.println(
                numOfOps("", "a") == 1
        );

        System.out.println(
                numOfOps("", "") == 0
        );

        System.out.println(
                numOfOps("dinitrophenylhydrazine", "acetylphenylhydrazine") == 11
        );
    }
}
