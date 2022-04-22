package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给定一个长度为 n 的整数数组 nums 。
 * <p>
 * 假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
 * <p>
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
 * 返回 F(0), F(1), ..., F(n-1)中的最大值 。
 * <p>
 * 生成的测试用例让答案符合 32 位 整数。
 *
 * @author mcw 2022/4/22 17:55
 */
public class leetCode396 {

    /**
     * 方法一：迭代
     * 思路
     * <p>
     * 记数组 nums 的元素之和为 numSum。根据公式，可以得到：
     * <p>
     * F(0) = 0 × nums[0] + 1 × nums[1] +…+ (n−1) × nums[n−1]
     * F(1) = 1 × nums[0] + 2 × nums[1] +…+ 0 × nums[n−1]  = F(0) + numSum − n × nums[n−1]
     * 更一般地，当 1 ≤ k < n 时，F(k) = F(k−1) + numSum − n × nums[n−k]。我们可以不停迭代计算出不同的 F(k)，并求出最大值。
     */
    public int maxRotateFunction(int[] nums) {
        int f = 0;
        int n = nums.length;
        int numSum = Arrays.stream(nums).sum();
        //计算f(0)
        for (int i = 0; i < n; i++) {
            f += i * nums[i];
        }

        int res = f;
        //计算f(k)
        for (int i = n - 1; i > 0; i--) {
            f += numSum - n * nums[i];
            res = Math.max(res, f);
        }
        return res;
    }
}
