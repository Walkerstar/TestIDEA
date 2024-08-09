package mcw.test.leetcode.bzhan;

/**
 * 2908. 元素和最小的山形三元组 I
 * <p>
 * 给你一个下标从 0 开始的整数数组 nums 。
 * <p>
 * 如果下标三元组 (i, j, k) 满足下述全部条件，则认为它是一个 山形三元组 ：
 * <p>
 * i < j < k
 * nums[i] < nums[j] 且 nums[k] < nums[j]
 * 请你找出 nums 中 元素和最小 的山形三元组，并返回其 元素和 。如果不存在满足条件的三元组，返回 -1 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [8,6,1,5,3]
 * 输出：9
 * 解释：三元组 (2, 3, 4) 是一个元素和等于 9 的山形三元组，因为：
 * - 2 < 3 < 4
 * - nums[2] < nums[3] 且 nums[4] < nums[3]
 * 这个三元组的元素和等于 nums[2] + nums[3] + nums[4] = 9 。可以证明不存在元素和小于 9 的山形三元组。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [5,4,8,7,10,2]
 * 输出：13
 * 解释：三元组 (1, 3, 5) 是一个元素和等于 13 的山形三元组，因为：
 * - 1 < 3 < 5
 * - nums[1] < nums[3] 且 nums[5] < nums[3]
 * 这个三元组的元素和等于 nums[1] + nums[3] + nums[5] = 13 。可以证明不存在元素和小于 13 的山形三元组。
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [6,5,4,3,4,5]
 * 输出：-1
 * 解释：可以证明 nums 中不存在山形三元组。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 50
 * 1 <= nums[i] <= 50
 *
 * @author MCW 2024/3/29
 */
public class leetCode2908 {

    /**
     * 枚举
     */
    public int minimumSum(int[] nums) {
        int sum = 1000, n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] < nums[j] && nums[j] > nums[k]) {
                        sum = Math.min(sum, nums[i] + nums[j] + nums[k]);
                    }
                }
            }
        }
        return sum;
    }


    /**
     * 方法二：数组
     * 思路与算法
     * <p>
     * 我们从左到右遍历，来求出前缀数组中的最小值，用 left[i] 来表示前 i 个数字的最小值。
     * <p>
     * 然后我们从右到左遍历，用 right 来表示当前数字右边的最小值。
     * 如果一个数比左右两边最小值大时，说明找到一个山形三元组，并更新当前山形三元组的最小元素和。
     * <p>
     * 最后我们返回最小元素和即可。
     */
    public int minimumSUum2(int[] nums) {
        int n = nums.length, res = 1000, mn = 1000;
        int[] left = new int[n];
        for (int i = 1; i < n; i++) {
            left[i] = mn = Math.min(nums[i - 1], mn);
        }

        int right = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (left[i] < nums[i] && nums[i] > right) {
                res = Math.min(res, left[i] + nums[i] + right);
            }
            right = Math.min(right, nums[i]);
        }
        return res < 1000 ? res : -1;
    }
}
