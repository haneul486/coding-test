package leetcode;

import java.util.*;

/**
 * LeetCode 39. Combination Sum
 *
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 *
 * The test cases are generated so that the number of unique combinations that sum up to target
 * is less than 150 combinations for the given input.
 *
 * Example 1:
 *   Input: candidates = [2,3,6,7], target = 7
 *   Output: [[2,2,3],[7]]
 *
 * Example 2:
 *   Input: candidates = [2,3,5], target = 8
 *   Output: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * Example 3:
 *   Input: candidates = [2], target = 1
 *   Output: []
 *
 * Constraints:
 *   - 1 <= candidates.length <= 30
 *   - 2 <= candidates[i] <= 40
 *   - All elements of candidates are distinct.
 *   - 1 <= target <= 40
 */
public class LeetCode39_CombinationSum {

    public static void main(String[] args) {
        runTest(new int[]{2,3,6,7}, 7, Arrays.asList(
                Arrays.asList(2,2,3),
                Arrays.asList(7)
        ));

        runTest(new int[]{2,3,5}, 8, Arrays.asList(
                Arrays.asList(2,2,2,2),
                Arrays.asList(2,3,3),
                Arrays.asList(3,5)
        ));

        runTest(new int[]{2}, 1, Collections.emptyList());

        runTest(new int[]{2}, 2, Arrays.asList(
                Arrays.asList(2)
        ));

        runTest(new int[]{2,3,5,7}, 10, Arrays.asList(
                Arrays.asList(2,2,2,2,2),
                Arrays.asList(2,2,3,3),
                Arrays.asList(2,3,5),
                Arrays.asList(3,7),
                Arrays.asList(5,5)
        ));
    }

    private static void runTest(int[] candidates, int target, List<List<Integer>> expected) {
        List<List<Integer>> actual = new Solution3().combinationSum(Arrays.copyOf(candidates, candidates.length), target);
        boolean passed = equalsAsMultisetOfSortedLists(expected, actual);

        System.out.println("candidates: " + Arrays.toString(candidates) + ", target: " + target);
        System.out.println("expected  : " + normalizeForPrint(expected));
        System.out.println("actual    : " + normalizeForPrint(actual));
        System.out.println(passed ? "✅ PASSED\n" : "❌ FAILED\n");
    }

    // Normalize each combination (sort inside), then sort the list of combinations,
    // and compare by string-set equality to ignore ordering differences.
    private static boolean equalsAsMultisetOfSortedLists(List<List<Integer>> a, List<List<Integer>> b) {
        Set<String> sa = toNormalizedStringSet(a);
        Set<String> sb = toNormalizedStringSet(b);
        return sa.equals(sb);
    }

    private static Set<String> toNormalizedStringSet(List<List<Integer>> list) {
        Set<String> set = new HashSet<>();
        for (List<Integer> comb : list) {
            List<Integer> copy = new ArrayList<>(comb);
            Collections.sort(copy);
            set.add(copy.toString());
        }
        return set;
    }

    private static String normalizeForPrint(List<List<Integer>> list) {
        List<List<Integer>> norm = new ArrayList<>();
        for (List<Integer> comb : list) {
            List<Integer> copy = new ArrayList<>(comb);
            Collections.sort(copy);
            norm.add(copy);
        }
        norm.sort(Comparator.comparing(Object::toString));
        return norm.toString();
    }
}

class Solution3 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(int[] a, int start, int target, List<Integer> path, List<List<Integer>> out) {
        if (target == 0) {                // 타겟 합 달성
            out.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < a.length; i++) {
            int v = a[i];
            if (v > target) break;        // 정렬되어 있으니 더 볼 필요 없음
            path.add(v);                  // v 사용
            dfs(a, i, target - v, path, out); // 같은 i를 다시 사용 가능(무제한 선택)
            path.remove(path.size() - 1); // 백트랙
        }
    }
}