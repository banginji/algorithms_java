package com.abc.algorithms.misc;

import java.util.Arrays;

public class Staircase {
    /**
     *
     * number of allowed steps = {1, 2}
     *
     * n = 0;   [
     *              {0}
     *          ]
     * n = 1;   [
     *              {1, 0}
     *          ]
     * n = 2;   [
     *              {(2,) 1, 0},
     *              {(2,) 0}
     *          ]
     * n = 3;   [
     *              {(3,) 2, 1, 0}, {(3,) 2, 0},
     *              {(3,) 1, 0}
     *          ]
     * n = 4;   [
     *              {(4,) 3, 2, 1, 0}, {(4,) 3, 2, 0}, {(4,) 3, 1, 0},
     *              {(4,) 2, 1, 0}, {(4,) 2, 0}
     *          ]
     * n = k; f(k) = f(k-1) + f(k-2)
     *
     * --------------------------------------------
     *
     * number of allowed steps = {1, 3}
     *
     * n = 0;   [
     *              {0}
     *          ]
     * n = 1;   [
     *              {(1,) 0}
     *          ]
     * n = 2;   [
     *              {(2,) 1, 0}                         f(1) (f(-1) is not applicable)
     *          ]
     * n = 3;   [
     *              {(3,) 2, 1, 0},                     f(2)
     *              {(3,) 0}                            f(0)
     *          ]
     * n = 4;   [
     *              {(4,) 3, 2, 1, 0}, {(4,) 3, 0},     f(3)
     *              {(4,) 1, 0}                         f(1)
     *          ]
     *
     * n = k; f(k) = f(k-1) + f(k-3)
     *
     * ---------------------------------------
     *
     * General formula
     *
     * n = k; {p, q, r} = f(k) = f(k-p) + f(k-q) + f(r)
     *
     */

    static int numberOfWays(int numberOfSteps) {
        if (numberOfSteps == 0 || numberOfSteps == 1)
            return 1;

        return numberOfWays(numberOfSteps - 1) + numberOfWays(numberOfSteps - 2);
    }

    static int effNumberOfWays(int numberOfSteps) {
        if (numberOfSteps == 0 || numberOfSteps == 1)
            return 1;

        Integer[] numOfWays = new Integer[] {1, 1};

        for (int idx = 2; idx <= numberOfSteps; idx++) {
            int numOfWaysIdx = numOfWays[1] + numOfWays[0];
            numOfWays[0] = numOfWays[1];
            numOfWays[1] = numOfWaysIdx;
        }

        return numOfWays[1];
    }

    static int effKNumberOfWays(int numberOfSteps, Integer[] allowedSteps) {
        if (numberOfSteps == 0 || numberOfSteps == 1)
            return 1;

        Integer[] numWays = new Integer[numberOfSteps];
        numWays[0] = 1;
        numWays[1] = 1;

        for (int idx = 2; idx < numberOfSteps; idx++) {
            final int currentIdx = idx;
            numWays[idx] = Arrays.stream(allowedSteps)
                    .filter(num -> currentIdx - num >= 0)
                    .mapToInt(_idx -> numWays[currentIdx - _idx]).sum();
        }

        return numWays[numberOfSteps-1];
    }

    public static void main(String[] args) {
        System.out.println(numberOfWays(5));
        System.out.println(effNumberOfWays(3));

        System.out.println(effKNumberOfWays(20, new Integer[]{1, 3, 5}));
    }
}
