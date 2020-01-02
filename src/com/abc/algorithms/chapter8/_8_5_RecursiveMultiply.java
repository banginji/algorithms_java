package com.abc.algorithms.chapter8;

public class _8_5_RecursiveMultiply {
    private static int multiplySlow(int num1, int num2) {
        int larger = Math.max(num1, num2);
        int smaller = Math.min(num1, num2);

        return multiplySlow(larger, smaller, 0);
    }

    private static int multiplySlow(int num1, int num2, int memo) {
        if (num2 == 1)
            return memo + num1;
        return multiplySlow(num1, num2 - 1, memo + num1);
    }

    /**
     * The idea is for calculating by doubling the previous calculation
     *
     * 2 -> 4 -> 8 -> 16 -> ...
     *
     * So for 71 * 12 this would be
     *      12          6               3           acc         2               1
     *    ( 71 )    ( 71 + 71 )     ( 142 + 142 )   (284)   ( 142 + 142 )   ( 284 + 284 )
     *
     * Result is 568 (idx 0) + 284 (acc) = 852
     */
    private static int multiplyFast(int num1, int num2) {
        // 1. Find the smaller number
        int larger = Math.max(num1, num2);
        int smaller = Math.min(num1, num2);

        // 2. Fix the larger number at the end of the memo
        int[] memo = new int[smaller + 1];
        memo[smaller] = larger;

        // 7. The result is at the 1st index
        return multiplyFast(memo, smaller, 0)[1];
    }

    private static int[] multiplyFast(int[] memo, int num2, int acc) {
        // 6. The terminating condition is when 1 is reached.
        // The result is doubled and the accumulator is added to the result
        if (num2 == 1) {
            memo[num2] = memo[num2 << 1] + memo[num2 << 1] + acc;
            return memo;
        }

        // 3. This check is for stopping this calculation for the first iteration
        // since the larger number has already been fixed at the end of memo
        if (num2 << 1 < memo.length)
            // 4. The previous calculation is doubled and saved
            memo[num2] = memo[num2 << 1] + memo[num2 << 1];

        // 5. If the index is odd then the result is added to the accumulator
        // The result is copied over to the previous even number i.e current odd - 1
        if (num2 % 2 != 0) {
            memo[num2 - 1] = memo[num2];
            acc += memo[num2];
            num2--;
        }

        return multiplyFast(memo, num2 >> 1, acc);
    }

    public static void main(String[] args) {
        System.out.println(multiplySlow(12, 20));
        System.out.println(multiplyFast(12, 71));
        System.out.println(12 * 71);
    }
}
