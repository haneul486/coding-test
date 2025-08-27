package leetcode;

import java.util.*;

/**
 * LeetCode 3110. Score of a String
 *
 * Given a string s, return the sum of the absolute differences between the ASCII values
 * of adjacent characters.
 *
 * Example 1:
 *   Input: s = "hello"
 *   Output: 13
 *
 * Example 2:
 *   Input: s = "zaz"
 *   Output: 50
 *
 * Constraints:
 *   - 1 <= s.length <= 100
 *   - s consists of lowercase English letters.
 */
public class LeetCode3110_ScoreOfAString {

    public static void main(String[] args) {
        runTest("hello", 13);
        runTest("zaz", 50);
        runTest("a", 0);
        runTest("ab", Math.abs('a' - 'b'));
    }

    private static void runTest(String s, int expected) {
        int actual = new Solution3110().scoreOfString(s);
        System.out.println("s        : " + s);
        System.out.println("expected : " + expected);
        System.out.println("actual   : " + actual);
        System.out.println((actual == expected) ? "✅ PASSED\n" : "❌ FAILED\n");
    }
}

class Solution3110 {
    /**
     * Return the score of string s.
     */
    public int scoreOfString(String s) {
        if(s == null || s.length() < 2 )
            return 0;
        char[] chars = s.toCharArray();

        int result = 0;
        for (int i = 0; i < chars.length-1 ; i++) {
            result += Math.abs(chars[i] - chars[i+1]);
        }
        return result;
    }
}