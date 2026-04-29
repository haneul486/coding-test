package leetcode.inprogress;

import java.util.*;

/**
 * LeetCode 3169. Count Days Without Meetings
 * Source: https://leetcode.com/problems/count-days-without-meetings/description/
 *
 * 1일부터 days일까지 근무 가능한 날이 있다.
 * meetings[i] = [start, end]는 해당 구간에 회의가 잡혀 있음을 의미한다.
 * 회의 구간들이 겹칠 수 있을 때, 회의가 전혀 없는 날짜 수를 구한다.
 *
 * 주의:
 *   - 입력 회의는 정렬되어 있지 않을 수 있다.
 *   - 겹치거나 맞닿는 구간은 하나로 합쳐서 생각해야 한다.
 *   - 이 파일은 연습용 스켈레톤이다.
 *   - 테스트만 제공하고 정답 구현은 비워둔다.
 */
public class LeetCode3169_CountDaysWithoutMeetings {

    public static void main(String[] args) {
        runTest(10, new int[][]{{5, 7}, {1, 3}, {9, 10}}, 2);
        runTest(5, new int[][]{{2, 4}, {1, 3}}, 1);
        runTest(6, new int[][]{{1, 6}}, 0);

        runTest(12, new int[][]{{8, 10}, {2, 4}}, 6);
        runTest(15, new int[][]{{1, 2}, {4, 8}, {6, 10}, {12, 12}}, 4);
        runTest(7, new int[][]{{2, 2}, {4, 4}, {6, 6}}, 4);
        runTest(20, new int[][]{{3, 5}, {1, 10}, {2, 6}, {15, 18}}, 6);
    }

    private static void runTest(int days, int[][] meetings, int expected) {
        int[][] meetingsCopy = deepCopy(meetings);

        try {
            int actual = new Solution3169().countDays(days, meetings);
            boolean passed = actual == expected && deepEquals(meetings, meetingsCopy);

            System.out.println("days     : " + days);
            System.out.println("meetings : " + Arrays.deepToString(meetingsCopy));
            System.out.println("expected : " + expected);
            System.out.println("actual   : " + actual);

            if (!deepEquals(meetings, meetingsCopy)) {
                System.out.println("! Input meetings array was modified.");
            }

            System.out.println(passed ? "PASSED\n" : "FAILED\n");
        } catch (UnsupportedOperationException e) {
            System.out.println("days     : " + days);
            System.out.println("meetings : " + Arrays.deepToString(meetingsCopy));
            System.out.println("TODO     : Solution3169.countDays(int days, int[][] meetings)를 직접 구현하세요.");
            System.out.println("FAILED (" + e.getMessage() + ")\n");
        }
    }

    private static int[][] deepCopy(int[][] arr) {
        int[][] copy = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return copy;
    }

    private static boolean deepEquals(int[][] a, int[][] b) {
        if (a == null || b == null || a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }
}

class Solution3169 {
    public int countDays(int days, int[][] meetings) {
        throw new UnsupportedOperationException("Write your own solution here.");
    }
}
