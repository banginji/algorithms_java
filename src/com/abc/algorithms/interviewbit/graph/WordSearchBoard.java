package com.abc.algorithms.interviewbit.graph;

import java.util.ArrayList;
import java.util.List;

public class WordSearchBoard {
    private static boolean wordSearch(String word, String[] words) {
        char[][] board = createBoard(words);

        boolean[][] visited = new boolean[board.length][board[0].length];

        List<int[]> sources = findSource(word.charAt(0), board);

        for (int[] source : sources) {
            if (doesWordExist(word, 1, source[0], source[1], board, visited)) return true;
            System.out.println();
        }

        return false;
    }

    private static List<int[]> findSource(char firstChar, char[][] board) {
        List<int[]> sources = new ArrayList<>();

        int numRows = board.length;
        int numCols = board[0].length;

        for (int rowIdx = 0; rowIdx < numRows; rowIdx++)
            for (int colIdx = 0; colIdx < numCols; colIdx++)
                if (board[rowIdx][colIdx] == firstChar)
                    sources.add(new int[]{rowIdx, colIdx});

        return sources;
    }

    private static boolean doesWordExist(String word, int currentIdx, int row, int col, char[][] board, boolean[][] visited) {
        if (currentIdx == word.length() - 1)
            return true;

        // find neighbors
        // right neighbor
        if (row + 1 < board.length)
            if (!visited[row + 1][col] && board[row + 1][col] == word.charAt(currentIdx)) {
                System.out.print("{" + (row + 1) + ", " + col + "} ->");
                visited[row + 1][col] = true;
                if (doesWordExist(word, currentIdx + 1, row + 1, col, board, visited))
                    return true;
            }

        // left neighbor
        if (row - 1 >= 0)
            if (!visited[row - 1][col] && board[row - 1][col] == word.charAt(currentIdx)) {
                System.out.print("{" + (row - 1) + ", " + col + "} ->");
                visited[row - 1][col] = true;
                if (doesWordExist(word, currentIdx + 1, row - 1, col, board, visited))
                    return true;
            }

        // down neighbor
        if (col + 1 < board[0].length)
            if (!visited[row][col + 1] && board[row][col + 1] == word.charAt(currentIdx)) {
                System.out.print("{" + row + ", " + (col + 1) + "} ->");
                visited[row][col + 1] = true;
                if (doesWordExist(word, currentIdx + 1, row, col + 1, board, visited))
                    return true;
            }


        // up neighbor
        if (col - 1 >= 0)
            if (!visited[row][col - 1] && board[row][col - 1] == word.charAt(currentIdx)) {
                System.out.print("{" + row + ", " + (col - 1) + "} ->");
                visited[row][col - 1] = true;
                return doesWordExist(word, currentIdx + 1, row, col - 1, board, visited);
            }

        return false;
    }

    private static char[][] createBoard(String[] words) {
        char[][] board = new char[words.length][words[0].length()];

        for (int idx = 0; idx < words.length; idx++)
            board[idx] = words[idx].toCharArray();

        return board;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"ABCE", "SFCS", "ADEE"};

        System.out.println(wordSearch("ABCCED", words));
    }
}
