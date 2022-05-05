package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 *
 * @author mcw 2022/5/5 14:19
 */
public class leetCode713 {
    /**
     * 二分查找
     */
    public static int numSubarrayProductLessThank(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        int n = nums.length;
        double[] logPrefix = new double[n + 1];
        for (int i = 0; i < n; i++) {
            logPrefix[i + 1] = logPrefix[i] + Math.log(nums[i]);
        }
        double logk = Math.log(k);
        int ret = 0;
        for (int j = 0; j < n; j++) {
            int l = 0;
            int r = j + 1;
            int idx = j + 1;
            //double 类型只能保证 15 位有效数字是精确的。为了避免计算带来的误差，我们将不等式 logPrefix[l] > logPrefix[j+1] − logk 的
            // 右边加上 10^-10 题目中的 double 数值整数部分的数字不超过 5 个），即 logPrefix[l] > logPrefix[j+1] − logk + 10^-10
            // ，从而防止不等式两边数值相等却被判定为大于的情况。
            double val = logPrefix[j + 1] - logk + 1e-10;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (logPrefix[mid] > val) {
                    idx = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            ret += j + 1 - idx;
        }
        return ret;
    }

    /**
     * 滑动窗口
     */
    public static int numSubarrayProductLessThank2(int[] nums, int k) {
        int n = nums.length;
        //满足条件的区间个数
        int ret = 0;
        //区间乘积和
        int prod = 1;
        //区间左边界
        int i = 0;
        for (int j = 0; j < n; j++) {
            prod *= nums[j];
            //如果当前子数组元素乘积 prod 大于等于 k，那么我们右移左端点 i 直到满足当前子数组元素乘积小于 k 或者 i > j
            while (i <= j && prod >= k) {
                prod /= nums[i];
                i++;
            }
            ret += j - i + 1;
        }
        return ret;
    }
}
