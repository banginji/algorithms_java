package com.abc.algorithms.leetcode.string;

public class _1542_LongestSubStrPalindrome {
    // TODO: Not working
    private static int longestAwesome(String s) {
        int length = s.length(), ans = 0, mask = 0;

        int[] memo = new int[1024];
        memo[0] = -1;

        for (int iIdx = 0; iIdx < length; iIdx++) {
            mask ^= 1 << s.charAt(iIdx);
            ans = Math.max(ans, iIdx - memo[mask]);

            for (int jIdx = 0; jIdx < 10; jIdx++) {
                int testMask = mask ^ (1 << jIdx);
                ans = Math.max(ans, iIdx - memo[testMask]);
            }

            memo[mask] = Math.min(memo[mask], 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(longestAwesome("3242415"));
    }
}
