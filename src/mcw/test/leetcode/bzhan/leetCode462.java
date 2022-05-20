package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.Random;

/**
 * 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。
 * <p>
 * 在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。
 *
 * @author mcw 2022/5/19 10:58
 */
public class leetCode462 {

    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int mid = nums[(nums.length - 1) / 2];
        int count = 0;
        for (int num : nums) {
            count += Math.abs(num - mid);
        }
        return count;
    }

    /**
     * 快速选择
     * 求解第 nums[ n / 2] 小的元素 (从 0 开始计数),
     */
    Random random = new Random();

    public int minMoves2(int[] nums) {
        int n = nums.length;
        int x = quickSelect(nums, 0, n - 1, n / 2);
        int ret = 0;
        for (int num : nums) {
            ret += Math.abs(num - x);
        }
        return ret;
    }

    public int quickSelect(int[] nums, int left, int right, int index) {
        int q = randomPartition(nums, left, right);
        if (q == index) {
            return nums[q];
        } else {
            return q < index ? quickSelect(nums, q + 1, right, index) : quickSelect(nums, left, q - 1, index);
        }
    }

    public int randomPartition(int[] nums, int left, int right) {
        int i = random.nextInt(right - left + 1) + left;
        swap(nums, i, right);
        return partition(nums, left, right);
    }

    public int partition(int[] nums, int left, int right) {
        int x = nums[right], i = left - 1;
        for (int j = left; j < right; j++) {
            if (nums[j] <= x) {
                ++i;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, right);
        return i + 1;
    }

    public void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
