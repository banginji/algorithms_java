package com.abc.algorithms.leetcode.string;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class _3556_LargestPrimesSum {
    public long sumOfLargestPrimes(String s) {
        PriorityQueue<Long> primes = new PriorityQueue<>((a, b) -> Long.compare(b, a));
        Set<Long> seen = new HashSet<>();

        char[] digits = s.toCharArray();

        for (int outerIdx = digits.length - 1; outerIdx >= 0; outerIdx--) {
            long position = 1, itrDigit = 0;
            for (int innerIdx = outerIdx; innerIdx >= 0; innerIdx--) {
                itrDigit += (digits[innerIdx] - '0') * position;
                position *= 10;
                if (seen.contains(itrDigit)) continue;
                seen.add(itrDigit);
                if (isPrime(itrDigit)) primes.offer(itrDigit);
            }
        }

        long result = 0;
        int count = 0;
        while (!primes.isEmpty()) {
            if (count == 3) break;
            result += primes.poll();
            count++;
        }

        return result;
    }

    private boolean isPrime(long num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        for (int divisor = 2; divisor <= Math.sqrt(num); divisor++)
            if (num % divisor == 0) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(
                new _3556_LargestPrimesSum().sumOfLargestPrimes("12234")
        );

        System.out.println(
                new _3556_LargestPrimesSum().sumOfLargestPrimes("111")
        );

        System.out.println(
                new _3556_LargestPrimesSum().sumOfLargestPrimes("6357501617")
        );
    }
}
