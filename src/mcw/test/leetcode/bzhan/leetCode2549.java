package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 2549. 统计桌面上的不同数字
 * <p>
 * 给你一个正整数 n ，开始时，它放在桌面上。在 109 天内，每天都要执行下述步骤：
 * <p>
 * 对于出现在桌面上的每个数字 x ，找出符合 1 <= i <= n 且满足 x % i == 1 的所有数字 i 。
 * 然后，将这些数字放在桌面上。
 * 返回在 10^9 天之后，出现在桌面上的 不同 整数的数目。
 * <p>
 * 注意：
 * <p>
 * 一旦数字放在桌面上，则会一直保留直到结束。
 * % 表示取余运算。例如，14 % 3 等于 2 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5
 * 输出：4
 * 解释：最开始，5 在桌面上。
 * 第二天，2 和 4 也出现在桌面上，因为 5 % 2 == 1 且 5 % 4 == 1 。
 * 再过一天 3 也出现在桌面上，因为 4 % 3 == 1 。
 * 在十亿天结束时，桌面上的不同数字有 2 、3 、4 、5 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 3
 * 输出：2
 * 解释：
 * 因为 3 % 2 == 1 ，2 也出现在桌面上。
 * 在十亿天结束时，桌面上的不同数字只有两个：2 和 3 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 100
 *
 * @author MCW 2024/3/23
 */
public class leetCode2549 {

    /**
     * 方法一：模拟
     * 我们使用数组 nums 记录桌面上已经出现的正整数，初始时 nums[n]=1 表示桌面上只出现正整数 n。
     * <p>
     * 每天都对桌面上已出现的数字进行遍历，
     * 对于当前遍历的数字 x，枚举正整数 i∈[1,n]，如果 x mod i = 1，那么令 nums[i]=1，即将数字 i 放到桌面上。
     * 最后统计 nums 中值为 1 的元素数目即可。
     */
    public int distinctIntegers(int n) {
        int[] nums = new int[n + 1];
        nums[n] = 1;
        for (int k = 0; k < n; k++) {
            for (int x = 1; x <= n; x++) {
                if (nums[x] == 0) {
                    continue;
                }
                for (int i = 1; i <= n; i++) {
                    if (x % i == 1) {
                        nums[i] = 1;
                    }
                }
            }
        }
        return Arrays.stream(nums).sum();
    }

    /**
     * 方法二：数学
     * 如果桌面上存在正整数 x>2，因为 x mod (x−1) = 1，所以在执行题目的步骤后，x−1 会出现在桌面上。对 n 进行分类讨论：
     * <p>
     * 当 n > 1 时，那么经过多次操作后，一定可以将 n−1,n−2,…,2 依次放到桌面上。
     * <p>
     * 当 n = 1 时，桌面只有一个数字 1。
     */
    public int distinctIntegers2(int n) {
        return n == 1 ? 1 : n - 1 ;
    }
}
