package com.abc.algorithms.misc;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class ScratchPad {

    private static ArrayList<String> competitors(
            int numComp,
            int topNComp,
            List<String> comp,
            int numReviews,
            List<String> reviews
    ) {
        return reviews
                .stream()
                .collect(
                        Collectors.groupingBy(
                                rev -> getComp(rev, comp),
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .sorted(
                        Map.Entry.<String, Long>comparingByKey().reversed()
                                .thenComparing(Map.Entry.<String, Long>comparingByValue().reversed())
                )
                .map(Map.Entry::getKey)
                .collect(
                        Collectors.toCollection(ArrayList::new)
                );

//        List<Map.Entry<String, Long>> entries = new LinkedList<>(res.entrySet());
//        entries.sort(Map.Entry.<String, Long>comparingByValue().thenComparing(Map.Entry.comparingByKey()));
//
//        Collections.reverse(entries);

//        return entries.stream()
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static String getComp(String review, List<String> comp) {
        return comp.stream().filter(review::contains).findFirst().get();
    }

    BiFunction<String, List<String>, Optional<String>> getComp =
            (rev, comp) -> comp.stream().filter(rev::contains).findFirst();


    private static int getMinHrs(int r, int c, List<List<Integer>> grid) {
        int count = 0;
        while (checkGrid(grid)) {
            iteration(grid);
            count++;
        }
        return count;
    }

    private static void iteration(List<List<Integer>> grid) {
        for (int rowIdx = 0; rowIdx < grid.size(); rowIdx++) {
            for (int colIdx = 0; colIdx < grid.get(0).size(); colIdx++) {
                if (grid.get(rowIdx).get(colIdx) == 0)
                    continue;

                if (grid.get(rowIdx).get(colIdx - 1) == 0) {
                    List<Integer> list = grid.get(rowIdx);
                    list.set(colIdx - 1, 1);
                    grid.set(rowIdx, list);
                }

                if (colIdx + 1 < grid.get(0).size() && grid.get(rowIdx).get(colIdx + 1) == 0) {
                    List<Integer> list = grid.get(rowIdx);
                    list.set(colIdx + 1, 1);
                    grid.set(rowIdx, list);
                }
            }
        }
    }

    private static boolean checkGrid(List<List<Integer>> grid) {
        for (int rowIdx = 0; rowIdx < grid.size(); rowIdx++) {
            for (int colIdx = 0; colIdx < grid.get(0).size(); colIdx++) {
                if (grid.get(rowIdx).get(colIdx) == 0)
                    return true;
            }
        }
        return false;
    }

    private static void fibonacci(int[] elems, int n) {
        while (n > 0) {
            System.out.print(elems[0] + " -> ");
            int newElem = elems[0] + elems[1];
            elems[0] = elems[1];
            elems[1] = newElem;
            n--;
        }
    }

    public static void main(String[] args) {
//        int numComp = 6;
//        int topNComp = 1;
//        List<String> comp = Arrays.asList("newshop", "shopnow", "afashion");
//        int numReviews = 6;
//        List<String> reviews = Arrays.asList(
//                "newshop is providing",
//                "everyone is using newshop",
//                "som afashion is awesome"
//        );
//
//        System.out.println(competitors(numComp, topNComp, comp, numReviews, reviews));
//
//        System.out.println(getComp("newshop is great", comp));
//
//        List<List<Integer>> grid = Arrays.asList(
//                Arrays.asList(0, 0, 1),
//                Arrays.asList(0, 1, 1),
//                Arrays.asList(1, 1, 1)
//        );
//
//        System.out.println(getMinHrs(3, 3, grid));

        fibonacci(new int[]{0, 1}, 10);
    }
}
