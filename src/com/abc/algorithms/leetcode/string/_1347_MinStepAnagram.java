package com.abc.algorithms.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class _1347_MinStepAnagram {
    private static int minSteps(String s, String t) {
        Map<Character, Integer> tMap = new HashMap<>();

        for (Character tc : t.toCharArray()) {
            tMap.computeIfPresent(tc, (tchar, tCount) -> tCount + 1);
            tMap.putIfAbsent(tc, 1);
        }

        for (Character sc : s.toCharArray())
            tMap.computeIfPresent(sc, (schar, tCount) -> tCount - 1);

        return tMap.values().stream()
                .mapToInt(i -> i)
                .filter(num -> num > 0)
                .sum();
    }

    public static void main(String[] args) {
        System.out.println(minSteps("friend", "family"));
    }
}
