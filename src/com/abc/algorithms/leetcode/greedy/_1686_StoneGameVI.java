package com.abc.algorithms.leetcode.greedy;

import java.util.Arrays;

public class _1686_StoneGameVI {
    private static int stoneGame(int[] av, int[] bv) {
        int[][] sum = new int[av.length][3];

        for (int idx = 0; idx < av.length; idx++) sum[idx] = new int[]{av[idx] + bv[idx], av[idx], bv[idx]};

        Arrays.sort(sum, (a, b) -> Integer.compare(b[0], a[0]));

        int a = 0, b = 0;
        for (int idx = 0; idx < sum.length; idx++) if (idx % 2 == 0) a += sum[idx][1]; else b += sum[idx][2];

        return Integer.compare(a, b);
    }

    public static void main(String[] args) {
        System.out.println(
                stoneGame(
                        new int[]{1, 3},
                        new int[]{2, 1}
                ) == 1
        );

        System.out.println(
                stoneGame(
                        new int[]{1, 2},
                        new int[]{3, 1}
                ) == 0
        );

        System.out.println(
                stoneGame(
                        new int[]{2, 4, 3},
                        new int[]{1, 6, 7}
                ) == -1
        );
    }
}
