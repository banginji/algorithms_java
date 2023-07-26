package com.abc.algorithms.leetcode.array;

import java.util.Stack;

public class _2375_DISmallestNum {
    private static String smallestNum(String pattern) {
        char[] ca = pattern.toCharArray();
        int[] res = new int[ca.length + 1];
        int resIdx = 0, num = 1;
        Stack<Integer> stack = new Stack<>();
        for (char c : ca) {
            if (c == 'D') {
                stack.push(num++);
                continue;
            }
            if (c == 'I') {
                res[resIdx++] = num++;
                while (stack.size() > 0) res[resIdx++] = stack.pop();
            }
        }
        stack.push(num);
        while (stack.size() > 0) res[resIdx++] = stack.pop();
        StringBuilder sb = new StringBuilder();
        for (int r : res) sb.append(r);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(
                smallestNum(
                        "IIIDIDDD"
                )
        );

        System.out.println(
                smallestNum(
                        "DDD"
                )
        );
    }

    /**
     * Explanation
     * Reversely iterate the array A.
     * x is the current upper bound.
     * Initiliza x with a big enough value.
     *
     * For element A[i],
     * If A[i] % x == 0,
     * then we can divide A[i] all into x with count k = A[i] / x.
     * with k - 1 operations,
     * the upper bound is still x
     * (// means integer division)
     *
     * If A[i] % x > 0,
     * then we can divide A[i] all into x with count k = A[i] / x + 1.
     * with k - 1 operations,
     * the upper bound is A[i] / k.
     * (// means integer division)
     *
     *
     * Complexity
     * Time O(n)
     * Space O(1)
     *
     *
     * Java
     *
     *     public long minimumReplacement(int[] A) {
     *         long x = 1000000000, res = 0, k;
     *         for (int i = A.length - 1; i >= 0; --i) {
     *             k = (A[i] + x - 1) / x;
     *             x = A[i] / k;
     *             res += k - 1;
     *         }
     *         return res;
     *     }
     *
     *     1,7,3,14,10
     *     1,2,2,3,3,4,10,10
     */
}
