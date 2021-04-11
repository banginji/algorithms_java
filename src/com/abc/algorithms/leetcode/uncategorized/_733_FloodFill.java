package com.abc.algorithms.leetcode.uncategorized;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class _733_FloodFill {
    private static class Point {
        private final int rowIdx;
        private final int colIdx;

        Point(Builder builder) {
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

            public Point build() {
                return new Point(this);
            }
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
            return Objects.hash(rowIdx, colIdx);
        }
    }

    private static int[][] floodFillDfs(int[][] image, int sr, int sc, int newColor) {
        return floodFillDfs(image, sr, sc, image[sr][sc], newColor);
    }

    private static int[][] floodFillDfs(int[][] image, int sr, int sc, int startColor, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length)
            return image;

        if (image[sr][sc] == startColor) {
            image[sr][sc] = newColor;
            floodFillDfs(image, sr - 1, sc, startColor, newColor);
            floodFillDfs(image, sr + 1, sc, startColor, newColor);
            floodFillDfs(image, sr, sc - 1, startColor, newColor);
            floodFillDfs(image, sr, sc + 1, startColor, newColor);
        }

        return image;
    }

    private static int[][] floodFillBfs(int[][] image, int sr, int sc, int newColor) {
        LinkedList<Point> queue = new LinkedList<>();

        int srcColor = image[sr][sc];

        Set<Point> visitedPoints = new HashSet<>();

        Predicate<Point> isNotVisitedPoint = point -> !visitedPoints.contains(point);

        Predicate<Point> canBeFilled = point -> image[point.getRowIdx()][point.getColIdx()] == srcColor;

        queue.add(
                Point.builder()
                        .setRowIdx(sr)
                        .setColIdx(sc)
                        .build()
        );

        image[sr][sc] = newColor;

        while (queue.size() > 0) {
            Point currentPoint = queue.pop();

            visitedPoints.add(currentPoint);

            List<Point> neighbors = getNeighbors(currentPoint, image.length, image[0].length);

            List<Point> feasiblePoints = neighbors.stream()
                    .filter(isNotVisitedPoint.and(canBeFilled))
                    .collect(Collectors.toList());

            feasiblePoints.forEach(point -> image[point.getRowIdx()][point.getColIdx()] = newColor);

            if (feasiblePoints.size() > 0)
                queue.addAll(feasiblePoints);
        }

        return image;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.deepToString(
                        floodFillBfs(
                                new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 0, 0, 2
                        )
                )
        );
    }

    private static List<Point> getNeighbors(Point point, int rowLength, int colLength) {
        List<Point> neighbors = new ArrayList<>();

        if (point.getRowIdx() + 1 <= rowLength - 1)
            neighbors.add(
                    Point.builder()
                            .setRowIdx(point.getRowIdx() + 1)
                            .setColIdx(point.getColIdx())
                            .build()
            );

        if (point.getRowIdx() - 1 >= 0)
            neighbors.add(
                    Point.builder()
                            .setRowIdx(point.getRowIdx() - 1)
                            .setColIdx(point.getColIdx())
                            .build()
            );

        if (point.getColIdx() + 1 <= colLength - 1)
            neighbors.add(
                    Point.builder()
                            .setRowIdx(point.getRowIdx())
                            .setColIdx(point.getColIdx() + 1)
                            .build()
            );

        if (point.getColIdx() - 1 >= 0)
            neighbors.add(
                    Point.builder()
                            .setRowIdx(point.getRowIdx())
                            .setColIdx(point.getColIdx() - 1)
                            .build()
            );

        return neighbors;
    }
}
