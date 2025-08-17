package leetcode;

import java.util.*;

/**
 * LeetCode 322. Coin Change
 *
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 *   Input: coins = [1,2,5], amount = 11
 *   Output: 3
 *   Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 *   Input: coins = [2], amount = 3
 *   Output: -1
 *
 * Example 3:
 *   Input: coins = [1], amount = 0
 *   Output: 0
 *
 * Constraints:
 *   - 1 <= coins.length <= 12
 *   - 1 <= coins[i] <= 2 * 10^4
 *   - 0 <= amount <= 10^4
 */
public class LeetCode322_CoinChange {

    public static void main(String[] args) {
        runTest(new int[]{1,2,5}, 11, 3);
        runTest(new int[]{2}, 3, -1);
        runTest(new int[]{1}, 0, 0);
        runTest(new int[]{1,3,4}, 6, 2);   // 3+3 or 2+4? (here 3+3 or 4+1+1 but fewest is 2)
        runTest(new int[]{2,5,10,1}, 27, 4); // 10+10+5+2 (one optimal)
        runTest(new int[]{186,419,83,408}, 6249, 20); // 대형 케이스
        runTest(new int[]{2}, 1, -1);
        runTest(new int[]{2,4}, 7, -1);
        runTest(new int[]{3,7}, 0, 0);
    }

    private static void runTest(int[] coins, int amount, int expected) {
        int actual = new Solution322().coinChange(Arrays.copyOf(coins, coins.length), amount);
        System.out.println("coins   : " + Arrays.toString(coins) + ", amount=" + amount);
        System.out.println("expected: " + expected);
        System.out.println("actual  : " + actual);
        System.out.println((actual == expected) ? "✅ PASSED\n" : "❌ FAILED\n");
    }
}

class Solution322 {

    /**
     * 동적계획법(DP) - Unbounded Knapsack
     * dp[a] = 금액 a를 만들 때 필요한 최소 동전 수 (불가능이면 INF)
     * 점화식: dp[a] = min(dp[a], dp[a - coin] + 1)
     * 금액은 오름차순(같은 동전 여러 번 사용 허용)
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;

        final int INF = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int coin : coins) {
            if (coin > amount) continue;
            for (int a = coin; a <= amount; a++) {
                dp[a] = Math.min(dp[a], dp[a - coin] + 1);
            }
        }

        return dp[amount] == INF ? -1 : dp[amount];
    }
}