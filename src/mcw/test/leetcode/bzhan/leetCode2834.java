package mcw.test.leetcode.bzhan;

/**
 * 2834. 找出美丽数组的最小和
 * <p>
 * 给你两个正整数：n 和 target 。
 * <p>
 * 如果数组 nums 满足下述条件，则称其为 美丽数组 。
 * <p>
 * nums.length == n.
 * nums 由两两互不相同的正整数组成。
 * 在范围 [0, n-1] 内，不存在 两个 不同 下标 i 和 j ，使得 nums[i] + nums[j] == target 。
 * 返回符合条件的美丽数组所可能具备的 最小 和，并对结果进行取模 10^9 + 7。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2, target = 3
 * 输出：4
 * 解释：nums = [1,3] 是美丽数组。
 * - nums 的长度为 n = 2 。
 * - nums 由两两互不相同的正整数组成。
 * - 不存在两个不同下标 i 和 j ，使得 nums[i] + nums[j] == 3 。
 * 可以证明 4 是符合条件的美丽数组所可能具备的最小和。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 3, target = 3
 * 输出：8
 * 解释：
 * nums = [1,3,4] 是美丽数组。
 * - nums 的长度为 n = 3 。
 * - nums 由两两互不相同的正整数组成。
 * - 不存在两个不同下标 i 和 j ，使得 nums[i] + nums[j] == 3 。
 * 可以证明 8 是符合条件的美丽数组所可能具备的最小和。
 * <p>
 * 示例 3：
 * <p>
 * 输入：n = 1, target = 1
 * 输出：1
 * 解释：nums = [1] 是美丽数组。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^9
 * 1 <= target <= 10^9
 *
 * @author MCW 2024/3/8
 */
public class leetCode2834 {

    /**
     * 方法一：贪心
     * 思路与算法
     * <p>
     * 根据题意，我们需要构造一个大小为 n 的正整数数组，该数组由不同的数字组成，并且没有任意两个数字的和等于 target，
     * 在满足这样的前提下，要保证数组的和最小。
     * <p>
     * 为了让数组之和最小，我们按照 1,2,3,⋯ 的顺序考虑，但添加了 x 之后，就不能添加 target−x，因此最大可以添加到 ⌊target/2⌋，
     * 如果个数还不够 n 个，就继续从 target,target+1,target+2,⋯ 依次添加。由于添加的数字是连续的，所以可以用等差数列求和公式快速求解。
     * <p>
     * 令 m=⌊target/2⌋，然后可以分情况求解：
     * <p>
     * 若 n ≤ m，最小数组和为 ( (1+n)×n ) / 2。
     * 否则 n > m，最小数组和为 ( (1+m)×m )/ 2 + ( (target+(target+(n−m)−1))×(n−m) ) / 2。
     */
    public int minimumPossibleSum(int n, int target) {
        final int MOD = (int) 1e9 + 7;
        int m = target / 2;
        if (n <= m) {
            return (int) ((long) (1 + n) * n / 2 % MOD);
        } else {
            return (int) (((long) (1 + m) * m / 2 + ((long) target + target + (n - m) - 1) * (n - m) / 2) % MOD);
        }
    }

    public static void main(String[] args) {
        System.out.println(new leetCode2834().minimumPossibleSum(3, 3));
    }
}
