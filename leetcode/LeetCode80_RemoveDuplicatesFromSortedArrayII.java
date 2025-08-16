package leetcode;

import java.util.*;

/**
 * LeetCode 80. Remove Duplicates from Sorted Array II
 *
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place
 * such that each unique element appears at most twice. The relative order of the elements
 * should be kept the same.
 *
 * Since it is impossible to change the length of the array in some languages, you must instead
 * have the result be placed in the first part of the array nums. More formally, if there are k
 * elements after removing the duplicates, then the first k elements of nums should hold the final result.
 * It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 *
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place
 * with O(1) extra memory.
 *
 * Example 1:
 *   Input: nums = [1,1,1,2,2,3]
 *   Output: 5, nums = [1,1,2,2,3,_]
 *
 * Example 2:
 *   Input: nums = [0,0,1,1,1,1,2,3,3]
 *   Output: 7, nums = [0,0,1,1,2,3,3,_,_]
 *
 * Constraints:
 *   - 1 <= nums.length <= 3 * 10^4
 *   - -10^4 <= nums[i] <= 10^4
 *   - nums is sorted in non-decreasing order.
 */
public class LeetCode80_RemoveDuplicatesFromSortedArrayII {

    public static void main(String[] args) {
        runTest(new int[]{1,1,1,2,2,3},          new int[]{1,1,2,2,3});
        runTest(new int[]{0,0,1,1,1,1,2,3,3},    new int[]{0,0,1,1,2,3,3});
        runTest(new int[]{1,1,2,2,2,3,3,3,3},    new int[]{1,1,2,2,3,3});
        runTest(new int[]{1},                    new int[]{1});
        runTest(new int[]{1,1},                  new int[]{1,1});
        runTest(new int[]{-1,-1,-1,-1,-1},       new int[]{-1,-1});
        runTest(new int[]{},                     new int[]{}); // edge (LeetCode won't give empty, but local check)
    }

    private static void runTest(int[] input, int[] expectedPrefix) {
        int[] nums = Arrays.copyOf(input, input.length);
        int k = new Solution2().removeDuplicates(nums);
        int[] actualPrefix = Arrays.copyOf(nums, k);

        System.out.println("input    : " + Arrays.toString(input));
        System.out.println("expected : " + Arrays.toString(expectedPrefix) + " (k=" + expectedPrefix.length + ")");
        System.out.println("actual   : " + Arrays.toString(actualPrefix) + " (k=" + k + ")");
        System.out.println(Arrays.equals(expectedPrefix, actualPrefix) && k == expectedPrefix.length ? "✅ PASSED\n" : "❌ FAILED\n");
    }
}

class Solution2 {
    public int removeDuplicates(int[] nums) {
        //1. 빈 배열이 들어왔을 경우 0을 리턴
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int curVal = nums[0];
        int curValCnt = 1;
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != curVal) {
                curVal = nums[i];
                curValCnt = 1;
                result++;
                removeVal(nums, result-1, i);
            }else{
                if(curValCnt == 1){
                    curValCnt++;
                    result++;
                    removeVal(nums, result-1, i);
                }
            }
        }

        return result;
    }
    public void removeVal(int[] nums, int setIndex, int getIndex) {
        nums[setIndex] = nums[getIndex];
    }
}