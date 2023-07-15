package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]]（若两个四元组元素一一对应，则认为两个四元组重复）：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 200
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 *
 * @author MCW 2023/7/15
 */
public class leetCode18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            // 找到下一个不重复的数字
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 如果 接下来 四个数字 的和 大于 目标值，则说明没有满足符合条件的组合，直接退出循环。因为已经从小到大排过序。
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            // 如果 最后三位数 加上当前数 的和 小于 目标值，则说明 当前的 nums[i] 偏小，所以跳过此次循环，需要往后找一个合适的数
            if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            // 固定 第二个数字 ，枚举
            for (int j = i + 1; j < length - 2; j++) {
                // 找到下一个不重复的数字
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 如果前面确定的两个数字 加上 后续的两个数字 的和 大于 目标值，则说明 没有满足符合条件的数组，退出循环
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                // 如果前面确定的两个数字 加上 最后的两位数字 的和 小于 目标值，则说明 此时的 nums[j] 不合适，往后找下一个数字
                if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                // 已经确定两个数字后，开始寻找剩下的 两个数字
                int left = j + 1, right = length - 1;
                // 双指针
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    // 如果等于目标值，加入答案
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 然后将 左指针 移至 到下一个不同的数字上
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // 上面的循环退出的 left 其实是 与 nums[left]相同数的 最后一个数的下标，所以此处 left 还需在加上一次
                        left++;
                        // 同时，将右指针 移至下一个 不同的数字上
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        // 原因同上，所以此处再减一次
                        right--;
                    } else if (sum < target) {
                        // 如果 此时的和 小于 目标值，则说明 nums[left] 小了，需要加大，所以 left 右移
                        left++;
                    } else {
                        // 如果 此时的和 大于 目标值，则说明 nums[right] 大了，需要缩小，所以 right 左移
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }
}
