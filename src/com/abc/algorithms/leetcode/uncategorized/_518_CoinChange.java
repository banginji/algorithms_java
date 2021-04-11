package com.abc.algorithms.leetcode.uncategorized;

public class _518_CoinChange {
    private static int coinChange(int amount, int[] coins) {
        int[] result = new int[amount + 1];
        result[0] = 1;

        for (int coinType : coins)
            for (int amt = coinType; amt <= amount; amt++)
                result[amt] += result[amt - coinType];

        return result[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(5, new int[]{1, 2, 5}));
    }
}
