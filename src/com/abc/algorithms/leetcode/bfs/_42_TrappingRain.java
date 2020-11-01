package com.abc.algorithms.leetcode.bfs;

public class _42_TrappingRain {
    private static int trappingRain(int[] height) {
        int amt = 0;

        for (int h = 1; h < height.length - 1; h++)
            amt += calcAmt(h, height);

        return amt;
    }

    private static int calcAmt(int h, int[] height) {
        int amt = 0;
        if (height[h] < height[h - 1])
            if (height[h] < height[h + 1])
                amt += Math.min(height[h + 1], height[h - 1]) - height[h];
            else {
                amt += height[h - 1] - height[h];
                height[h] = height[h - 1];
            }

        return amt;
    }

    public static void main(String[] args) {
        System.out.println(
                trappingRain(
                        new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}
                )
        );

        System.out.println(
                trappingRain(
                        new int[]{4, 2, 0, 3, 2, 5}
                )
        );
    }
}
