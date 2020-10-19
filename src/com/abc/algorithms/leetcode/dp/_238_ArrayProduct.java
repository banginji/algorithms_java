package com.abc.algorithms.leetcode.dp;

import java.util.Arrays;

public class _238_ArrayProduct {
    private static int[] arrayProduct(int[] nums) {
        int[] prdArray = new int[nums.length];

        long zerosCount = Arrays.stream(nums).filter(num -> num == 0).count();
        if (zerosCount > 1) {
            Arrays.fill(prdArray, 0);
            return prdArray;
        }

        if (zerosCount == 1) {
            int prd = Arrays.stream(nums).filter(num -> num != 0).reduce(1, (x, y) -> x * y);

            for (int idx = 0; idx < nums.length; idx++)
                prdArray[idx] = nums[idx] == 0 ? prd : 0;
        } else {
            int prd = Arrays.stream(nums).reduce(1, (x, y) -> x * y);

            for (int idx = 0; idx < nums.length; idx++)
                prdArray[idx] = prd / nums[idx];
        }

        return prdArray;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.equals(
                        arrayProduct(
                                new int[]{1, 2, 3, 4}
                        ),
                        new int[]{24, 12, 8, 6}
                )
        );

        System.out.println(
                Arrays.equals(
                        arrayProduct(
                                new int[]{1, 0}
                        ),
                        new int[]{0, 1}
                )
        );

        System.out.println(
                Arrays.equals(
                        arrayProduct(
                                new int[]{1, 1}
                        ),
                        new int[]{1, 1}
                )
        );

        System.out.println(
                Arrays.equals(
                        arrayProduct(
                                new int[]{9, 0, -2}
                        ),
                        new int[]{0, -18, 0}
                )
        );
    }
}
