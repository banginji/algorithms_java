package com.abc.algorithms.misc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupContacts {
    private static List<List<String>> groupContacts(List<List<String>> contacts) {
        Map<String, List<String>> nameDepMap = new HashMap<>();
        Map<String, List<String>> emailDepMap = new HashMap<>();
        Map<String, List<String>> numberDepMap = new HashMap<>();

        /**
         * This map is to count the number of times a name appears in the contacts to duplicate
         * them later since union find on a map can be done only on a unique name. Of course there
         * might be a better way to do it to consider each contact different
         */
        Map<String, Integer> nameCountMap = new HashMap<>();

        for (List<String> contact : contacts) {
            nameDepMap.computeIfAbsent(contact.get(0), x -> new ArrayList<>()).add(contact.get(0));
            emailDepMap.computeIfAbsent(contact.get(1), x -> new ArrayList<>()).add(contact.get(0));
            numberDepMap.computeIfAbsent(contact.get(2), x -> new ArrayList<>()).add(contact.get(0));

            // Keep count of the number of time a name appears in the contacts
            nameCountMap.putIfAbsent(contact.get(0), 0);
            nameCountMap.computeIfPresent(contact.get(0), (key, value) -> value + 1);
        }

        List<List<String>> txnContacts = Stream
                .of(
                        nameDepMap.values(),
                        emailDepMap.values(),
                        numberDepMap.values()
                )
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        Map<String, String> axnMap = new HashMap<>();

        for (List<String> contact : txnContacts)
            for (String name : contact) {
                axnMap.putIfAbsent(name, name);
                union(contact.get(0), name, axnMap);
            }

        Map<String, List<String>> result = new HashMap<>();

        for (String name : axnMap.keySet()) {
            String axnName = find(name, axnMap);
            result.computeIfAbsent(axnName, x -> new ArrayList<>()).add(name);

            if (nameCountMap.get(name) > 1) {
                // Subtracting one time since the name has already been added to the map
                int duplicateCount = nameCountMap.get(name) - 1;
                while (duplicateCount-- > 0)
                    result.computeIfPresent(
                            axnName,
                            (k, v) -> {
                                v.add(name);
                                return v;
                            }
                    );
            }
        }

        return new ArrayList<>(result.values());
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

    public static void main(String[] args) {
        System.out.println(
                groupContacts(
                        List.of(
                                List.of("Tom", "tom@fb.com", "987654321"),
                                List.of("Sam", "sam@fb.com", "187654321"),
                                List.of("Tom", "rom@fb.com", "123456789"),
                                List.of("Harry", "harry@fb.com", "234567890"),
                                List.of("Jack", "jack@fb.com", "987654321"),
                                List.of("Sam", "tom@fb.com", "16768739")
                        )
                )
        );
    }
}
