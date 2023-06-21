package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,6,5,1,8]
 * 输出：18
 * 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
 * <p>
 * 示例 2：
 * 输入：nums = [4]
 * 输出：0
 * 解释：4 不能被 3 整除，所以无法选出数字，返回 0。
 * <p>
 * 示例 3：
 * 输入：nums = [1,2,3,4,4]
 * 输出：12
 * 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
 * <p>
 * 提示：
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 *
 * @author MCW 2023/6/19
 */
public class leetCode1262 {

    public int maxSumDivThree1(int[] nums) {
        int n = nums.length;
        // 因为被3整除，余数为 0，1，2，所以定义一个3位的数组，下标分别表示余数值
        int[] remainder = new int[3];
        for (int num : nums) {
            // 依次对数字求和
            int a, b, c;
            a = remainder[0] + num;
            b = remainder[1] + num;
            c = remainder[2] + num;
            // 比较 余数位置的值 与 此时的累加值 的大小，
            remainder[a % 3] = Math.max(remainder[a % 3], a);
            remainder[b % 3] = Math.max(remainder[b % 3], b);
            remainder[c % 3] = Math.max(remainder[c % 3], c);
        }
        // 返回 余数为0 的值
        return remainder[0];
    }

    /**
     * 动态规划
     */
    public int maxSumDivThree(int[] nums) {
        int[] f = {0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int num : nums) {
            int[] g = new int[3];
            System.arraycopy(f, 0, g, 0, 3);
            for (int i = 0; i < 3; i++) {
                g[(i + num % 3) % 3] = Math.max(g[(i + num % 3) % 3], f[i] + num);
            }
            f = g;
        }
        return f[0];
    }
}
