package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mcw 2020\5\14 0014-15:09
 * Combination Sum II
 * 给定一个 *元素重复的数组* 和 一个目标值，返回所有 和为目标值的数组集合
 *
 */
public class leetCode40 {
    public static List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        ArrayList<Integer> curList = new ArrayList<>();
        if (nums == null) return results;
        Arrays.sort(nums);
        combinationSumHelper(nums, target, 0, curList, results);
        return results;
    }

    private static void combinationSumHelper(int[] nums, int target, int index,
                                             List<Integer> curList, List<List<Integer>> results) {
        if (target == 0) {
            results.add(new ArrayList<>(curList));
        } else if (target > 0) {
            for (int i = index; i < nums.length; i++) {
                //去重
                if (i != index && nums[i] == nums[i - 1]) continue;
                curList.add(nums[i]);
                combinationSumHelper(nums, target - nums[i], i + 1, curList, results);
                curList.remove(curList.size() - 1);
            }
        }
    }
}
