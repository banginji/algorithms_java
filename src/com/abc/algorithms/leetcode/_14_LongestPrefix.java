package com.abc.algorithms.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class _14_LongestPrefix {
    private static String longestPrefix(String[] strs) {
        Optional<String> shortest = Arrays
                .stream(strs)
                .min(Comparator.comparing(String::length));

        int count = 0;
        for (int idx = 0; idx < shortest.get().length(); idx++) {
            if (allCharsAtIdxMatch(strs, idx))
                count++;
            else
                break;
        }

        return shortest.get().substring(0, count);
    }

    private static boolean allCharsAtIdxMatch(String[] strs, int idx) {
        return Arrays.stream(strs)
                .map(str -> str.charAt(idx))
                .allMatch(character -> character == strs[0].charAt(idx));
    }

    public static void main(String[] args) {
        System.out.println(longestPrefix(new String[]{"flower", "flow", "fliwht"}));
    }
}
