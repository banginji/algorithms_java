package com.abc.algorithms.leetcode;

import com.abc.algorithms.datastructure.BinaryHeap;
import com.abc.algorithms.misc.Tuple;

import java.util.*;

public class _313_UglyNumbers {
    private static Integer superUglyNumber(Integer nth, Integer[] primes) {
        List<Integer> superUglyNumbers = new LinkedList<>();

        HashMap<Integer, List<Tuple<Integer, Integer>>> tracker = new HashMap<>();
        tracker.put(1, new ArrayList<>());
        for (Integer prime: primes) {
            tracker.get(1).add(new Tuple<>(0, prime));
        }

        BinaryHeap.MinHeap<Integer> heap = new BinaryHeap.MinHeap<>();
        heap.heapPush(1);

        while (superUglyNumbers.size() < nth) {
            superUglyNumbers.add(heap.heapPop());

            for (Tuple<Integer, Integer> tuple: tracker.get(
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

        return superUglyNumbers.get(nth);
    }

    public static void main(String[] args) {
        BinaryHeap.MinHeap<Integer> heap = new BinaryHeap.MinHeap<>(new LinkedList<>(Arrays.asList(1, 23, 74, 23, 2, 88, 2, -1, -35)));

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
