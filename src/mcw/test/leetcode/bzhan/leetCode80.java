package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * @author mcw 2020\7\1 0001-14:39
 */
public class leetCode80 {


    /**
     * 双指针
     *
     * 因为给定数组是有序的，所以相同元素必然连续。我们可以使用双指针解决本题，遍历数组检查每一个元素是否应该被保留，
     * 如果应该被保留，就将其移动到指定位置。具体地，我们定义两个指针 slow 和 fast 分别为慢指针和快指针，其中慢指针表示处理出的
     * 数组的长度，快指针表示已经检查过的数组的长度，即 nums[fast] 表示待检查的第一个元素，nums[slow−1] 为上一个应该被保留的
     * 元素所移动到的指定位置。
     *
     * 因为本题要求相同元素最多出现两次而非一次，所以我们需要检查上上个应该被保留的元素 nums[slow−2] 是否和当前待检查元素
     * nums[fast] 相同。当且仅当 nums[slow−2]=nums[fast] 时，当前待检查元素 nums[fast] 不应该被保留（因为此时必然有
     * nums[slow−2]=nums[slow−1]=nums[fast]）。最后，slow 即为处理好的数组的长度。
     *
     * 特别地，数组的前两个数必然可以被保留，因此对于长度不超过 2 的数组，我们无需进行任何处理，
     * 对于长度超过 2 的数组，我们直接将双指针的初始值设为 2 即可。
     */
    public int removeDuplicates2(int[] nums){
        int n= nums.length;
        if (n<=2){
            return n;
        }
        int slow=2,fast=2;
        while (fast<n){
            if (nums[slow-2]!=nums[fast]){
                nums[slow]=nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

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
