package com.abc.algorithms.leetcode.dp;

public class _1682_LongestPalindromeSubSeqII {
    private static int lpDp(String s) {
        Integer[][][] dp = new Integer[s.length()][s.length()][27];
        return lpDp(s, 0, s.length() - 1, 26, dp);
    }

    private static int lpDp(String s, int idxOne, int idxTwo, int prev, Integer[][][] dp) {
        if (idxOne >= idxTwo) return 0;

        if (dp[idxOne][idxTwo][prev] != null) return dp[idxOne][idxTwo][prev];

        if (s.charAt(idxOne) == s.charAt(idxTwo) && s.charAt(idxOne) - 'a' != prev)
            return dp[idxOne][idxTwo][prev] = 2 + lpDp(s, idxOne + 1, idxTwo - 1, s.charAt(idxOne) - 'a', dp);

        return dp[idxOne][idxTwo][prev] = Math.max(
                lpDp(s, idxOne + 1, idxTwo, prev, dp),
                lpDp(s, idxOne, idxTwo - 1, prev, dp)
        );
    }

    public static void main(String[] args) {
        System.out.println(
                lpDp("bbabab") == 4
        );

        System.out.println(
                lpDp("abbccbb") == 4
        );

        System.out.println(
                lpDp("dcbccacdb") == 4
        );

        System.out.println(
                lpDp("whswlvwumkmpfvyetlummgczproabzibzkhez") == 8
        );
    }
}
