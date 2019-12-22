package com.abc.algorithms.leetcode;

import com.abc.algorithms.datastructure.BinaryHeap;

public class _703_KthLargest {
    private static Integer getKthLargest(int k, int[] nums) {
        BinaryHeap.MaxHeap<Integer> heap = new BinaryHeap.MaxHeap<>();
        for (int num : nums)
            heap.heapPush(num);

        heap.getHeap().stream()
                .map(num -> String.format("%+2d, ", num))
                .forEach(System.out::print);

        System.out.println();

        return heap.getHeap().get(k);
    }

    public static void main(String[] args) {
        System.out.println(getKthLargest(1, new int[]{-11, 35, -14, 55, 111, 153, -14244323}));
    }
}
