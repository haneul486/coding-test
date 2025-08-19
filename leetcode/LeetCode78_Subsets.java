package leetcode;

import java.util.*;

/**
 * LeetCode 78. Subsets
 *
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 *   Input: nums = [1,2,3]
 *   Output: [[],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]]
 *
 * Example 2:
 *   Input: nums = [0]
 *   Output: [[],[0]]
 *
 * Constraints:
 *   - 1 <= nums.length <= 10
 *   - -10 <= nums[i] <= 10
 *   - All the numbers of nums are unique.
 */
public class LeetCode78_Subsets {

    public static void main(String[] args) {
        // 기본 예시
        runAndCheck(new int[]{1,2,3}, Arrays.asList(
                Arrays.asList(),
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList(1,2),
                Arrays.asList(1,3),
                Arrays.asList(2,3),
                Arrays.asList(1,2,3)
        ));

        runAndCheck(new int[]{0}, Arrays.asList(
                Arrays.asList(),
                Arrays.asList(0)
        ));

        // 추가 케이스
        runAndCheck(new int[]{5,7}, Arrays.asList(
                Arrays.asList(),
                Arrays.asList(5),
                Arrays.asList(7),
                Arrays.asList(5,7)
        ));

        runAndCheck(new int[]{-1,2,4}, Arrays.asList(
                Arrays.asList(),
                Arrays.asList(-1),
                Arrays.asList(2),
                Arrays.asList(4),
                Arrays.asList(-1,2),
                Arrays.asList(-1,4),
                Arrays.asList(2,4),
                Arrays.asList(-1,2,4)
        ));
    }

    private static void runAndCheck(int[] nums, List<List<Integer>> expectedAnyOrder) {
        List<List<Integer>> actual = new Solution78().subsets(Arrays.copyOf(nums, nums.length));

        Set<String> exp = normalize(expectedAnyOrder);
        Set<String> act = normalize(actual);

        System.out.println("nums     : " + Arrays.toString(nums));
        System.out.println("expected : " + exp);
        System.out.println("actual   : " + act);
        System.out.println(exp.equals(act) ? "✅ PASSED\n" : "❌ FAILED\n");
    }

    private static Set<String> normalize(List<List<Integer>> lists) {
        Set<String> set = new LinkedHashSet<>();
        for (List<Integer> l : lists) {
            List<Integer> copy = new ArrayList<>(l);
            Collections.sort(copy);
            set.add(copy.toString());
        }
        return set;
    }
}

class Solution78 {
    /**
     * Return all subsets (power set) of nums.
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        subset(nums,0, new int[nums.length], 0, list);
        return list;
    }

    public void subset(int[]nums, int index, int[]curNums, int curIndex, List<List<Integer>> list) {
        if(index == nums.length) {
            List<Integer> list1 = new ArrayList<>();
            for (int i = 0; i < curIndex; i++) {
                list1.add(curNums[i]);
            }
            list.add(list1);
            return ;
        }
        subset(nums,index+1, curNums, curIndex, list);
        curNums[curIndex] = nums[index];
        subset(nums,index+1, curNums, curIndex+1, list);
    }
}