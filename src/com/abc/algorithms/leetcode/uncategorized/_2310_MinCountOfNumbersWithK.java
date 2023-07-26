package com.abc.algorithms.leetcode.uncategorized;

import java.util.ArrayList;
import java.util.List;

public class _2310_MinCountOfNumbersWithK {
    private static int count(int num, int k) {
        List<Integer> list = new ArrayList<>();
        for (int itr = 0; (itr * 10) + k < num; itr++) list.add(itr * 10 + k);
        int[] nums = new int[list.size()];
        int itrIdx = 0;
        for (int elem : list) nums[itrIdx++] = elem;
        int res = countR(nums, 0, num, 0);
        return res == 100 ? -1 : res;
    }

    // this is slow considering the other mathematical solution in discussions
    private static int countR(int[] nums, int idx, int num, int itrSum) {
        if (idx >= nums.length || itrSum > num) return 100;
        if (itrSum == num) return 0;
        return Math.min(
                1 + countR(nums, idx + 1, num, itrSum + nums[idx]),
                countR(nums, idx + 1, num, itrSum)
        );
    }

    public static void main(String[] args) {
        System.out.println(
                count(58, 9)
        );

        System.out.println(
                count(57, 2)
        );
    }
}
