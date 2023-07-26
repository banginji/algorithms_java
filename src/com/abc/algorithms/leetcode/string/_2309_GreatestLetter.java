package com.abc.algorithms.leetcode.string;

public class _2309_GreatestLetter {
    public static String greatestLetter(String s) {
        boolean[] smallChars = new boolean[26];
        boolean[] largeChars = new boolean[26];
        int greatest = Integer.MIN_VALUE;
        char[] charArray = s.toCharArray();

        for (char c : charArray) {
            int currentAlpha = 0;
            if (c - 'a' >= 0 && c - 'a' < 26) {
                smallChars[c - 'a'] = true;
                currentAlpha = c - 'a';
            } else {
                largeChars[c - 'A'] = true;
                currentAlpha = c - 'A';
            }
            if (smallChars[currentAlpha] && largeChars[currentAlpha])
                greatest = Math.max(greatest, currentAlpha);
        }
        // L_NOTES: Integer between 1 and 26 to convert to a string, use String.valueOf
        return greatest == Integer.MIN_VALUE ? "" : String.valueOf((char) (greatest + 1 + 64));
    }

    public static void main(String[] args) {
        System.out.println(
                greatestLetter("lEeTcOdE").equals("E")
        );
        System.out.println(
                greatestLetter("arRAzFif").equals("R")
        );
        System.out.println(
                greatestLetter("AbCdEfGhIjK").equals("")
        );
        int itr = 0;
        while (itr < 10)
            System.out.println((char)(itr++ + 1 + 64));
    }
}
