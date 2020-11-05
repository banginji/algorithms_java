package com.abc.algorithms.leetcode.binarysearch;

public class _1095_FindInMtArray {
    record MountainArray(int[] nums) {
        public int get(int index) {
            return nums[index];
        }

        public int length() {
            return nums.length;
        }
    }

    private static int findInMtArray(int target, MountainArray mountainArray) {
        int peekIdx = findPeek(mountainArray);

        int resOne = binarySearch(target, mountainArray, 0, peekIdx, -1);
        if (resOne != -1) return resOne;

        return binarySearch(target, mountainArray, peekIdx, mountainArray.length() - 1, 1);
    }

    private static int findPeek(MountainArray mountainArray) {
        int leftIdx = 0, rightIdx = mountainArray.length() - 1;
        while (leftIdx < rightIdx) {
            int midPoint = (leftIdx + rightIdx) / 2;

            if (mountainArray.get(midPoint) < mountainArray.get(midPoint + 1))
                leftIdx = midPoint + 1;
            else
                rightIdx = midPoint;
        }

        return leftIdx;
    }

    private static int binarySearch(int target, MountainArray mountainArray, int startIdx, int endIdx, int intCompareResult) {
        if (endIdx - startIdx <= 1) {
            if (mountainArray.get(endIdx) == target)
                return endIdx;
            if (mountainArray.get(startIdx) == target)
                return startIdx;
            return -1;
        }
        int midPoint = (startIdx + endIdx) / 2;

        if (Integer.compare(mountainArray.get(midPoint), target) == intCompareResult)
            return binarySearch(target, mountainArray, midPoint, endIdx, intCompareResult);
        else
            return binarySearch(target, mountainArray, startIdx, midPoint, intCompareResult);
    }

    public static void main(String[] args) {
        System.out.println(
                findInMtArray(
                        3,
                        new MountainArray(new int[]{1, 2, 6, 3, 4, 5, 6, 5, 4, 3, 2, 1})
                ) == 9
        );

        System.out.println(
                findInMtArray(
                        32,
                        new MountainArray(new int[]{4, 12, 22, 32, 67, 43, 1})
                ) == 3
        );

        System.out.println(
                findInMtArray(
                        132,
                        new MountainArray(new int[]{4, 12, 22, 32, 67, 43, 1})
                ) == -1
        );

        System.out.println(
                findInMtArray(
                        0,
                        new MountainArray(new int[]{1, 5, 2})
                ) == -1
        );
    }
}
