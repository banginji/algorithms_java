package com.abc.algorithms.chapter8;

import java.util.stream.IntStream;

public class _8_1_Staircase {
    private static int numberOfWaysSlow(int n) {
        if (n < 0)
            return 0;
        if (n == 0)
            return 1;
        return numberOfWaysSlow(n - 1) + numberOfWaysSlow(n - 2) + numberOfWaysSlow(n - 3);
    }

    private static int numberOfWaysFast(int steps, int[] allowedSteps) {
        int[] result = new int[steps + 1];
        result[0] = 1;

        for (int step = 1; step <= steps; step++) {
            for (int allowedStep : allowedSteps) {
                if (step < allowedStep)
                    continue;
                int prev = result[step - allowedStep];
                result[step] += prev;
            }
        }

        return result[steps];
    }

    private static int numberOfWaysFastWithStream(int steps, int[] allowedSteps) {
        int[] result = new int[steps + 1];
        result[0] = 1;

        for (int step = 1; step <= steps; step++) {
            final int currentStep = step;
            result[step] = IntStream.of(allowedSteps)
                    .filter(allowedStep -> currentStep - allowedStep >= 0)
                    .mapToObj(allowedStep -> result[currentStep - allowedStep])
                    .mapToInt(Integer::intValue)
                    .sum();
        }

        return result[steps];
    }

    public static void main(String[] args) {
        System.out.println(numberOfWaysSlow(3));

        System.out.println(numberOfWaysFast(3, new int[]{1, 2, 3}));

        System.out.println(numberOfWaysFastWithStream(3, new int[]{1, 2, 3}));
    }
}
