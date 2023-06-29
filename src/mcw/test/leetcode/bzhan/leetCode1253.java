package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 2 行 n 列的二进制数组：
 * <p>
 * 矩阵是一个二进制矩阵，这意味着矩阵中的每个元素不是 0 就是 1。
 * 第 0 行的元素之和为 upper。
 * 第 1 行的元素之和为 lower。
 * 第 i 列（从 0 开始编号）的元素之和为 colsum[i]，colsum 是一个长度为 n 的整数数组。
 * 你需要利用 upper，lower 和 colsum 来重构这个矩阵，并以二维整数数组的形式返回它。
 * <p>
 * 如果有多个不同的答案，那么任意一个都可以通过本题。
 * <p>
 * 如果不存在符合要求的答案，就请返回一个空的二维数组。
 * <p>
 * 示例 1：
 * 输入：upper = 2, lower = 1, colsum = [1,1,1]
 * 输出：[[1,1,0],[0,0,1]]
 * 解释：[[1,0,1],[0,1,0]] 和 [[0,1,1],[1,0,0]] 也是正确答案。
 * <p>
 * 示例 2：
 * 输入：upper = 2, lower = 3, colsum = [2,2,1,1]
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
 * 输出：[[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
 * <p>
 * 提示：
 * <p>
 * 1 <= colsum.length <= 10^5
 * 0 <= upper, lower <= colsum.length
 * 0 <= colsum[i] <= 2
 *
 * @author MCW 2023/6/29
 */
public class leetCode1253 {

    /**
     * 方法一：贪心
     * 思路与算法
     * <p>
     * 我们需要求得任意一个 2 行 n 列的二进制数组（其中行和列的序号从 0 开始），满足数组中的每一个元素不是 0 就是 1，
     * 并且第 0 行的元素和为 upper，第 1 行的元素和为 lower，第 i 列的元素之和为 colsum[i]，若不存在直接返回一个空的二维数组即可。
     * <p>
     * 记 sum 为数组 colsum 的元素和， two 为数组 colsum 中 2 的个数。明显当 sum ≠ upper+lower 时，一定不存在满足题意的矩阵。
     * 然后当第 i 列 colsum[i]=2 时，第 i 列的两个元素只能都为 1。那么如果 two > min{upper,lower} 时，此时同样不存在满足题意的矩阵。
     * <p>
     * 否则我们一定可以通过下述的方案来构造一个符合题目要求的矩阵。设结果矩阵为 res[2][n]。当第 i 列 colsum[i] 等于 0 或者 2 时只有一种情况：
     * colsum[i]=0 时： res[0][i]=res[1][i]=0。
     * colsum[i]=2 时： res[0][i]=res[1][i]=1。
     * 所以现在我们只关注 colsum[i]=1 的情况。首先我们将初始的 upper 和 lower 减去数组 colsum 中 2 的个数 two，
     * 那么现在 upper+lower 为数组 colsum 中 1 的个数。那么我们将从左到右遍历 colsum 中的每一列，若第 i 列 colsum[i] 等于 1：
     * <p>
     * 1. 若 upper > 0，则我们在该列的第一行放置 1，第二行放置 0： res[0][i]=1， res[1][i]=0，并且 upper 减一。
     * 2. 否则我们在该列的第一行放置 0，第二行放置 1： res[0][i]=0， res[1][i]=1。
     * <p>
     * 当遍历完成后就得到了符合题目要求的矩阵 res[2][n]。现在给出该方案的正确性证明：从上述的构造过程可以得到，
     * 整个数组中除了 1 就 0，每一列中 1 的个数完全符合数组 colsum 描述，且在第一行中我们共放置了 upper 个 1，第二行共放置了 lower 个 1。
     * 因此这样构造的矩阵 res[2][n] 为满足题意的二进制矩阵，正确性得证。
     */
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        int sum = 0;
        int two = 0;
        // 统计 2 出现的次数， 并求和
        for (int j : colsum) {
            if (j == 2) {
                ++two;
            }
            sum += j;
        }
        // 如果 2 的和与总和不相等， 或者 大于 两行总和的最小值，那么就说明 无法构造 一个二维数组，返回一个空数组
        if (sum != upper + lower || Math.min(upper, lower) < two) {
            return new ArrayList<List<Integer>>();
        }
        // 第一行总和 和 第二行总和 分别减去 2 出现的次数， 即可分别得到 两行中剩余 1 的个数
        upper -= two;
        lower -= two;
        // 构建一个二维数组
        var res = new ArrayList<List<Integer>>();
        for (int i = 0; i < 2; i++) {
            res.add(new ArrayList<Integer>());
        }
        // 遍历填入数据
        for (int i = 0; i < n; i++) {
            // 如果当前列的值的和是 2
            if (colsum[i] == 2) {
                // 则第一行 和  第二行 的值 都是 1
                res.get(0).add(1);
                res.get(1).add(1);
            } else if (colsum[i] == 1) {
                // 如果 当前列的 值的和 是 1
                // 先判断 第一行总和 是否大于 0
                if (upper > 0) {
                    // 如果大于 0， 即说明 第一行 可以填入 1，第二行 填入 0
                    res.get(0).add(1);
                    res.get(1).add(0);
                    // 更新 upper 的值
                    --upper;
                } else {
                    // 如果 第一行总和 小于 0 ， 即说明 第一行 只能 填入 0， 第二行 填入 1
                    res.get(0).add(0);
                    res.get(1).add(1);
                }
            } else {
                // 如果 当前列的 值的和 是 0， 那么 两行都填入 0
                res.get(0).add(0);
                res.get(1).add(0);
            }
        }
        return res;
    }
}
