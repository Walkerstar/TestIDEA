package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个下标从 0 开始的整数数组 nums 。在一步操作中，你可以执行以下步骤：
 * <p>
 * 从 nums 选出 两个 相等的 整数
 * 从 nums 中移除这两个整数，形成一个 数对
 * 请你在 nums 上多次执行此操作直到无法继续执行。
 * <p>
 * 返回一个下标从 0 开始、长度为 2 的整数数组 answer 作为答案，
 * 其中 answer[0] 是形成的数对数目，answer[1] 是对 nums 尽可能执行上述操作后剩下的整数数目。
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 *
 * @author mcw 2023/2/16 11:47
 */
public class leetCode2341 {

    /**
     * 方法一：哈希表
     * <p>
     * 遍历一次数组，用一个哈希表保存元素个数的奇偶性，偶数为 false，奇数则为 true。
     * 每遇到一个元素，则将奇偶性取反，若取反完后为偶数个，则表明在上次偶数个之后又遇到了两个该元素，可以形成一个数对。
     * 最后返回一个数组，第一个元素是数对数，第二个元素是数组长度减去数对数的两倍。
     */
    public int[] numberOfPairs(int[] nums) {
        Map<Integer, Boolean> cnt = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            cnt.put(num, !cnt.getOrDefault(num, false));
            if (!cnt.get(num)) {
                res++;
            }
        }
        return new int[]{res, nums.length - 2 * res};
    }

    /**
     * set 去重
     */
    public int[] numberOfPairs2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
                count++;
            } else {
                set.add(num);
            }
        }
        return new int[]{count, set.size()};
    }
}
