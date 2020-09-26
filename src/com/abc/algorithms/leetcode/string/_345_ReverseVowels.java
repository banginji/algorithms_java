package com.abc.algorithms.leetcode.string;

import java.util.Set;

public class _345_ReverseVowels {
    private static String reverseVowels(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

        int forwardPtr = 0, backwardPtr = s.length() - 1;

        char[] charArray = s.toCharArray();

        while (forwardPtr < backwardPtr) {
            while (!vowels.contains(charArray[forwardPtr]) && forwardPtr < backwardPtr) forwardPtr++;
            while (!vowels.contains(charArray[backwardPtr]) && backwardPtr > forwardPtr) backwardPtr--;
            char tempChar = charArray[forwardPtr];
            charArray[forwardPtr++] = charArray[backwardPtr];
            charArray[backwardPtr--] = tempChar;
        }

        return new String(charArray);
    }

    public static void main(String[] args) {
        System.out.println(reverseVowels("leetcode"));
    }
}
