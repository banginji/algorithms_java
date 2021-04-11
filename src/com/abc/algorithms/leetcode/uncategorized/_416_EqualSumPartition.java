package com.abc.algorithms.leetcode.uncategorized;

import com.abc.algorithms.datastructure.BinaryHeap;

import java.util.List;
import java.util.stream.IntStream;

public class _416_EqualSumPartition {
    private static boolean canPartition(int[] nums) {
        BinaryHeap.MinHeap<Integer> heap = new BinaryHeap.MinHeap<>();
        for (int num : nums)
            heap.heapPush(num);

        heap.heapSort();

        List<Integer> elements = heap.getHeap();

        int pivot = 0;

        int sum = elements.stream().mapToInt(Integer::intValue).sum();
        sum -= elements.get(pivot);

        int remainder = 0;
        remainder += elements.get(pivot);

        while (pivot < elements.size()) {
            if (sum == remainder)
                return true;
            if (sum < remainder)
                break;
            sum -= elements.get(pivot);
            remainder += elements.get(pivot);
            pivot++;
        }

        return false;
    }

    private static boolean canPartitionFast(int[] nums) {
        int targetSum = IntStream.of(nums).sum();

        if (targetSum % 2 != 0)
            return false;

        targetSum /= 2;

        int[] result = new int[targetSum + 1];

        for (int num : nums)
            for (int sum = targetSum; sum >= num; sum--)
                result[sum] = Math.max(result[sum], num + result[sum - num]);

        return result[targetSum] == targetSum;
    }

    public static void main(String[] args) {
        System.out.println(
                canPartitionFast(
                        new int[]{1, 5, 2, 2}
                )
        );
    }
}
