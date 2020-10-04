package com.abc.algorithms.leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

public class _523_SubArraySumCt {
    private static int checkSubArraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.putIfAbsent(0, -1);

        for (int idx = 0; idx < nums.length; idx++) {
            sum += nums[idx];
            sum %= k;

            if (prefixSumMap.containsKey(sum))
                if (idx - prefixSumMap.get(sum) >= 2)
                    count++;

            prefixSumMap.putIfAbsent(sum, idx);
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                checkSubArraySum(
                        new int[]{23, 2, 4, 6, 7},
                        6
                ) == 3
        );

        System.out.println(
                checkSubArraySum(
                        new int[]{23, 2, 6, 4, 7},
                        6
                ) == 2
        );
    }
}
