package mcw.test.leetcode.bzhan;

/**
 * Rotate Image
 * you are given an n x n 2D matrix representing an image. rotate the image by 90 degrees(clockwise)
 *
 * @author mcw 2020\6\1 0001-15:30
 */
public class leetCode48 {
    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int top = 0;
        int left = 0;
        int right = matrix.length - 1;
        int botton = matrix.length - 1;
        int n = matrix.length; //number of cells in one line
        while (n > 1) {
            for (int i = 0; i < n - 1; i++) {
                int tmp = matrix[top][left + i];
                matrix[top][left + i] = matrix[botton - i][left];
                matrix[botton - i][left] = matrix[botton][right - i];
                matrix[botton][right - i] = matrix[top + i][right];
                matrix[top + i][right] = tmp;
            }
            top++;
            botton--;
            left++;
            right--;
            n -= 2;

        }
    }
}
