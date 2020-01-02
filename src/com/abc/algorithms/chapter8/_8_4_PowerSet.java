package com.abc.algorithms.chapter8;

import java.util.Arrays;

public class _8_4_PowerSet {
    private static int[][] powerSet(int[] elems, int idx, int[][] base) {
        int[][] result = new int[][]{};

        for (int[] baseElem : base) {
            int[] newBaseElem = copyToArray(baseElem, elems[idx]);
            if (idx == elems.length - 1) {
                result = copyTo2DArray(
                        result,
                        new int[][]{baseElem, newBaseElem}
                );
            } else {
                result = copyTo2DArray(
                        result,
                        powerSet(
                                elems,
                                idx + 1,
                                new int[][]{baseElem, newBaseElem}
                        )
                );
            }
        }

        return result;
    }

    private static int[] copyToArray(int[] array, int elem) {
        int[] clone = Arrays.copyOf(array, array.length + 1);
        clone[array.length] = elem;
        return clone;
    }

    private static int[][] copyTo2DArray(int[][] array, int[][] elems) {
        int newLength = array.length + elems.length;
        int[][] clone = Arrays.copyOf(array, newLength);
        int elemIdx = 0;
        for (int idx = array.length; idx < newLength; idx++)
            clone[idx] = elems[elemIdx++];
        return clone;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.deepToString(
                        powerSet(
                                new int[]{0, 1, 2}, 0, new int[][]{new int[]{}}
                        )
                )
        );
    }
}
