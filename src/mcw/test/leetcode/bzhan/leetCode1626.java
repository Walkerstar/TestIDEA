package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
 * <p>
 * 然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
 * <p>
 * 给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * 输出：34
 * 解释：你可以选中所有球员。
 * 示例 2：
 * <p>
 * 输入：scores = [4,5,6,5], ages = [2,1,2,1]
 * 输出：16
 * 解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
 * 示例 3：
 * <p>
 * 输入：scores = [1,2,3,5], ages = [8,9,10,1]
 * 输出：6
 * 解释：最佳的选择是前 3 名球员。
 * <p>
 * 提示：
 * <p>
 * 1 <= scores.length, ages.length <= 1000
 * scores.length == ages.length
 * 1 <= scores[i] <= 10^6
 * 1 <= ages[i] <= 1000
 *
 * @author mcw 2023/3/22 10:29
 */
public class leetCode1626 {

    /**
     * 动态优化
     */
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] people = new int[n][2];
        for (int i = 0; i < n; i++) {
            people[i] = new int[]{scores[i], ages[i]};
        }
        //首先我们将所有队员按照分数升序进行排序，分数相同时，则按照年龄升序进行排序，
        Arrays.sort(people, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        //设 dp[i] 为我们最后组建的球队中的最大球员序号为排序后的第 i 名球员时的球队最大分数（此时的球员序号为排序后的新序号）
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (people[j][1] <= people[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += people[i][0];
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    int maxAge;
    int[] t;
    int[][] people;

    /**
     * 树状数组优化
     */
    public int bestSource2(int[] scores, int[] ages) {
        maxAge = Arrays.stream(ages).max().getAsInt();
        t = new int[maxAge + 1];
        int res = 0;
        int n = scores.length;
        people = new int[n][2];
        for (int i = 0; i < n; i++) {
            people[i] = new int[]{scores[i], ages[i]};
        }
        Arrays.sort(people, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        for (int i = 0; i < n; i++) {
            int score = people[i][0], age = people[i][1], cur = score + query(age);
            update(age, cur);
            res = Math.max(res, cur);
        }
        return res;
    }

    public int lowBit(int x) {
        return x & (-x);
    }

    public void update(int i, int val) {
        for (; i <= maxAge; i += lowBit(i)) {
            t[i] = Math.max(t[i], val);
        }
    }

    public int query(int i) {
        int ret = 0;
        for (; i > 0; i -= lowBit(i)) {
            ret = Math.max(ret, t[i]);
        }
        return ret;
    }
}
