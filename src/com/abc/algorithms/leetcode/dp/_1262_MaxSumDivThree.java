package com.abc.algorithms.leetcode.dp;

import java.util.List;
import java.util.PriorityQueue;

public class _1262_MaxSumDivThree {
    private static int maxSumDivThree(int[] nums) {
        return maxSumDivThree(nums, 0, 0);
    }

    private static int maxSumDivThree(int[] nums, int sum, int idx) {
        if (idx > nums.length - 1) return sum;

        int resOne = maxSumDivThree(nums, sum + nums[idx], idx + 1);
        int resTwo = maxSumDivThree(nums, sum, idx + 1);

        int maxRes = Math.max(resOne, resTwo);
        int minRes = Math.min(resOne, resTwo);

        if (maxRes % 3 == 0) return maxRes;
        else if (minRes % 3 == 0) return minRes;
        else return 0;
    }

    /**
     * Usual Dp TD approach does not work
     *
     * Create 3 PQs each for nums that give mod 0, 1, 2 when divided by 3
     * Add all nums
     *
     * For a 1 mod numbered sum, subtract either the least 1 mod number or else 2 2-mod numbers
     * Ex: 10 (1-mod sum) either subtract
     * 1 (1-mod) {10-(1) = 9} or
     * 5 & 2 (2-mod) {10-(5+2) = 3}
     *
     * For a 2 mod numbered sum, subtract either 2 1 mod numbers or else 1 2-mod number
     * Ex: 11 (2-mod sum) either subtract
     * 1, 4 (1-mods) {11-(1+4) = 6} or
     * 2 (2-mod) {11-2 = 9}
     *
     * @param nums
     * @return
     */
    private static int maxSumDivThreeDp(int[] nums) {
        List<PriorityQueue<Integer>> pqs = List.of(new PriorityQueue<>(), new PriorityQueue<>(), new PriorityQueue<>());

        int sum = 0;

        for (int num : nums) {
            pqs.get(num % 3).offer(num);
            sum += num;
        }

        int mod = sum % 3;
        return switch (mod) {
            case 0 -> sum;
            case 1, 2 -> {
                int one = 0;
                if (pqs.get(mod).size() > 0)
                    one = sum - pqs.get(mod).peek();
                int two = 0;
                if (pqs.get(3 - mod).size() > 1)
                    two = sum - pqs.get(3 - mod).poll() - pqs.get(3 - mod).poll();
                yield Math.max(one, two);
            }
            default -> 0;
        };
    }

    private static int maxSumDivThreePq(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int sum = 0;

        for (int num : nums) {
            pq.offer(num);
            sum += num;
        }

        while (pq.size() > 0) {
            if ((sum - pq.peek()) % 3 == 0) return sum - pq.poll();
            pq.poll();
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(
                maxSumDivThree(new int[]{3, 6, 5, 1, 8}) == 18
        );

        System.out.println(
                maxSumDivThree(new int[]{4}) == 0
        );

        System.out.println(
                maxSumDivThree(new int[]{1, 2, 3, 4, 4}) == 12
        );

        System.out.println(
                maxSumDivThree(new int[]{1, 8, 3, 55, 23, 11, 7, 22, 3}) == 132
        );

        System.out.println(
                maxSumDivThree(new int[]{2, 6, 2, 2, 7}) == 15
        );

        System.out.println("-".repeat(25));

        System.out.println(
                maxSumDivThreeDp(new int[]{3, 6, 5, 1, 8}) == 18
        );

        System.out.println(
                maxSumDivThreeDp(new int[]{4}) == 0
        );

        System.out.println(
                maxSumDivThreeDp(new int[]{1, 2, 3, 4, 4}) == 12
        );

        System.out.println(
                maxSumDivThreeDp(new int[]{1, 8, 3, 55, 23, 11, 7, 22, 3}) == 132
        );

        System.out.println(
                maxSumDivThreeDp(new int[]{2, 6, 2, 2, 7}) == 15
        );
    }
}
