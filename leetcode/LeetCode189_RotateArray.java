package leetcode;

import java.util.*;

/**
 * LeetCode 189. Rotate Array
 *
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 * You must do this in-place with O(1) extra space.
 *
 * Example 1:
 *   Input: nums = [1,2,3,4,5,6,7], k = 3
 *   Output: [5,6,7,1,2,3,4]
 *
 * Example 2:
 *   Input: nums = [-1,-100,3,99], k = 2
 *   Output: [3,99,-1,-100]
 *
 * Constraints:
 *   - 1 <= nums.length <= 10^5
 *   - -2^31 <= nums[i] <= 2^31 - 1
 *   - 0 <= k <= 10^5
 *
 * Follow up:
 *   - Try to come up with as many solutions as you can.
 *   - There are at least three different ways to solve this problem.
 */
public class LeetCode189_RotateArray {

    public static void main(String[] args) {
        // Basic examples from the problem
        runAndCheck(new int[]{1,2,3,4,5,6,7}, 3, new int[]{5,6,7,1,2,3,4});
        runAndCheck(new int[]{-1,-100,3,99}, 2, new int[]{3,99,-1,-100});

        // Edge cases
        runAndCheck(new int[]{1}, 0, new int[]{1});                 // k = 0
        runAndCheck(new int[]{1}, 100000, new int[]{1});            // n=1, large k
        runAndCheck(new int[]{1,2}, 1, new int[]{2,1});             // small n
        runAndCheck(new int[]{1,2,3}, 0, new int[]{1,2,3});         // no rotation
        runAndCheck(new int[]{1,2,3}, 3, new int[]{1,2,3});         // k == n
        runAndCheck(new int[]{1,2,3}, 4, new int[]{3,1,2});         // k > n
        runAndCheck(new int[]{-5,0,5}, 2, new int[]{0,5,-5});       // negatives, zeros

        // Randomized sanity checks
        for (int n = 1; n <= 30; n++) {
            int[] arr = randomArray(n, -10, 10, 42 + n);
            for (int k = 0; k < 2*n; k++) {
                int[] expected = rotateByCopy(arr, k);
                runAndCheck(arr, k, expected);
            }
        }

        System.out.println("All tests passed ✅");
    }
    // ---------------------- Test Harness Below ----------------------

    private static void runAndCheck(int[] nums, int k, int[] expected) {
        int[] input = Arrays.copyOf(nums, nums.length);
        rotate(input, k);
        if (!Arrays.equals(input, expected)) {
            throw new AssertionError(
                    "Failed for nums=" + Arrays.toString(nums) +
                            ", k=" + k +
                            "\nExpected: " + Arrays.toString(expected) +
                            "\nActual  : " + Arrays.toString(input)
            );
        }
    }

    // Helper: produce expected output using non-inplace copy (for verification only)
    private static int[] rotateByCopy(int[] nums, int k) {
        int n = nums.length;
        int[] out = new int[n];
        int shift = (n == 0) ? 0 : (k % n);
        for (int i = 0; i < n; i++) {
            out[(i + shift) % n] = nums[i];
        }
        return out;
    }

    private static int[] randomArray(int n, int lo, int hi, long seed) {
        Random r = new Random(seed);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = lo + r.nextInt(hi - lo + 1);
        }
        return a;
    }



    // ====== You implement here ======
    public static void rotate(int[] nums, int k) {
        if(nums.length == 1){
            return ;
        }
        System.out.println("args value:"+nums.length +":"+k);
        if(k > nums.length){
            k = k % nums.length;
        }
        System.out.println("% value:"+nums.length +":"+k);
        int [] result = new int[nums.length];
        for(int i = 0 ; i < k ; i++){
            result[i] = nums[nums.length-k+i];
            System.out.println(i+":"+nums[nums.length-k+i]);
        }
        for(int i = 0 ; i < nums.length - k ; i++){
            result[i+k] = nums[i];
            System.out.println((i+k)+":"+nums[i]);
        }
        for(int i = 0 ; i < nums.length ; i++){
            nums[i] = result[i];
        }
    }

}