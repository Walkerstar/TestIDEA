package mcw.test.leetcode.bzhan;

/**
 * 共有 n 位员工，每位员工都有一个从 0 到 n - 1 的唯一 id 。
 * <p>
 * 给你一个二维整数数组 logs ，其中 logs[i] = [idi, leaveTimei] ：
 * <p>
 * idi 是处理第 i 个任务的员工的 id ，且
 * leaveTimei 是员工完成第 i 个任务的时刻。所有 leaveTimei 的值都是 唯一 的。
 * 注意，第 i 个任务在第 (i - 1) 个任务结束后立即开始，且第 0 个任务从时刻 0 开始。
 * <p>
 * 返回处理用时最长的那个任务的员工的 id 。如果存在两个或多个员工同时满足，则返回几人中 最小 的 id 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10, logs = [[0,3],[2,5],[0,9],[1,15]]
 * 输出：1
 * 解释：
 * 任务 0 于时刻 0 开始，且在时刻 3 结束，共计 3 个单位时间。
 * 任务 1 于时刻 3 开始，且在时刻 5 结束，共计 2 个单位时间。
 * 任务 2 于时刻 5 开始，且在时刻 9 结束，共计 4 个单位时间。
 * 任务 3 于时刻 9 开始，且在时刻 15 结束，共计 6 个单位时间。
 * 时间最长的任务是任务 3 ，而 id 为 1 的员工是处理此任务的员工，所以返回 1 。
 * 示例 2：
 * <p>
 * 输入：n = 26, logs = [[1,1],[3,7],[2,12],[7,17]]
 * 输出：3
 * 解释：
 * 任务 0 于时刻 0 开始，且在时刻 1 结束，共计 1 个单位时间。
 * 任务 1 于时刻 1 开始，且在时刻 7 结束，共计 6 个单位时间。
 * 任务 2 于时刻 7 开始，且在时刻 12 结束，共计 5 个单位时间。
 * 任务 3 于时刻 12 开始，且在时刻 17 结束，共计 5 个单位时间。
 * 时间最长的任务是任务 1 ，而 id 为 3 的员工是处理此任务的员工，所以返回 3 。
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 500
 * 1 <= logs.length <= 500
 * logs[i].length == 2
 * 0 <= idi <= n - 1
 * 1 <= leaveTimei <= 500
 * idi != idi + 1
 * leaveTimei 按严格递增顺序排列
 *
 * @author MCW 2023/5/5
 */
public class leetCode2432 {

    /**
     * 我们可以对数组 logs 进行一次遍历，算出其中每位员工的处理用时并得到答案。
     * <p>
     * 具体地，我们在遍历时维护两个变量 maxCost 和 ans，前者表示最长的处理用时，后者表示其对应的员工。
     * 首个任务从时刻 0 开始，因此初始时，我们将这两个变量赋值为 logs[0] 中的两个值。
     * 随后我们从 logs[1] 开始遍历，通过相邻两项的差值计算出处理用时，并根据题目的要求对于 maxCost 和 ans 进行更新即可。
     */
    public int hardestWorker(int n, int[][] logs) {
        int ans = logs[0][0], maxCost = logs[0][1];
        for (int i = 1; i < logs.length; i++) {
            int idx = logs[i][0];
            int cost = logs[i][1] - logs[i - 1][1];
            if (cost > maxCost || (cost == maxCost && idx < ans)) {
                ans = idx;
                maxCost = cost;
            }
        }
        return ans;
    }
}
