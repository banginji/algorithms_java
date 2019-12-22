package com.abc.algorithms.leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

public class _705_HashSet {
    private static class MyHashSet {
        int[] data = new int[1];
        int lastElemIdx = -1;

        public void add(int key) {
            if (this.data.length - 1 == lastElemIdx)
                this.data = Arrays.copyOf(this.data, lastElemIdx + 2);
            for (int elem : this.data)
                if (elem == key)
                    return;
            this.data[++lastElemIdx] = key;
        }

        public void remove(int key) {
            int itrIdx = -1;
            for (int elem : this.data) {
                itrIdx++;
                if (elem == key)
                    break;
            }

            for (int idx = itrIdx; idx < lastElemIdx; idx++)
                this.data[idx] = this.data[idx + 1];
            this.data = Arrays.copyOf(this.data, --lastElemIdx);
        }

        public boolean contains(int key) {
            for (int elem : this.data)
                if (elem == key)
                    return true;
            return false;
        }

        public int[] getData() {
            return data;
        }
    }

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        IntStream.range(1, 10).boxed().forEach(myHashSet::add);

        System.out.println(Arrays.toString(myHashSet.getData()));

        myHashSet.remove(5);

        System.out.println(Arrays.toString(myHashSet.getData()));
    }
}
