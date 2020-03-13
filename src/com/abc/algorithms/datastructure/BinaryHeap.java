package com.abc.algorithms.datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryHeap {
    public static class MinHeap<T extends Comparable<T>> {
        List<T> heap;

        public MinHeap() {
            this.heap = new ArrayList<>();
        }

        public MinHeap(List<T> heap) {
            this.heap = heap;
        }

        private void buildMinHeap() {
            for (int idx = heap.size() / 2; idx >= 0; idx--)
                minHeapify(idx, heap.size() - 1);
        }

        private void minHeapify(int idx, int endIdx) {
            int leftIdx = leftIdx(idx);
            int rightIdx = rightIdx(idx);

            int smallestIdx = idx;

            if (leftIdx <= endIdx && this.heap.get(leftIdx).compareTo(this.heap.get(smallestIdx)) < 0)
                smallestIdx = leftIdx;
            if (rightIdx <= endIdx && this.heap.get(rightIdx).compareTo(this.heap.get(smallestIdx)) < 0)
                smallestIdx = rightIdx;

            if (smallestIdx != idx) {
                Collections.swap(this.heap, idx, smallestIdx);
                minHeapify(smallestIdx, endIdx);
            }
        }

        public void heapPush(T... elements) {
            for (T elem : elements) {
                this.heap.add(elem);
                percolateUp(this.heap.size() - 1);
            }
        }

        private void percolateUp(int idx) {
            if (parentIdx(idx) >= 0 && this.heap.get(idx).compareTo(this.heap.get(parentIdx(idx))) < 0) {
                Collections.swap(this.heap, idx, parentIdx(idx));
                percolateUp(parentIdx(idx));
            }
        }

        public T heapPop() {
            Collections.swap(this.heap, 0, this.heap.size() - 1);
            minHeapify(0, this.heap.size() - 2);

            return this.heap.remove(this.heap.size() - 1);
        }

        private int parentIdx(int idx) {
            return (idx - 1) / 2;
        }

        private int leftIdx(int idx) {
            return 2 * idx + 1;
        }

        private int rightIdx(int idx) {
            return 2 * idx + 2;
        }

        public void heapSort() {
            buildMinHeap();
            for (int idx = this.heap.size() - 1; idx > 0; idx--) {
                Collections.swap(this.heap, idx, 0);
                minHeapify(0, idx - 1);
            }
        }

        public List<T> getHeap() {
            return heap;
        }
    }

    public static class MaxHeap<T extends Comparable<T>> {
        List<T> heap;

        public MaxHeap() {
            this.heap = new ArrayList<>();
        }

        public MaxHeap(List<T> heap) {
            this.heap = heap;
        }

        private void buildMaxHeap() {
            for (int idx = heap.size() / 2; idx >= 0; idx--)
                maxHeapify(idx, heap.size() - 1);
        }

        private void maxHeapify(int idx, int endIdx) {
            int leftIdx = leftIdx(idx);
            int rightIdx = rightIdx(idx);

            int largestIdx = idx;

            if (leftIdx <= endIdx && this.heap.get(leftIdx).compareTo(this.heap.get(largestIdx)) > 0)
                largestIdx = leftIdx;
            if (rightIdx <= endIdx && this.heap.get(rightIdx).compareTo(this.heap.get(largestIdx)) > 0)
                largestIdx = rightIdx;

            if (largestIdx != idx) {
                Collections.swap(this.heap, idx, largestIdx);
                maxHeapify(largestIdx, endIdx);
            }
        }

        public void heapPush(T... elements) {
            for (T elem : elements) {
                this.heap.add(elem);
                percolateUp(this.heap.size() - 1);
            }
        }

        private void percolateUp(int idx) {
            if (parentIdx(idx) >= 0 && this.heap.get(idx).compareTo(this.heap.get(parentIdx(idx))) > 0) {
                Collections.swap(this.heap, idx, parentIdx(idx));
                percolateUp(parentIdx(idx));
            }
        }

        public T heapPop() {
            Collections.swap(this.heap, 0, this.heap.size() - 1);
            maxHeapify(0, this.heap.size() - 2);

            return this.heap.remove(this.heap.size() - 1);
        }

        private int parentIdx(int idx) {
            return (idx - 1) / 2;
        }

        private int leftIdx(int idx) {
            return 2 * idx + 1;
        }

        private int rightIdx(int idx) {
            return 2 * idx + 2;
        }

        public void heapSort() {
            buildMaxHeap();
            for (int idx = this.heap.size() - 1; idx > 0; idx--) {
                Collections.swap(this.heap, idx, 0);
                maxHeapify(0, idx - 1);
            }
        }

        public List<T> getHeap() {
            return heap;
        }
    }
}
