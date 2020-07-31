package com.abc.algorithms.leetcode.string;

import java.util.*;

public class _859_BuddyStrings {
    private static boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false;

        if (A.equals(B)) {
            char[] ac = A.toCharArray();

            Set<Character> set = new HashSet<>();

            for (char c: ac)
                set.add(c);

            return set.size() != A.length();
        }

        List<List<Character>> diffList = new ArrayList<>();
        int countThreshold = 2;

        for (int idx = 0; idx < A.length(); idx++) {
            if (A.charAt(idx) != B.charAt(idx)) {
                diffList.add(List.of(A.charAt(idx), B.charAt(idx)));
                countThreshold--;
            }

            if (countThreshold < 0) return false;
        }

        if (diffList.size() != 2) return false;

        List<Character> firstDiff = diffList.get(0);
        List<Character> secondDiff = diffList.get(1);

        return firstDiff.get(0) == secondDiff.get(1) && firstDiff.get(1) == secondDiff.get(0);
    }

    public static void main(String[] args) {
        System.out.println(buddyStrings("aa", "aa"));
    }
}
