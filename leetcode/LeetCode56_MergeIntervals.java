package leetcode;

import java.util.*;

/**
 * LeetCode 56. Merge Intervals
 *
 * Given an array of intervals where intervals[i] = [start_i, end_i],
 * merge all overlapping intervals, and return an array of the non-overlapping
 * intervals that cover all the intervals in the input.
 *
 * Example 1:
 *   Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 *   Output: [[1,6],[8,10],[15,18]]
 *
 * Example 2:
 *   Input: intervals = [[1,4],[4,5]]
 *   Output: [[1,5]]
 *
 * Constraints:
 *   - 1 <= intervals.length <= 10^4
 *   - intervals[i].length == 2
 *   - 0 <= start_i <= end_i <= 10^4
 */
public class LeetCode56_MergeIntervals {

    public static void main(String[] args) {
        // 기본 예시
        runAndCheck(new int[][]{{1,3},{2,6},{8,10},{15,18}},
                new int[][]{{1,6},{8,10},{15,18}});
        runAndCheck(new int[][]{{1,4},{4,5}},
                new int[][]{{1,5}});

        // 추가/경계 케이스
        runAndCheck(new int[][]{{1,4},{0,2},{3,5}},
                new int[][]{{0,5}});
        runAndCheck(new int[][]{{1,4}},
                new int[][]{{1,4}});
        runAndCheck(new int[][]{{1,4},{5,6}},
                new int[][]{{1,4},{5,6}});
        runAndCheck(new int[][]{{1,4},{0,0}},
                new int[][]{{0,0},{1,4}});
        runAndCheck(new int[][]{{2,3},{4,5},{6,7},{8,9},{1,10}},
                new int[][]{{1,10}});
        runAndCheck(new int[][]{{1,4},{2,3}},
                new int[][]{{1,4}});

        System.out.println("All tests passed ✅");
    }


    // ====== You implement here ======
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> mergedIntervals = new ArrayList();
        //System.out.println(Arrays.deepToString(intervals));
        int[] firstInterval = new int [2];
        firstInterval[0] = intervals[0][0];
        firstInterval[1] = intervals[0][1];
        //System.out.println(Arrays.toString(firstInterval));
        mergedIntervals.add(firstInterval);
        for (int i = 1; i < intervals.length; i++) {
            int [] lastItem = mergedIntervals.get(mergedIntervals.size() - 1);
            int[] curItem = intervals[i];
            if(curItem[0] <= lastItem[1] && curItem[1] >= lastItem[0] ){
                lastItem[1] = Math.max(lastItem[1], curItem[1]);
                mergedIntervals.set(mergedIntervals.size()-1, lastItem);
            }else{
                mergedIntervals.add(curItem);
            }
        }
        int [][] result = new int[mergedIntervals.size()][2];
        for (int i = 0; i < mergedIntervals.size(); i++) {
            result[i] = mergedIntervals.get(i);
        }
        return result;
    }
    // ---------------------- Test Harness Below ----------------------

    private static void runAndCheck(int[][] input, int[][] expected) {
        int[][] inCopy = deepCopy(input);
        int[][] actual;
        try {
            actual = merge(inCopy);
        } catch (UnsupportedOperationException e) {
            System.out.println("Method not implemented yet. Skipping tests.");
            System.exit(0);
            return;
        }

        sortIntervals(actual);
        sortIntervals(expected);

        if (!deepEquals(actual, expected)) {
            throw new AssertionError(
                    "Failed for input=" + Arrays.deepToString(input) +
                            "\nExpected: " + Arrays.deepToString(expected) +
                            "\nActual  : " + Arrays.deepToString(actual)
            );
        }
    }

    private static void sortIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> (a[0] != b[0]) ? a[0]-b[0] : a[1]-b[1]);
    }

    private static boolean deepEquals(int[][] a, int[][] b) {
        if (a == null || b == null || a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != b[i].length) return false;
            if (a[i][0] != b[i][0] || a[i][1] != b[i][1]) return false;
        }
        return true;
    }

    private static int[][] deepCopy(int[][] arr) {
        int[][] copy = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return copy;
    }
}