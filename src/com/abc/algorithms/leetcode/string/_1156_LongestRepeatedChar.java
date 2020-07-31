package com.abc.algorithms.leetcode.string;

public class _1156_LongestRepeatedChar {
    private static int longestRepeatedString(String text) {
        char[] ca = text.toCharArray();

        int longest = longest(ca);

        for (int outerIdx = 0; outerIdx < ca.length; outerIdx++) {
            for (int innerIdx = outerIdx + 1; innerIdx < ca.length; innerIdx++) {
                if (ca[outerIdx] != ca[innerIdx]) {
                    swap(ca, outerIdx, innerIdx);
                    longest = Math.max(longest, longest(ca));
                    swap(ca, outerIdx, innerIdx);
                }
            }
        }

        return longest;
    }

    private static void swap(char[] arr, int idx1, int idx2) {
        char buffer = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = buffer;
    }

    private static int longest(char[] ca) {
        int longest = 0, itrLongest = 0;

        for (int idx = 1; idx < ca.length; idx++) {
            if (ca[idx] == ca[idx - 1])
                itrLongest++;
            else {
                longest = Math.max(longest, ++itrLongest);
                itrLongest = 0;
            }
        }

        return Math.max(longest, ++itrLongest);
    }

    public static void main(String[] args) {
        System.out.println(longest("aaaaa".toCharArray()));
    }
}
