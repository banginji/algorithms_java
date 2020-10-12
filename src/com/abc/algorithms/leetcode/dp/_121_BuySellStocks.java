package com.abc.algorithms.leetcode.dp;

public class _121_BuySellStocks {
    private static int maxProfit(int[] prices) {
        int maxProfit = 0;

        for (int iIdx = 0; iIdx < prices.length; iIdx++) {
            for (int jIdx = iIdx; jIdx < prices.length; jIdx++) {
                int currentProfit = prices[jIdx] - prices[iIdx];
                if (currentProfit > 0)
                    maxProfit = Math.max(currentProfit, maxProfit);
            }
        }

        return maxProfit;
    }

    private static int maxProfitDp(int[] prices) {
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;

        for (int price: prices) {
            if (price < minPrice)
                minPrice = price;
            else
                maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(
                maxProfit(
                        new int[]{7, 1, 5, 3, 6, 4}
                ) == 5
        );

        System.out.println(
                maxProfit(
                        new int[]{7, 6, 4, 3, 1}
                ) == 0
        );

        System.out.println(
                maxProfitDp(
                        new int[]{7, 1, 5, 3, 6, 4}
                ) == 5
        );

        System.out.println(
                maxProfitDp(
                        new int[]{7, 6, 4, 3, 1}
                ) == 0
        );
    }
}
