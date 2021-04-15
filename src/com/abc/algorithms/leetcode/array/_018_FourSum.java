package com.abc.algorithms.leetcode.array;

import java.util.*;

public class _018_FourSum {
    private static List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> res = new HashSet<>();
        for (int iIdx = 0; iIdx < nums.length; iIdx++) {
            int iSum = nums[iIdx];
            for (int jIdx = iIdx + 1; jIdx < nums.length; jIdx++) {
                int jSum = iSum + nums[jIdx];
                Map<Integer, Integer> numMap = new HashMap<>();
                for (int kIdx = jIdx + 1; kIdx < nums.length; kIdx++) {
                    if (numMap.containsKey(nums[kIdx]))
                        res.add(List.of(nums[iIdx], nums[jIdx], nums[kIdx], nums[numMap.get(nums[kIdx])]));
                    numMap.put(target - jSum - nums[kIdx], kIdx);
                }
            }
        }
        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
        System.out.println(
                fourSum(
                        new int[]{1, 0, -1, 0, -2, 2},
                        0
                ).equals(
                        List.of(
                                List.of(1, 0, 0, -1),
                                List.of(0, 0, 2, -2),
                                List.of(1, -1, 2, -2)
                        )
                )
        );

        System.out.println(
                fourSum(
                        new int[]{2, 2, 2, 2, 2},
                        8
                ).equals(
                        List.of(
                                List.of(2, 2, 2, 2)
                        )
                )
        );

        System.out.println(
                fourSum(
                        new int[]{3, 4, 8, 2, -2, -21, 5, 7, -5, -100, 34, 6, 154, 89, -19, -89},
                        45
                ).equals(
                        List.of(
                                List.of(3, 2, 6, 34),
                                List.of(8, -2, 34, 5),
                                List.of(-2, 7, 6, 34),
                                List.of(4, 2, 34, 5)
                        )
                )
        );
    }
}
