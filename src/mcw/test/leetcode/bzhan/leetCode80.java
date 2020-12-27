package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author mcw 2020\7\1 0001-14:39
 * Remove Duplicates form Sorted Array II
 * 数组中每一个元素只能重复 2 次，多的删除，最后返回数组中元素的个数
 */
public class leetCode80 {

    /**
     * 利用 hashMap ，key 为元素本身，value 为 元素出现的个数
     */
    public static int removeDuplicates(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : array) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else if (map.get(i) < 2) {
                map.put(i, map.get(i) + 1);
            }
        }
        int most = 0;
        for (Integer value : map.values()) {
            most = most + value;
        }
        return most;
    }

    /**
     * ******* 当重复的数字相距较远时，结果不对 ************
     * 指定下标 从 2 开始， 如果 1.loc 的前两个数相等， 2.且前一个值与当前值相等，
     * 则说明 loc 所指明的元素 重复超过 2 次，i 循环 加 1，将 下标 为 i 的值赋给下标为 loc 的值
     */
    public static int removeDuplicates1(int[] nums) {
        if (nums == null || nums.length <= 2) {
            assert nums != null;
            return nums.length;
        }
        int loc = 2;
        for (int i = 2; i < nums.length; i++) {
            if (!(nums[loc - 1] == nums[loc - 2] && nums[loc - 1] == nums[i])) {
                nums[loc++] = nums[i];
            }
        }
        return loc;
    }
}
