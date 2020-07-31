package com.abc.algorithms.leetcode.graph;

import java.util.*;
import java.util.stream.Collectors;

public class _721_AccountsMerge {
    private static Collection<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailMap = new HashMap<>();
        Map<String, String> lookUp = new HashMap<>();

        for (List<String> account : accounts) {
            String connector = account.get(1);
            for (int idx = 1; idx < account.size(); idx++)
                if (emailMap.containsKey(account.get(idx))) {
                    connector = account.get(idx);
                    break;
                }

            lookUp.putIfAbsent(connector, account.get(0));

            for (int idx = 1; idx < account.size(); idx++)
                emailMap.putIfAbsent(account.get(idx), connector != null ? connector : account.get(1));
        }

        Map<String, List<String>> collect = emailMap.entrySet().stream()
                .collect(
                        Collectors.groupingBy(
                                Map.Entry::getValue,
                                Collectors.mapping(
                                        Map.Entry::getKey,
                                        Collectors.toList()
                                )
                        )
                );

        for (Map.Entry<String, List<String>> entry : collect.entrySet()) {
            if (lookUp.containsKey(entry.getKey())) {
                List<String> list = new ArrayList<>();
                list.add(lookUp.get(entry.getKey()));
                list.addAll(entry.getValue());
                entry.setValue(list);
            }
        }

        return collect.values();
    }

    // Not efficient. Check to see if this can be implemented using Union Find
    public static void main(String[] args) {
        List<List<String>> accounts = List.of(
                List.of("John", "johnsmith@mail.com", "john00@mail.com"),
                List.of("John", "johnnybravo@mail.com"),
                List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                List.of("Mary", "mary@mail.com")
        );

        System.out.println(accountsMerge(accounts));
    }
}
