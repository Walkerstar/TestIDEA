package mcw.test.leetcode.bzhan;

/**
 * 给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，如果这些点构成一个 回旋镖 则返回 true 。
 * <p>
 * 回旋镖 定义为一组三个点，这些点 各不相同 且 不在一条直线上 。
 *
 * @author mcw 2022/6/8 18:03
 */
public class leetCode1037 {

    /**
     * 方法一：向量叉乘
     * 计算从 points[0] 开始，分别指向 points[1] 和 points[2] 的向量 v1 和 v2。「
     * 三点各不相同且不在一条直线上」等价于「这两个向量的叉乘结果不为零」： v1 x v2 ≠ 0 (0向量)
     */
    public boolean isBoomerang(int[][] points) {
        int[] v1 = {points[1][0] - points[0][0], points[1][1] - points[0][1]};
        int[] v2 = {points[2][0] - points[0][0], points[2][1] - points[0][1]};
        return v1[0] * v2[1] - v1[1] * v2[0] != 0;
    }
}
