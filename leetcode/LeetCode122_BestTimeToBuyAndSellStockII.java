package leetcode;

import java.util.*;

/**
 * LeetCode 122. Best Time to Buy and Sell Stock II
 *
 * You are given an integer array prices where prices[i] is the price of a given stock on the i-th day.
 * On each day, you may decide to buy and/or sell the stock. You can hold at most one share at a time.
 * However, you can buy it and then immediately sell it on the same day.
 *
 * Return the maximum profit you can achieve.
 *
 * Examples:
 * 1) Input: prices = [7,1,5,3,6,4]
 *    Output: 7
 *    Explanation: Buy on day 2 (price=1) and sell on day 3 (price=5), profit = 4.
 *                 Then buy on day 4 (price=3) and sell on day 5 (price=6), profit = 3.
 *                 Total profit = 4 + 3 = 7.
 *
 * 2) Input: prices = [1,2,3,4,5]
 *    Output: 4
 *    Explanation: Buy on day 1 and sell on day 5, profit = 4. (Or sell each day; total is the same.)
 *
 * 3) Input: prices = [7,6,4,3,1]
 *    Output: 0
 *    Explanation: No transactions are done; profit = 0.
 *
 * Constraints:
 *  - 1 <= prices.length <= 3 * 10^4
 *  - 0 <= prices[i] <= 10^4
 */
public class LeetCode122_BestTimeToBuyAndSellStockII {

    public static void main(String[] args) {
        // 기본 예시
        runAndCheck(new int[]{7,1,5,3,6,4}, 7);
        runAndCheck(new int[]{1,2,3,4,5}, 4);
        runAndCheck(new int[]{7,6,4,3,1}, 0);

        // 경계/추가 케이스
        runAndCheck(new int[]{1}, 0);                 // 길이 1
        runAndCheck(new int[]{2,1}, 0);               // 하락만
        runAndCheck(new int[]{1,2}, 1);               // 단순 상승
        runAndCheck(new int[]{2,1,2,1,2}, 2);         // 지그재그
        runAndCheck(new int[]{3,3,3,3}, 0);           // 동일가
        runAndCheck(new int[]{0,10,0,10,0,10}, 30);   // 큰 등락 반복
        runAndCheck(new int[]{2,4,1,7}, 8);           // 여러 구간 이익 누적

        // 랜덤 검증(참고: 기대값은 단순 비교용 구현으로 계산)
        Random r = new Random(122);
        for (int n = 1; n <= 100; n++) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = r.nextInt(11); // 0..10
            int expected = expectedGreedy(arr);
            runAndCheck(arr, expected);
        }

        System.out.println("All tests passed ✅");
    }


    // 기대값 계산용(비인플레이스, 검증 전용): 연속 상승분 합산
    private static int expectedGreedy(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }



    // ---------------------- Test Harness Below ----------------------

    private static void runAndCheck(int[] prices, int expected) {
        int actual;
        try {
            int[] copy = Arrays.copyOf(prices, prices.length);
            actual = maxProfit(copy);
        } catch (UnsupportedOperationException e) {
            // 구현 전에는 테스트를 건너뛰고 명확히 표시
            System.out.println("Method not implemented yet. Skipping tests.");
            System.exit(0);
            return;
        }
        if (actual != expected) {
            throw new AssertionError(
                    "Failed for prices=" + Arrays.toString(prices) +
                            "\nExpected: " + expected +
                            "\nActual  : " + actual
            );
        }
    }
    // ====== You implement here ======
    public static int maxProfit(int[] prices) {
        int result = 0;

        //prices 반복문을 수행한다.
        //이전값에 증가하면 증가로 판단한다.
        //값이 감소하면 이전에 저장한 min값에서 max값을 빼서 result에 누적한다.
        boolean flag = true;//flag=true 이전상태가 증가중상태, false:이전상태가 감소중 상태
        int min = prices[0];
        int max = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) { // 증가중
                if (flag) {
                    max = prices[i];
                } else {
                    min = prices[i - 1];
                    max = prices[i];      // ← 이 줄이 필요
                }
                flag = true;
            }else if (prices[i] < prices[i - 1]) {//감소중
                if(flag){
                    result += max - min;
                }
                min = prices[i];
                flag = false;
            }
        }
        if (flag) {
            result += max - min;
        }
        return result;
    }
}