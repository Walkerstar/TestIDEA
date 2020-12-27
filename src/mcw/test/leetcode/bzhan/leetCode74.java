package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\7\1 0001-14:22
 * Search  a 2D Matrix 参考 offer 第一题
 */
public class leetCode74 {

    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int i = row - 1, j = 0;
        while (i >= 0 && j < col) {
            //行数减一层
            if (target < matrix[i][j]) {
                i--;
                //列数加一位
            } else if (target > matrix[i][j]) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 对 行和列 使用两个二分法搜索
     */
    public static boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int startRow = 0;
        int endRow = matrix.length - 1;
        int endCol = matrix[0].length - 1;
        int row = -1;
        while (startRow + 1 < endRow) {
            int mid = startRow + (endRow - startRow) / 2;
            if (matrix[mid][endCol] < target) startRow = mid;
            else endRow = mid;
        }
        if (matrix[startRow][endCol] >= target) row = startRow;
        else if (matrix[endRow][endCol] >= target) row = endRow;
        else return false;

        int start = 0;
        int end = endCol;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[row][mid] < target) target = mid;
            else end = mid;
        }
        return matrix[row][start] == target || matrix[row][end] == target;
    }
}
