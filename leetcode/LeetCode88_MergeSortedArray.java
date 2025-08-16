package leetcode;

import java.util.*;

/**
 * LeetCode 88. Merge Sorted Array
 *
 * You are given two integer arrays nums1 and nums2, both sorted in non-decreasing order, and two integers m and n,
 * representing the number of elements in nums1 and nums2 respectively.
 *
 * Merge nums2 into nums1 as one sorted array.
 *
 * The final sorted array should NOT be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
 * and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 *
 * Example 1:
 *   Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 *   Output: [1,2,2,3,5,6]
 *
 * Example 2:
 *   Input: nums1 = [1], m = 1, nums2 = [], n = 0
 *   Output: [1]
 *
 * Example 3:
 *   Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 *   Output: [1]
 *
 * Constraints:
 *   - nums1.length == m + n
 *   - nums2.length == n
 *   - 0 <= m, n <= 200
 *   - 1 <= m + n <= 200
 *   - -10^9 <= nums1[i], nums2[i] <= 10^9
 *   - nums1 and nums2 are sorted in non-decreasing order.
 *
 * Follow up:
 *   Can you come up with an algorithm that runs in O(m + n) time and uses O(1) extra space?
 */

public class LeetCode88_MergeSortedArray {

    public static void main(String[] args) {
        runTest(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3, new int[]{1, 2, 2, 3, 5, 6});
        runTest(new int[]{1}, 1, new int[]{}, 0, new int[]{1});
        runTest(new int[]{0}, 0, new int[]{1}, 1, new int[]{1});
        runTest(new int[]{-3, -1, 0, 0, 0, 0}, 2, new int[]{-3, -2, -1, 2}, 4, new int[]{-3, -3, -2, -1, -1, 2});
        runTest(new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3, new int[]{1, 2, 3, 4, 5, 6});
        runTest(new int[]{0, 0, 0}, 0, new int[]{}, 0, new int[]{}); // edge case
    }

    private static void runTest(int[] nums1, int m, int[] nums2, int n, int[] expected) {
        new Solution().merge(nums1, m, nums2, n);
        int[] result = Arrays.copyOf(nums1, m + n);

        System.out.println("expected: " + Arrays.toString(expected));
        System.out.println("actual  : " + Arrays.toString(result));
        System.out.println(Arrays.equals(expected, result) ? "✅ PASSED\n" : "❌ FAILED\n");
    }
}

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int mi = 0;
        int ni = 0;
        int [] result = new int[m + n];
        for (int i = 0; i < m+n; i++) {
            if(mi < m && ni < n && nums1[mi] >= nums2[ni]){
                result[i] = nums2[ni];
                ni++;
            }else if(mi < m && ni < n && nums1[mi] < nums2[ni]){
                result[i] = nums1[mi];
                mi++;
            }else if(mi == m){
                result[i] = nums2[ni];
                ni++;
            }else{
                result[i] = nums1[mi];
                mi++;
            }
        }
        for (int i = 0; i < result.length; i++) {
            nums1[i] = result[i];
        }
    }

}