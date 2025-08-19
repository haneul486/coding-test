package leetcode;

import java.util.*;

/**
 * LeetCode 79. Word Search
 *
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally
 * or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example 1:
 *   Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 *   Output: true
 *
 * Example 2:
 *   Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 *   Output: true
 *
 * Example 3:
 *   Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 *   Output: false
 *
 * Constraints:
 *   - m == board.length
 *   - n == board[i].length
 *   - 1 <= m, n <= 6
 *   - 1 <= word.length <= 15
 *   - board and word consist of only lowercase and uppercase English letters.
 */
public class LeetCode79_WordSearch {

    public static void main(String[] args) {
        // Example tests
        char[][] b1 = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        runTest(b1, "ABCCED", true);
        runTest(b1, "SEE",     true);
        runTest(b1, "ABCB",    false);

        // Additional tests
        char[][] b2 = {
                {'a'}
        };
        runTest(b2, "a", true);
        runTest(b2, "aa", false);

        char[][] b3 = {
                {'C','A','A'},
                {'A','A','A'},
                {'B','C','D'}
        };
        runTest(b3, "AAB", true);

        char[][] b4 = {
                {'A','B'},
                {'C','D'}
        };
        runTest(b4, "ACDB", true); // A -> C -> D -> B (valid path)

        char[][] b5 = {
                {'E','D','E'},
                {'B','E','E'},
                {'E','E','K'}
        };
        runTest(b5, "BEE", true);
        runTest(b5, "BEES", false);

        System.out.println("\nAll tests executed.");
    }

    private static void runTest(char[][] board, String word, boolean expected) {
        // Defensive copy so Solution79 can mutate visited if needed without affecting next tests
        char[][] copy = new char[board.length][];
        for (int i = 0; i < board.length; i++) copy[i] = Arrays.copyOf(board[i], board[i].length);

        boolean actual = new Solution79().exist(copy, word);
        System.out.println("board:\n" + prettyBoard(board));
        System.out.println("word  : " + word);
        System.out.println("expect: " + expected + "  actual: " + actual);
        System.out.println(actual == expected ? "✅ PASSED\n" : "❌ FAILED\n");
    }

    private static String prettyBoard(char[][] b) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : b) sb.append(Arrays.toString(row)).append('\n');
        return sb.toString();
    }
}

class Solution79 {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                word = word.replaceFirst(String.valueOf(board[i][j]), "");
                System.out.println(board[i][j] +":"+ word);
            }
        }
        return word.length() == 0 ? true : false;
    }
}