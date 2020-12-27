package mcw.test.leetcode.bzhan;

/**
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * @author mcw 2020/12/17 19:58
 */
public class leetCode714 {
    /**
     * 动态规划
     * 1.定义二维数组 dp[n][2]：
     * dp[i][0] 表示第 i 天不持有可获得的最大利润；
     * dp[i][1] 表示第 i 天持有可获得的最大利润（注意是第 i 天持有，而不是第 i 天买入）。
     *
     * 定义状态转移方程：
     * 不持有：dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee)
     *      对于今天不持有，可以从两个状态转移过来：1. 昨天也不持有；2. 昨天持有，今天卖出。两者取较大值。
     *
     * 持有：dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i])
     *      对于今天持有，可以从两个状态转移过来：1. 昨天也持有；2. 昨天不持有，今天买入。两者取较大值。
     *
     * 2. 给定转移方程初始值
     * 对于第 0 天：
     *
     * 不持有： dp[0][0] = 0
     * 持有（即花了 price[0] 的钱买入）： dp[0][1] = -prices[0]
     *
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    public int maxProfit1(int[] prices, int fee) {
        int n = prices.length;
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = -prices[0];
        for (int i = 1; i < n; i++) {
            int tmp = dp[0];
            dp[0] = Math.max(dp[0], dp[1] + prices[i] - fee);
            dp[1] = Math.max(dp[1], tmp - prices[i]);
        }
        return dp[0];
    }

    /**
     * 贪心算法
     * 在初始时，buy 的值为 prices[0] 加上手续费 fee。那么当我们遍历到第 i (i>0) 天时：
     *
     * 1.如果当前的股票价格 prices[i] 加上手续费 fee 小于 buy，那么与其使用 buy 的价格购买股票，我们不如以 prices[i]+fee 的价格
     * 购买股票，因此我们将 buy 更新为 prices[i]+fee；
     *
     * 2.如果当前的股票价格 prices[i] 大于 buy，那么我们直接卖出股票并且获得 prices[i]−buy 的收益。但实际上，我们此时卖出股票可能
     * 并不是全局最优的（例如下一天股票价格继续上升），因此我们可以提供一个反悔操作，看成当前手上拥有一支买入价格为 prices[i] 的股票，
     * 将 buy 更新为 prices[i]。这样一来，如果下一天股票价格继续上升，我们会获得 prices[i+1]−prices[i] 的收益，
     * 加上这一天 prices[i]−buy 的收益，恰好就等于在这一天不进行任何操作，而在下一天卖出股票的收益；
     *
     * 对于其余的情况，prices[i] 落在区间 [buy−fee,buy] 内，它的价格没有低到我们放弃手上的股票去选择它，也没有高到我们可以通过卖出
     * 获得收益，因此我们不进行任何操作。
     */
    public int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                profit += prices[i] - buy;
                buy = prices[i];
            }
        }
        return profit;
    }
}
