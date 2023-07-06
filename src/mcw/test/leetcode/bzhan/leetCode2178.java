package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数 finalSum 。请你将它拆分成若干个 互不相同 的正偶数之和，且拆分出来的正偶数数目 最多 。
 * <p>
 * 比方说，给你 finalSum = 12 ，那么这些拆分是 符合要求 的（互不相同的正偶数且和为 finalSum）：(2 + 10) ，(2 + 4 + 6) 和 (4 + 8) 。它们中，(2 + 4 + 6) 包含最多数目的整数。注意 finalSum 不能拆分成 (2 + 2 + 4 + 4) ，因为拆分出来的整数必须互不相同。
 * 请你返回一个整数数组，表示将整数拆分成 最多 数目的正偶数数组。如果没有办法将 finalSum 进行拆分，请你返回一个 空 数组。你可以按 任意 顺序返回这些整数。
 * <p>
 * 示例 1：
 * 输入：finalSum = 12
 * 输出：[2,4,6]
 * 解释：以下是一些符合要求的拆分：(2 + 10)，(2 + 4 + 6) 和 (4 + 8) 。
 * (2 + 4 + 6) 为最多数目的整数，数目为 3 ，所以我们返回 [2,4,6] 。
 * [2,6,4] ，[6,2,4] 等等也都是可行的解。
 * <p>
 * 示例 2：
 * 输入：finalSum = 7
 * 输出：[]
 * 解释：没有办法将 finalSum 进行拆分。
 * 所以返回空数组。
 * <p>
 * 示例 3：
 * 输入：finalSum = 28
 * 输出：[6,8,2,12]
 * 解释：以下是一些符合要求的拆分：(2 + 26)，(6 + 8 + 2 + 12) 和 (4 + 24) 。
 * (6 + 8 + 2 + 12) 有最多数目的整数，数目为 4 ，所以我们返回 [6,8,2,12] 。
 * [10,2,4,12] ，[6,2,4,16] 等等也都是可行的解。
 * <p>
 * 提示：
 * <p>
 * 1 <= finalSum <= 10^10
 *
 * @author MCW 2023/7/6
 */
public class leetCode2178 {
    /**
     * 方法一：贪心
     * 首先，如果 finalSum 为奇数，那么无法拆分为若干偶数，我们返回空数组即可。
     * <p>
     * 其次，我们希望拆分成尽可能多的偶数，我们应该尽可能拆份成最小的若干个偶数。
     * 从最小的偶整数 2 开始依次尝试拆分，直到剩余的数值小于等于当前被拆分的最大偶整数为止。
     * 此时，我们已经拆分成尽可能多的偶数，不可能拆分出更多的互不相同的偶数。
     * 如果此时拆分后剩余的 finalSum 大于零，则将这个数值加到最大的偶整数上，从而保证所有的数互不相同。
     */
    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> result = new ArrayList<>();
        if (finalSum % 2 > 0) {
            return result;
        }
        for (long i = 2; i <= finalSum; i += 2) {
            result.add(i);
            finalSum -= i;
        }
        result.set(result.size(), result.get(result.size() - 1) + finalSum);
        return result;
    }
}
