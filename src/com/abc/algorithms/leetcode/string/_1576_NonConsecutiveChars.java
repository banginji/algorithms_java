package com.abc.algorithms.leetcode.string;

import java.util.*;

public class _1576_NonConsecutiveChars {
    private static String modifyString(String s) {
        char[] charArray = s.toCharArray();

        for (int idx = 0; idx < charArray.length; idx++)
            if (charArray[idx] == '?')
                replaceChar(charArray, idx);

        return new String(charArray);
    }

    private static void replaceChar(char[] charArray, int idx) {
        List<Character> chars = List.of('a', 'b', 'c');

        if (idx - 1 >= 0 && idx + 1 < charArray.length)
            if (charArray[idx - 1] != '?' && charArray[idx + 1] != '?') {
                for (Character aChar : chars)
                    if (charArray[idx - 1] != aChar && charArray[idx + 1] != aChar) {
                        charArray[idx] = aChar;
                        return;
                    }
            } else if (charArray[idx - 1] != '?') {
                for (Character aChar : chars)
                    if (charArray[idx - 1] != aChar) {
                        charArray[idx] = aChar;
                        return;
                    }
            } else if (charArray[idx + 1] != '?') {
                for (Character aChar : chars)
                    if (charArray[idx - 1] != aChar) {
                        charArray[idx] = aChar;
                        return;
                    }
            }

        if (idx - 1 >= 0)
            if (charArray[idx - 1] != '?') {
                for (Character aChar : chars)
                    if (charArray[idx - 1] != aChar)
                        charArray[idx] = aChar;
            } else {
                charArray[idx] = 'a';
            }

        if (idx + 1 < charArray.length)
            if (charArray[idx + 1] != '?') {
                for (Character aChar : chars)
                    if (charArray[idx + 1] != aChar)
                        charArray[idx] = aChar;
            } else {
                charArray[idx] = 'a';
            }
    }

    public static void main(String[] args) {
        System.out.println(modifyString("??yw?ipkj?"));
    }
}
