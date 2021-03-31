package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * offer/Test19
 * @author mcw 2021\3\15 0015-16:21
 */
public class leetCode54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        //记录四个角的位置
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            //将第一行的数加入到 order 中
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            //将最右边一列加入order 中
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            //
            if (left < right && top < bottom) {
                //将最下边一行加入 order 中
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                //将最左边一列加入 order 中
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}
