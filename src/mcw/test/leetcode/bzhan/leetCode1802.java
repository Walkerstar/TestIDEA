package mcw.test.leetcode.bzhan;

/**
 * 给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
 *
 * <li>nums.length == n</li>
 * <li>nums[i] 是 正整数 ，其中 0 <= i < n</li>
 * <li>abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1</li>
 * <li>nums 中所有元素之和不超过 maxSum</li>
 * <li>nums[index] 的值被 最大化</li>
 * <li>返回你所构造的数组中的 nums[index] 。</li>
 * <p>
 * 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
 * <p>
 * 提示：
 * <li>1 <= n <= maxSum <= 109</li>
 * <li>0 <= index < n</li>
 *
 * @author mcw 2023/1/4 10:34
 */
public class leetCode1802 {

    /**
     * 方法一：贪心 + 二分查找
     * 思路
     * <p>
     * 根据题意，需要构造一个长度为 n 的数组 nums，所有元素均为正整数，元素之和不超过 maxSum，相邻元素之差不超过 1，
     * 且需要最大化 nums[index]。根据贪心的思想，可以使 nums[index] 成为数组最大的元素，并使其他元素尽可能小，
     * 即从 nums[index] 开始，往左和往右，下标每相差 1，元素值就减少 1，直到到达数组边界，或者减少到仅为 1 后保持为 1 不变。
     * <p>
     * 根据这个思路，一旦 nums[index] 确定后，这个数组的和 numsSum 也就确定了。并且 nums[index] 越大，数组和 numsSum 也越大。
     * 据此，可以使用二分搜索来找出最大的使得 numsSum ≤ maxSum 成立的 nums[index]。
     * <p>
     * 代码实现上，二分搜索的左边界为 1，右边界为 maxSum。函数 valid 用来判断当前的 nums[index] 对应的 numsSum 是否满足条件。
     * numsSum 由三部分组成，nums[index]，nums[index] 左边的部分之和，和 nums[index] 右边的部分之和。
     * cal 用来计算单边的元素和，需要考虑边界元素是否早已下降到 1 的情况。
     */
    public int maxValue(int n, int index, int maxSum) {
        int left = 1, right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (valid(mid, n, index, maxSum)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public boolean valid(int mid, int n, int index, int maxSum) {
        //表明 左边有 index 个数
        int left = index;
        //表明 右边有 (n-index-1) 个数
        int right = n - index - 1;
        return mid + cal(mid, left) + cal(mid, right) <= maxSum;
    }

    public long cal(int big, int length) {
        if (length + 1 < big) {
            //因为数之间间隔的差值的绝对值为1，所有的数为正整数， 所以 small 是边界值
            int small = big - length;
            return (long) (big - 1 + small) * length / 2;
        } else {
            int ones = length - (big - 1);
            return (long) big * (big - 1) / 2 + ones;
        }
    }

    /**
     * 数学推导
     */
    public int maxValue2(int n, int index, int maxSum) {
        double left = index;
        double right = n - index - 1;
        if (left > right) {
            double temp = left;
            left = right;
            right = temp;
        }

        double upper = ((double) (left + 1) * (left + 1) - 3 * (left + 1)) / 2 + left + 1 + (left + 1) + ((left + 1) * (left + 1) - 3 * (left + 1)) / 2 + right + 1;
        if (upper >= maxSum) {
            double a = 1;
            double b = -2;
            double c = left + right + 2 - maxSum;
            return (int) Math.floor((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));
        }

        upper = ((double) 2 * (right + 1) - left - 1) * left / 2 + (right + 1) + ((right + 1) * (right + 1) - 3 * (right + 1)) / 2 + right + 1;
        if (upper >= maxSum) {
            double a = 1.0 / 2;
            double b = left + 1 - 3.0 / 2;
            double c = right + 1 + (-left - 1) * left / 2 - maxSum;
            return (int) Math.floor((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));
        } else {
            double a = left + right + 1;
            ;
            double b = (-left * left - left - right * right - right) / 2 - maxSum;
            return (int) Math.floor(-b / a);
        }
    }
}
