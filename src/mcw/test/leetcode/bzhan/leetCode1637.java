package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你 n 个二维平面上的点 points ，其中 points[i] = [xi, yi] ，请你返回两点之间内部不包含任何点的 最宽垂直区域 的宽度。
 * <p>
 * 垂直区域 的定义是固定宽度，而 y 轴上无限延伸的一块区域（也就是高度为无穷大）。 最宽垂直区域 为宽度最大的一个垂直区域。
 * <p>
 * 请注意，垂直区域 边上 的点 不在 区域内。
 * 提示：
 * <p>
 * n == points.length
 * 2 <= n <= 10^5
 * points[i].length == 2
 * 0 <= xi, yi <= 109
 *
 * @author mcw 2023/3/30 14:33
 */
public class leetCode1637 {
    /**
     * 方法一：排序
     * <p>
     * 两点之间内部不包含任何点的最宽垂直面积的宽度，即所有点投影到横轴上后，求相邻的两个点的最大距离。
     * 可以先将输入的坐标按照横坐标排序，然后依次求出所有相邻点的横坐标距离，返回最大值。
     */
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int max = 0;
        for (int i = 1; i < points.length; i++) {
            max = Math.max(points[i][0] - points[i - 1][0], max);
        }
        return max;
    }
}
