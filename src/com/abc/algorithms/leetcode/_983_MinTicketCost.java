package com.abc.algorithms.leetcode;

import java.util.stream.IntStream;

public class _983_MinTicketCost {
    private static int minTicketCost(int[] days, int[] costs) {
        //min(dp(i-1)+cost[0]+...)
        int releventDayLimit = days[days.length - 1];
        int[] dayCosts = new int[releventDayLimit + 1];
        int day = 1;

        while (day < releventDayLimit + 1) {
            final int itrDay = day;
            if (IntStream.of(days).anyMatch(num -> num == itrDay)) {
                dayCosts[day] = Math.min(
                        dayCosts[Math.max(0, day - 1)] + costs[0],
                        dayCosts[Math.max(0, day - 7)] + costs[1]
                );
                dayCosts[day] = Math.min(
                        dayCosts[day],
                        dayCosts[Math.max(0, day - 30)] + costs[2]
                );
            } else {
                dayCosts[day] = dayCosts[day - 1];
            }
            day++;
        }
        return dayCosts[releventDayLimit];
    }

    public static void main(String[] args) {
        System.out.println(
                minTicketCost(
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31},
                        new int[]{2, 7, 15}
                )
        );
    }
}
