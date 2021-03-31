package com.abc.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class _1615_MaximalNwRank {
    private record Road(int cityOne, int cityTwo) {
    }

    private static Integer maxRank(int n, int[][] roads) {
        List<Road> possibleRoads = new ArrayList<>();
        for (int cityOne = 0; cityOne < n; cityOne++)
            for (int cityTwo = cityOne + 1; cityTwo < n; cityTwo++)
                possibleRoads.add(new Road(cityOne, cityTwo));

        Map<Road, Integer> roadCountMap = new HashMap<>();
        AtomicInteger maxRank = new AtomicInteger(Integer.MIN_VALUE);
        for (int[] road : roads)
            possibleRoads.stream()
                    .filter(itrRoad -> itrRoad.cityOne() == road[0] || itrRoad.cityOne() == road[1] || itrRoad.cityTwo() == road[0] || itrRoad.cityTwo() == road[1])
                    .collect(Collectors.toList())
                    .forEach(itrRoad -> {
                        roadCountMap.putIfAbsent(itrRoad, 0);
                        roadCountMap.computeIfPresent(itrRoad, (k, v) -> v + 1);
                        maxRank.set(Math.max(maxRank.get(), roadCountMap.get(itrRoad)));
                    });

        return maxRank.get();
    }

    public static void main(String[] args) {
        System.out.println(
                maxRank(
                        4,
                        new int[][]{
                                new int[]{0, 1},
                                new int[]{0, 3},
                                new int[]{1, 2},
                                new int[]{1, 3}
                        }
                ) == 4
        );

        System.out.println(
                maxRank(
                        5,
                        new int[][]{
                                new int[]{0, 1},
                                new int[]{0, 3},
                                new int[]{1, 2},
                                new int[]{1, 3},
                                new int[]{2, 3},
                                new int[]{2, 4}
                        }
                ) == 5
        );

        System.out.println(
                maxRank(
                        8,
                        new int[][]{
                                new int[]{0, 1},
                                new int[]{1, 2},
                                new int[]{2, 3},
                                new int[]{2, 4},
                                new int[]{5, 6},
                                new int[]{5, 7}
                        }
                ) == 5
        );
    }
}
