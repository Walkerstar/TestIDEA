package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数 money ，表示你总共有的钱数（单位为美元）和另一个整数 children ，表示你要将钱分配给多少个儿童。
 * <p>
 * 你需要按照如下规则分配：
 * <p>
 * 所有的钱都必须被分配。
 * 每个儿童至少获得 1 美元。
 * 没有人获得 4 美元。
 * 请你按照上述规则分配金钱，并返回 最多 有多少个儿童获得 恰好 8 美元。如果没有任何分配方案，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：money = 20, children = 3
 * 输出：1
 * 解释：
 * 最多获得 8 美元的儿童数为 1 。一种分配方案为：
 * - 给第一个儿童分配 8 美元。
 * - 给第二个儿童分配 9 美元。
 * - 给第三个儿童分配 3 美元。
 * 没有分配方案能让获得 8 美元的儿童数超过 1 。
 * 示例 2：
 * <p>
 * 输入：money = 16, children = 2
 * 输出：2
 * 解释：每个儿童都可以获得 8 美元。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= money <= 200
 * 2 <= children <= 30
 *
 * @author MCW 2023/9/22
 */
public class leetCode2591 {

    /**
     * 方法一：贪心
     * 思路与算法
     * <p>
     * 首先，若 money < children，则无解，返回 −1。
     * <p>
     * 然后，给每个人先分配 1 美元，令 money 减去 children。
     * <p>
     * 接着，给尽可能多的人分配 7 美元（加上前面分配的 1 美元就是 8 美元），这样最多分给 cnt=min(⌊ money / 7 ⌋,children) 个人。
     * <p>
     * 令 money 减去 7×cnt，children 减去 cnt。此时还剩余 money 美元和 children 个人，进行分类讨论：
     * <p>
     * 1. 若剩余 0 个人，并且 money > 0，那么将所有的美元分配给一个已经分到 8 美元的人，令 cnt 减去 1。
     * 2. 若剩余 1 个人，并且 money = 3，为了避免分到 4 美元，并注意到题目输入中的 children>=2，因此将这 3 美元拆成两部分，将其中的一部分分配给已经分到 8 美元的人，令 cnt 减去 1。
     * 3. 对于其他情况，若 money > 0，可以将所有美元分配给一个人，cnt 不变。
     */
    public int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }
        money -= children;
        int cnt = Math.min(money / 7, children);
        money -= cnt * 7;
        if ((children == 0 && money > 0) || (children == 1 && money == 3)) {
            cnt--;
        }
        return cnt;
    }

}
