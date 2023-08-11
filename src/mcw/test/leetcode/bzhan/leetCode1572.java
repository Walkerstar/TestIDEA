package mcw.test.leetcode.bzhan;

/**
 * 给你一个正方形矩阵 mat，请你返回矩阵对角线元素的和。
 * <p>
 * 请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。
 * <p>
 * 示例  1：
 * 输入：mat = [[1,2,3],
 * [4,5,6],
 * [7,8,9]]
 * 输出：25
 * 解释：对角线的和为：1 + 5 + 9 + 3 + 7 = 25
 * 请注意，元素 mat[1][1] = 5 只会被计算一次。
 * <p>
 * 示例  2：
 * 输入：mat = [[1,1,1,1],
 * [1,1,1,1],
 * [1,1,1,1],
 * [1,1,1,1]]
 * 输出：8
 * <p>
 * 示例 3：
 * 输入：mat = [[5]]
 * 输出：5
 * <p>
 * <p>
 * 提示：
 * n == mat.length == mat[i].length
 * 1 <= n <= 100
 * 1 <= mat[i][j] <= 100
 *
 * @author MCW 2023/8/11
 */
public class leetCode1572 {

    /**
     * 方法一：遍历矩阵
     * 思路与算法
     * <p>
     * 我们知道矩阵中某个位置 (i,j) 处于对角线上，则一定满足下列条件之一：
     * <p>
     * i=j；
     * i+j=n−1；
     * 根据上述结论，我们可以遍历整个矩阵，如果当前坐标 (i,j) 满足 i=j 或者 i+j=n−1 则表示该位置一定在对角线上，则把当前的数字加入到答案之中。
     */
    public static int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || i + j == n - 1) {
                    sum += mat[i][j];
                }
            }
        }
        return sum;
    }

    /**
     * 思路与算法
     * <p>
     * 逐行遍历，记当前的行号为 i，则当前行中处于对角线的元素为：坐标 (i,i)和坐标 (i,n−i−1)，
     * 因此我们把 (i,i) 与 (i,n−i−1) 处的数字加入到答案中。
     * <p>
     * 如果 n 是奇数的话，则主对角线与副对角线存在交点 (⌊ n/2 ⌋,⌊ n/2 ⌋)，该点会被计算两次。
     * 所以当 n 为奇数的时候，需要减掉交点处的值。
     */
    public static int diagonalSum2(int[][] mat) {
        int n = mat.length;
        int sum = 0;
        int mid = n / 2;
        // 依次 加上每一行的两个位于对角线上的元素
        for (int i = 0; i < n; i++) {
            sum += mat[i][i] + mat[i][n - 1 - i];
        }
        //  n & 1 判断奇偶性
        return sum - mat[mid][mid] * (n & 1);
    }

    public static void main(String[] args) {
        var a = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(diagonalSum(a));
        System.out.println(diagonalSum2(a));
    }
}
