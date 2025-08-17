package leetcode;

import java.util.*;

/**
 * LeetCode 416. Partition Equal Subset Sum
 *
 * Given an integer array nums, return true if you can partition the array into two subsets
 * such that the sum of the elements in both subsets is equal. Otherwise, return false.
 *
 * Example 1:
 *   Input: nums = [1,5,11,5]
 *   Output: true
 *   Explanation: The array can be partitioned as [1,5,5] and [11].
 *
 * Example 2:
 *   Input: nums = [1,2,3,5]
 *   Output: false
 *   Explanation: The array cannot be partitioned into equal sum subsets.
 *
 * Constraints:
 *   - 1 <= nums.length <= 200
 *   - 1 <= nums[i] <= 100
 */
public class LeetCode416_PartitionEqualSubsetSum {

    public static void main(String[] args) {
        runTest(new int[]{1,5,11,5}, true);
        runTest(new int[]{1,2,3,5}, false);

        // Additional checks
        runTest(new int[]{2,2,3,5}, false);
        runTest(new int[]{2,2,1,1}, true);
        runTest(new int[]{3,3,3,4,5}, true);
        runTest(new int[]{1}, false);
        runTest(new int[]{100,100}, true);
        runTest(new int[]{2,2,2,2,2,2}, true);
        runTest(new int[]{9,5}, false);
    }

    private static void runTest(int[] nums, boolean expected) {
        boolean actual = new Solution416().canPartition(Arrays.copyOf(nums, nums.length));
        System.out.println("nums     : " + Arrays.toString(nums));
        System.out.println("expected : " + expected);
        System.out.println("actual   : " + actual);
        System.out.println(actual == expected ? "✅ PASSED\n" : "❌ FAILED\n");
    }
}

class Solution416 {
    // 집합(Set) 기반으로 "가능한 합"들을 확장해가는 방식
    public static boolean canPartition(int[] nums) {
        int sum = 0;

        for (int x : nums)
            sum += x;

        if (sum % 2 != 0)
            return false;  // 합이 홀수면 불가능

        int target = sum / 2;

        Set<Integer> possible = new HashSet<>();
        possible.add(0); // 시작은 합 0만 가능
        for (int num : nums) {
            Set<Integer> newSums = new HashSet<>();
            for (int s : possible) {
                if (s + num == target) return true;
                if (s + num < target) newSums.add(s + num);
            }
            possible.addAll(newSums);
        }
        return possible.contains(target);
    }
}