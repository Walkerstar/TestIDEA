package mcw.test.leetcode.bzhan;

/**
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 * @author mcw 2021\3\3 0003-17:21
 */
public class leetCode304 {

    int[][] sums;
    int[][] sums1;

    /**
     * 一维前缀和
     */
    public void NumMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m > 0) {
            int n = matrix[0].length;
            sums = new int[m][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    sums[i][j + 1] = sums[i][j] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            sum += sums[i][col2 + 1] - sums[i][col1];
        }
        return sum;
    }

    /**
     * 二维前缀和
     */
    public void NumMatrix1(int[][] matrix) {
        int m = matrix.length;
        if (m > 0) {
            int n = matrix[0].length;
            sums1 = new int[m + 1][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    sums1[i + 1][j + 1] = sums1[i][j + 1] + sums1[i + 1][j] - sums1[i][j] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion1(int row1, int col1, int row2, int col2) {
        return sums1[row2 + 1][col2 + 1] - sums1[row1][col2 + 1] - sums1[row2 + 1][col1] + sums1[row1][col1];
    }
}
