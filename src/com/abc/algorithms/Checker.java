package com.abc.algorithms;

import java.util.HashMap;

public class Checker {
    public void ls(String s, int k) {

    }

    private int ls(int idx, char[] ca, int k) {
//        if (idx >= ca.length) 0;
//        return Math.max(
//                ls(idx + 1, ca, k),
//
//        )
        return 0;
    }

    public static String decodeMessage(String key, String message) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(' ', ' ');
        int itr = 0;
        for (Character c : key.toCharArray()) {
            if (!map.containsKey(c))
                map.put(c, (char)(itr++ + 1 + 64));
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : message.toCharArray()) sb.append(map.get(c));
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = String.valueOf((char) (65));
        System.out.println(s);

        decodeMessage("the quick brown fox jumps over the lazy dog", "vkbs bs t suepuv");
    }
}
