package leetcode;

import java.util.*;

/**
 * LeetCode 359. Logger Rate Limiter
 *
 * Design a logger system that receives a stream of messages along with their timestamps.
 * Each message should be printed at most once in every 10 seconds.
 *
 * Implement the Logger class:
 *   - Logger() Initializes the logger object.
 *   - boolean shouldPrintMessage(int timestamp, String message)
 *     Returns true if the message should be printed in the given timestamp, otherwise returns false.
 *     If this method returns false, the message will not be printed (i.e., rate-limited).
 *
 * Notes:
 *   - Timestamps are given in seconds granularity and are non-decreasing.
 *   - If a message was printed at time t, it may be printed again at time t + 10 or later.
 *
 * Example:
 *   Input:
 *     ["Logger", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage"]
 *     [[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11, "foo"]]
 *   Output:
 *     [null, true, true, false, false, false, true]
 */
public class LeetCode359_LoggerRateLimiter {

    public static void main(String[] args) {
        Logger logger = new Logger();

        // Basic sample from problem description
        expect(logger.shouldPrintMessage(1, "foo"), true, "t=1, foo");
        expect(logger.shouldPrintMessage(2, "bar"), true, "t=2, bar");
        expect(logger.shouldPrintMessage(3, "foo"), false, "t=3, foo (blocked before 11)");
        expect(logger.shouldPrintMessage(8, "bar"), false, "t=8, bar (blocked before 12)");
        expect(logger.shouldPrintMessage(10, "foo"), false, "t=10, foo (blocked before 11)");
        expect(logger.shouldPrintMessage(11, "foo"), true, "t=11, foo (allowed)");

        // Additional edge checks
        expect(logger.shouldPrintMessage(12, "bar"), true, "t=12, bar (allowed)");
        expect(logger.shouldPrintMessage(21, "foo"), true, "t=21, foo (allowed again)");
        expect(logger.shouldPrintMessage(21, "foo"), false, "t=21 again, foo (immediate duplicate)");
        expect(logger.shouldPrintMessage(31, "foo"), true, "t=31, foo (allowed)");

        System.out.println("All tests passed ✅");
    }

    private static void expect(boolean actual, boolean expected, String label) {
        if (actual != expected) {
            throw new AssertionError("Fail: " + label + " | expected=" + expected + ", actual=" + actual);
        }
    }

    static class Logger {
        private Map<String, Integer> msgMap;
        public Logger() {
            // TODO: initialize your data structure
            msgMap = new HashMap<>();
        }

        /**
         * Returns true if the message should be printed at the given timestamp, false otherwise.
         * timestamp: non-decreasing seconds
         */
        public boolean shouldPrintMessage(int timestamp, String message) {
            boolean result = false;
            if (msgMap.containsKey(message)) {
                int time = msgMap.get(message);
                if(timestamp >= time + 10) {
                    msgMap.put(message, timestamp);
                    result = true;
                }
            }else{
                msgMap.put(message, timestamp);
                result = true;
            }
            return result;
        }
    }
}