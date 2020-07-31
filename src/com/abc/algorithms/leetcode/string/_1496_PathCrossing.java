package com.abc.algorithms.leetcode.string;

import java.util.*;

public class _1496_PathCrossing {
    private static boolean pathCrossing(String path) {
        Map<Character, Point> directions = Map.of(
                'N', new Point(0, 1),
                'S', new Point(0, -1),
                'E', new Point(1, 0),
                'W', new Point(-1, 0)
        );

        Point currentPoint = new Point(0, 0);

        Set<Point> traversedPoints = new HashSet<>();

        for (Character direction: path.toCharArray()) {
            if (traversedPoints.contains(currentPoint))
                return true;

            traversedPoints.add(new Point(currentPoint.getX(), currentPoint.getY()));

            Point newPoint = directions.get(direction);
            currentPoint.setX(currentPoint.getX() + newPoint.getX());
            currentPoint.setY(currentPoint.getY() + newPoint.getY());
        }

        return traversedPoints.contains(currentPoint);
    }

    public static void main(String[] args) {
        System.out.println(pathCrossing("SN"));
    }

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return this.x;
        }

        int getY() {
            return this.y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
