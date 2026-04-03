package leetcode.inprogress;

import java.util.*;

/**
 * LeetCode 40. Combination Sum II
 * Source: https://leetcode.com/problems/combination-sum-ii/description/
 *
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *   - The solution set must not contain duplicate combinations.
 *   - You may return the combinations in any order.
 *
 * Example 1:
 *   Input: candidates = [10,1,2,7,6,1,5], target = 8
 *   Output: [[1,1,6],[1,2,5],[1,7],[2,6]]
 *
 * Example 2:
 *   Input: candidates = [2,5,2,1,2], target = 5
 *   Output: [[1,2,2],[5]]
 *
 * Constraints:
 *   - 1 <= candidates.length <= 100
 *   - 1 <= candidates[i] <= 50
 *   - 1 <= target <= 30
 */
public class LeetCode40_CombinationSumII {

    public static void main(String[] args) {
        runTest(
                new int[]{10, 1, 2, 7, 6, 1, 5},
                8,
                Arrays.asList(
                        Arrays.asList(1, 1, 6),
                        Arrays.asList(1, 2, 5),
                        Arrays.asList(1, 7),
                        Arrays.asList(2, 6)
                )
        );

        runTest(
                new int[]{2, 5, 2, 1, 2},
                5,
                Arrays.asList(
                        Arrays.asList(1, 2, 2),
                        Arrays.asList(5)
                )
        );

        runTest(
                new int[]{3, 4, 7},
                2,
                Collections.emptyList()
        );

        runTest(
                new int[]{1, 1, 1, 1},
                2,
                Arrays.asList(
                        Arrays.asList(1, 1)
                )
        );

        runTest(
                new int[]{1, 1, 1, 2, 2},
                4,
                Arrays.asList(
                        Arrays.asList(1, 1, 2),
                        Arrays.asList(2, 2)
                )
        );

        runTest(
                new int[]{1, 1, 2, 2, 5},
                5,
                Arrays.asList(
                        Arrays.asList(1, 2, 2),
                        Arrays.asList(5)
                )
        );
    }

    private static void runTest(int[] candidates, int target, List<List<Integer>> expected) {
        int[] inputCopy = Arrays.copyOf(candidates, candidates.length);

        try {
            List<List<Integer>> actual = new Solution40().combinationSum2(candidates, target);
            boolean passed = validateResult(inputCopy, target, expected, actual)
                    && Arrays.equals(candidates, inputCopy);

            System.out.println("candidates : " + Arrays.toString(inputCopy));
            System.out.println("target     : " + target);
            System.out.println("expected   : " + normalizeForPrint(expected));
            System.out.println("actual     : " + normalizeForPrint(actual));

            if (!Arrays.equals(candidates, inputCopy)) {
                System.out.println("! Input array was modified.");
            }

            System.out.println(passed ? "PASSED\n" : "FAILED\n");
        } catch (UnsupportedOperationException e) {
            System.out.println("candidates : " + Arrays.toString(inputCopy));
            System.out.println("target     : " + target);
            System.out.println("TODO       : Implement Solution40.combinationSum2()");
            System.out.println("FAILED (" + e.getMessage() + ")\n");
        }
    }

    private static boolean validateResult(int[] candidates, int target, List<List<Integer>> expected, List<List<Integer>> actual) {
        if (actual == null) {
            System.out.println("! Result is null.");
            return false;
        }

        Map<Integer, Integer> availableCounts = buildCounts(candidates);
        Set<String> actualSet = new HashSet<>();

        for (int i = 0; i < actual.size(); i++) {
            List<Integer> combination = actual.get(i);

            if (combination == null) {
                System.out.println("! Null combination found at index " + i + ".");
                return false;
            }

            int sum = 0;
            Map<Integer, Integer> usedCounts = new HashMap<>();
            for (int value : combination) {
                sum += value;
                usedCounts.put(value, usedCounts.getOrDefault(value, 0) + 1);
                if (usedCounts.get(value) > availableCounts.getOrDefault(value, 0)) {
                    System.out.println("! Combination uses a value too many times: " + combination);
                    return false;
                }
            }

            if (sum != target) {
                System.out.println("! Combination does not sum to target: " + combination);
                return false;
            }

            String key = normalizeCombination(combination).toString();
            if (!actualSet.add(key)) {
                System.out.println("! Duplicate combination found: " + combination);
                return false;
            }
        }

        Set<String> expectedSet = toNormalizedSet(expected);
        return expectedSet.equals(actualSet);
    }

    private static Map<Integer, Integer> buildCounts(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        return counts;
    }

    private static Set<String> toNormalizedSet(List<List<Integer>> combinations) {
        Set<String> set = new HashSet<>();
        for (List<Integer> combination : combinations) {
            set.add(normalizeCombination(combination).toString());
        }
        return set;
    }

    private static String normalizeForPrint(List<List<Integer>> combinations) {
        List<List<Integer>> normalized = new ArrayList<>();
        for (List<Integer> combination : combinations) {
            normalized.add(normalizeCombination(combination));
        }
        normalized.sort(Comparator.comparing(Object::toString));
        return normalized.toString();
    }

    private static List<Integer> normalizeCombination(List<Integer> combination) {
        List<Integer> copy = new ArrayList<>(combination);
        Collections.sort(copy);
        return copy;
    }
}

class Solution40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }

        return result;

    }
    private void recusive(int[] nums, List<List<Integer>> result, List<Integer> combination) {

    }

}

