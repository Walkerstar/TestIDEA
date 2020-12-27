package mcw.test.leetcode.bzhan;

import java.util.HashMap;

/**
 * Two Sum  (输入一个数组和一个目标数，找出两个数之和等于目标数，返回两个数的索引)
 * 思路：
 * 创建一个 hashMap<数的值，数的索引>， 遍历数组，当目标值 减去 当前值后，
 * 如果在 map 中存在，则返回索引，否则，将 数放入 map 中
 * @author mcw 2020\5\16 0016-12:00
 */
public class leetCode01 {
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length <= 1) return res;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int val = target - num;
            if (map.containsKey(val)) {
                res[0] = i;
                res[1] = map.get(val);
                return res;
            } else {
                map.put(num, i);
            }
        }
        return res;
    }
}
