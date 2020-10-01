package com.abc.algorithms.leetcode.greedy;

public class _1594_MaxNonNegProduct {
    private record MinMax(int min, int max) {}

    private static int maxProductPath(int[][] grid) {
        int rowLength = grid.length, colLength = grid[0].length;
        MinMax[][] productGrid = calcProductGrid(grid);

        MinMax maxProduct = productGrid[rowLength - 1][colLength - 1];

        return maxProduct.max() >= 0 ? maxProduct.max() : -1;
    }

    private static MinMax[][] calcProductGrid(int[][] grid) {
        int rowLength = grid.length, colLength = grid[0].length;

        MinMax[][] productGrid = new MinMax[rowLength][colLength];

        for (int rowIdx = 0; rowIdx < rowLength; rowIdx++) {
            for (int colIdx = 0; colIdx < colLength; colIdx++) {
                // First element
                if (rowIdx == 0 && colIdx == 0) {
                    productGrid[rowIdx][colIdx] = new MinMax(grid[rowIdx][colIdx], grid[rowIdx][colIdx]);
                    continue;
                }

                // First row
                if (rowIdx == 0) {
                    int possibleMin = productGrid[rowIdx][colIdx - 1].min() * grid[rowIdx][colIdx];
                    int possibleMax = productGrid[rowIdx][colIdx - 1].max() * grid[rowIdx][colIdx];
                    productGrid[rowIdx][colIdx] = new MinMax(
                            Math.min(possibleMin, possibleMax),
                            Math.max(possibleMin, possibleMax)
                    );
                    continue;
                }

                // First col
                if (colIdx == 0) {
                    int possibleMin = productGrid[rowIdx - 1][colIdx].min() * grid[rowIdx][colIdx];
                    int possibleMax = productGrid[rowIdx - 1][colIdx].max() * grid[rowIdx][colIdx];
                    productGrid[rowIdx][colIdx] = new MinMax(
                            Math.min(possibleMin, possibleMax),
                            Math.max(possibleMin, possibleMax)
                    );
                    continue;
                }

                // Top element product
                int tepMin = productGrid[rowIdx - 1][colIdx].min() * grid[rowIdx][colIdx];
                int tepMax = productGrid[rowIdx - 1][colIdx].max() * grid[rowIdx][colIdx];
                MinMax tep = new MinMax(
                        Math.min(tepMin, tepMax),
                        Math.max(tepMin, tepMax)
                );
                // Left element product
                int lepMin = productGrid[rowIdx][colIdx - 1].min() * grid[rowIdx][colIdx];
                int lepMax = productGrid[rowIdx][colIdx - 1].max() * grid[rowIdx][colIdx];
                MinMax lep = new MinMax(
                        Math.min(lepMin, lepMax),
                        Math.max(lepMin, lepMax)
                );

                productGrid[rowIdx][colIdx] = new MinMax(
                        Math.min(tep.min(), lep.min()),
                        Math.max(tep.max(), lep.max())
                );
            }
        }

        return productGrid;
    }

    public static void main(String[] args) {
        System.out.println(
                maxProductPath(
                        new int[][]{
                                new int[]{-1, -2, -3},
                                new int[]{-2, -3, -3},
                                new int[]{-3, -3, -2}
                        }
                ) == -1
        );

        System.out.println(
                maxProductPath(
                        new int[][]{
                                new int[]{1, -2, 1},
                                new int[]{1, -2, 1},
                                new int[]{3, -4, 1}
                        }
                ) == 8
        );

        System.out.println(
                maxProductPath(
                        new int[][]{
                                new int[]{1, 3},
                                new int[]{0, -4}
                        }
                ) == 0
        );

        System.out.println(
                maxProductPath(
                        new int[][]{
                                new int[]{1, 4, 4, 0},
                                new int[]{-2, 0, 0, 1},
                                new int[]{1, -1, 1, 1}
                        }
                ) == 2
        );
    }
}
