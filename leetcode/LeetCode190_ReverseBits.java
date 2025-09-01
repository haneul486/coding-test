package leetcode;

import java.util.*;

/**
 * LeetCode 190. Reverse Bits
 *
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * Note:
 *  - In some languages, such as Java, there is no unsigned integer type.
 *    In this case, both input and output will be given as signed 32-bit integers,
 *    but the binary representation is the same, so the result is the unsigned value.
 *
 * Example 1:
 *   Input: n = 00000010100101000001111010011100
 *   Output:    964176192 (00111001011110000010100101000000)
 *
 * Example 2:
 *   Input: n = 11111111111111111111111111111101
 *   Output:   3221225471 (10111111111111111111111111111111)
 *
 * Constraints:
 *   - The input must be a binary string of length 32 or a 32-bit integer.
 *
 * Follow up:
 *   If this function is called many times, how would you optimize it?
 */
public class LeetCode190_ReverseBits {

    public static void main(String[] args) {
        // 기본 예시
        runAndCheck(0b00000010100101000001111010011100, 964176192);
        runAndCheck(0b11111111111111111111111111111101, 3221225471L);

        // 추가 테스트
        runAndCheck(0b00000000000000000000000000000000, 0L);            // 0
        runAndCheck(0b00000000000000000000000000000001, 2147483648L);   // MSB 이동
        runAndCheck(0b10000000000000000000000000000000, 1L);            // LSB 이동
        runAndCheck(0b11110000111100001111000011110000, 252645135L);    // 패턴 반전

        System.out.println("All tests passed ✅");
    }

    // ---------------------- Test Harness Below ----------------------

    private static void runAndCheck(int input, long expectedUnsigned) {
        int result;
        try {
            result = reverseBits(input);
        } catch (UnsupportedOperationException e) {
            System.out.println("Method not implemented yet. Skipping tests.");
            System.exit(0);
            return;
        }

        long actualUnsigned = Integer.toUnsignedLong(result);
        if (actualUnsigned != expectedUnsigned) {
            throw new AssertionError(
                    "Failed for input=" + Integer.toBinaryString(input) +
                            "\nExpected: " + expectedUnsigned +
                            "\nActual  : " + actualUnsigned
            );
        }
    }


    // ====== You implement here ======
    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;           // 왼쪽으로 한 칸 밀기
            result |= (n & 1);      // n의 마지막 비트를 결과에 추가
            n >>>= 1;               // n을 unsigned right shift
        }
        return result;
    }

}