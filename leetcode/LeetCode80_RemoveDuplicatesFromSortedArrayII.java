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
        //1. 최대 2개의 중복 값을 식별하기 위해 두가지 변수를 생성한다.
        //1-1. 현재 체크중인 값
        //1-2. 현재 체크중인 값의 갯수
        //2. 중복제거가 끝난 array의 길이를 체크하기 위한 변수를 생성한다.
        //3. 반복문을 수행하면서 새로운 숫자가 나오면 체크중값을 변경한고 갯수도 초기화한다.
        //3-1. 같은 수가 반복될경우 갯수를 최대 2까지만 증가시키고 결과반환값도 증가시킨다.
        //3-2. 같은 수가 나왔을때 중복제거를 위해 nums에서 읽어나간 값을 중복제거 위치에 쌓는다, 쌓을 위치는 result값이 된다.
        //4. 체크중 갯수가 2를 초과하면 무시하면서 다음체크를 이어나간다.

        return 0;
    }
}