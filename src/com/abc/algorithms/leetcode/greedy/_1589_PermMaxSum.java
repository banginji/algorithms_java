package com.abc.algorithms.leetcode.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _1589_PermMaxSum {
    private static int permMaxSum(int[] nums, int[][] requests) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int[] request : requests)
            for (int idx = request[0]; idx <= request[1]; idx++) {
                freqMap.putIfAbsent(idx, 0);
                freqMap.computeIfPresent(idx, (k, v) -> v + 1);
            }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) pq.offer(entry.getValue());

        PriorityQueue<Integer> numsPq = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) numsPq.offer(num);

        long sum = 0;

        while (pq.size() > 0 && numsPq.size() > 0) sum += numsPq.poll() * pq.poll();

        return (int) (sum % (Math.pow(10, 9) + 7));
    }

    private static int permMaxSumPrime(int[] nums, int[][] requests) {
        int[] freqs = new int[nums.length];

        for (int[] request : requests) {
            freqs[request[0]]++;
            if (request[1] + 1 < nums.length) freqs[request[1] + 1]--;
        }

        for (int idx = 1; idx < freqs.length; idx++) freqs[idx] += freqs[idx - 1];

        Arrays.sort(freqs);
        Arrays.sort(nums);

        long sum = 0;

        for (int idx = 0; idx < freqs.length; idx++) sum += (long) freqs[idx] * (long) nums[idx];

        return (int) (sum % (Math.pow(10, 9) + 7));
    }

    public static void main(String[] args) {
        System.out.println(
                permMaxSum(
                        new int[]{1, 2, 3, 4, 5},
                        new int[][]{
                                new int[]{1, 3},
                                new int[]{0, 1}
                        }
                ) == 19
        );

        System.out.println(
                permMaxSum(
                        new int[]{1, 2, 3, 4, 5, 6},
                        new int[][]{
                                new int[]{0, 1}
                        }
                ) == 11
        );

        System.out.println(
                permMaxSum(
                        new int[]{1, 2, 3, 4, 5, 10},
                        new int[][]{
                                new int[]{0, 2},
                                new int[]{1, 3},
                                new int[]{1, 1}
                        }
                ) == 47
        );

        System.out.println(
                permMaxSumPrime(
                        new int[]{1, 2, 3, 4, 5},
                        new int[][]{
                                new int[]{1, 3},
                                new int[]{0, 1}
                        }
                ) == 19
        );

        System.out.println(
                permMaxSumPrime(
                        new int[]{1, 2, 3, 4, 5, 6},
                        new int[][]{
                                new int[]{0, 1}
                        }
                ) == 11
        );

        System.out.println(
                permMaxSumPrime(
                        new int[]{1, 2, 3, 4, 5, 10},
                        new int[][]{
                                new int[]{0, 2},
                                new int[]{1, 3},
                                new int[]{1, 1}
                        }
                ) == 47
        );
    }
}
