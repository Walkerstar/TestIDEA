package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定 m x n 矩阵 matrix 。
 * <p>
 * 你可以从中选出任意数量的列并翻转其上的 每个 单元格。（即翻转后，单元格的值从 0 变成 1，或者从 1 变为 0 。）
 * <p>
 * 返回 经过一些翻转后，行与行之间所有值都相等的最大行数 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[0,1],[1,1]]
 * 输出：1
 * 解释：不进行翻转，有 1 行所有值都相等。
 * 示例 2：
 * <p>
 * 输入：matrix = [[0,1],[1,0]]
 * 输出：2
 * 解释：翻转第一列的值之后，这两行都由相等的值组成。
 * 示例 3：
 * <p>
 * 输入：matrix = [[0,0,0],[0,0,1],[1,1,0]]
 * 输出：2
 * 解释：翻转前两列的值之后，后两行由相等的值组成。
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] == 0 或 1
 *
 * @author MCW 2023/5/15
 */
public class leetCode1072 {

    /**
     * 方法一：哈希
     * 思路与算法
     * <p>
     * 题目给定 m×n 的矩阵，要求从中选取任意数量的列并翻转其上的每个单元格。单元格仅包含  0 或者  1。
     * 问最多可以得到多少个由相同元素组成的行。如果某一行全部是  1 或者全部是  0，则表示该行由相同元素组成。
     * <p>
     * 如果翻转固定的某些列，可以使得两个不同的行都变成由相同元素组成的行，那么我们称这两行为本质相同的行。例如  001 和 110 就是本质相同的行。
     * <p>
     * 本质相同的行有什么特点呢？可以发现，本质相同的行只存在两种情况，一种是由  0 开头的行，另一种是由  1 开头的行。
     * 在开头的元素确定以后，由于翻转的列已经固定，所以可以推断出后续所有元素是  0 还是  1。
     * <p>
     * 为了方便统计本质相同的行的数量，我们让由  1 开头的行全部翻转，翻转后行内元素相同的行即为本质相同的行。
     * 之后我们将每一行转成字符串形式存储到哈希表中，遍历哈希表得到最多的本质相同的行的数量即为答案。
     */
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            char[] arr = new char[n];
            Arrays.fill(arr, '0');
            for (int j = 0; j < n; j++) {
                //如果 matrix[i][0]为 1 ，则对该行元素进行翻转
                arr[j] = (char) ('0' + (matrix[i][j] ^ matrix[i][0]));
            }
            String s = new String(arr);
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            res = Math.max(res, entry.getValue());
        }
        return res;
    }

    /**
     * 灵茶山艾府
     * <p>
     * 从答案出发倒着思考。关注最后全为  0 或者全为  1 的行，倒数第二步是什么样的？
     * <p>
     * 假如翻转最后一列， 000 变成  001， 111 变成  110。从这个例子可以发现，对于相同的行，或者「互补」的行，
     * 一定存在一种翻转方式，可以使这些行最终全为  0 或者全为 1。
     * <p>
     * 从图论的角度来看的话，就是在这些相同或者互补的行之间连边，答案就是最大连通块的大小。
     * <p>
     * 但实际上不需要建图，用哈希表统计这些行。为了统计互补的行，可以把第一个数为 1 的行全部翻转。
     * <p>
     * 例如示例 3，把最后一行翻转得到  001（变成互补的行），发现与第二行是一样的，所以答案等于  2。
     */
    public int maxEqualRowsAfterFlips2(int[][] matrix) {
        int ans = 0;
        int n = matrix[0].length;
        Map<String, Integer> map = new HashMap<>();
        for (int[] row : matrix) {
            char[] r = new char[n];
            for (int j = 0; j < n; j++) {
                //翻转第一个数为 1 的行
                r[j] = (char) (row[j] ^ row[0]);
            }
            int c = map.merge(new String(r), 1, Integer::sum);
            ans = Math.max(ans, c);
        }
        return ans;
    }
}
