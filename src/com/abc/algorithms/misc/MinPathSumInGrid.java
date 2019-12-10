package com.abc.algorithms.misc;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class MinPathSumInGrid {
    private static Integer[][] createGrid(int numberOfRows, int numberOfColumns) {
        Integer[][] grid = new Integer[numberOfRows][numberOfColumns];

        for (int rowIdx = 0; rowIdx < numberOfRows; rowIdx++) {
            for (int colIdx = 0; colIdx < numberOfColumns; colIdx++) {
                grid[rowIdx][colIdx] = (
                        Math.random() > 0.5
                                ? 1
                                : -1
                ) * new Random().nextInt(10);
            }
        }

        return grid;
    }

    private static Integer[][] createWeightGrid(Integer[][] grid) {
        int numberOfRows = grid.length;
        int numberOfCols = grid[0].length;

        Integer[][] weightGrid = new Integer[numberOfRows][numberOfCols];

        for (int rowIdx = numberOfRows - 1; rowIdx >= 0; rowIdx--) {
            for (int colIdx = numberOfCols - 1; colIdx >= 0; colIdx--) {

                // Last element
                if (rowIdx + 1 >= numberOfRows && colIdx + 1 >= numberOfCols) {
                    weightGrid[rowIdx][colIdx] = grid[rowIdx][colIdx];
                    continue;
                }

                // Last row
                if (rowIdx + 1 >= numberOfRows) {
                    weightGrid[rowIdx][colIdx] = grid[rowIdx][colIdx] + weightGrid[rowIdx][colIdx+1];
                    continue;
                }

                // Last col
                if (colIdx + 1 >= numberOfCols) {
                    weightGrid[rowIdx][colIdx] = grid[rowIdx][colIdx] + weightGrid[rowIdx+1][colIdx];
                    continue;
                }

                // Intermediate element
                weightGrid[rowIdx][colIdx] = Math.min(
                        grid[rowIdx][colIdx] + weightGrid[rowIdx][colIdx+1],
                        grid[rowIdx][colIdx] + weightGrid[rowIdx+1][colIdx]
                );
            }
        }

        return weightGrid;
    }

    static Consumer<Integer[]> pnt = integers -> {
        Arrays.stream(integers)
                .map(integer -> String.format("%+03d", integer) + ", ")
                .forEach(System.out::print);
        System.out.println();
    };

    public static void main(String[] args) {
        Integer[][] grid = createGrid(5, 6);

        Arrays.stream(grid).forEach(pnt);

        System.out.println();

        Arrays.stream(createWeightGrid(grid)).forEach(pnt);
    }
}
