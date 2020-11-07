package com.abc.algorithms.leetcode.array;

import java.util.Arrays;

public class _1109_FlightBookings {
    private static int[] flightBookings(int[][] bookings, int n) {
        int[] seats = new int[n];

        for (int[] booking : bookings) {
            int flightOne = Math.min(booking[0], booking[1]);
            int flightTwo = Math.max(booking[0], booking[1]);

            for (int flight = flightOne; flight <= flightTwo; flight++)
                seats[flight - 1] += booking[2];
        }

        return seats;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.equals(flightBookings(
                        new int[][]{
                                new int[]{1, 2, 10},
                                new int[]{2, 3, 20},
                                new int[]{2, 5, 25}
                        },
                        5
                ), new int[]{10, 55, 45, 25, 25})
        );
    }
}
