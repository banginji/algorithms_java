package com.abc.algorithms.leetcode.linesweep;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _253_MeetingRoomsII {
    private static int meetingRooms(int[][] intervals) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        pq.offer(intervals[0][1]);

        for (int idx = 1; idx < intervals.length; idx++) {
            if (pq.size() > 0 && intervals[idx][0] >= pq.peek()) pq.poll();
            pq.offer(intervals[idx][1]);
        }

        return pq.size();
    }

    public static void main(String[] args) {
        System.out.println(
                meetingRooms(
                        new int[][]{
                                new int[]{0, 30},
                                new int[]{5, 10},
                                new int[]{15, 20}
                        }
                ) == 2
        );

        System.out.println(
                meetingRooms(
                        new int[][]{
                                new int[]{7, 10},
                                new int[]{2, 4}
                        }
                ) == 1
        );
    }
}
