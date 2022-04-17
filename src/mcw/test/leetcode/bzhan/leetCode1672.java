package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给你一个 m x n 的整数网格 accounts ，其中 accounts[i][j] 是第 i  位客户在第 j 家银行托管的资产数量。返回最富有客户所拥有的 资产总量 。
 * <p>
 * 客户的 资产总量 就是他们在各家银行托管的资产数量之和。最富有客户就是 资产总量 最大的客户。
 *
 * @author MCW 2022/4/14
 */
public class leetCode1672 {

    public int maximumWealth(int[][] accounts) {
        int maxWealth = Integer.MAX_VALUE;
        for (int[] account : accounts) {
            maxWealth = Math.max(maxWealth, Arrays.stream(account).sum());
        }
        return maxWealth;
    }
}
