package com.abc.algorithms.leetcode.linesweep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _1272_RemoveInterval {
    private static List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();

        for (int[] interval : intervals) {
            if (interval[0] < toBeRemoved[0])
                res.add(
                        List.of(
                                interval[0],
                                Math.min(
                                        toBeRemoved[0],
                                        interval[1]
                                )
                        )
                );
            if (toBeRemoved[1] < interval[1])
                res.add(
                        List.of(
                                Math.max(
                                        interval[0],
                                        toBeRemoved[1]
                                ),
                                interval[1]
                        )
                );
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(
                removeInterval(
                        new int[][]{
                                new int[]{0, 2},
                                new int[]{3, 4},
                                new int[]{5, 7}
                        },
                        new int[]{1, 6}
                ).equals(
                        List.of(
                                List.of(0, 1),
                                List.of(6, 7)
                        )
                )
        );

        System.out.println(
                removeInterval(
                        new int[][]{
                                new int[]{0, 5}
                        },
                        new int[]{2, 3}
                ).equals(
                        List.of(
                                List.of(0, 2),
                                List.of(3, 5)
                        )
                )
        );

        System.out.println(
                removeInterval(
                        new int[][]{
                                new int[]{-5, -4},
                                new int[]{-3, -2},
                                new int[]{1, 2},
                                new int[]{3, 5},
                                new int[]{8, 9}
                        },
                        new int[]{-1, 4}
                ).equals(
                        List.of(
                                List.of(-5, -4),
                                List.of(-3, -2),
                                List.of(4, 5),
                                List.of(8, 9)
                        )
                )
        );
    }
}
