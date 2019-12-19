package com.abc.algorithms.leetcode;

import java.util.Arrays;

public class _344_ReverseString {
    private static Character[] reverseString(Character[] chars) {
        int lengthOfChars = chars.length;

        for (int idx = 0; idx < lengthOfChars / 2; idx++) {
            Character temp = chars[idx];
            chars[idx] = chars[lengthOfChars-1-idx];
            chars[lengthOfChars-1-idx] = temp;
        }

        return chars;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        reverseString(
                                new Character[]{'a', 'b', 'c'}
                                )
                )
        );
    }
}
