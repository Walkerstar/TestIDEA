package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mcw 2020\5\14 0014-14:31
 * Combination Sum I (组合总和)
 * 给定一个 *元素不重复的数组* 和 一个目标值，返回所有 和为目标值的数组集合
 */
public class leetCode39 {
    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0 || target <= 0) return results;
        Arrays.sort(nums);
        combinationSumHelper(nums, target, 0, results, new ArrayList<>());
        return results;
    }

    private static void combinationSumHelper(int[] nums, int target, int index,
                                             List<List<Integer>> results, List<Integer> curSeq) {
        if (target == 0) {
            results.add(new ArrayList<>(curSeq));
        } else if (target > 0) {
            for (int i = index; i < nums.length; i++) {
                if (nums[i] > target) break; //used to improve performance
                curSeq.add(nums[i]);
                combinationSumHelper(nums, target - nums[i], i, results, curSeq);
                curSeq.remove(curSeq.size() - 1);
            }
        }
    }
}
