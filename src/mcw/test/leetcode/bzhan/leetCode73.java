package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\6\7 0007-14:57
 * Set Matrix Zeroes
 * given a m x n matrix,if an element is 0 .set its entrie row and column to 0. Do it in place.
 */
public class leetCode73 {
    public static void setZeroes(int[][] matrix){
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        boolean firstRowZero = false;
        boolean firstColZero = false;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRowZero = true;
                break;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }
        for (int x = 1; x < matrix[0].length; x++) {
            for (int y = 1; y < matrix.length; y++) {
                if (matrix[y][x] == 0) {
                    matrix[y][0] = 0;
                    matrix[0][x] = 0;
                }
            }
        }

        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRowZero) for (int j = 0; j < matrix[0].length; j++) matrix[0][j] = 0;
        if (firstColZero) for (int j = 0; j < matrix.length; j++) matrix[j][0] = 0;
    }
}
