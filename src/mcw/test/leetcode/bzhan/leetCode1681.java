package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 给你一个整数数组 nums 和一个整数 k 。你需要将这个数组划分到 k 个相同大小的子集中，使得同一个子集里面没有两个相同的元素。
 * <p>
 * 一个子集的 不兼容性 是该子集里面最大值和最小值的差。
 * <p>
 * 请你返回将数组分成 k 个子集后，各子集 不兼容性 的 和 的 最小值 ，如果无法分成分成 k 个子集，返回 -1 。
 * <p>
 * 子集的定义是数组中一些数字的集合，对数字顺序没有要求。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,1,4], k = 2
 * 输出：4
 * 解释：最优的分配是 [1,2] 和 [1,4] 。
 * 不兼容性和为 (2-1) + (4-1) = 4 。
 * 注意到 [1,1] 和 [2,4] 可以得到更小的和，但是第一个集合有 2 个相同的元素，所以不可行。
 * <p>
 * 示例 2：
 * 输入：nums = [6,3,8,1,3,1,2,2], k = 4
 * 输出：6
 * 解释：最优的子集分配为 [1,2]，[2,3]，[6,8] 和 [1,3] 。
 * 不兼容性和为 (2-1) + (3-2) + (8-6) + (3-1) = 6 。
 * <p>
 * 示例 3：
 * 输入：nums = [5,3,3,6,3,3], k = 3
 * 输出：-1
 * 解释：没办法将这些数字分配到 3 个子集且满足每个子集里没有相同数字。
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= nums.length <= 16
 * nums.length 能被 k 整除。
 * 1 <= nums[i] <= nums.length
 *
 * @author MCW 2023/6/28
 */
public class leetCode1681 {

    /**
     * 对于一个有「二进制」表示中有 k 个 1 的正整数 x，其非空子集有 (2^k) −1 个。所以对于 x 枚举子集的时间复杂度为 O(2^k )。
     * <p>
     * 本题中，数组 nums 的大小  n 最多  16，所以我们可以通过「二进制」和「状态压缩」，用一个整数  mask 来表示一个子集。
     * 从  mask 「二进制」表示的低位到高位，第  i 位为  1 则表示元素  nums[i] 存在这个子集中。
     * <p>
     * 方法一：动态规划 + 状态压缩
     * 思路与算法
     * <p>
     * 题目给定一个整数数组 nums 和一个整数  k。我们需要将这个数组划分到 k 个相同大小的子集中，使得同一个子集里面没有两个相同的元素。
     * 题目保证  nums 大小能被  k 整除。
     * <p>
     * 一个子集的不兼容是该子集里面最大值和最小值的差。
     * 我们需要返回将数组分成  k 个子集后，各子集不兼容性的和的最小值，如果无法分成分成  k 个子集，返回  −1。
     * <p>
     * 首先我们做一步预处理，从 1 到  (1<<n)−1 遍历所有子集，筛选出所有大小符合条件的子集 mask，
     * 并求出子集对应的不兼容性 values[mask] 并保存在哈希表中，以便后续快速查找符合条件的子集。
     * <p>
     * 然后我们尝试用「状态压缩动态规划」来解决本题，设  dp[mask] 表示已经分配过的元素集合的不兼容性之和，
     * dp[0] 初始化为  0，因为集合为空集，其余 dp[mask] 初始化为最大整数，表示尚未确定最小不兼容性。
     * <p>
     * 我们从  1 到  (1<< n)−1 依次遍历  mask 所有状态。
     * 对于每一个状态，我们求出还没有被分配的元素集合  sub，并且保证相等元素只保留最后出现的一个。
     * 再遍历  sub 的子集  nxt，检查是否符合条件，作为下一组划分的元素集合。由此我们可以写出状态转移方程：
     * dp[ mask ∣ nxt]=  min  (dp[mask] + values[nxt])
     *              nxt is valid
     * <p>
     * 按照上面状态转移方程，我们从小到大来遍历每一个状态，最终返回  dp[(1<<n)−1] 的结果，即为划分所有元素后的最小不兼容性。
     */
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length, group = n / k, inf = Integer.MAX_VALUE;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, inf);
        dp[0] = 0;
        var values = new HashMap<Integer, Integer>();
        for (int mask = 1; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) != group) {
                continue;
            }
            int mn = 20, mx = 0;
            var cur = new HashSet<Integer>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) > 0) {
                    if (cur.contains(nums[i])) {
                        break;
                    }
                    cur.add(nums[i]);
                    mn = Math.min(mn, nums[i]);
                    mx = Math.max(mx, nums[i]);
                }
            }
            if (cur.size() == group) {
                values.put(mask, mx - mn);
            }
        }
        for (int mask = 0; mask < (1 << n); mask++) {
            if (dp[mask] == inf) {
                continue;
            }
            var seen = new HashMap<Integer, Integer>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    seen.put(nums[i], i);
                }
            }
            if (seen.size() < group) {
                continue;
            }
            int sub = 0;
            for (int v : seen.values()) {
                sub |= (1 << v);
            }
            int nxt = sub;
            while (nxt > 0) {
                if (values.containsKey(nxt)) {
                    dp[mask | nxt] = Math.min(dp[mask | nxt], dp[mask + values.get(nxt)]);
                }
                nxt = (nxt - 1) & sub;
            }
        }
        return (dp[(1 << n) - 1] < inf) ? dp[(1 << n) - 1] : -1;
    }
}
