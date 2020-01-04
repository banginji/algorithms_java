package com.abc.algorithms.misc;

public class KnapSack {
    private static int knapSackSlow(int[] values, int[] weights, int capacity) {
        return knapSackSlow(values, weights, capacity, 0);
    }

    private static int knapSackSlow(int[] values, int[] weights, int capacity, int idx) {
        if (idx >= weights.length || capacity <= 0)
            return 0;

        if (weights[idx] > capacity)
            return knapSackSlow(values, weights, capacity, idx + 1);

        return Math.max(
                knapSackSlow(values, weights, capacity, idx + 1),
                values[idx] + knapSackSlow(values, weights, capacity - weights[idx], idx + 1)
        );
    }

    private static int knapSackFast(int[] values, int[] weights, int capacity) {
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

    public static void main(String[] args) {
        // 309
        System.out.println(
                knapSackFast(
                        new int[]{92, 57, 49, 68, 60, 43, 67, 84, 87, 72},
                        new int[]{23, 31, 29, 44, 53, 38, 63, 85, 89, 82},
                        165
                )
        );

        // 51
        System.out.println(
                knapSackFast(
                        new int[]{24, 13, 23, 15, 16},
                        new int[]{12, 7, 11, 8, 9},
                        26
                )
        );
    }
}
