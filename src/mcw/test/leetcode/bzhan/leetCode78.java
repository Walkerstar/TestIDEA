package mcw.test.leetcode.bzhan;

import java.util.LinkedList;
import java.util.List;

/**
 * @author mcw 2020\6\29 0029-16:04
 * Subsets
 * given a set of distinct integers,nums , return all possible subsets.
 * 给定一组不同的整数nums，返回所有可能的子集。
 */
public class leetCode78 {
    public static List<List<Integer>> subSets(int[] nums) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length == 0) return result;
        subsetsHelper(nums, 0, result, new LinkedList<>());
        //subsetsHelper(nums, 0, result, new LinkedList<Integer>());
        return result;
    }

    /**
     *典型递归实现
     */
    private static void subsetsHelper(int[] nums, int currIdx, List<List<Integer>> result, List<Integer> curr) {
        result.add(new LinkedList<>(curr));
        for (int i = currIdx; i < nums.length; i++) {
            curr.add(nums[i]);
            subsetsHelper(nums, i + 1, result, curr);
            curr.remove(curr.size() - 1);
        }
    }

    private static void subsetsHelper1(int[] nums, int currIdx, List<List<Integer>> result, List<Integer> curr) {
        if (currIdx == nums.length) {
            result.add(new LinkedList<>(curr));
        } else {
            //不选择当前数
            subsetsHelper1(nums, currIdx + 1, result, curr);
            //或者选择当前数
            curr.add(nums[currIdx]);
            subsetsHelper1(nums, currIdx + 1, result, curr);
            curr.remove(curr.size() - 1);
        }
    }
}
