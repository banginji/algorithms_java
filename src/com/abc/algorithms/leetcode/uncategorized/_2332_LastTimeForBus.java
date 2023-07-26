package com.abc.algorithms.leetcode.uncategorized;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class _2332_LastTimeForBus {
    private static int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        Stack<Integer> stack = new Stack<>();
        int pIdx = 0, latestMin = passengers[0] - 1;
        for (int bus : buses) {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(bus);
            while (list.getFirst() > passengers[pIdx] && list.size() < capacity + 1) {
                list.add(passengers[pIdx]);
                stack.push(passengers[pIdx++]);
            }
            while (stack.size() > 0) {
                latestMin = stack.peek() - 1;
                stack.pop();
                if (stack.size() == 0) break;
                if (latestMin != stack.peek()) break;
                while (stack.size() > 0 && latestMin-- == stack.peek()) stack.pop();
            }
        }
        return latestMin;
    }

    private static int latestTime(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses); Arrays.sort(passengers);
        int pIdx = 0, latestTime = passengers[0] - 1, itrCapacity = 0;
        for (int bus : buses) {
            Stack<Integer> stack =  new Stack<>();
            while (itrCapacity++ < capacity && pIdx < (passengers.length - 1) && passengers[pIdx] <= bus)
                stack.push(passengers[pIdx++]);
            if (passengers[pIdx] > bus)
                latestTime = bus;
            else {
                while (stack.size() > 0) {
                    latestTime = stack.peek() - 1;
                    stack.pop();
                    if (stack.size() == 0) break;
                    if (latestTime != stack.peek()) break;
                    while (stack.size() > 0 && latestTime-- == stack.peek()) stack.pop();
                }
            }
            itrCapacity = 0;
        }
        return latestTime;
    }

    public static void main(String[] args) {
        System.out.println(
                latestTime(
                        new int[]{10, 20},
                        new int[]{2, 17, 18, 19},
                        2
                )
        );

        System.out.println(
                latestTime(
                        new int[]{20, 30, 10},
                        new int[]{19, 13, 26, 4, 25, 11, 21},
                        2
                )
        );

        System.out.println(
                latestTime(
                        new int[]{10, 20, 56, 87, 96, 312},
                        new int[]{2, 17, 18, 19, 31, 124, 743, 12},
                        2
                )
        );
    }
}
