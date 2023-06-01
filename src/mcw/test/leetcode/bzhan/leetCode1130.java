package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：
 * <p>
 * 每个节点都有 0 个或是 2 个子节点。
 * 数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。
 * 每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
 * 在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。
 * <p>
 * 如果一个节点有 0 个子节点，那么该节点为叶节点。
 * <p>
 * 示例 1：
 * 输入：arr = [6,2,4]
 * 输出：32
 * 解释：有两种可能的树，第一种的非叶节点的总和为 36 ，第二种非叶节点的总和为 32 。
 * <p>
 * 示例 2：
 * 输入：arr = [4,11]
 * 输出：44
 * <p>
 * 提示：
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * 答案保证是一个 32 位带符号整数，即小于 2^31 。
 *
 * @author MCW 2023/5/31
 */
public class leetCode1130 {

    /**
     * 方法一：动态规划
     * 已知数组 arr 与二叉树的中序遍历的所有叶子节点对应，并且二叉树的每个节点都有 0 个节点或 2 个节点。
     * <p>
     * 考虑数组 arr 可以生成的所有二叉树，我们可以将 arr 切分成任意两个非空子数组，分别对应左子树和右子树，
     * 然后递归地对两个非空子树组执行相同的操作，直到子数组大小等于 1，即叶子节点，那么一种切分方案对应一个合法的二叉树。
     * <p>
     * 使用 dp[i][j] 表示子数组 [i,j] (i≤j) 对应的子树所有非叶子节点的最小总和，
     * 那么 dp[i][j] 可以通过切分子树求得，状态转移方程如下：
     * <p>
     * dp[i][j]= 0,                                             i=j
     * min( dp[i][k] + dp[k+1][j] + m_ik × m_(k+1)j),  i<j
     * k∈[i,j)
     * <p>
     * 其中 m_ik 表示子数组  [i,k] 的最大值，可以预先计算并保存下来。
     */
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 4);
        }
        int[][] mval = new int[n][n];
        for (int j = 0; j < n; j++) {
            mval[j][j] = arr[j];
            dp[j][j] = 0;
            for (int i = j - 1; i >= 0; i--) {
                mval[i][j] = Math.max(arr[i], mval[i + 1][j]);
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + mval[i][k] * mval[k + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
