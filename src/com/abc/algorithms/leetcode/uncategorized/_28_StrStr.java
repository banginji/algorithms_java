package com.abc.algorithms.leetcode.uncategorized;

import com.abc.algorithms.misc.Tuple;

public class _28_StrStr {
    private static int strStr(String haystack, String needle) {
        int hIdx = 0, nIdx = 0;

        while (hIdx < haystack.length()) {
            if (haystack.charAt(hIdx) == needle.charAt(nIdx)) {
                Tuple<Integer, Boolean> allMatch = allMatch(haystack, hIdx, needle);
                if (allMatch.second())
                    return hIdx;
                else
                    hIdx = allMatch.first();
            } else
                hIdx++;
        }
        return -1;
    }

    private static Tuple<Integer, Boolean> allMatch(String s1, int currents1Idx, String s2) {
        int currents2Idx = 0;

        while (currents1Idx < s1.length() && currents2Idx < s2.length()) {
            if (s1.charAt(currents1Idx) != s2.charAt(currents2Idx))
                return new Tuple<>(currents1Idx, false);
            currents1Idx++;
            currents2Idx++;
        }
        return new Tuple<>(currents1Idx, true);
    }

    public static void main(String[] args) {
        System.out.println(strStr("asdfghjklqwertyasdfghqwertyuasdfgh", "qwerty"));
    }
}
