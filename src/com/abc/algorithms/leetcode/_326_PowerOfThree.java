package com.abc.algorithms.leetcode;

public class _326_PowerOfThree {
    private static int nearestCubeRoot(int num, int start, int end) {
        int midPoint = (start + end) / 2;

        if (midPoint - start == 1) {
            if (Math.pow(start, 3) == num)
                return start;
            else
                return midPoint;
        }

        if (Math.pow(midPoint, 3) > num)
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
        System.out.println(nearestCubeRoot(65, 0, 27));
        System.out.println(isPowerOfThree(64));
    }
}
