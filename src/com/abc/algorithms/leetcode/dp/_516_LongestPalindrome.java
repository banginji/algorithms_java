package com.abc.algorithms.leetcode.dp;

import com.abc.algorithms.leetcode.TimeIt;

public class _516_LongestPalindrome {
    private static int lpTdDp(String s) {
        int[][] dp = new int[s.length() + 1][s.length() + 1];

        char[] reversedCharArray = new char[s.length()];

        int idx = s.length() - 1;
        for (char c : s.toCharArray()) reversedCharArray[idx--] = c;

        return lpTdDp(s.toCharArray(), 0, reversedCharArray, 0, dp);
    }

    private static int lpTdDp(char[] charArrOne, int idxOne, char[] charArrTwo, int idxTwo, int[][] dp) {
        if (idxOne >= charArrOne.length || idxTwo >= charArrTwo.length) return 0;

        if (dp[idxOne][idxTwo] != 0) return dp[idxOne][idxTwo];

        if (charArrOne[idxOne] == charArrTwo[idxTwo])
            return dp[idxOne][idxTwo] = 1 + lpTdDp(charArrOne, idxOne + 1, charArrTwo, idxTwo + 1, dp);

        return dp[idxOne][idxTwo] = Math.max(
                lpTdDp(charArrOne, idxOne + 1, charArrTwo, idxTwo, dp),
                lpTdDp(charArrOne, idxOne, charArrTwo, idxTwo + 1, dp)
        );
    }

    private static int lpBuDp(String s) {
        int[][] dp = new int[s.length() + 1][s.length() + 1];

        char[] ca = s.toCharArray();

        char[] rca = new char[s.length()];
        int idx = s.length() - 1;
        for (char c : s.toCharArray()) rca[idx--] = c;

        for (int iIdx = 1; iIdx <= s.length(); iIdx++)
            for (int jIdx = 1; jIdx <= s.length(); jIdx++)
                if (ca[iIdx - 1] == rca[jIdx - 1])
                    dp[iIdx][jIdx] = 1 + dp[iIdx - 1][jIdx - 1];
                else
                    dp[iIdx][jIdx] = Math.max(dp[iIdx - 1][jIdx], dp[iIdx][jIdx - 1]);

        return dp[s.length()][s.length()];
    }

    public static void main(String[] args) {
        System.out.println(
                lpTdDp("bbbab") == 4
        );

        System.out.println(
                lpTdDp("cbbd") == 2
        );

        System.out.println(
                lpBuDp("bbbab") == 4
        );

        System.out.println(
                lpBuDp("cbbd") == 2
        );

        // lpTdDp times out for the following input
        System.out.println(
                lpBuDp("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg") == 494
        );

        /**
         * Time taken: 0.014248513
         * Time taken: 0.006560066
         * Time taken: 0.006648132
         * Time taken: 0.006525429
         * Time taken: 0.005052511
         * Time taken: 0.001925971
         * Time taken: 0.001787999
         * Time taken: 0.001888014
         * Time taken: 0.00187175
         *
         * ~ 0.005 sec
         */
        int itrOne = 0;
        while (itrOne++ < 9)
            TimeIt.timeTaken(() ->
                    lpBuDp("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg")
            );
    }
}
