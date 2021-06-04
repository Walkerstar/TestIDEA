package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 * <p>
 * 子数组大小 至少为 2 ，且<br>
 * 子数组元素总和为 k 的倍数。<br>
 * 如果存在，返回 true ；否则，返回 false 。
 *
 * @author mcw 2021/6/2 11:32
 */
public class leetCode523 {

    /**
     * 同余定理<BR>
     * 当 prefixSums[q]−prefixSums[p] 为 k 的倍数时，prefixSums[p] 和 prefixSums[q] 除以 k 的余数相同。因此只需要计算每个下标
     * 对应的前缀和除以 k 的余数即可，使用哈希表存储每个余数第一次出现的下标。
     *
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int m = nums.length;
        if (m < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        //规定空的前缀的结束下标为 −1，由于空的前缀的元素和为 0
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < m; i++) {
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)) {
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }
}
