package com.abc.algorithms.sorts;

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
            Tuple swappedNums = swap(nums[largestIdx], nums[idx]);
            nums[largestIdx] = swappedNums.getOne();
            nums[idx] = swappedNums.getTwo();
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
            Tuple swappedNums = swap(nums[idx], nums[0]);
            nums[idx] = swappedNums.getOne();
            nums[0] = swappedNums.getTwo();

            maxHeapify(nums, 0, idx);
        }
    }

    private static Tuple swap(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;

        return new Tuple(a, b);
    }

    private static class Tuple {
        private Integer one;
        private Integer two;

        Tuple(Integer one, Integer two) {
            this.one = one;
            this.two = two;
        }

        Integer getOne() {
            return this.one;
        }

        Integer getTwo() {
            return this.two;
        }
    }
}
