package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 *
 * @author mcw 2021/6/3 11:01
 */
public class leetCode525 {

    public int findMacLength(int[] nums) {
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<>();
        //前缀和
        int counter = 0;
        map.put(counter, -1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            //含有相同数量的 0 和 1 ，以 -1 代替 0，则相同数量的 0 和 1 进行相加，和为 0
            if (num == 1) {
                counter++;
            } else {
                counter--;
            }
            //若该前缀和的值已出现过 则说明标记中的下标到当前扫描的下标的这段数组的总和值是为0的
            if (map.containsKey(counter)) {
                int prevIndex = map.get(counter);
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                map.put(counter, i);
            }
        }
        return maxLength;
    }
}
