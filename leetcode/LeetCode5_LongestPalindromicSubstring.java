package leetcode;

import java.util.*;

/**
 * LeetCode 5. Longest Palindromic Substring
 *
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 *   Input: s = "babad"
 *   Output: "bab"
 *   Explanation: "aba" is also a valid answer.
 *
 * Example 2:
 *   Input: s = "cbbd"
 *   Output: "bb"
 *
 * Constraints:
 *   - 1 <= s.length <= 1000
 *   - s consists of only digits and English letters.
 */
public class LeetCode5_LongestPalindromicSubstring {

    public static void main(String[] args) {
        runTest("babad", "bab");   // "aba"도 정답 가능
        runTest("cbbd",  "bb");
        runTest("a",     "a");
        runTest("ac",    "a");     // "a" 또는 "c" 가능
        runTest("aaa",   "aaa");
        runTest("abccba", "abccba");
        runTest("forgeeksskeegfor", "geeksskeeg");
        runTest("abcd", "a");      // 단일 문자 중 하나
        runTest("abba", "abba");
    }

    private static void runTest(String s, String expectedOnePossible) {
        String actual = new Solution5().longestPalindrome(s);
        // 여러 정답 가능 케이스 대비: 실제 결과가 팰린드롬이면서 s에 존재하고 길이가 최장인지 기본 검증
        boolean ok = isPalindrome(actual) && s.contains(actual) && noLongerPalindromeExists(s, actual.length());
        System.out.println("s        : " + s);
        System.out.println("actual   : " + actual);
        System.out.println("valid?   : " + ok + (ok ? " ✅" : " ❌"));
        System.out.println();
    }

    private static boolean isPalindrome(String t) {
        int i = 0, j = t.length() - 1;
        while (i < j) if (t.charAt(i++) != t.charAt(j--)) return false;
        return true;
    }

    private static boolean noLongerPalindromeExists(String s, int len) {
        // s 안에 길이 len+1 이상의 팰린드롬이 존재하지 않는지 간단 체크
        for (int L = len + 1; L <= s.length(); L++) {
            for (int i = 0; i + L <= s.length(); i++) {
                if (isPalindrome(s.substring(i, i + L))) return false;
            }
        }
        return true;
    }
}

class Solution5 {
    /**
     * Return the longest palindromic substring of s.
     */
    public String longestPalindrome(String s) {
        /*

        runTest("babad", "bab");   // "aba"도 정답 가능
        runTest("cbbd",  "bb");
        runTest("a",     "a");
        runTest("ac",    "a");     // "a" 또는 "c" 가능
        runTest("aaa",   "aaa");
        runTest("abccba", "abccba");
        runTest("forgeeksskeegfor", "geeksskeeg");
        runTest("abcd", "a");      // 단일 문자 중 하나
        runTest("abba", "abba");
         */
        char [] chars = s.toCharArray();
        String result = chars[0] + "";
        int [] max = new int[chars.length];
        max[0]=1;
        for (int i = 1; i < chars.length; i++) {
            int count = 1;
            for(int j = 1; j < (chars.length / 2)+1; j++) {
                char left = '+';
                char right = '-';
                if(i-j > 0)
                    left = chars[i-j];
                if(i+j < chars.length)
                    right = chars[i+j];
                if(left == right){
                    max[i] = max[i]+1;
                }else{
                    break;
                }
            }
        }
        //TODO-hnkim: 가로체크 내용을 추가해야함.
        return result;
    }
}