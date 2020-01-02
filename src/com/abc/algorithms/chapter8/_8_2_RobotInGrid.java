package com.abc.algorithms.chapter8;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class _8_2_RobotInGrid {
    private static class Point {
        private final int rowIdx;
        private final int colIdx;
        private final Point parentPoint;

        Point(int rowIdx, int colIdx, Point parentPoint) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.parentPoint = parentPoint;
        }

        public int getRowIdx() {
            return rowIdx;
        }

        public int getColIdx() {
            return colIdx;
        }

        public Point getParentPoint() {
            return parentPoint;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "rowIdx=" + rowIdx +
                    ", colIdx=" + colIdx +
                    ", parentPoint=" + parentPoint +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return rowIdx == point.rowIdx &&
                    colIdx == point.colIdx;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowIdx, colIdx, parentPoint);
        }
    }

    private static boolean[][] randomGrid(int row, int column) {
        boolean[][] grid = new boolean[row][column];

        for (int rowIdx = 0; rowIdx < row; rowIdx++) {
            for (int colIdx = 0; colIdx < column; colIdx++) {
                if (rowIdx == 0 && colIdx == 0) {
                    grid[rowIdx][colIdx] = true;
                    continue;
                }

                if (rowIdx == row - 1 && colIdx == column - 1) {
                    grid[rowIdx][colIdx] = true;
                    continue;
                }

                grid[rowIdx][colIdx] = new Random().nextBoolean();
            }
        }

        System.out.println(Arrays.deepToString(grid));

        return grid;
    }

    private static List<Point> getPathG(boolean[][] grid) {
        List<Point> points = new ArrayList<>();
        getPath(grid, grid.length - 1, grid[0].length - 1, points);

        return points;
    }

    private static boolean getPath(boolean[][] grid, int row, int col, List<Point> points) {
        if (row < 0 || col < 0 || !grid[row][col])
            return false;

        boolean isAtOrigin = row == 0 && col == 0;

        if (isAtOrigin || getPath(grid, row, col - 1, points) || getPath(grid, row - 1, col, points)) {
            points.add(new Point(row, col, null));
            return true;
        }
        return false;
    }

    private static Point getPathBfs(final boolean[][] grid) {
        LinkedList<Point> linkedList = new LinkedList<>();
        int numberOfRows = grid.length;
        int numberOfCols = grid[0].length;

        HashSet<Point> visitedPoints = new HashSet<>();

        linkedList.add(
                new Point(
                        numberOfRows - 1,
                        numberOfCols - 1,
                        null
                )
        );

        Predicate<Point> isAccessiblePoint = (point) -> grid[point.getRowIdx()][point.getColIdx()];

        Predicate<Point> isVisitedPoint = (point) -> !visitedPoints.contains(point);

        while (linkedList.size() > 0) {
            Point currentPoint = linkedList.removeFirst();

            visitedPoints.add(currentPoint);

            List<Point> neighbors = getNeighbors(currentPoint).stream()
                    .filter(isAccessiblePoint.and(isVisitedPoint))
                    .collect(Collectors.toList());

            for (Point neighbor : neighbors)
                if (neighbor.getRowIdx() == 0 && neighbor.getColIdx() == 0)
                    return neighbor;

            if (neighbors.size() > 0) {
                linkedList.addAll(neighbors);
            }
        }
        return null;
    }

    private static List<Point> getNeighbors(Point point) {
        List<Point> neighbors = new ArrayList<>();

        if (point.getRowIdx() - 1 >= 0)
            neighbors.add(
                    new Point(
                            point.getRowIdx() - 1,
                            point.getColIdx(),
                            point
                    )
            );

        if (point.getColIdx() - 1 >= 0)
            neighbors.add(
                    new Point(
                            point.getRowIdx(),
                            point.getColIdx() - 1,
                            point
                    )
            );

        return neighbors;
    }

    public static void main(String[] args) {
        System.out.println(
                getPathBfs(
                        randomGrid(5, 5)
                )
        );
//        11110
//        00110
//        11111
//        11010
//        11111

        /*
                                new boolean[][]{
                                {true, true, true, true, false},
                                {false, false, true, true, false},
                                {true, true, true, true, true},
                                {true, true, false, true, false},
                                {true, true, true, true, true}
                        }
         */
    }
}
