package com.abc.algorithms.leetcode;

import java.util.Stack;

public class _394_DecodeString {
    private static String decodeString(String str) {
        Stack<Character> stack = new Stack<>();

        char[] chars = str.toCharArray();

        StringBuilder stringBuilder = new StringBuilder();

        for (Character character: chars) {
            if (character != ']')
                stack.push(character);
            else {
                stringBuilder.append(buildString(stack));
                stack.empty();
            }
        }

        return stringBuilder.toString();
    }

    private static String buildString(Stack<Character> stack) {
        StringBuilder stringBuilder = new StringBuilder();
        while (stack.iterator().hasNext()) {
            if (stack.peek() != '[')
                stringBuilder.append(stack.pop());
            else {
                stack.pop();
                break;
            }
        }

        int repeatTimes = Integer.parseInt(String.valueOf(stack.pop()));

        String result = stringBuilder.toString();

        return result.repeat(repeatTimes);
    }

    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]"));
    }
}
