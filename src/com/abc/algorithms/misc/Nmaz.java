package com.abc.algorithms.misc;

import java.util.*;
import java.util.stream.Collectors;

public class Nmaz {
    private static List<String> subStrs(String inputString, int num) {
        if (inputString.length() == 0)
            return Collections.emptyList();

        Map<Character, Integer> charMap = new HashMap<>();

        for (int idx = 0; idx < num; idx++) {
            char charAtIdx = inputString.charAt(idx);

            charMap.putIfAbsent(charAtIdx, 0);
            charMap.computeIfPresent(charAtIdx, (key, value) -> value + 1);
        }

        List<String> result = new ArrayList<>();

        if (checkChars(charMap, num))
            result.add(inputString.substring(0, num));

        for (int idx = num; idx < inputString.length(); idx++) {
            char charAtIdx = inputString.charAt(idx);
            char charToRemove = inputString.charAt(idx - num);

            System.out.println(inputString.substring(idx + 1 - num, idx + 1));

            charMap.computeIfPresent(charToRemove, (key, value) -> value - 1);
            if (charMap.get(charToRemove) == 0)
                charMap.remove(charToRemove);

            charMap.putIfAbsent(charAtIdx, 0);
            charMap.computeIfPresent(charAtIdx, (key, value) -> value + 1);

            if (checkChars(charMap, num))
                result.add(inputString.substring(idx + 1 - num, idx + 1));
        }

        return result;
    }

    private static boolean checkChars(Map<Character, Integer> charMap, int num) {
        return charMap.values().stream().mapToInt(i -> i).sum() == num && (long) charMap.keySet().size() == num - 1;
    }

    private static List<String> s(String s, int num) {
        String[] strings = s.split("\\s+");

        List<String> result = new ArrayList<>();

        for (String str: strings)
            result.addAll(subStrLessThanK(str, num));

        return result;
    }

    private static List<String> subStrLessThanK(String inputString, int num) {

        List<String> result = new ArrayList<>();

        for (int idx = num; idx <= inputString.length(); idx++) {
            String subStr = inputString.substring(idx - num, idx);
            if (checkSubStr(subStr.toLowerCase()))
                result.add(subStr);
        }

        return result;
    }

    private static boolean checkSubStr(String str) {
        Set<Character> set = new HashSet<>();

        for (int idx = 0; idx < str.length(); idx++)
            set.add(str.charAt(idx));

        return set.size() == str.length() - 1;
    }

    private static List<String> graph(List<PairString> itemAssociation) {
        Map<String, String> itemAssociationMap = new HashMap<>();

        for (PairString ia : itemAssociation) {
            itemAssociationMap.putIfAbsent(ia.first(), ia.first());
            itemAssociationMap.putIfAbsent(ia.second(), ia.first());
            union(ia.first(), ia.second(), itemAssociationMap);
        }

        Map<String, List<String>> result = new HashMap<>();

        for (String email : itemAssociationMap.keySet().stream().sorted().collect(Collectors.toList())) {
            String parentEmail = find(email, itemAssociationMap);
            result.computeIfAbsent(parentEmail, x -> new ArrayList<>()).add(email);
        }

        return result.values().stream().max(Comparator.comparing(List::size)).orElse(Collections.emptyList());
    }

    private static void union(String itemOne, String itemTwo, Map<String, String> itemAssociationMap) {
        String parentOne = find(itemOne, itemAssociationMap);
        String parentTwo = find(itemTwo, itemAssociationMap);

        if (!parentOne.equals(parentTwo))
            itemAssociationMap.put(parentOne, parentTwo);
    }

    private static String find(String item, Map<String, String> itemAssociationMap) {
        return itemAssociationMap.get(item).equals(item) ? item : find(itemAssociationMap.get(item), itemAssociationMap);
    }

    record PairString(String first, String second) {}

    public static void main(String[] args) {
        System.out.println(s("demo Cracy", 5));
        System.out.println(graph(
                List.of(
                        new PairString("item1", "item2"),
                        new PairString("item3", "item4"),
                        new PairString("item4", "item5")
                )
        ));
    }
}
