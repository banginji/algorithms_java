package com.abc.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _056_MergeIntervals {
    private static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> res = new ArrayList<>();
        int itrLow = intervals[0][0], itrHigh = intervals[0][1];
        for (int[] interval : intervals) {
            int low = interval[0], high = interval[1];
            if (low > itrHigh) {
                res.add(new int[]{itrLow, itrHigh});
                itrLow = low;
                itrHigh = high;
            } else {
                itrLow = Math.min(itrLow, low);
                itrHigh = Math.max(itrHigh, high);
            }
        }
        res.add(new int[]{itrLow, itrHigh});
        return res.toArray(new int[res.size()][2]);
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.deepEquals(
                        merge(
                                new int[][]{
                                        new int[]{1, 3},
                                        new int[]{2, 6},
                                        new int[]{8, 10},
                                        new int[]{15, 18}
                                }
                        ), new int[][]{
                                new int[]{1, 6},
                                new int[]{8, 10},
                                new int[]{15, 18}
                        }
                )
        );

        System.out.println(
                Arrays.deepEquals(
                        merge(
                                new int[][]{
                                        new int[]{1, 4},
                                        new int[]{4, 5}
                                }
                        ),
                        new int[][]{
                                new int[]{1, 5}
                        }
                )
        );

        System.out.println(
                Arrays.deepEquals(
                        merge(
                                new int[][]{
                                        new int[]{1, 2},
                                        new int[]{5, 12},
                                        new int[]{0, 14},
                                        new int[]{3, 4},
                                        new int[]{7, 17}
                                }
                        ),
                        new int[][]{
                                new int[]{0, 17}
                        }
                )
        );
    }
}
