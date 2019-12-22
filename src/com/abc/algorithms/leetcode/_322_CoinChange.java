package com.abc.algorithms.leetcode;

import java.util.Arrays;

public class _322_CoinChange {

    /**
     * Start from the largest coin and work backwards
     * Does not work for when coins pertaining to the answer are not continuous
     * Ex: {1, 2, 5}, 6
     */
    private static int coinChange(int[] coins, int amount, int count) {
        if (coins.length == 0 || amount == 0) {
            if (amount != 0)
                return -1;
            return count;
        }

        if (amount < coins[coins.length - 1])
            return coinChange(Arrays.copyOf(coins, coins.length - 1), amount, count);

        int currentCoin = coins[coins.length - 1];

        while (amount >= currentCoin) {
            amount -= currentCoin;
            count++;
        }

        return coinChange(Arrays.copyOf(coins, coins.length - 1), amount, count);
    }

    private static int coinChanges(int[] coins, int amount) {
        int[] amounts = new int[amount + 1];

        for (int amt = 1; amt <= amount; amt++) {
            amounts[amt] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (amt - coin < 0)
                    continue;

                int prev = amounts[amt - coin];
                amounts[amt] = prev == Integer.MAX_VALUE ? amounts[amt] : Math.min(prev + 1, amounts[amt]);
            }

            // For insight
            for (int idx = 0; idx < amounts.length; idx++) {
                if (amounts[idx] < Integer.MAX_VALUE - 100)
                    System.out.print("(" + idx + "): " + amounts[idx] + ", ");
            }
            System.out.println();
        }
        return amounts[amount] == Integer.MAX_VALUE ? -1 : amounts[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChanges(new int[]{1, 2, 5}, 6));
    }
}
