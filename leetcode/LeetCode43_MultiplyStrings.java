package leetcode;

import java.math.BigDecimal;
import java.util.*;

/**
 * LeetCode 43. Multiply Strings
 *
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 *
 * Note:
 * - You must not use any built-in BigInteger library.
 * - You must not convert the inputs to integer directly.
 *
 * Example 1:
 *   Input: num1 = "2", num2 = "3"
 *   Output: "6"
 *
 * Example 2:
 *   Input: num1 = "123", num2 = "456"
 *   Output: "56088"
 *
 * Constraints:
 *   - 1 <= num1.length, num2.length <= 200
 *   - num1 and num2 consist of digits only.
 *   - Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
public class LeetCode43_MultiplyStrings {

    public static void main(String[] args) {
        runTest("2", "3", "6");
        runTest("123", "456", "56088");
        runTest("0", "0", "0");
        runTest("0", "99999", "0");
        runTest("99999", "0", "0");
        runTest("1", "9999999999999999999999999", "9999999999999999999999999");
        runTest("999", "999", "998001");
        runTest("1000", "1000", "1000000");
        runTest("10", "10", "100");
        runTest("500", "20", "10000");
        // Large-ish strings to check handling (no built-ins)
        runTest("3141592653589793238462643383279", "2718281828459045235360287471352",
                "8539734222673567065463550869543126215972716558040325068323208");
    }

    private static void runTest(String num1, String num2, String expected) {
        String actual = new Solution43().multiply(num1, num2);
        boolean pass = Objects.equals(expected, actual);
        System.out.println("num1 * num2 = " + num1 + " * " + num2);
        System.out.println("expected    = " + expected);
        System.out.println("actual      = " + actual);
        System.out.println(pass ? "✅ PASSED\n" : "❌ FAILED\n");
    }
}

class Solution43 {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        int m = num1.length(), n = num2.length();
        int[] res = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = mul + res[i + j + 1];
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num : res) {
            if (!(sb.length() == 0 && num == 0)) {
                sb.append(num);
            }
        }
        return sb.toString();
    }
}