package mcw.test.leetcode.bzhan;

/**
 * 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 *
 * 实现 NumArray 类：
 *
 * 1.NumArray(int[] nums) 使用数组 nums 初始化对象
 * 2.int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点
 * （也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 *
 * @author mcw 2021\3\3 0003-17:13
 */
public class leetCode303 {
    int[] sum;

    /**
     * 前缀和
     */
    public void NumArray(int[] nums) {
        int n = nums.length;
        sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
