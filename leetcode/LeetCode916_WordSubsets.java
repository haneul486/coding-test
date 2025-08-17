package leetcode;

import java.util.*;

/**
 * LeetCode 916. Word Subsets
 *
 * You are given two string arrays words1 and words2.
 * A string b is a subset of string a if every letter in b occurs in a including multiplicity.
 * For example, "oo" is a subset of "oooo" but not a subset of "abc".
 *
 * A string a from words1 is universal if for every string b in words2, b is a subset of a.
 * Return an array of all the universal strings in words1. You may return the answer in any order.
 *
 * Example 1:
 *   Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
 *   Output: ["facebook","google","leetcode"]
 *
 * Example 2:
 *   Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
 *   Output: ["apple","google","leetcode"]
 *
 * Example 3:
 *   Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","oo"]
 *   Output: ["facebook","google"]
 *
 * Example 4:
 *   Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["lo","eo"]
 *   Output: ["google","leetcode"]
 *
 * Example 5:
 *   Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["ec","oc","ceo"]
 *   Output: ["facebook","leetcode"]
 *
 * Constraints:
 *   - 1 <= words1.length, words2.length <= 10^4
 *   - 1 <= words1[i].length, words2[i].length <= 10
 *   - words1[i] and words2[i] consist only of lowercase English letters.
 *   - All the strings of words1 are unique.
 */
public class LeetCode916_WordSubsets {

    public static void main(String[] args) {
        runTest(
                new String[]{"amazon","apple","facebook","google","leetcode"},
                new String[]{"e","o"},
                Arrays.asList("facebook","google","leetcode")
        );

        runTest(
                new String[]{"amazon","apple","facebook","google","leetcode"},
                new String[]{"l","e"},
                Arrays.asList("apple","google","leetcode")
        );

        runTest(
                new String[]{"amazon","apple","facebook","google","leetcode"},
                new String[]{"e","oo"},
                Arrays.asList("facebook","google")
        );

        runTest(
                new String[]{"amazon","apple","facebook","google","leetcode"},
                new String[]{"lo","eo"},
                Arrays.asList("google","leetcode")
        );

        runTest(
                new String[]{"amazon","apple","facebook","google","leetcode"},
                new String[]{"ec","oc","ceo"},
                Arrays.asList("facebook","leetcode")
        );

        // custom cases
        runTest(
                new String[]{"a","b","ab","ba","aba"},
                new String[]{"a"},
                Arrays.asList("a","ab","ba","aba")
        );

        runTest(
                new String[]{"aaaa","aa","b","aab"},
                new String[]{"aa"},
                Arrays.asList("aaaa","aa","aab")
        );
    }

    private static void runTest(String[] words1, String[] words2, List<String> expectedAnyOrder) {
        List<String> actual = new Solution916().wordSubsets(Arrays.copyOf(words1, words1.length),
                Arrays.copyOf(words2, words2.length));
        Set<String> exp = new HashSet<>(expectedAnyOrder);
        Set<String> act = new HashSet<>(actual);

        System.out.println("words1   : " + Arrays.toString(words1));
        System.out.println("words2   : " + Arrays.toString(words2));
        System.out.println("expected : " + exp);
        System.out.println("actual   : " + act);
        System.out.println(exp.equals(act) ? "✅ PASSED\n" : "❌ FAILED\n");
    }
}
class Solution916 {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        // 1) words2 압축: 알파벳별 요구 최소 개수 need[c]
        int[] need = new int[26];
        for (String b : words2) {
            int[] cnt = new int[26];
            for (int i = 0; i < b.length(); i++) {
                cnt[b.charAt(i) - 'a']++;
            }
            for (int c = 0; c < 26; c++) {
                need[c] = Math.max(need[c], cnt[c]);
            }
        }

        // 2) words1 각 단어가 need를 만족하는지 확인
        List<String> res = new ArrayList<>();
        outer:
        for (String a : words1) {
            int[] have = new int[26];
            for (int i = 0; i < a.length(); i++) {
                have[a.charAt(i) - 'a']++;
            }
            for (int c = 0; c < 26; c++) {
                if (have[c] < need[c]) {
                    continue outer; // 이 단어는 조건 불만족
                }
            }
            res.add(a);
        }
        return res;
    }
}