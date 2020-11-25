package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _787_CheapestFlights {
    private static int cheapestFlights(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> neighborMap = new HashMap<>();

        for (int city = 0; city < n; city++)
            neighborMap.put(city, new ArrayList<>());

        for (int[] flight : flights)
            neighborMap.computeIfAbsent(flight[0], x -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(cityAndCost -> cityAndCost[1]));
        pq.offer(new int[]{src, 0, -1});

        while (pq.size() > 0) {
            int[] currentCityCostAndHops = pq.poll();

            int currentCity = currentCityCostAndHops[0];
            int costToTravelToCurrentCity = currentCityCostAndHops[1];
            int cityHops = currentCityCostAndHops[2];

            if (currentCity == dst) return costToTravelToCurrentCity;

            List<int[]> currentCityNeighborsAndCosts = neighborMap.get(currentCity);

            if (currentCityNeighborsAndCosts == null) continue;

            int dstCityHops = ++cityHops;

            if (dstCityHops > k) continue;

            for (int[] neighborCityAndCost : currentCityNeighborsAndCosts)
                pq.offer(new int[]{neighborCityAndCost[0], costToTravelToCurrentCity + neighborCityAndCost[1], dstCityHops});
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(
                cheapestFlights(
                        4,
                        new int[][]{
                                new int[]{0, 1, 100},
                                new int[]{1, 2, 100},
                                new int[]{0, 2, 500}
                        },
                        0,
                        2,
                        1
                ) == 200
        );

        System.out.println(
                cheapestFlights(
                        4,
                        new int[][]{
                                new int[]{0, 1, 1},
                                new int[]{0, 2, 5},
                                new int[]{1, 2, 1},
                                new int[]{2, 3, 1}
                        },
                        0,
                        3,
                        1
                ) == 6
        );
    }
}
