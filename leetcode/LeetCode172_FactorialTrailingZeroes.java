package leetcode;

/**
 * LeetCode 172. Factorial Trailing Zeroes
 *
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Example 1:
 *   Input: n = 3
 *   Output: 0
 *   Explanation: 3! = 6, no trailing zero.
 *
 * Example 2:
 *   Input: n = 5
 *   Output: 1
 *   Explanation: 5! = 120, one trailing zero.
 *
 * Example 3:
 *   Input: n = 25
 *   Output: 6
 *   Explanation: 25! has 6 trailing zeroes.
 *
 * Constraints:
 *   - 0 <= n <= 10^4
 *
 * Follow up: Could you write a solution that works in logarithmic time complexity?
 */
public class LeetCode172_FactorialTrailingZeroes {

    public static void main(String[] args) {
        runTest(3, 0);
        runTest(5, 1);
        runTest(10, 2);
        runTest(25, 6);
        runTest(100, 24);
    }

    private static void runTest(int n, int expected) {
        int actual = new Solution172().trailingZeroes(n);
        System.out.println("n = " + n);
        System.out.println("expected: " + expected + ", actual: " + actual);
        System.out.println((expected == actual ? "✅ PASSED" : "❌ FAILED") + "\n");
    }
}

class Solution172 {
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            n /= 5;
            count += n;
        }
        return count;
    }
}