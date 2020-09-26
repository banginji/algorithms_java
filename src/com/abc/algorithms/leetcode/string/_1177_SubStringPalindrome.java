package com.abc.algorithms.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _1177_SubStringPalindrome {
    // TODO: Bit Manipulation check later
    private static List<Boolean> subStringPalindromes(String s, int[][] queries) {
        List<Boolean> result = new ArrayList<>();

        for (int[] query : queries) result.add(isPalindrome(s, query));

        return result;
    }

    private static boolean isPalindrome(String word, int[] query) {
        int startIdx = query[0], endIdx = query[1], allowedInfractions = query[2];

        if (startIdx == endIdx) return true;

        String targetWord = word.substring(startIdx, endIdx + 1);

        Map<Character, Integer> charMap = new HashMap<>();

        for (Character character : targetWord.toCharArray()) {
            charMap.putIfAbsent(character, 0);
            charMap.computeIfPresent(character, (key, value) -> value + 1);
        }

        int numberOfInfractions = 0;
        for (Map.Entry<Character, Integer> entry : charMap.entrySet())
            if (entry.getValue() % 2 != 0) numberOfInfractions++;

        return numberOfInfractions / 2 <= allowedInfractions;
    }

    public static void main(String[] args) {
        System.out.println(
                subStringPalindromes(
                        "abcda",
                        new int[][]{
                                new int[]{3, 3, 0},
                                new int[]{1, 2, 0},
                                new int[]{0, 3, 1},
                                new int[]{0, 3, 2},
                                new int[]{0, 4, 1}
                        }
                )
        );
    }
}
