package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给你两个只包含 1 到 9 之间数字的数组 nums1 和 nums2 ，每个数组中的元素 互不相同 ，请你返回 最小 的数字，两个数组都 至少 包含这个数字的某个数位。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [4,1,3], nums2 = [5,7]
 * 输出：15
 * 解释：数字 15 的数位 1 在 nums1 中出现，数位 5 在 nums2 中出现。15 是我们能得到的最小数字。
 * 示例 2：
 * <p>
 * 输入：nums1 = [3,5,2,6], nums2 = [3,1,7]
 * 输出：3
 * 解释：数字 3 的数位 3 在两个数组中都出现了。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length, nums2.length <= 9
 * 1 <= nums1[i], nums2[i] <= 9
 * 每个数组中，元素 互不相同 。
 *
 * @author MCW 2023/9/5
 */
public class leetCode2605 {

    /**
     * 方法一：分类讨论
     * 思路与算法
     * <p>
     * 如果 nums1 和 nums2 中有相同的数字，那么我们选出其中最小的那个即为答案，它是一个一位数。
     * 这一步可以使用哈希表解决：将 nums1 中的所有数字放入一个哈希集合，再依次枚举 nums2 中的每个数字，判断其是否在哈希集合中即可。
     * <p>
     * 如果 nums1 和 nums2 中没有相同的数字，那么取 nums1 最小的数字 x 和 nums2 中最小的数字 y，组成一个两位数，即为答案。答案是 xy 和 yx 中的较小值。
     */
    public int minNumber(int[] nums1, int[] nums2) {
        int s = same(nums1, nums2);
        if (s != -1) {
            return s;
        }
        int x = Arrays.stream(nums1).min().getAsInt();
        int y = Arrays.stream(nums2).min().getAsInt();
        return Math.min(x * 10 + y, y * 10 + x);
    }

    public int same(int[] nums1, int[] nums2) {
        Set<Integer> s = new HashSet<>();
        for (int i : nums1) {
            s.add(i);
        }
        int x = 10;
        for (int i : nums2) {
            if (s.contains(i)) {
                x = Math.min(x, i);
            }
        }
        return x == 10 ? -1 : x;
    }


    /**
     * 位运算
     */
    public int minNumber2(int[] nums1, int[] nums2) {
        int mask1 = 0, mask2 = 0;
        for (int i : nums1) {
            mask1 |= 1 << i;
        }
        for (int i : nums2) {
            mask2 |= 1 << i;
        }
        int mask = mask1 & mask2;
        if (mask != 0) {
            return Integer.numberOfTrailingZeros(mask);
        }
        int a = Integer.numberOfTrailingZeros(mask1);
        int b = Integer.numberOfTrailingZeros(mask2);
        return Math.min(a * 10 + b, b * 10 + a);
    }

}
