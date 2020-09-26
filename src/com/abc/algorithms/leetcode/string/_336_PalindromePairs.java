package com.abc.algorithms.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class _336_PalindromePairs {
    private static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();

        for (int firstIdx = 0; firstIdx < words.length; firstIdx++)
            for (int secondIdx = 0; secondIdx < words.length; secondIdx++) {
                if (firstIdx == secondIdx) continue;
                StringBuilder sb = new StringBuilder();
                if (isPalindrome(sb.append(words[firstIdx]).append(words[secondIdx]).toString())) {
                    result.add(List.of(firstIdx, secondIdx));
                    break;
                }
            }

        return result;
    }

    private static boolean isPalindrome(String word) {
        int pivotIdx = word.length() - 1;
        for (int idx = 0; idx < word.length(); idx++)
            if (word.charAt(idx) != word.charAt(pivotIdx--)) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"}));
    }
}
