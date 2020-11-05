package com.abc.algorithms.leetcode.heap;

import java.util.*;
import java.util.stream.Collectors;

public class _1333_SortRestaurants {
    private static List<Integer> sortRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((resOne, resTwo) -> {
            if (resOne[1] == resTwo[1])
                return resTwo[0] - resOne[0];
            return resTwo[1] - resOne[1];
        });

        for (int[] restaurant : restaurants)
            if (restaurant[2] >= veganFriendly && restaurant[3] <= maxPrice && restaurant[4] <= maxDistance)
                pq.offer(restaurant);

        List<Integer> result = new ArrayList<>();

        while (pq.size() > 0) result.add(pq.poll()[0]);

        return result;
    }

    private static List<Integer> sortRestaurantsByStream(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        return Arrays.stream(restaurants)
                .filter(restaurant -> restaurant[2] >= veganFriendly && restaurant[3] <= maxPrice && restaurant[4] <= maxDistance)
                .sorted((resOne, resTwo) -> {
                    if (resOne[1] == resTwo[1])
                        return resTwo[0] - resOne[0];
                    return resTwo[1] - resOne[1];
                })
                .map(restaurant -> restaurant[0])
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[][] restaurants = new int[][]{
                new int[]{1, 4, 1, 40, 10},
                new int[]{2, 8, 0, 50, 5},
                new int[]{3, 8, 1, 30, 4},
                new int[]{4, 10, 0, 10, 3},
                new int[]{5, 1, 1, 15, 1},
        };

        System.out.println(
                sortRestaurants(
                        restaurants,
                        1,
                        50,
                        10
                ).equals(List.of(3, 1, 5))
        );

        System.out.println(
                sortRestaurants(
                        restaurants,
                        0,
                        50,
                        10
                ).equals(List.of(4, 3, 2, 1, 5))
        );

        System.out.println(
                sortRestaurants(
                        restaurants,
                        0,
                        30,
                        3
                ).equals(List.of(4, 5))
        );

        System.out.println(
                sortRestaurantsByStream(
                        restaurants,
                        1,
                        50,
                        10
                ).equals(List.of(3, 1, 5))
        );

        System.out.println(
                sortRestaurantsByStream(
                        restaurants,
                        0,
                        50,
                        10
                ).equals(List.of(4, 3, 2, 1, 5))
        );

        System.out.println(
                sortRestaurantsByStream(
                        restaurants,
                        0,
                        30,
                        3
                ).equals(List.of(4, 5))
        );
    }
}
