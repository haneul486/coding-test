package leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 2348. Number of Zero-Filled Subarrays
 *
 * Given an integer array nums, return the number of subarrays filled with 0.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 *   Input: nums = [1,3,0,0,2,0,0,4]
 *   Output: 6
 *   Explanation: There are 4 occurrences of [0] as a subarray, 2 occurrences of [0,0] as a subarray.
 *                There is no occurrence of a subarray with a size more than 2 filled with 0.
 *                Therefore, we return 6.
 *
 * Example 2:
 *   Input: nums = [0,0,0,2,0,0]
 *   Output: 9
 *   Explanation: There are 5 occurrences of [0] as a subarray, 3 occurrences of [0,0] as a subarray, and 1 occurrence of [0,0,0] as a subarray.
 *                Therefore, we return 9.
 *
 * Example 3:
 *   Input: nums = [2,10,2019]
 *   Output: 0
 *   Explanation: There is no subarray filled with 0. Therefore, we return 0.
 *
 * Constraints:
 *   - 1 <= nums.length <= 10^5
 *   - -10^9 <= nums[i] <= 10^9
 */
public class LeetCode2348_NumberOfZeroFilledSubarrays {

    public static void main(String[] args) {
        // Examples
        runTest(new int[]{1,3,0,0,2,0,0,4}, 6L);
        runTest(new int[]{0,0,0,2,0,0}, 9L);
        runTest(new int[]{2,10,2019}, 0L);

        // Additional tests
        runTest(new int[]{0}, 1L);
        runTest(new int[]{0,0}, 3L);                // [0],[0],[0,0]
        runTest(new int[]{0,1,0}, 2L);              // [0], [0]
        runTest(new int[]{1,0,0,0,1,0}, 7L);        // 3-length block: 6 + tail 1
        runTest(new int[]{}, 0L);                   // 빈 배열 Edge (LeetCode 입력은 >=1이지만 방어)
        runTest(new int[]{0,0,0,0}, 10L);           // 4*5/2
        runTest(new int[]{5,-1,0,0,-2,0}, 4L);      // 두 블록: 3 + 1
    }

    private static void runTest(int[] nums, long expected) {
        long actual = new Solution2348().zeroFilledSubarray(Arrays.copyOf(nums, nums.length));
        System.out.println("nums     : " + Arrays.toString(nums));
        System.out.println("expected : " + expected);
        System.out.println("actual   : " + actual);
        System.out.println(expected == actual ? "✅ PASSED\n" : "❌ FAILED\n");
    }
}

class Solution2348 {
    /**
     * Return the number of zero-filled subarrays in nums.
     */
    public long zeroFilledSubarray(int[] nums) {
        long result = 0;
        long cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                cnt++;
            }else{
                if(cnt != 0) {
                    result += (cnt * (cnt + 1) / 2);
                    cnt = 0;
                }
            }
        }
        if(cnt != 0) {
            result += (cnt * (cnt + 1) / 2);
            cnt = 0;
        }
        return result;
    }
}