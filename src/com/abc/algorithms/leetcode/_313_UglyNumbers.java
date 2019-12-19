package com.abc.algorithms.leetcode;

import com.abc.algorithms.misc.Tuple;

import java.util.*;

public class _313_UglyNumbers {
    /**
     *
     * primes = {2, 7, 13, 19}; n=12
     * generated numbers =  {
     *                          1,
     *                          2(1*2),
     *                          4(2*2),
     *                          7(1*7),
     *                          8(2*4),
     *                          13(1*13),
     *                          14(2*7),
     *                          16(4*4),
     *                          19(1*19),
     *                          26(2*13),
     *                          28(4*7),
     *                          32(4*8)
     *                      }
     *
     */

    private static class Heap {
        List<Integer> heap;

        Heap(List<Integer> heap) {
            this.heap = heap;
            buildMinHeap();
        }

        public List<Integer> getHeap() {
            return heap;
        }

        void buildMinHeap() {
            for (int idx = heap.size() / 2; idx >= 0; idx--) {
                minHeapify(idx, heap.size());
            }
        }

        void minHeapify(int idx, int end) {
            int smallestIdx = idx;
            int leftIdx = left(idx);
            int rightIdx = right(idx);

            if (leftIdx < end && heap.get(leftIdx) < heap.get(smallestIdx))
                smallestIdx = leftIdx;
            if (rightIdx < end && heap.get(rightIdx) < heap.get(smallestIdx))
                smallestIdx = rightIdx;

            if (smallestIdx != idx) {
                Collections.swap(heap, idx, smallestIdx);
                minHeapify(smallestIdx, end);
            }
        }

        int left(int idx) {
            return 2 * idx + 1;
        }

        int right(int idx) {
            return 2 * idx + 2;
        }

        // TODO: Check heap pop and push logic
        Integer heapPop() {
            Collections.swap(heap, 0, heap.size()-1);
            Integer poppedElement = heap.remove(heap.size()-1);

            minHeapify(0, heap.size());

            return poppedElement;
        }

        void heapPush(Integer elem) {
            heap.add(elem);
            minHeapify(0, heap.size());
        }

        void heapSort() {
            buildMinHeap();
            for (int idx = heap.size() - 1; idx >= 0; idx--) {
                Collections.swap(heap, 0, idx);
                minHeapify(0, idx);
            }
        }
    }

    private static Integer superUglyNumber(Integer idx, Integer[] primes) {
        List<Integer> superUglyNumbers = new LinkedList<>();

        HashMap<Integer, List<Tuple<Integer>>> tracker = new HashMap<>();
        tracker.put(1, new ArrayList<>());
        for (Integer prime: primes) {
            tracker.get(1).add(new Tuple<>(0, prime));
        }

        Heap heap = new Heap(
                new LinkedList<>(
                        Arrays.asList(1)
                )
        );

        while (superUglyNumbers.size()-1 < idx) {
            superUglyNumbers.add(heap.heapPop());

            for (Tuple<Integer> tuple: tracker.get(
                    superUglyNumbers.get(
                            superUglyNumbers.size()-1
                    )
            )) {
                Integer newUgly = superUglyNumbers.get(tuple.getFirst()) * tuple.getSecond();

                if (tracker.containsKey(newUgly)) {
                    tracker.get(newUgly).add(
                            new Tuple<>(
                                    tuple.getFirst()+1,
                                    tuple.getSecond()
                            )
                    );
                } else {
                    tracker.put(
                            newUgly,
                            new LinkedList<>(
                                    Arrays.asList(
                                            new Tuple<>(
                                                    tuple.getFirst()+1,
                                                    tuple.getSecond()
                                            )
                                    )
                            )
                    );
                    heap.heapPush(newUgly);
                }
            }
            tracker.remove(
                    superUglyNumbers.get(
                            superUglyNumbers.size()-1
                    )
            );
        }

        return superUglyNumbers.get(idx);
    }

    public static void main(String[] args) {
        Heap heap = new Heap(new LinkedList<>(Arrays.asList(1, 23, 74, 23, 2, 88, 2, -1, -35)));

        heap.getHeap().stream()
                .map(num -> String.format("%+2d", num) + ", ")
                .forEach(System.out::print);

        System.out.println();
        System.out.println(heap.heapPop());

        heap.getHeap().stream()
                .map(num -> String.format("%+2d", num) + ", ")
                .forEach(System.out::print);

        System.out.println();
        System.out.println(
                superUglyNumber(12, new Integer[]{2, 7, 13, 19})
        );
    }
}
