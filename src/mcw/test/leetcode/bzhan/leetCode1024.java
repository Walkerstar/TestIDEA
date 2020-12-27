package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 视频片段clips[i]都用区间进行表示：开始于clips[i][0]并于clips[i][1]结束。我们甚至可以对这些片段自由地再剪辑，
 * 例如片段[0, 7]可以剪切成[0, 1] +[1, 3] + [3, 7]三部分。
 *
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，
 * 如果无法完成该任务，则返回-1 。
 *
 * 例：
 * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * 输出：3
 * 解释：
 * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
 * 然后，按下面的方案重制比赛片段：
 * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
 * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
 * @author mcw 2020/10/24 13:41
 */
public class leetCode1024 {


    /**
     * 貪心算法
     * 注意到对于所有左端点相同的子区间，其右端点越远越有利。且最佳方案中不可能出现两个左端点相同的子区间。于是我们预处理所有的子区间，对于每一个位置 ii，
     * 我们记录以其为左端点的子区间中最远的右端点，记为 {maxn}[i]。
     *
     * 具体地，我们枚举每一个位置，假设当枚举到位置 ii 时，记左端点不大于 ii 的所有子区间的最远右端点为 last。这样 last 就代表了当前能覆盖到的最远的右端点。
     *
     * 每次我们枚举到一个新位置，我们都用 maxn[i] 来更新 last。如果更新后 last==i，那么说明下一个位置无法被覆盖，我们无法完成目标。
     *
     * 同时我们还需要记录上一个被使用的子区间的结束位置为 pre，每次我们越过一个被使用的子区间，就说明我们要启用一个新子区间，这个新子区间的结束位置即为当前的 last。
     * 也就是说，每次我们遇到 i==pre，则说明我们用完了一个被使用的子区间。这种情况下我们让答案加 1，并更新 pre 即可。
     */
    public int videoStitching(int[][] clips, int t) {
        int[] max = new int[t];
        int last = 0;
        int ret = 0;
        int pre = 0;
        for (int[] clip : clips) {
            if (clip[0] < t) {
                max[clip[0]] = Math.max(max[clip[0]], clip[1]);
            }
        }
        for (int i = 0; i < t; i++) {
            last = Math.max(last, max[i]);
            if (i == last) {
                return -1;
            }
            if (i == pre) {
                ret++;
                pre = last;
            }
        }
        return ret;
    }

    /**
     * 动态规划
     * 我们令 dp[i] 表示将区间 [0,i) 覆盖所需的最少子区间的数量。由于我们希望子区间的数目尽可能少，
     * 因此可以将所有 dp[i] 的初始值设为一个大整数，并将 dp[0]（即空区间）的初始值设为 0。
     *
     * 我们可以枚举所有的子区间来依次计算出所有的 dp 值。我们首先枚举 i，同时对于任意一个子区间 [a_j,b_j)，
     * 若其满足 a_j < i <=b_j，那么它就可以覆盖区间 [0,i) 的后半部分，而前半部分则可以用 dp[a_j] 对应的最优方法进行覆盖，
     * 因此我们可以用 dp[a_j] + 1来更新 dp[i]。状态转移方程如下：
     *  dp[i] = min { dp[a_j] } + 1  (a_j < i <= b_j)
     * 最终的答案即为 dp[T]。
     */
    public int videoStitching1(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip : clips) {
                if (clip[0] < i && i <= clip[i]) {
                    dp[i] = Math.min(dp[i], dp[clip[0] + 1]);
                }
            }
        }
        return dp[T] == Integer.MAX_VALUE - 1 ? -1 : dp[T];
    }

}
