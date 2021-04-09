package com.abc.algorithms.leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class _1146_SnapshotArray {
    private static class SnapshotArray {
        private final Map<Integer, Integer>[] nums;
        private int snapShotId = 0;

        SnapshotArray(int length) {
            this.nums = new HashMap[length];
        }

        public void set(int index, int val) {
            if (nums[index] == null) nums[index] = new HashMap<>();
            nums[index].put(snapShotId, val);
        }

        public int snap() {
            return ++snapShotId - 1;
        }

        public int get(int index, int snapShotId) {
            return nums[index].get(snapShotId);
        }
    }

    public static void main(String[] args) {
        SnapshotArray snapshotArray = new SnapshotArray(3);
        snapshotArray.set(0, 5);  // Set array[0] = 5
        System.out.println(snapshotArray.snap());  // Take a snapshot, return snap_id = 0
        snapshotArray.set(0, 6);
        System.out.println(snapshotArray.get(0, 0));
    }
}
