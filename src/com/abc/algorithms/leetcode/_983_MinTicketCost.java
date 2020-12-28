package com.abc.algorithms.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class _983_MinTicketCost {
    // https://leetcode.com/problems/minimum-cost-for-tickets/discuss/886443/Evolution-%3A-Recursion-greater-Memo-on-Recursion-greater-DP-greater-6-line-DP.-Detailed-explanation
    private static int minTicketCost(int[] days, int[] costs) {
        //min(dp(i-1)+cost[0]+...)
        int relevantDayLimit = days[days.length - 1];
        int[] dayCosts = new int[relevantDayLimit + 1];
        int day = 1;

        while (day < relevantDayLimit + 1) {
            final int itrDay = day;
            if (IntStream.of(days).anyMatch(num -> num == itrDay))
                dayCosts[day] = Math.min(
                        dayCosts[Math.max(0, day - 1)] + costs[0],
                        Math.min(
                                dayCosts[Math.max(0, day - 7)] + costs[1],
                                dayCosts[Math.max(0, day - 30)] + costs[2]
                        )
                );
            else
                dayCosts[day] = dayCosts[day - 1];
            day++;
        }
        return dayCosts[relevantDayLimit];
    }

    private static int minCost(int[] days, int[] costs) {
        Map<Integer, Boolean> daysMap = new HashMap<>();
        for (int day : days) daysMap.put(day, true);
        return minCost(0, daysMap, days, costs);
    }

    private static int minCost(int currentDay, Map<Integer, Boolean> daysMap, int[] days, int[] costs) {
        if (currentDay > days[days.length - 1]) return 0;

        if (daysMap.getOrDefault(currentDay, false))
            return Math.min(
                    minCost(currentDay + 1, daysMap, days, costs) + costs[0],
                    Math.min(
                            minCost(currentDay + 7, daysMap, days, costs) + costs[1],
                            minCost(currentDay + 30, daysMap, days, costs) + costs[2]
                    )
            );
        else
            return minCost(currentDay + 1, daysMap, days, costs);
    }

    private static int minCostTdDp(int[] days, int[] costs) {
        Map<Integer, Boolean> daysMap = new HashMap<>();
        for (int day : days) daysMap.put(day, true);

        int[] dp = new int[days[days.length - 1] + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);

        return minCostTdDp(0, days, costs, daysMap, dp);
    }

    private static int minCostTdDp(int currentDay, int[] days, int[] costs, Map<Integer, Boolean> daysMap, int[] dp) {
        if (currentDay > days[days.length - 1]) return 0;
        if (dp[currentDay] != Integer.MIN_VALUE) return dp[currentDay];

        if (daysMap.getOrDefault(currentDay, false))
            return dp[currentDay] = Math.min(
                    minCostTdDp(currentDay + 1, days, costs, daysMap, dp) + costs[0],
                    Math.min(
                            minCostTdDp(currentDay + 7, days, costs, daysMap, dp) + costs[1],
                            minCostTdDp(currentDay + 30, days, costs, daysMap, dp) + costs[2]
                    )
            );
        else
            return dp[currentDay] = minCostTdDp(currentDay + 1, days, costs, daysMap, dp);
    }

    public static void main(String[] args) {
        System.out.println(
                minTicketCost(
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31},
                        new int[]{2, 7, 15}
                ) == 17
        );

        System.out.println(
                minCost(
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31},
                        new int[]{2, 7, 15}
                ) == 17
        );

        System.out.println(
                minCost(
                        new int[]{1, 4, 6, 7, 8, 20},
                        new int[]{2, 7, 15}
                ) == 11
        );

        System.out.println(
                minCostTdDp(
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31},
                        new int[]{2, 7, 15}
                ) == 17
        );

        System.out.println(
                minCostTdDp(
                        new int[]{1, 4, 6, 7, 8, 20},
                        new int[]{2, 7, 15}
                ) == 11
        );
    }
}
