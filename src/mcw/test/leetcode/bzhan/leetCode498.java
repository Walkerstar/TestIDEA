package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 *
 * @author mcw 2022/6/14 15:02
 */
public class leetCode498 {

    /**
     * 当第 i 条对角线从下往上遍历时，每次行索引减 1，列索引加 1，直到矩阵的边缘为止：
     * 当 i < m 时，则此时对角线遍历的起点位置为 (i,0)；
     * 当 i ≥ m 时，则此时对角线遍历的起点位置为 (m - 1, i - m + 1)；
     * <p>
     * 当第 i 条对角线从上往下遍历时，每次行索引加 1，列索引减 1，直到矩阵的边缘为止：
     * 当 i < n 时，则此时对角线遍历的起点位置为 (0, i)；
     * 当 i ≥ n 时，则此时对角线遍历的起点位置为 (i - n + 1, n - 1)；
     */
    public static int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] res = new int[m * n];
        int pos = 0;
        //矩阵 一共有 ( m + n -1 )条对角线
        for (int i = 0; i < m + n - 1; i++) {
            //当 i 为奇数时，则第 i 条对角线的走向是从上往下遍历；
            if (i % 2 == 1) {
                int x = i < n ? 0 : i - n + 1;
                int y = i < n ? i : n - 1;
                while (x < m && y >= 0) {
                    res[pos] = mat[x][y];
                    pos++;
                    x++;
                    y--;
                }
            } else {
                //当 i 为偶数时，则第 i 条对角线的走向是从下往上遍历；
                int x = i < m ? i : m - 1;
                int y = i < m ? 0 : i - m + 1;
                while (x >= 0 && y < n) {
                    res[pos] = mat[x][y];
                    pos++;
                    x--;
                    y++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
    }
}
