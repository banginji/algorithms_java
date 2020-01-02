package com.abc.algorithms.misc;

public class Fibonacci {
    private static void fibonacci(int n) {
        fibonacci(n - 2, new int[]{0, 1});
    }

    private static int fibonacci(int n, int[] memo) {
        if (n == 0)
            return 0;
        int sum = memo[0] + memo[1];
        System.out.print(sum + ", ");

        memo[0] = memo[1];
        memo[1] = sum;

        return fibonacci(n - 1, memo);
    }

    public static void main(String[] args) {
        System.out.print(0 + ", ");
        System.out.print(1 + ", ");
        fibonacci(20);
    }
}
