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

    // TODO Check how to correct the logic
    private static int maxValueWithinWeight(int[] values, int[] weights, int weightUpperLimit) {
        int[] result = new int[weightUpperLimit + 1];

        for (int weight = 1; weight <= weightUpperLimit; weight++) {
            for (int weightIdx = 0; weightIdx < weights.length; weightIdx++) {
                if (weight < weights[weightIdx]) {
                    result[weight] = result[weight - 1];
                } else {
                    result[weight] = Math.max(
                            result[weight],
                            result[weight - weights[weightIdx]] + values[weightIdx]
                    );
                }
            }
        }

        return result[weightUpperLimit];
    }

    // TODO check how to correct the logic
    private static int alt(int[] values, int[] weights, int capacity) {
        int[] result = new int[capacity + 1];

        for (int weightIdx = 0; weightIdx < weights.length; weightIdx++) {
            for (int weight = weights[weightIdx]; weight <= capacity; weight++) {
                result[weight] = Math.max(
                        result[weightIdx],
                        result[weight - weights[weightIdx]] + values[weightIdx]
                );
            }
        }

        return result[capacity];
    }

    public static void main(String[] args) {
        // 309
        System.out.println(
                knapSackSlow(
                        new int[]{92, 57, 49, 68, 60, 43, 67, 84, 87, 72},
                        new int[]{23, 31, 29, 44, 53, 38, 63, 85, 89, 82},
                        165
                )
        );
    }
}
