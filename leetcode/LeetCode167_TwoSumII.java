package leetcode;

import java.util.*;

/**
 * LeetCode 167. Two Sum II - Input Array Is Sorted
 *
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number.
 *
 * Return the indices of the two numbers, 1-indexed, as an integer array answer of size 2,
 * where 1 <= answer[0] < answer[1] <= numbers.length.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * Your solution must use only constant extra space.
 *
 * Example 1:
 *   Input: numbers = [2,7,11,15], target = 9
 *   Output: [1,2]
 *
 * Example 2:
 *   Input: numbers = [2,3,4], target = 6
 *   Output: [1,3]
 *
 * Example 3:
 *   Input: numbers = [-1,0], target = -1
 *   Output: [1,2]
 *
 * Constraints:
 *   - 2 <= numbers.length <= 3 * 10^4
 *   - -1000 <= numbers[i] <= 1000
 *   - numbers is sorted in non-decreasing order
 *   - -1000 <= target <= 1000
 *   - The tests are generated such that there is exactly one solution
 */
public class LeetCode167_TwoSumII {

    public static void main(String[] args) {
        // 기본 예시
        runAndCheck(new int[]{2,7,11,15}, 9, new int[]{1,2});
        runAndCheck(new int[]{2,3,4}, 6, new int[]{1,3});
        runAndCheck(new int[]{-1,0}, -1, new int[]{1,2});

        // 추가 테스트
        runAndCheck(new int[]{1,2,3,4,5}, 9, new int[]{4,5});
        runAndCheck(new int[]{1,2,3,4,4,9,56,90}, 8, new int[]{4,5});
        runAndCheck(new int[]{5,25,75}, 100, new int[]{2,3});

        System.out.println("All tests passed ✅");
    }

    // ====== You implement here ======
    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        for(int i = 0 ; i < numbers.length ; i++){
            for(int j = i+1 ; j < numbers.length ; j++){
                if(numbers[i]+numbers[j] == target){
                    result[0] = i+1;
                    result[1] = j+1;
                }else if(numbers[i]+numbers[j] > target){
                    break ;
                }else{
                    continue ;
                }
            }
        }
        return result;
    }

    // ---------------------- Test Harness Below ----------------------

    private static void runAndCheck(int[] numbers, int target, int[] expected) {
        int[] result = twoSum(Arrays.copyOf(numbers, numbers.length), target);
        if (!Arrays.equals(result, expected)) {
            throw new AssertionError(
                    "Failed for numbers=" + Arrays.toString(numbers) +
                            ", target=" + target +
                            "\nExpected: " + Arrays.toString(expected) +
                            "\nActual  : " + Arrays.toString(result)
            );
        }
    }
}