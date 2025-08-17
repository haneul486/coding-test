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
        List<String> result = new ArrayList<>();
        //워드 하나하나 검증을 시작
        for (String word : words1) {
            checkWord(word, words2, result);
        }
        return result;
    }

    private void checkWord(String word, String[] words2, List<String> result) {
        int passCount = 0;//word2 검증결과 숫자를 저장할 flag length와 같으면 통과
        //"warrior" "wrr"
        for (int i = 0; i < words2.length; i++) {
            String t = new String(word);
            //t에서 words2[i]의 문자열을 하나씩 지워나가고
            char [] words2Array = words2[i].toCharArray();
            for (int j = 0; j < words2Array.length; j++) {
                t = t.replaceFirst(""+words2Array[j], "");
            }
            //최종 결과(t.length)가 word.length - words[i].length == t면 통과
            if(word.length() - words2[i].length() == t.length()){
                passCount++;
            }
        }
        if(passCount == words2.length){
            result.add(word);
        }
    }


}