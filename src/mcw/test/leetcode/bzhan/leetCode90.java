package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 78题的翻版 --------给出的数组中包含重复数
 *
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * @author mcw 2020\6\29 0029-20:54
 */
public class leetCode90 {

    public static List<List<Integer>> subSetsWithDup(int[] nums) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
        subsetsHelper(nums, 0, true, result, new LinkedList<Integer>());
        return result;
    }

    private static void subsetsHelper(int[] nums, int currIdx, boolean taken, List<List<Integer>> result, List<Integer> curr) {
        if (currIdx == nums.length) {
            result.add(new LinkedList<>(curr));
        } else {
            //不选择当前数
            subsetsHelper(nums, currIdx + 1, false, result, curr);
            if (taken || nums[currIdx - 1] != nums[currIdx]) {
                //或者选择当前数
                curr.add(nums[currIdx]);
                subsetsHelper(nums, currIdx + 1, true, result, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }
}
