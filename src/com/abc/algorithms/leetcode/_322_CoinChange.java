package com.abc.algorithms.leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

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

    private static int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (IntStream.of(coins).noneMatch(coin -> amount >= coin)) return -1;

        int result = Integer.MAX_VALUE;

        for (int coin : coins)
            if (amount >= coin) {
                int dpRes = coinChange(coins, amount - coin);
                if (dpRes == -1) continue;
                result = Math.min(result, dpRes + 1);
            }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int coinChangeDp(int[] coins, int amount) {
        int[][] dp = new int[amount + 1][coins.length];
        for (int[] d : dp) Arrays.fill(d, Integer.MAX_VALUE);

        return coinChangeDp(coins, amount, dp);
    }

    private static int coinChangeDp(int[] coins, int amount, int[][] dp) {
        if (amount == 0) return 0;

        if (IntStream.of(coins).noneMatch(coin -> amount >= coin)) return -1;

        int result = Integer.MAX_VALUE;

        for (int coinIdx = 0; coinIdx < coins.length; coinIdx++)
            if (amount >= coins[coinIdx]) {
                int dpRes;
                if (dp[amount][coinIdx] != Integer.MAX_VALUE)
                    dpRes = dp[amount][coinIdx];
                else
                    dpRes = dp[amount][coinIdx] = coinChangeDp(coins, amount - coins[coinIdx], dp);
                if (dpRes == -1) continue;
                result = Math.min(result, dpRes + 1);
            }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11) == 3);
        System.out.println(coinChange(new int[]{2}, 3) == -1);
        System.out.println(coinChange(new int[]{1}, 0) == 0);
        System.out.println(coinChange(new int[]{1}, 1) == 1);
        System.out.println(coinChange(new int[]{1}, 2) == 2);
//        This will timeout
//        System.out.println(coinChange(new int[]{186, 419, 83, 408}, 6249) == 20);

        System.out.println(coinChangeDp(new int[]{1, 2, 5}, 11) == 3);
        System.out.println(coinChangeDp(new int[]{2}, 3) == -1);
        System.out.println(coinChangeDp(new int[]{1}, 0) == 0);
        System.out.println(coinChangeDp(new int[]{1}, 1) == 1);
        System.out.println(coinChangeDp(new int[]{1}, 2) == 2);
        System.out.println(coinChangeDp(new int[]{186, 419, 83, 408}, 6249) == 20);
    }
}
