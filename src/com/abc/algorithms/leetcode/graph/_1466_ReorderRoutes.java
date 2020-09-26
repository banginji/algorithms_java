package com.abc.algorithms.leetcode.graph;

import java.util.HashSet;
import java.util.Set;

public class _1466_ReorderRoutes {
    private static int reorderRoutes(int n, int[][] connections) {
        int res = 0;
        Set<Integer> s = new HashSet<>();
        int fromCity, destinationCity;
        s.add(0);

        while (s.size() != n) {
            for (int[] connection : connections) {
                fromCity = connection[0];
                destinationCity = connection[1];

                if (s.contains(fromCity) && !s.contains(destinationCity)) {
                    s.add(destinationCity);
                    res += 1;
                } else if (s.contains(destinationCity))
                    s.add(fromCity);
            }
        }

        return res;
    }

    private static void union(int cityOne, int cityTwo, int[] cities) {
        int neighborCityOne = find(cityOne, cities);
        int neighborCityTwo = find(cityTwo, cities);

        if (neighborCityOne != neighborCityTwo)
            cities[neighborCityOne] = neighborCityTwo;
    }

    private static int find(int city, int[] cities) {
        return cities[city] == city ? city : find(cities[city], cities);
    }

    public static void main(String[] args) {
        System.out.println(
                reorderRoutes(
                        6,
                        new int[][]{
                                new int[]{1, 3},
                                new int[]{2, 4},
                                new int[]{0, 1},
                                new int[]{2, 5},
                                new int[]{5, 0}
                        }
                )
        );
    }
}
