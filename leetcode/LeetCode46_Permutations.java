package leetcode;

import java.util.*;

/**
 * LeetCode 46. Permutations
 *
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 *
 * Example 1:
 *   Input: nums = [1,2,3]
 *   Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Example 2:
 *   Input: nums = [0,1]
 *   Output: [[0,1],[1,0]]
 *
 * Example 3:
 *   Input: nums = [1]
 *   Output: [[1]]
 *
 * Constraints:
 *   - 1 <= nums.length <= 6
 *   - -10 <= nums[i] <= 10
 *   - All the integers of nums are unique.
 */
public class LeetCode46_Permutations {

    public static void main(String[] args) {
        runAndValidate(new int[]{1,2,3});
        runAndValidate(new int[]{0,1});
        runAndValidate(new int[]{1});
        runAndValidate(new int[]{-1,0,1});
        runAndValidate(new int[]{2,5,9,11});
    }

    private static void runAndValidate(int[] nums) {
        System.out.println("nums      : " + Arrays.toString(nums));
        List<List<Integer>> res = new Solution46().permute(Arrays.copyOf(nums, nums.length));
        boolean ok = validatePermutations(nums, res);
        System.out.println("result    : " + res);
        System.out.println("size      : " + res.size() + " (expected " + factorial(nums.length) + ")");
        System.out.println(ok ? "✅ VALID\n" : "❌ INVALID\n");
    }

    /**
     * Validator for permutation results.
     * Checks:
     * 1) Input integers are unique (per problem constraints).
     * 2) Each permutation has the same length as input and is a permutation of input (same multiset).
     * 3) No duplicate permutations in output.
     * 4) Output size equals n! exactly.
     */
    private static boolean validatePermutations(int[] nums, List<List<Integer>> res) {
        // 1) Input uniqueness check (problem constraint)
        Set<Integer> inSet = new HashSet<>();
        for (int x : nums) {
            if (!inSet.add(x)) {
                System.out.println("! Input contains duplicate value: " + x);
                return false;
            }
        }

        int n = nums.length;
        int expectedSize = factorial(n);

        // 2) Build reference multiset for input
        Map<Integer, Integer> ref = new HashMap<>();
        for (int x : nums) ref.put(x, ref.getOrDefault(x, 0) + 1);

        // 3) Check duplicates in output
        Set<String> seen = new HashSet<>();
        for (int idx = 0; idx < res.size(); idx++) {
            List<Integer> p = res.get(idx);

            // length check
            if (p.size() != n) {
                System.out.println("! Permutation length mismatch at index " + idx + ": " + p);
                return false;
            }

            // element multiset check
            Map<Integer, Integer> cur = new HashMap<>();
            for (Integer v : p) cur.put(v, cur.getOrDefault(v, 0) + 1);

            if (!cur.equals(ref)) {
                System.out.println("! Not a valid permutation at index " + idx + ": " + p + " vs input " + ref);
                return false;
            }

            // duplicate permutation check
            String key = p.toString();
            if (!seen.add(key)) {
                System.out.println("! Duplicate permutation found: " + p);
                return false;
            }
        }

        // 4) size check equals n!
        if (res.size() != expectedSize) {
            System.out.println("! Size mismatch: got " + res.size() + ", expected " + expectedSize);
            return false;
        }

        return true;
    }

    private static int factorial(int n) {
        int f = 1;
        for (int i = 2; i <= n; i++) f *= i;
        return f;
    }
}

class Solution46 {

    public List<List<Integer>> permute(int[] nums) {
        // 시간복잡도: O(n * n!)  (생성해야 하는 순열의 수가 n!)
        // 공간복잡도: O(n) (재귀 스택 + 경로 저장; 결과 리스트 제외)
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> out) {
        if (path.size() == nums.length) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue;

            used[i] = true;
            path.add(nums[i]);
            backtrack(nums, used, path, out);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}