package com.abc.algorithms.leetcode.string;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class _1249_BalanceParentheses {
    private static String balanceParentheses(String s) {
        Stack<Object[]> stack = new Stack<>();

        for (int idx = 0; idx < s.toCharArray().length; idx++) {
            if (s.charAt(idx) == '(') {
                stack.push(new Object[]{'(', idx});
                continue;
            }

            if (s.charAt(idx) == ')') {
                if (!stack.empty()) {
                    Object[] lastObject = stack.peek();
                    if ((char) lastObject[0] == '(') {
                        stack.pop();
                        continue;
                    }
                }
                stack.push(new Object[]{')', idx});
            }
        }

        Set<Integer> indices = new HashSet<>();

        while (stack.size() > 0)
            indices.add((int)stack.pop()[1]);

        StringBuilder sb = new StringBuilder();

        for (int idx = 0; idx < s.length(); idx++) {
            if (indices.contains(idx)) continue;

            sb.append(s.charAt(idx));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(balanceParentheses("lee(t(c)o)de)"));
    }
}
