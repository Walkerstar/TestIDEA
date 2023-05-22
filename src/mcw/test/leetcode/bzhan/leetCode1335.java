package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 你需要制定一份 d 天的工作计划表。工作之间存在依赖，要想执行第 i 项工作，你必须完成全部 j 项工作（ 0 <= j < i）。
 * <p>
 * 你每天 至少 需要完成一项任务。工作计划的总难度是这 d 天每一天的难度之和，而一天的工作难度是当天应该完成工作的最大难度。
 * <p>
 * 给你一个整数数组 jobDifficulty 和一个整数 d，分别代表工作难度和需要计划的天数。第 i 项工作的难度是 jobDifficulty[i]。
 * <p>
 * 返回整个工作计划的 最小难度 。如果无法制定工作计划，则返回 -1 。
 * <p>
 * 示例 1：
 * 输入：jobDifficulty = [6,5,4,3,2,1], d = 2
 * 输出：7
 * 解释：第一天，您可以完成前 5 项工作，总难度 = 6.
 * 第二天，您可以完成最后一项工作，总难度 = 1.
 * 计划表的难度 = 6 + 1 = 7
 * <p>
 * 示例 2：
 * 输入：jobDifficulty = [9,9,9], d = 4
 * 输出：-1
 * 解释：就算你每天完成一项工作，仍然有一天是空闲的，你无法制定一份能够满足既定工作时间的计划表。
 * <p>
 * 示例 3：
 * 输入：jobDifficulty = [1,1,1], d = 3
 * 输出：3
 * 解释：工作计划为每天一项工作，总难度为 3 。
 * <p>
 * 示例 4：
 * 输入：jobDifficulty = [7,1,7,1,7,1], d = 3
 * 输出：15
 * <p>
 * 示例 5：
 * 输入：jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
 * 输出：843
 * <p>
 * 提示：
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 *
 * @author MCW 2023/5/16
 */
public class leetCode1335 {

    /**
     * 方法一：动态规划
     * 思路与算法
     * <p>
     * 题目给出了 n 份工作，其中第 i 份（工作下标从0 开始计算）工作的工作强度为 jobDifficulty[i]， 0 ≤ i < n。
     * 现在我们需要将 n 份工作分配到 d 天完成，其中每一天至少需要完成一份工作，并且在完成第 i 份工作时，必须完成全部第 j 份工作， 0 ≤ j < i。
     * 每一天的工作难度为当天应该完成工作的最大难度，现在需要求整个工作计划的最小难度。
     * <p>
     * 首先当工作份数小于 d 时，因为每天至少需要完成一份工作，所以此时无法制定工作计划，直接返回 −1。否
     * 则我们设 dp[i][j] 表示前 i+1 天完成前 j 项工作的最小难度，有：
     * <p>
     * dp[i][j] =   min      { dp[i−1][k] + f(k+1,j) }
     * k=i−1,i,…,j−1
     * <p>
     * 其中 k 为前 i 天完成的工作份数， f(i,j) 表示第 i 份工作到第 j 份工作的最大工作强度，即：
     * f(i,j)=    max   { jobDifficulty[t] }
     * t=i,i+1,…,j
     * <p>
     * 以上的讨论建立在 i>0 的基础上，边界条件当i=0 时，有：
     * <p>
     * dp[i][j] = f(0,j)
     * <p>
     * 最后我们返回 dp[d−1][n−1] 即可。
     * <p>
     * 在实现的过程中可以发现  dp[i] 的求解只与上一层状态 dp[i−1] 有关，因此我们可以通过「滚动数组」来实现编码过程中的空间优化。
     */
    public int midDifficulty1(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        int[][] dp = new int[d + 1][n];
        for (int i = 0; i <= d; i++) {
            Arrays.fill(dp[i], 0x3f3f3f);
        }
        int ma = 0;
        for (int i = 0; i < n; i++) {
            ma = Math.max(ma, jobDifficulty[i]);
            dp[0][i] = ma;
        }
        for (int i = 1; i < d; i++) {
            for (int j = i; j < n; j++) {
                ma = 0;
                for (int k = j; k >= i; k--) {
                    ma = Math.min(dp[i][j], ma + dp[i - 1][k - 1]);
                }
            }
        }
        return dp[d - 1][n - 1];
    }

    /**
     * 通过滚动数组 空间优化
     */
    public int minDifficulty1_1(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        int[] dp = new int[n];
        for (int i = 0, j = 0; i < n; ++i) {
            j = Math.max(j, jobDifficulty[i]);
            dp[i] = j;
        }
        for (int i = 1; i < d; ++i) {
            int[] ndp = new int[n];
            Arrays.fill(ndp, 0x3f3f3f3f);
            for (int j = i; j < n; ++j) {
                int ma = 0;
                for (int k = j; k >= i; --k) {
                    ma = Math.max(ma, jobDifficulty[k]);
                    ndp[j] = Math.min(ndp[j], ma + dp[k - 1]);
                }
            }
            dp = ndp;
        }
        return dp[n - 1];
    }

