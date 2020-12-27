package mcw.test.leetcode.bzhan;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mcw 2020\6\1 0001-14:50
 * Permutations(排列)
 * given a collection od distinct numbers, return all possible permutations.
 */
public class leetCode46 {
    public static List<List<Integer>> permute(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) return res;
        permuteHelper(res, new LinkedList<Integer>(), nums, new HashSet<Integer>());
        return res;
    }

    /**
     *
     * @param res 所有可能的结果
     * @param clist 当前list的状态
     * @param nums 输入的数字集合
     * @param set 记录当前的数字是否使用
     */
    private static void permuteHelper(List<List<Integer>> res, List<Integer> clist, int[] nums, HashSet<Integer> set) {
        if (clist.size() == nums.length)
            res.add(new LinkedList<>(clist));
        else {
            for (int num : nums) {
                if (!set.contains(num)) {
                    clist.add(num);
                    int last = clist.size() - 1;
                    set.add(num);
                    permuteHelper(res, clist, nums, set);
                    set.remove(num);
                    clist.remove(last);
                }
            }
        }
    }
}
