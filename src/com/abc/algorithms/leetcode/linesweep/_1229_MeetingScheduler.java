package com.abc.algorithms.leetcode.linesweep;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class _1229_MeetingScheduler {
    private static List<Integer> meetingScheduler(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(slots2, Comparator.comparingInt(a -> a[0]));

        int iIdx = 0, jIdx = 0;

        while (iIdx < slots1.length && jIdx < slots2.length) {
            int intersectStart = Math.max(slots1[iIdx][0], slots2[jIdx][0]);
            int intersectEnd = Math.min(slots1[iIdx][1], slots2[jIdx][1]);

            if (intersectStart + duration <= intersectEnd)
                return List.of(intersectStart, intersectStart + duration);
            else if (slots1[iIdx][0] < slots2[jIdx][0])
                iIdx++;
            else
                jIdx++;
        }

        return Collections.emptyList();
    }

    public static void main(String[] args) {
        System.out.println(
                meetingScheduler(
                        new int[][]{
                                new int[]{10, 50},
                                new int[]{60, 120},
                                new int[]{140, 210}
                        },
                        new int[][]{
                                new int[]{0, 15},
                                new int[]{60, 70}
                        },
                        8
                )
        );

        System.out.println(
                meetingScheduler(
                        new int[][]{
                                new int[]{10, 50},
                                new int[]{60, 120},
                                new int[]{140, 210}
                        },
                        new int[][]{
                                new int[]{0, 15},
                                new int[]{60, 70}
                        },
                        12
                )
        );
    }
}
