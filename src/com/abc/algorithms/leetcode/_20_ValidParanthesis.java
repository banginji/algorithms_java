package com.abc.algorithms.leetcode;

import java.util.Map;
import java.util.Stack;

public class _20_ValidParanthesis {
    private static boolean isValid(String s) {
        Map<Character, Character> pairs = Map.of(
                '(', ')',
                '{', '}',
                '[', ']'
        );

        Stack<Character> stack = new Stack<>();

        for (Character c: s.toCharArray()) {
            if (!stack.empty() && pairs.get(stack.peek()) != c) {
                stack.push(c);
                continue;
            }
            if (!stack.empty() && pairs.get(stack.peek()) == c) {
                stack.pop();
                continue;
            }
            stack.push(c);
        }

        return stack.empty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("{([])}"));
    }
}
