package com.abc.algorithms.misc;

import java.util.HashMap;
import java.util.Map;

public class KnapSack {
    private static int knapSack(int[] values, int[] weights, int capacity) {
        return knapSack(0, values, weights, capacity);
    }

    private static int knapSack(int itemIdx, int[] values, int[] weights, int capacity) {
        if (itemIdx >= values.length) return 0;
        if (capacity < weights[itemIdx]) return knapSack(itemIdx + 1, values, weights, capacity);
        return Math.max(
                values[itemIdx] + knapSack(itemIdx + 1, values, weights, capacity - weights[itemIdx]),
                knapSack(itemIdx + 1, values, weights, capacity)
        );
    }

    private static int knapSackDpBu(int[] values, int[] weights, int capacity) {
        int[] result = new int[capacity + 1];

        for (int weightIdx = 0; weightIdx < weights.length; weightIdx++) {
            for (int weight = capacity; weight >= 1; weight--) {
                if (weight < weights[weightIdx])
                    continue;
                result[weight] = Math.max(
                        result[weight],
                        values[weightIdx] + result[weight - weights[weightIdx]]
                );
            }
        }

        return result[capacity];
    }

    private static record DpKey(int value, int weight, int capacity) {}

    private static int knapSackDpTd(int[] values, int[] weights, int capacity) {
        Map<DpKey, Integer> dp = new HashMap<>();
        return knapSackDpTd(0, values, weights, capacity, dp);
    }

    private static int knapSackDpTd(int itemIdx, int[] values, int[] weights, int capacity, Map<DpKey, Integer> dp) {
        if (itemIdx >= values.length) return 0;
        if (dp.containsKey(new DpKey(values[itemIdx], weights[itemIdx], capacity)))
            return dp.get(new DpKey(values[itemIdx], weights[itemIdx], capacity));
        if (capacity < weights[itemIdx]) {
            dp.put(
                    new DpKey(values[itemIdx], weights[itemIdx], capacity),
                    knapSackDpTd(itemIdx + 1, values, weights, capacity, dp)
            );
            return dp.get(new DpKey(values[itemIdx], weights[itemIdx], capacity));
        }

        dp.put(
                new DpKey(values[itemIdx], weights[itemIdx], capacity),
                Math.max(
                        values[itemIdx] + knapSackDpTd(itemIdx + 1, values, weights, capacity - weights[itemIdx], dp),
                        knapSackDpTd(itemIdx + 1, values, weights, capacity, dp)
                )
        );
        return dp.get(new DpKey(values[itemIdx], weights[itemIdx], capacity));
    }

    public static void main(String[] args) {
        // 309
        System.out.println(
                knapSack(
                        new int[]{92, 57, 49, 68, 60, 43, 67, 84, 87, 72},
                        new int[]{23, 31, 29, 44, 53, 38, 63, 85, 89, 82},
                        165
                ) == 309
        );

        // 51
        System.out.println(
                knapSack(
                        new int[]{24, 13, 23, 15, 16},
                        new int[]{12, 7, 11, 8, 9},
                        26
                ) == 51
        );

        // 309
        System.out.println(
                knapSackDpBu(
                        new int[]{92, 57, 49, 68, 60, 43, 67, 84, 87, 72},
                        new int[]{23, 31, 29, 44, 53, 38, 63, 85, 89, 82},
                        165
                ) == 309
        );

        // 51
        System.out.println(
                knapSackDpBu(
                        new int[]{24, 13, 23, 15, 16},
                        new int[]{12, 7, 11, 8, 9},
                        26
                ) == 51
        );

        // 309
        System.out.println(
                knapSackDpTd(
                        new int[]{92, 57, 49, 68, 60, 43, 67, 84, 87, 72},
                        new int[]{23, 31, 29, 44, 53, 38, 63, 85, 89, 82},
                        165
                ) == 309
        );

        // 51
        System.out.println(
                knapSackDpTd(
                        new int[]{24, 13, 23, 15, 16},
                        new int[]{12, 7, 11, 8, 9},
                        26
                ) == 51
        );
    }
}
