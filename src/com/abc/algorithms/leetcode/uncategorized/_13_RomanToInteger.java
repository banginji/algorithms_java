package com.abc.algorithms.leetcode.uncategorized;

import java.util.Map;

public class _13_RomanToInteger {
    private static int romanToInteger(String s) {
        Map<Character, Integer> romanNumerals = Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1000
        );

        char[] chars = s.toCharArray();

        int result = 0;

        for (int idx = 0; idx < chars.length - 1; idx++) {
            int currentIdxValue = romanNumerals.get(chars[idx]);
            int nextIdxValue = romanNumerals.get(chars[idx + 1]);

            if (currentIdxValue < nextIdxValue)
                result -= currentIdxValue;
            else
                result += currentIdxValue;
        }

        result += romanNumerals.get(chars[chars.length - 1]);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInteger("MCMXCIV"));
    }
}
