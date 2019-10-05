package com.abc.algorithms.sorts;

import java.util.Arrays;

public class QuickSort {
    private static void quickSort(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }

        int pivot = nums[end], wallIdx = start, idx = start;

        while (idx < nums.length - 1) {
            if (pivot > nums[idx]) {
                if (wallIdx != idx) {
                    Tuple<Integer> swappedNums = swap(nums[wallIdx], nums[idx]);
                    nums[wallIdx] = swappedNums.getOne();
                    nums[idx] = swappedNums.getTwo();

                    wallIdx++;
                    idx++;
                } else {
                    wallIdx++;
                    idx++;
                }
            } else {
                idx++;
            }
        }

        Tuple<Integer> swappedNums = swap(nums[wallIdx], nums[end]);
        nums[wallIdx] = swappedNums.getOne();
        nums[end] = swappedNums.getTwo();

        if (start < wallIdx) {
            quickSort(nums, start, wallIdx - 1);
        }
        if (wallIdx < end) {
            quickSort(nums, wallIdx + 1, end);
        }
    }

    private static Tuple<Integer> swap(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;

        return new Tuple<>(a, b);
    }

    public static void main(String[] args) {
        int[] nums = {213, 4, 123, 546, 13, 67, 1, -1};
        quickSort(nums, 0, nums.length - 1);

        Arrays.stream(nums).forEach(System.out::println);
    }

    private static class Tuple<T> {
        private final T one;
        private final T two;

        Tuple(T one, T two) {
            this.one = one;
            this.two = two;
        }

        T getOne() {
            return this.one;
        }

        T getTwo() {
            return this.two;
        }
    }
}
