package com.abc.algorithms.misc;

import java.util.Arrays;

public class SpecialPerms {
    private static void specialPerms(int n) {
        int[] nums = new int[2 * n];
        Arrays.fill(nums, -1);
        generate(1, nums, n);
        System.out.println(Arrays.toString(nums));
    }

    private static boolean generate(int num, int[] nums, int n) {
        int idx = 0;
        while (idx < nums.length && idx + num + 1 < nums.length) {
            if (nums[idx] != -1) {
                idx++;
                continue;
            }
            nums[idx] = num;
            if (nums[idx + num + 1] != -1) {
                nums[idx] = -1;
                idx++;
                continue;
            }
            nums[idx + num + 1] = num;
            if (num == n) return true;
            if (!generate(num + 1, nums, n)) {
                nums[idx] = -1;
                nums[idx + num + 1] = -1;
                idx++;
            } else
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        specialPerms(7);
    }
}
