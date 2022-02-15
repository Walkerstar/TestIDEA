package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 *
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 *
 * @author MCW 2022/2/15
 */
public class leetCode1380 {

    /**
     * 遍历矩阵 matrix，判断 matrix[i][j] 是否是它所在行的最小值和所在列的最大值，如果是，则加入返回结果。
     */
    public List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //默认 matrix[i][j] 是它所在行的最小值和所在列的最大值
                boolean isMin = true, isMax = true;
                //遍历行
                for (int k = 0; k < n; k++) {
                    if (matrix[i][k] < matrix[i][j]) {
                        isMin = false;
                        break;
                    }
                }
                if (!isMin) {
                    continue;
                }
                //遍历列
                for (int k = 0; k < m; k++) {
                    if (matrix[k][j] > matrix[i][j]) {
                        isMax = false;
                        break;
                    }
                }
                if (isMax) {
                    ret.add(matrix[i][j]);
                }
            }
        }
        return ret;
    }

    /**
     * 预处理
     */
    public List<Integer> luckyNumber2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] minRow = new int[m];
        Arrays.fill(minRow, Integer.MAX_VALUE);
        int[] maxCol = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //每一行的最小值
                minRow[i] = Math.min(minRow[i], matrix[i][j]);
                //每一列的最大值
                maxCol[j] = Math.max(maxCol[i], matrix[i][j]);
            }
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == minRow[i] && matrix[i][j] == maxCol[j]) {
                    ret.add(matrix[i][j]);
                }
            }
        }
        return ret;
    }
}
