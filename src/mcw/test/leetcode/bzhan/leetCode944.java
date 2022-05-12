package mcw.test.leetcode.bzhan;

/**
 * 给你由 n 个小写字母字符串组成的数组 strs，其中每个字符串长度相等。
 * 这些字符串可以每个一行，排成一个网格。例如，strs = ["abc", "bce", "cee"] 可以排列为：
 * <p>
 * a b c<br/>
 * b c e<br/>
 * c e e<br/>
 * </p>
 * ***** 固定列，比较每一列的每个字符是否按照字典序排列 <br/>
 * 你需要找出并删除 不是按字典序升序排列的 列。在上面的例子（下标从 0 开始）中，
 * 列 0（'a', 'b', 'c'）和列 2（'c', 'e', 'e'）都是按升序排列的，而列 1（'b', 'c', 'a'）不是，所以要删除列 1 。
 * <p>
 * 返回你需要删除的列数。
 *
 * @author mcw 2022/5/12 10:34
 */
public class leetCode944 {

    /**
     * 方法一：直接遍历
     * 思路与算法
     * <p>
     * 题目要求删除不是按字典序升序排列的列，由于每个字符串的长度都相等，我们可以逐列访问字符串数组，统计不是按字典序升序排列的列。
     * <p>
     * 对于第 j 列的字符串，我们需要检测所有相邻字符是否均满足 strs[i−1][j] ≤ strs[i][j]。
     */
    public int minDeletionSize(String[] strs) {
        int row = strs.length;
        int col = strs[0].length();
        int ans = 0;
        for (int j = 0; j < col; j++) {
            for (int i = 1; i < row; i++) {
                if (strs[i-1].charAt(i) > strs[i].charAt(i)) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }
}
