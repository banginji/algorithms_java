package com.abc.algorithms.leetcode;

public class _326_PowerOfThree {
    private static int nearestCubeRoot(int num, int start, int end) {
        if (Math.abs(start-end) <= 1)
            return start;

        int midPoint = Math.abs(start + end) / 2;
        if (Math.pow(midPoint, 3) >= num)
            return nearestCubeRoot(num, start, midPoint);
        else
            return nearestCubeRoot(num, midPoint, end);
    }

    private static boolean isPowerOfThree(int num) {
        if (num < 1)
            return false;

        while (num % 3 == 0)
            num /= 3;

        return num == 1;
    }

    public static void main(String[] args) {
        System.out.println(nearestCubeRoot(750, 0, 26));
        System.out.println(isPowerOfThree(64));
    }
}
