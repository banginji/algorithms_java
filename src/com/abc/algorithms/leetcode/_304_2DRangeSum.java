package com.abc.algorithms.leetcode;

import com.abc.algorithms.misc.Tuple;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class _304_2DRangeSum {
    private static class NumMatrix {
        Integer[][] matrix;

        NumMatrix(Integer[][] matrix) {
            this.matrix = matrix;
        }

        int sumRegion(
                Tuple<Integer> leftTopCorner,
                Tuple<Integer> rightBottomCorner
        ) {
            int sum = 0;
            for (int rowIdx = leftTopCorner.getFirst();
                 rowIdx <= rightBottomCorner.getFirst();
                 rowIdx++
            ) {
                for (int colIdx = leftTopCorner.getSecond();
                     colIdx <= rightBottomCorner.getSecond();
                     colIdx++
                ) {
                    sum += this.matrix[rowIdx][colIdx];
                }
            }

            return sum;
        }
    }

    static Consumer<Integer[]> pnt = arrays -> {
        Arrays.stream(arrays)
                .map(num -> String.format(" %3d ", num))
                .forEach(System.out::print);
        System.out.println();
    };

    public static void main(String[] args) {
        int numOfRows = 10;
        int numOfCols = 12;

        Integer[][] matrix = new Integer[numOfRows][numOfCols];
        for (int rowIdx = 0; rowIdx < numOfRows; rowIdx++) {
            for (int colIdx = 0; colIdx < numOfCols; colIdx++) {
                matrix[rowIdx][colIdx] = (
                        Math.random() > 0.5
                                ? -1
                                : 1
                ) * new Random().nextInt(10);
            }
        }

        NumMatrix numMatrix = new NumMatrix(matrix);
        Arrays.stream(numMatrix.matrix).forEach(pnt);

        System.out.println("Sum of region - " +
                numMatrix.sumRegion(
                        new Tuple<>(1, 1),
                        new Tuple<>(2, 2)
                )
        );
    }
}
