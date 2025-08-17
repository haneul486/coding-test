package leetcode;

import java.util.*;

/**
 * LeetCode 90. Subsets II
 *
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 *   Input: nums = [1,2,2]
 *   Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * Example 2:
 *   Input: nums = [0]
 *   Output: [[],[0]]
 *
 * Constraints:
 *   - 1 <= nums.length <= 10
 *   - -10 <= nums[i] <= 10
 */
public class LeetCode90_SubsetsII {

    public static void main(String[] args) {
        // 기본 예시
        runAndCheck(new int[]{1,2,2}, Arrays.asList(
                Arrays.asList(),
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(1,2),
                Arrays.asList(2,2),
                Arrays.asList(1,2,2)
        ));

        runAndCheck(new int[]{0}, Arrays.asList(
                Arrays.asList(),
                Arrays.asList(0)
        ));

        // 추가 케이스
        runAndCheck(new int[]{1,1}, Arrays.asList(
                Arrays.asList(),
                Arrays.asList(1),
                Arrays.asList(1,1)
        ));

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

        runAndCheck(new int[]{-1,-1,2}, Arrays.asList(
                Arrays.asList(),
                Arrays.asList(-1),
                Arrays.asList(-1,-1),
                Arrays.asList(2),
                Arrays.asList(-1,2),
                Arrays.asList(-1,-1,2)
        ));
    }

    private static void runAndCheck(int[] nums, List<List<Integer>> expectedAnyOrder) {
        List<List<Integer>> actual = new Solution90().subsetsWithDup(Arrays.copyOf(nums, nums.length));

        // 정규화 후 비교 (부분집합 내부 정렬 → 문자열로 변환 → 집합 비교)
        Set<String> exp = toNormalizedSet(expectedAnyOrder);
        Set<String> act = toNormalizedSet(actual);

        System.out.println("nums     : " + Arrays.toString(nums));
        System.out.println("expected : " + exp);
        System.out.println("actual   : " + act);
        System.out.println(exp.equals(act) ? "✅ PASSED\n" : "❌ FAILED\n");
    }

    private static Set<String> toNormalizedSet(List<List<Integer>> lists) {
        Set<String> set = new LinkedHashSet<>();
        for (List<Integer> l : lists) {
            List<Integer> copy = new ArrayList<>(l);
            Collections.sort(copy);
            set.add(copy.toString());
        }
        return set;
    }
}
class Solution90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> list = new HashSet();
        subset(nums, 0, new int[nums.length], 0, list);
        return new ArrayList(list);
    }
    public void subset(int[] nums, int index, int[] curNums, int curIndex, Set<List<Integer>> list){
        if(index == nums.length){
            List<Integer> curList = new ArrayList();
            for(int i = 0 ; i < curIndex ; i++){
                curList.add(curNums[i]);
            }
            Collections.sort(curList);
            list.add(curList);
            return ;
        }
        subset(nums, index+1, curNums, curIndex, list);
        curNums[curIndex] = nums[index];
        subset(nums, index+1, curNums, curIndex+1, list);
    }
}