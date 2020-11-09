package com.abc.algorithms.leetcode.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _253_MeetingRoomsII {
    private static int meetingRooms(int[][] intervals) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        for (int[] interval : intervals) {
            if (pq.size() > 0 && interval[0] >= pq.peek()) pq.poll();
            pq.offer(interval[1]);
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

        System.out.println(
                meetingRooms(
                        new int[][]{
                                new int[]{0, 30},
                                new int[]{45, 55},
                                new int[]{35, 40}
                        }
                ) == 1
        );

        System.out.println(
                meetingRooms(
                        new int[][]{
                                new int[]{0, 30},
                                new int[]{45, 55},
                                new int[]{25, 40}
                        }
                ) == 2
        );

        System.out.println(
                meetingRooms(
                        new int[][]{
                                new int[]{6, 15},
                                new int[]{13, 20},
                                new int[]{6, 17}
                        }
                ) == 3
        );

        System.out.println(
                meetingRooms(
                        new int[][]{
                                new int[]{13, 15},
                                new int[]{1, 13}
                        }
                ) == 1
        );
    }
}
