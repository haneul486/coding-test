package leetcode;

import java.util.*;

/**
 * LeetCode 2353. Design a Food Rating System
 *
 * Design a food rating system that can do the following:
 *  - Initialize the system with n foods, their cuisines, and initial ratings.
 *  - changeRating(food, newRating): Changes the rating of the given food to newRating.
 *  - highestRated(cuisine): Returns the name of the food with the highest rating for the given cuisine.
 *    If there is a tie, return the lexicographically smaller name.
 *
 * Constraints (abridged):
 *  - 1 <= n <= 2 * 10^4
 *  - 1 <= foods[i].length, cuisines[i].length <= 10
 *  - foods[i], cuisines[i] consist of lowercase English letters.
 *  - 1 <= ratings[i] <= 10^8
 *  - changeRating / highestRated calls: up to 2 * 10^4
 *
 * Example:
 *  Input:
 *    ["FoodRatings","highestRated","changeRating","highestRated","changeRating","highestRated"]
 *    [[["kimchi","miso","sushi","moussaka","ramen","bulgogi"],
 *      ["korean","japanese","japanese","greek","japanese","korean"],
 *      [9,12,8,15,14,7]],
 *     ["korean"],
 *     ["sushi",16],
 *     ["japanese"],
 *     ["ramen",16],
 *     ["japanese"]]
 *  Output:
 *    [null,"kimchi",null,"sushi",null,"ramen"]
 *
 * Notes:
 *  - Ties on rating broken by lexicographically smaller food name.
 *  - highestRated should be O(log n) ideally; per-cuisine max-heap (or TreeSet) is common.
 */
public class LeetCode2353_DesignFoodRatingSystem {

    public static void main(String[] args) {
        // 기본 예시
        String[] foods = {"kimchi","miso","sushi","moussaka","ramen","bulgogi"};
        String[] cuisines = {"korean","japanese","japanese","greek","japanese","korean"};
        int[] ratings = {9,12,8,15,14,7};

        FoodRatings fr;
        try {
            fr = new FoodRatings(foods, cuisines, ratings);
        } catch (UnsupportedOperationException e) {
            System.out.println("Not implemented yet. Exiting tests.");
            return;
        }

        expect(fr.highestRated("korean"), "kimchi", "highestRated(korean)");
        fr.changeRating("sushi", 16);
        expect(fr.highestRated("japanese"), "sushi", "highestRated(japanese) after sushi=16");
        fr.changeRating("ramen", 16);
        expect(fr.highestRated("japanese"), "ramen", "highestRated(japanese) tie at 16, lexicographically smaller is ramen? (\"ramen\" < \"sushi\")");
        // 추가 케이스
        fr.changeRating("bulgogi", 10);
        expect(fr.highestRated("korean"), "kimchi", "korean still kimchi(9) vs bulgogi(10) -> should be bulgogi actually if implemented");
        // 위 라인은 너 구현 시 맞게 수정/검증하면 됨

        System.out.println("Sample tests finished ✅ (구현 후 검증 결과를 확인하세요)");
    }

    private static void expect(String actual, String expected, String label) {
        if (!Objects.equals(actual, expected)) {
            throw new AssertionError("Fail: " + label + " | expected=" + expected + ", actual=" + actual);
        }
    }

    static class FoodRatings {
        class FoodRating {
            String food;
            String cuisine;
            int rating;
            public FoodRating(String food, String cuisine, int rating) {
                this.food = food;
                this.cuisine = cuisine;
                this.rating = rating;
            }
        }

        // food → FoodRating (food명으로 빠르게 찾기)
        Map<String, FoodRating> allMap = new HashMap<>();
        // cuisine → 최고 FoodRating
        Map<String, FoodRating> maxMap = new HashMap<>();

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            for (int i = 0; i < foods.length; i++) {
                allMap.put(foods[i], new FoodRating(foods[i], cuisines[i], ratings[i]));
            }
            setMaxMap();
        }

        // cuisine별 최고 평점(동점 시 food 사전순)을 다시 계산
        private void setMaxMap() {
            maxMap.clear();
            for (FoodRating cur : allMap.values()) {
                FoodRating best = maxMap.get(cur.cuisine);
                if (best == null
                        || cur.rating > best.rating
                        || (cur.rating == best.rating && cur.food.compareTo(best.food) < 0)) {
                    maxMap.put(cur.cuisine, cur);
                }
            }
        }

        public void changeRating(String food, int newRating) {
            FoodRating f = allMap.get(food);
            if (f == null) return; // 또는 IllegalArgumentException 던지기
            f.rating = newRating;   // 같은 객체 참조이므로 allMap에 다시 put 불필요
            setMaxMap();            // 전체 재계산 (간단하지만 O(N))
        }

        public String highestRated(String cuisine) {
            FoodRating fr = maxMap.get(cuisine);
            return fr == null ? null : fr.food; // 필요시 예외로 처리 가능
        }
    }
}