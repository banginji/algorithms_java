package com.abc.algorithms.sorts;

import com.abc.algorithms.misc.Tuple;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        System.out.println("Heap sort Implementation");

        int[] nums = {1, 2, 7, 3, 5, 6, 4};

        System.out.println("Before sorting");
        Arrays.stream(nums).forEach(System.out::println);

        heapSort(nums);

        System.out.println("After sorting");
        Arrays.stream(nums).forEach(System.out::println);
    }

    private static void buildMaxHeap(int[] nums) {
        for (int idx = nums.length / 2; idx >= 0; idx--) {
            maxHeapify(nums, idx, nums.length);
        }
    }

    private static void maxHeapify(int[] nums, int idx, int end) {
        int leftIdx = leftIdx(idx);
        int rightIdx = rightIdx(idx);

        int largestIdx = idx;

        if (leftIdx < end && nums[leftIdx] > nums[largestIdx]) {
            largestIdx = leftIdx;
        }

        if (rightIdx < end && nums[rightIdx] > nums[largestIdx]) {
            largestIdx = rightIdx;
        }

        if (largestIdx != idx) {
            Tuple<Integer, Integer> swappedNums = swap(nums[largestIdx], nums[idx]);
            nums[largestIdx] = swappedNums.getFirst();
            nums[idx] = swappedNums.getSecond();
            maxHeapify(nums, largestIdx, end);
        }
    }

    private static int leftIdx(int idx) {
        return 2 * idx + 1;
    }

    private static int rightIdx(int idx) {
        return 2 * idx + 2;
    }

    private static void heapSort(int[] nums) {
        buildMaxHeap(nums);
        for (int idx = nums.length - 1; idx >= 0; idx--) {
            Tuple<Integer, Integer> swappedNums = swap(nums[idx], nums[0]);
            nums[idx] = swappedNums.getFirst();
            nums[0] = swappedNums.getSecond();

            maxHeapify(nums, 0, idx);
        }
    }

    private static Tuple<Integer, Integer> swap(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;

        return new Tuple<>(a, b);
    }
}