    /**
     * 方法二：单调栈
     * 思路与算法
     * <p>
     * 现在对于前 j 份工作，找到小于 j 的最大下标 p，使得 jobDifficulty[p] > jobDifficulty[j]。
     * <p>
     * 那么在「方法一」中，对于 dp[i][j] 的求解可以分解为：
     * <p>
     * 当 k ≥ p 时，有 f(k+1,j)=jobDifficulty[j]，得：
     * dp[i][j] = dp[i-1][k] + jobDifficulty[j] {k=p,p+1,...,j - 1}
     * <p>
     * 当 k < p 时，有 f(k+1,j) = f(k+1,p)，得：
     * dp[i][j] = dp[i-1][k] + f(k + 1, p) ={dp}[i][p]     {k = i-1,i,....,p - 1}
     * <p>
     * 在求解 dp[i][j]，i ≤ j < n 时，对于 p 的求解，类似于 739. 每日温度，我们可以通过「单调栈」来进行求解，
     * 这样对于 j 求解 p 的均摊时间复杂度为 O(1)。
     * <p>
     * 我们维护一个存储二元组 (idx_i,val_i) 的单调栈，从栈底到栈顶的二元组对应的 idx_i 依次递减，
     * 其中 idx_i  为对应的工作下标，val_i  表示相应区间的最小值，
     * 具体来说，如果现在正在求解状态 dp[i][j]，i>0，且「单调栈」中所存的下标为 idx_1,idx_2, ,…,id_x，
     * 则 val_1 表示区间 dp[i−1][0] 到  dp[i−1][idx_1 − 1] 的最小值，val_2 表示区间 dp[i−1][idx_1 - 1 ] 到 dp[i−1][idx_2 − 1] 的最小值，
     * 以此类推。
     * <p>
     * 这样在维护「单调栈」的过程中，就可以在得到对应 p 的同时，得到：
     * k =    min    dp[k][j−1]
     * p,p+1,…,j−1
     * <p>
     * 以上的分析在  i>0 的基础上，同样与「方法一」相同，当  i=0 时为边界情况，有：
     * dp[i][j] = f(0,j)
     * <p>
     * 最后我们返回 dp[d−1][n−1] 即可。在实现的过程中同样可以发现  dp[i] 的求解只与上一层状态  dp[i−1] 有关，
     * 因此我们可以通过「滚动数组」来实现编码过程中的空间优化。
     */
    public int minDifficulty2(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        int[][] dp = new int[d][n];
        for (int j = 0, ma = 0; j < n; ++j) {
            ma = Math.max(ma, jobDifficulty[j]);
            dp[0][j] = ma;
        }
        for (int i = 1; i < d; ++i) {
            Deque<int[]> stack = new ArrayDeque<int[]>();
            for (int j = i; j < n; ++j) {
                int mi = dp[i - 1][j - 1];
                while (!stack.isEmpty() && jobDifficulty[stack.peek()[0]] < jobDifficulty[j]) {
                    mi = Math.min(mi, stack.pop()[1]);
                }
                if (stack.isEmpty()) {
                    dp[i][j] = mi + jobDifficulty[j];
                } else {
                    dp[i][j] = Math.min(dp[i][stack.peek()[0]], mi + jobDifficulty[j]);
                }
                stack.push(new int[]{j, mi});
            }
        }
        return dp[d - 1][n - 1];
    }

    public int minDifficulty2_1(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        int[] dp = new int[n];
        for (int j = 0, ma = 0; j < n; ++j) {
            ma = Math.max(ma, jobDifficulty[j]);
            dp[j] = ma;
        }
        for (int i = 1; i < d; ++i) {
            Deque<int[]> stack = new ArrayDeque<int[]>();
            int[] ndp = new int[n];
            for (int j = i; j < n; ++j) {
                int mi = dp[j - 1];
                while (!stack.isEmpty() && jobDifficulty[stack.peek()[0]] < jobDifficulty[j]) {
                    mi = Math.min(mi, stack.pop()[1]);
                }
                if (stack.isEmpty()) {
                    ndp[j] = mi + jobDifficulty[j];
                } else {
                    ndp[j] = Math.min(ndp[stack.peek()[0]], mi + jobDifficulty[j]);
                }
                stack.push(new int[]{j, mi});
            }
            dp = ndp;
        }
        return dp[n - 1];
    }
}
