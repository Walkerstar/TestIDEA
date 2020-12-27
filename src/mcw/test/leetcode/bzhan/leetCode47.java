package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mcw 2020\6\1 0001-15:13
 * Permutations II
 * given a collection of numbers that might contain duplicates,return all possible permutations.
 */
public class leetCode47 {
    public static List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        Arrays.sort(nums);
        LinkedList<List<Integer>> result = new LinkedList<>();
        getPermutationHelper(nums, new boolean[nums.length], new LinkedList<>(), result);
        return result;
    }

    private static void getPermutationHelper(int[] nums, boolean[] used,
                                             List<Integer> curList, List<List<Integer>> result) {
        if (curList.size() == nums.length) {
            result.add(new LinkedList<>(curList));
        } else {
            int preNum = nums[0] - 1;
            for (int i = 0; i < nums.length; i++) {
                if (!used[i] && (nums[i] != preNum)) {
                    preNum = nums[i];
                    curList.add(nums[i]);
                    used[i] = true;
                    getPermutationHelper(nums, used, curList, result);
                    used[i] = false;
                    curList.remove(curList.size() - 1);
                }
            }
        }
    }
}
