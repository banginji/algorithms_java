package com.abc.algorithms.leetcode;

import java.util.HashMap;
import java.util.stream.IntStream;

public class _567_PermString {
    private static boolean permString(String str1, String str2) {
        String longer = str1.length() >= str2.length() ? str1 : str2;
        char[] shorter = str1.length() < str2.length() ? str1.toCharArray() : str2.toCharArray();

        int windowSize = shorter.length;
        int winStartIdx = 0;

        /**
         * Note that adding window size increases the index range by 1
         * Ex: winStartIdx = 0; windowSize = 2; winEndIdx = 0+2 = 2
         * Indices -> 0, 1, 2 where the last index should be ignored
         *
         * This is taken care when the IntStream.range is used instead
         * of IntStream.rangeClosed
         */
        int winEndIdx = winStartIdx + windowSize;
        HashMap<Character, Integer> shorterStringMap = new HashMap<>();

        for (Character character : shorter) {
            if (shorterStringMap.containsKey(character)) {
                shorterStringMap.put(character, shorterStringMap.get(character) + 1);
                continue;
            }
            shorterStringMap.put(character, 1);
        }

        while (winEndIdx <= longer.length() - 1) {
            if (
                    IntStream
                            .range(winStartIdx, winEndIdx)
                            .boxed()
                            .map(longer::charAt)
                            .allMatch(shorterStringMap::containsKey)
            ) {
                return true;
            }
            winStartIdx++;
            winEndIdx++;
        }


        return false;
    }

    public static void main(String[] args) {
        System.out.println(
                permString("ab", "eidbaooo")
        );
    }
}
