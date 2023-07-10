package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * <p>
 * 你返回所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * <p>
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 *
 * @author mcw 2020\5\2 0002-14:41
 */
public class leetCode15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length <= 2) {
            return res;
        }
        int n = nums.length;
        int i = 0;
        Arrays.sort(nums);
        while (i < n - 2) {
            int base = nums[i];
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = base + nums[left] + nums[right];
                if (sum == 0) {
                    LinkedList<Integer> list = new LinkedList<>();
                    list.add(base);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    left = moveRight(nums, left + 1);
                    right = moveLeft(nums, right - 1);
                } else if (sum > 0) {
                    right = moveLeft(nums, right - 1);
                } else {
                    left = moveRight(nums, left + 1);
                }
            }
            i = moveRight(nums, i + 1);
        }
        return res;
    }

    private static int moveRight(int[] nums, int left) {
        // 往右 找到下一个不相等的数字的下标
        while (left == 0 || (left < nums.length && nums[left] == nums[left - 1])) {
            left++;
        }
        return left;
    }

    private static int moveLeft(int[] nums, int right) {
        // 往左 找到下一个不相等的数字的下标
        while (right == nums.length - 1 || (right >= 0 && nums[right] == nums[right + 1])) {
            right--;
        }
        return right;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
