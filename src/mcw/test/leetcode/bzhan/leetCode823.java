package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给出一个含有不重复整数元素的数组 arr ，每个整数 arr[i] 均大于 1。
 * <p>
 * 用这些整数来构建二叉树，每个整数可以使用任意次数。其中：每个非叶结点的值应等于它的两个子结点的值的乘积。
 * <p>
 * 满足条件的二叉树一共有多少个？答案可能很大，返回 对 10^9 + 7 取余 的结果。
 * <p>
 * 示例 1:
 * 输入: arr = [2, 4]
 * 输出: 3
 * 解释: 可以得到这些二叉树: [2], [4], [4, 2, 2]
 * <p>
 * 示例 2:
 * 输入: arr = [2, 4, 5, 10]
 * 输出: 7
 * 解释: 可以得到这些二叉树: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 1000
 * 2 <= arr[i] <= 10^9
 * arr 中的所有值 互不相同
 *
 * @author MCW 2023/8/29
 */
public class leetCode823 {

    /**
     * 方法一：动态规划 + 双指针
     * 因为每个整数 arr[i] 均大于 1，因此每个非叶结点的值都大于它的子结点的值。
     * 考虑以 arr[i] 为根结点的带因子的二叉树，那么它的所有子孙结点的值都小于 arr[i]。
     * 我们将 arr 从小到大进行排序，那么对于以 arr[i] 为根结点的带因子的二叉树，它的子孙结点值的下标只能在区间 [0,i−1) 中。
     * 使用 dp[i] 保存以 arr[i] 为根结点的带因子的二叉树数目。我们从区间 [0,i−1) 内枚举 arr[i] 的子结点，
     * 假设存在 0 ≤ left ≤ right < i ，使 arr[left] × arr[right] = arr[i] 成立，那么 arr[left] 和 arr[right] 可以作为 arr[i] 的两个子结点。
     * 同时 arr[left] 和 arr[right] 为根结点的带因子二叉树数目分别为 dp[left] 和 dp[right]，
     * 不难推导出 arr[left] 和 arr[right] 作为 arr[i] 的两个子结点时，带因子二叉树数目 s 为：
     * <p>
     * left = right 时，s = dp[left] × dp[right]
     * <p>
     * left ≠ right 时，因为两个子结点可以交换，所以 s = dp[left] × dp[right] × 2
     * <p>
     * 当 arr[i] 没有子结点时，对应 1 个带因子二叉树。因此，状态转移方程为：
     * <p>
     * dp[i] = 1 +     ∑  dp[left] × dp[right] × (1 + f(left,right))
     * (left,right) ∈ U
     * <p>
     * 其中 (left,right) ∈ U 表示所有满足 0 ≤ left ≤ right < i 且 arr[left] × arr[right] = arr[i] 时，两个子结点可以交换）。
     * <p>
     * 找出 (left,right)∈U 的所有 (left,right) 可以使用双指针进行查找。
     */
    public int numFactoredBinaryTrees(int[] arr) {
        // 我们将 arr 从小到大进行排序，那么对于以 arr[i] 为根结点的带因子的二叉树，它的子孙结点值的下标只能在区间 [0, i - 1) 中。
        Arrays.sort(arr);
        int n = arr.length;
        // dp[i] 保存以 arr[i] 为根结点的带因子的二叉树数目
        long[] dp = new long[n];
        long res = 0, mod = 1000000007;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int left = 0, right = i - 1; left <= right; left++) {
                while (right >= left && (long) arr[left] * arr[right] > arr[i]) {
                    right--;
                }
                if (right >= left && (long) arr[left] * arr[right] == arr[i]) {
                    if (right != left) {
                        dp[i] = (dp[i] + dp[left] * dp[right] * 2) % mod;
                    } else {
                        dp[i] = (dp[i] + dp[left] * dp[right]) % mod;
                    }
                }
            }
            res = (res + dp[i]) % mod;
        }
        return (int) res;
    }
}
