package com.abc.algorithms.chapter8;

public class _8_11_Coins {
    private static int coins(double amount) {
        return coins((int) (amount * 100), new int[]{1, 5, 10, 25});
    }

    private static int coins(int amount, int[] coinTypes) {
        int[] result = new int[amount + 1];
        result[0] = 1;

        for (int coinType : coinTypes)
            for (int amt = coinType; amt <= amount; amt++)
                result[amt] += result[amt - coinType];

        return result[amount];
    }

    public static void main(String[] args) {
        System.out.println(coins(12.99));
    }
}
