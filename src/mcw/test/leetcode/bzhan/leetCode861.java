package mcw.test.leetcode.bzhan;

/**
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 *
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 *
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 *
 * 返回尽可能高的分数。
 *
 * 输入：[[0,0,1,1],         输出：39    解释：转换为 [[1,1,1,1],
 *       [1,0,1,0],                                   [1,0,0,1],
 *       [1,1,0,0]]                                   [1,1,1,1]]
 *  0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 *
 * @author mcw 2020\12\7 0007-20:07
 */
public class leetCode861 {

    /**
     * 假设矩阵共有 m 行 n 列，计算方法如下：
     *
     * 对于最左边的列而言，由于最优情况下，它们的取值都为 1，因此每个元素对分数的贡献都为 2^(n−1)
     *  ，总贡献为 m * 2^{n-1}。
     *
     * 对于第 j 列（j>0，此处规定最左边的列是第 0 列）而言，我们统计这一列 0,1 的数量，令其中的最大值为 k，
     * 则 k 是列翻转后的 1 的数量，该列的总贡献为 k * 2^{n-j-1}。需要注意的是，在统计 0,1 的数量的时候，
     * 要考虑最初进行的行反转。
     */
    public int matrixScore(int[][] A) {
        int m = A.length, n = A[0].length;
        int ret = m * (1 << (n - 1));

        for (int j = 1; j < n; j++) {
            int nOnes = 0;
            for (int i = 0; i < m; i++) {
                if (A[i][0] == 1) {
                    nOnes += A[i][j];
                } else {
                    //如果这一行进行了行反转，则该元素的实际取值为 1 - A[i][j]
                    nOnes += (1 - A[i][j]);
                }
            }
            int k = Math.max(nOnes, m - nOnes);
            ret += k * (1 << (n - j - 1));
        }
        return ret;
    }
}
