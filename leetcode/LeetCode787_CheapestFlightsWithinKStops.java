package leetcode;

import java.util.*;

/**
 * LeetCode 787. Cheapest Flights Within K Stops
 *
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei]
 * indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops.
 * If there is no such route, return -1.
 *
 * Example 1:
 *   Input: n = 4, flights = [[0,1,100],[1,2,100],[2,3,100],[0,2,500]], src = 0, dst = 3, k = 1
 *   Output: 600
 *
 * Example 2:
 *   Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 *   Output: 200
 *
 * Example 3:
 *   Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 *   Output: 500
 *
 * Constraints:
 *   - 1 <= n <= 100
 *   - 0 <= flights.length <= (n * (n - 1) / 2)
 *   - flights[i].length == 3
 *   - 0 <= fromi, toi < n
 *   - 1 <= pricei <= 10^4
 *   - There will not be any multiple flights from fromi to toi.
 *   - 0 <= src, dst, k < n
 */
public class LeetCode787_CheapestFlightsWithinKStops {

    public static void main(String[] args) {
        // Example 1
        int n1 = 4;
        int[][] f1 = {{0,1,100},{1,2,100},{2,3,100},{0,2,500}};
        runTest(n1, f1, 0, 3, 1, 600);

        // Example 2
        int n2 = 3;
        int[][] f2 = {{0,1,100},{1,2,100},{0,2,500}};
        runTest(n2, f2, 0, 2, 1, 200);

        // Example 3
        int n3 = 3;
        int[][] f3 = {{0,1,100},{1,2,100},{0,2,500}};
        runTest(n3, f3, 0, 2, 0, 500);

        // Additional
        runTest(5, new int[][]{{0,1,10},{1,2,10},{2,3,10},{3,4,10},{0,4,100}}, 0, 4, 2, 40);
        runTest(5, new int[][]{{0,1,5},{1,2,5},{2,3,5},{3,1,1}}, 0, 3, 2, 15);
        runTest(3, new int[][]{}, 0, 2, 1, -1);
    }

    private static void runTest(int n, int[][] flights, int src, int dst, int k, int expected) {
        int actual = new Solution787().findCheapestPrice(n, flights, src, dst, k);
        System.out.println("n=" + n + ", src=" + src + ", dst=" + dst + ", k=" + k);
        System.out.println("expected: " + expected + ", actual: " + actual);
        System.out.println((expected == actual ? "✅ PASSED" : "❌ FAILED") + "\n");
    }
}

class Solution787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final int INF = 1_000_000_000;
        int[] minCost = new int[n];
        Arrays.fill(minCost, INF);
        minCost[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] nextCost = Arrays.copyOf(minCost, n);
            for (int[] flight : flights) {
                int fromCity = flight[0];
                int toCity = flight[1];
                int price = flight[2];
                if (minCost[fromCity] == INF) continue;
                if (minCost[fromCity] + price < nextCost[toCity]) {
                    nextCost[toCity] = minCost[fromCity] + price;
                }
            }
            minCost = nextCost;
        }

        return minCost[dst] >= INF ? -1 : minCost[dst];
    }
}