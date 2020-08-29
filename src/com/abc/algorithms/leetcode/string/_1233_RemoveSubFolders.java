package com.abc.algorithms.leetcode.string;

import java.util.*;
import java.util.stream.Collectors;

public class _1233_RemoveSubFolders {
    private static List<String> removeSubFolders(String[] folders) {
        Set<String> folderSet = new HashSet<>();

        List<String> folderList = Arrays.stream(folders).sorted(Comparator.comparing(String::length)).collect(Collectors.toList());

        for (String folder : folderList) {
            String[] subFolders = folder.substring(1).split("/");

            StringBuilder sb = new StringBuilder();

            boolean putInSet = true;

            for (String subFolder: subFolders) {
                sb.append("/").append(subFolder);
                if (folderSet.contains(sb.toString())) {
                    putInSet = false;
                    break;
                }
            }

            if (putInSet) folderSet.add(sb.toString());
        }

        return new ArrayList<>(folderSet);
    }

    public static void main(String[] args) {
        System.out.println(removeSubFolders(new String[]{"/a/b/c","/a/b/ca","/a/b/d"}));
    }
}
