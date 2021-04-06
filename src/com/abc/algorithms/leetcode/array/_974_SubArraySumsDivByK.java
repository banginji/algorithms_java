package com.abc.algorithms.leetcode.array;

public class _974_SubArraySumsDivByK {
    // KEY POINT: Number of sub arrays by combination is the sum of the prev freq of repeating modulus
    private static int subArrayCount(int[] A, int K) {
        // Possible mods (remainders) are 0 to K - 1. Hence indices serve as the mods while values as the freqs
        int[] modFreq = new int[K];
        modFreq[0] = 1;
        int sum = 0, count = 0;
        for (int a : A) {
            sum = (sum + a) % K;
            if (sum < 0) sum += K;
            count += modFreq[sum];
            modFreq[sum]++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                subArrayCount(
                        new int[]{4, 5, 0, -2, -3, 1},
                        5
                ) == 7
        );
    }
}
