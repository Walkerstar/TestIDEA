package mcw.test.leetcode.niuke;


import java.util.Arrays;

/**
 * @author mcw 2020\3\21 0021-17:12
 *
 * 给出一个三角形，计算从三角形顶部到底部的最小路径和，每一步都可以移动到下面一行相邻的数字。
 * 例：[[2],              最小路径和是：  2+3+5+1=11
 *      [3,4],
 *      [6,5,7],          ******注意： 如果能用 O(n) 额外空间解决这个问题，可以得到附加分
 *      [4,1,8,3]]                           n 是三角形的行数
 */
public class Test31 {

    /**
     * 从下往上找，不使用额外空间，缺点是修改了输入
     */
    public static int minimumTotal(int[][] arr) {
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] += Math.min(arr[i + 1][j], arr[i + 1][j + 1]);
            }
        }
        return arr[0][0];
    }

    /**
     *使用 O(n) 额外空间,不修改原输入
     */
    public static int minTotal(int[][] arr) {
        int[] result = new int[arr.length];
        //取二维数组的最后一行的数据，即使用O(N)的额外空间
        System.arraycopy(arr[arr.length - 1], 0, result, 0, arr[arr.length - 1].length);
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = 0; j < arr[i].length; j++) {
                result[j] = arr[i][j] + Math.min(result[j], result[j + 1]);
            }
        }
        return result[0];
    }

    public static void main(String[] args) {
        int[][] arr={{2},{3,4},{6,5,7},{4,1,8,3}};
        //int total = minimumTotal(arr);
        int total = minTotal(arr);
        System.out.println(total);
    }
}
