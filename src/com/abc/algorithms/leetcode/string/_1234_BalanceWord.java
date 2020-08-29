package com.abc.algorithms.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class _1234_BalanceWord {
    private static int balanceWord(String s) {
        Map<Character, Integer> charMap = new HashMap<>();

        int allowedLength = s.length() / 4;

        for (Character character : s.toCharArray()) {
            charMap.putIfAbsent(character, 0);
            charMap.computeIfPresent(character, (key, value) -> value + 1);
        }

        return charMap.values().stream()
                .filter(value -> value > allowedLength)
                .map(value -> value - allowedLength)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static int bw(String s) {
        Map<Character, Integer> charMap;

        int count = 0;

        for (int idx = 0; idx < s.length(); idx += 4) {
            charMap = new HashMap<>();

            for (int innerIdx = idx; innerIdx < idx + 4; innerIdx++) {
                charMap.putIfAbsent(s.charAt(innerIdx), 0);
                charMap.computeIfPresent(s.charAt(innerIdx), (key, value) -> value + 1);
            }

            count += charMap.values().stream()
                    .filter(value -> value > 1)
                    .map(value -> value - 1)
                    .mapToInt(Integer::intValue)
                    .sum();
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(balanceWord("WWQQRRRRQRQQ"));
    }
}
