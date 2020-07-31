package com.abc.algorithms.leetcode.string;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _1436_DestinationCity {
    private static String destinationCity(List<List<String>> paths) {
        Map<String, String> cities = new HashMap<>();

        for (List<String> path : paths) {
            cities.putIfAbsent(path.get(0), path.get(0));
            cities.putIfAbsent(path.get(1), path.get(1));

            union(path.get(0), path.get(1), cities);
        }

        return cities.entrySet().stream()
                .filter(entrySet -> entrySet.getKey().equals(entrySet.getValue()))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse("");
    }

    private static void union(String cityOne, String cityTwo, Map<String, String> cities) {
        String destOne = find(cityOne, cities);
        String destTwo = find(cityTwo, cities);

        if (!destOne.equals(destTwo))
            cities.put(destOne, destTwo);
    }

    private static String find(String city, Map<String, String> cities) {
        return cities.get(city).equals(city) ? city : find(cities.get(city), cities);
    }

    public static void main(String[] args) {
        System.out.println(
                destinationCity(
                        List.of(
                                List.of("London", "New York"),
                                List.of("Paris", "Rome"),
                                List.of("New York", "Paris")
                        )
                )
        );
    }
}
