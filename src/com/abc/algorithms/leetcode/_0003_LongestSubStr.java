package com.abc.algorithms.leetcode;

public class _0003_LongestSubStr {
    private static int longestNonRepeatingSubStr(String s) {
        int length = 0;

        for (int idx = 0; idx < s.length(); idx++) {
            boolean[] vc = new boolean[128];
            int itrLength = 0;
            for (int itrIdx = idx; itrIdx < s.length(); itrIdx++) {
                if (!vc[s.charAt(itrIdx)]) {
                    vc[s.charAt(itrIdx)] = true;
                    itrLength++;
                } else
                    break;
            }

            length = Math.max(length, itrLength);

            if (length > s.length() - idx)
                return length;
        }

        return length;
    }

    public static void main(String[] args) {
        System.out.println(longestNonRepeatingSubStr("abcabcbb"));
    }
}
