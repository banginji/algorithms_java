package com.abc.algorithms.leetcode;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class _463_IslandPerimeter {
    // TODO - not implemented
    private static class GridPoint {
        private final int rowIdx;
        private final int colIdx;

        GridPoint(Builder builder) {
            this.rowIdx = builder.rowIdx;
            this.colIdx = builder.colIdx;
        }

        public static Builder builder() {
            return new Builder();
        }

        public int getRowIdx() {
            return rowIdx;
        }

        public int getColIdx() {
            return colIdx;
        }

        public static class Builder {
            private int rowIdx;
            private int colIdx;

            public Builder setRowIdx(int rowIdx) {
                this.rowIdx = rowIdx;
                return this;
            }

            public Builder setColIdx(int colIdx) {
                this.colIdx = colIdx;
                return this;
            }

            public GridPoint build() {
                return new GridPoint(this);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GridPoint gridPoint = (GridPoint) o;
            return rowIdx == gridPoint.rowIdx &&
                    colIdx == gridPoint.colIdx;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowIdx, colIdx);
        }
    }

    private static int islandPerimeterBfs(int[][] grid) {
        LinkedList<GridPoint> queue = new LinkedList<>();

        Set<GridPoint> visitedPoints = new HashSet<>();

        queue.add(GridPoint.builder().setRowIdx(0).setColIdx(0).build());

        Predicate<GridPoint> isAccessiblePoint = gridPoint -> grid[gridPoint.getRowIdx()][gridPoint.getColIdx()] == 1;

        Predicate<GridPoint> isNotVisitedPoint = gridPoint -> !visitedPoints.contains(gridPoint);

        Predicate<GridPoint> isBoundary = gridPoint ->
                gridPoint.getRowIdx() == 0
                        || gridPoint.getRowIdx() == grid.length - 1
                        || gridPoint.getColIdx() == 0
                        || gridPoint.getColIdx() == grid[0].length - 1;

        int perimeter = 0;

        while (queue.size() > 0) {
            GridPoint currentPoint = queue.pop();

            visitedPoints.add(currentPoint);

            List<GridPoint> neighbors = getNeighbors(currentPoint, grid.length, grid[0].length);

            List<GridPoint> feasiblePoints = neighbors.stream()
                    .filter(isAccessiblePoint.and(isNotVisitedPoint))
                    .collect(Collectors.toList());

            if (grid[currentPoint.getRowIdx()][currentPoint.getColIdx()] == 1) {
                perimeter += neighbors.stream()
                        .filter(point -> grid[point.getRowIdx()][point.getColIdx()] == 0)
                        .count();

                perimeter += neighbors.stream()
                        .filter(point -> grid[point.getRowIdx()][point.getColIdx()] == 0)
                        .filter(isBoundary)
                        .count();
            }

            if (feasiblePoints.size() > 0)
                queue.addAll(feasiblePoints);
        }

        return perimeter;
    }

    private static List<GridPoint> getNeighbors(GridPoint point, int rowLength, int colLength) {
        List<GridPoint> neighbors = new ArrayList<>();

        if (point.getRowIdx() - 1 >= 0)
            neighbors.add(
                    GridPoint.builder()
                            .setRowIdx(point.getRowIdx() - 1)
                            .setColIdx(point.getColIdx())
                            .build()
            );

        if (point.getRowIdx() + 1 < rowLength - 1)
            neighbors.add(
                    GridPoint.builder()
                            .setRowIdx(point.getRowIdx() + 1)
                            .setColIdx(point.getColIdx())
                            .build()
            );

        if (point.getColIdx() - 1 >= 0)
            neighbors.add(
                    GridPoint.builder()
                            .setRowIdx(point.getRowIdx())
                            .setColIdx(point.getColIdx() - 1)
                            .build()
            );

        if (point.getColIdx() + 1 < colLength - 1)
            neighbors.add(
                    GridPoint.builder()
                            .setRowIdx(point.getRowIdx())
                            .setColIdx(point.getColIdx() + 1)
                            .build()
            );

        return neighbors;
    }

    public static void main(String[] args) {
        System.out.println(
                islandPerimeterBfs(
                        new int[][]{
                                {0, 1, 0, 0},
                                {1, 1, 1, 0},
                                {0, 1, 0, 0},
                                {1, 1, 0, 0}
                        }
                )
        );
    }
}
