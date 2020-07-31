package com.abc.algorithms.misc;

public class MyClass {

    /**
     * Searching for the highest floor that has maximum items using Binary Search algorithm
     * @param arr - array that contains the floor the items are on
     * @return - returns the highest floor with maximum number of items
     */
    public static int find(int[] arr) {
        // Vars instantiation and declaration
        int size = arr.length;

        int floor = 1;
        int ceiling = (size - 1);

        int lower_floor;
        int lower_ceiling;
        int upper_floor;
        int upper_ceiling;

        int items_in_lower_range;
        int distinct_possible_integers_in_lower_range;

        while ((floor < ceiling)) {
            // Calculate the lower and upper floors and ceilings based on the calculated midpoint
            int midpoint = floor + (ceiling - floor) / 2;

            lower_floor = floor;
            lower_ceiling = midpoint;

            upper_floor = midpoint + 1;
            upper_ceiling = ceiling;

            items_in_lower_range = 0;

            // Calculate the number of items in between the lower floor and ceiling
            for (int i = 0; i < size; i++) {
                if (arr[i] >= lower_floor && arr[i] <= lower_ceiling)
                    items_in_lower_range++;
            }

            // Find the number of integers in between the lower floor and ceiling
            distinct_possible_integers_in_lower_range = ((lower_ceiling - lower_floor) + 1);

            // If items found in lower range are greater then the next loop is iterated using
            // the lower floor and ceiling or else the upper floor and ceiling are used
            if ((items_in_lower_range > distinct_possible_integers_in_lower_range)) {
                floor = lower_floor;
                ceiling = lower_ceiling;
            } else {
                floor = upper_floor;
                ceiling = upper_ceiling;
            }
        }

        return floor;
    }

    public static void main(String args[]) {
        int[] intList = {6, 2, 3, 4, 1, 4, 4};

        int result = find(intList);

        System.out.println(result);
    }
}
