package com.abc.algorithms.leetcode.twoptrs;

public class _713_SubArrayPrdCt {
    private static int subArrayPrdCt(int[] nums, int k) {
        int count = 0, leftPtr = 0, rightPtr = -1, prd = 1;

        while (rightPtr < nums.length) {
            if (prd < k) {
                count += rightPtr - leftPtr + 1;
                if (rightPtr + 1 < nums.length)
                    prd *= nums[++rightPtr];
                else break;
            } else
                prd /= nums[leftPtr++];
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                subArrayPrdCt(
                        new int[]{10, 5, 2, 6},
                        100
                ) == 8
        );
    }
}
