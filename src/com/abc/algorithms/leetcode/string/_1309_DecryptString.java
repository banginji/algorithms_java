package com.abc.algorithms.leetcode.string;

import java.util.Stack;

public class _1309_DecryptString {
    private static String freqAlphabets(String s) {
        int idx = s.length() - 1;

        char[] ca = s.toCharArray();

        Stack<Character> stack = new Stack<>();

        while (idx >= 0) {
            if (ca[idx] == '#') {
                StringBuilder sb = new StringBuilder();
                int num = Integer.parseInt(sb.append(ca[idx - 2]).append(ca[idx - 1]).toString());
                stack.push(numToChar(num));
                idx -= 3;
                continue;
            }

            StringBuilder sb = new StringBuilder();
            int num = Integer.parseInt(sb.append(ca[idx]).toString());
            stack.push(numToChar(num));
            idx--;
        }

        StringBuilder sb = new StringBuilder();
        while (stack.size() > 0)
            sb.append(stack.pop());

        return sb.toString();
    }

    private static char numToChar(int num) {
        return (char) (num + 'a' - 1);
    }

    public static void main(String[] args) {
        System.out.println(freqAlphabets("10#11#12"));
    }
}
