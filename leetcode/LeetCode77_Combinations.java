package leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 77. Combinations
 *
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 * You may return the answer in any order.
 *
 * Example 1:
 *   Input: n = 4, k = 2
 *   Output: [[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
 *
 * Example 2:
 *   Input: n = 1, k = 1
 *   Output: [[1]]
 *
 * Constraints:
 *   - 1 <= n <= 20
 *   - 1 <= k <= n
 */
public class LeetCode77_Combinations {

    public static void main(String[] args) {
        runAndValidate(4, 2);
        runAndValidate(1, 1);
        runAndValidate(5, 3);
        runAndValidate(6, 1);
        runAndValidate(6, 6);
        runAndValidate(7, 4);
    }

    private static void runAndValidate(int n, int k) {
        List<List<Integer>> res = new Solution77().combine(n, k);

        boolean ok = validateCombinations(n, k, res);
        System.out.println("n=" + n + ", k=" + k);
        System.out.println("result size : " + (res == null ? 0 : res.size()) +
                " (expected " + nCk(n, k) + ")");
        System.out.println("result      : " + res);
        System.out.println(ok ? "✅ VALID\n" : "❌ INVALID\n");
    }

    /**
     * Validator for combinations:
     *  - Each list has size k
     *  - Elements are within [1..n]
     *  - Elements are strictly increasing (no duplicates inside a combo)
     *  - No duplicate combos in output
     *  - Total count equals C(n,k)
     */
    private static boolean validateCombinations(int n, int k, List<List<Integer>> res) {
        if (res == null) {
            System.out.println("! result is null");
            return false;
        }

        // Track duplicates
        Set<String> seen = new HashSet<>();
        for (int idx = 0; idx < res.size(); idx++) {
            List<Integer> comb = res.get(idx);

            // length check
            if (comb.size() != k) {
                System.out.println("! wrong size at index " + idx + ": " + comb);
                return false;
            }

            // range + strictly increasing
            int prev = 0;
            for (int j = 0; j < comb.size(); j++) {
                int v = comb.get(j);
                if (v < 1 || v > n) {
                    System.out.println("! out of range value at index " + idx + ": " + comb);
                    return false;
                }
                if (j > 0 && v <= prev) {
                    System.out.println("! not strictly increasing at index " + idx + ": " + comb);
                    return false;
                }
                prev = v;
            }

            // duplicate combo check
            String key = comb.toString();
            if (!seen.add(key)) {
                System.out.println("! duplicate combo: " + comb);
                return false;
            }
        }

        // size check
        long expected = nCk(n, k);
        if (res.size() != expected) {
            System.out.println("! size mismatch: got " + res.size() + ", expected " + expected);
            return false;
        }

        return true;
    }

    // Compute nCk safely in long (n <= 20 so fits)
    private static long nCk(int n, int k) {
        if (k < 0 || k > n) return 0;
        k = Math.min(k, n - k);
        long num = 1, den = 1;
        for (int i = 1; i <= k; i++) {
            num *= (n - k + i);
            den *= i;
        }
        return num / den;
    }
}
class Solution77 {
    public List<List<Integer>> combine(int n, int k) {
        int [] nums = new int[n];
        for(int i = 0 ; i < n ; i++){
            nums[i] = i+1;
        }
        List<List<Integer>> list = new ArrayList<>();
        recursive(nums, 0, new int[nums.length], 0, list, k);
        return list;

    }
    public void recursive(int[] nums, int index, int [] cur, int curIndex, List<List<Integer>> list, int k){
        if(index == nums.length){
            if(k == curIndex){
                int [] arr = Arrays.copyOf(cur, curIndex);
                List<Integer> result = Arrays.stream(arr).boxed().collect(Collectors.toList());
                list.add(result);
            }
            return ;
        }
        recursive(nums, index+1, cur, curIndex, list, k);
        cur[curIndex] = nums[index];
        recursive(nums, index+1, cur, curIndex+1, list, k);
    }
}