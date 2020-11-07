package com.abc.algorithms.leetcode.greedy;

import java.util.Arrays;

public class _1094_CarPooling {
    private static boolean carPooling(int[][] trips, int capacity) {
        int maxDestination = Integer.MIN_VALUE;
        for (int[] trip : trips) maxDestination = Math.max(maxDestination, trip[2]);

        int[] locations = new int[maxDestination];

        for (int[] trip : trips) {
            int startLoc = trip[1];
            int endLoc = trip[2];

            for (int location = startLoc; location < endLoc; location++)
                locations[location - 1] += trip[0];
        }

        return Arrays.stream(locations)
                .mapToObj (location -> location <= capacity)
                .reduce(true, (isInCapOne, isInCapTwo) -> isInCapOne && isInCapTwo);
    }

    public static void main(String[] args) {
        System.out.println(
                !carPooling(
                        new int[][]{
                                new int[]{2, 1, 5},
                                new int[]{3, 3, 7}
                        },
                        4
                )
        );

        System.out.println(
                carPooling(
                        new int[][]{
                                new int[]{2, 1, 5},
                                new int[]{3, 3, 7}
                        },
                        5
                )
        );

        System.out.println(
                carPooling(
                        new int[][]{
                                new int[]{2, 1, 5},
                                new int[]{3, 5, 7}
                        },
                        3
                )
        );

        System.out.println(
                carPooling(
                        new int[][]{
                                new int[]{3, 2, 7},
                                new int[]{3, 7, 9},
                                new int[]{8, 3, 9}
                        },
                        11
                )
        );
    }
}
