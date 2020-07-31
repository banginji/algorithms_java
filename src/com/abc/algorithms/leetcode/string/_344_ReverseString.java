package com.abc.algorithms.leetcode.string;

import java.util.Arrays;
import java.util.Random;

public class _344_ReverseString {
    private static String reverseString(char[] s) {
        int length = s.length;

        int secondPointer = length - 1;

        for (int idx = 0; idx < length / 2; idx++) {
            char temp = s[idx];
            s[idx] =  s[secondPointer];
            s[secondPointer--] = temp;
        }

        new Random();

        return Arrays.toString(s);
    }

    public static void main(String[] args) {
        System.out.println(reverseString(new char[]{'a', 'b', 'c', 'd'}));
    }
}
