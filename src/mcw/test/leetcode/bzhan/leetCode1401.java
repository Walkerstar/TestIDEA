package mcw.test.leetcode.bzhan;

/**
 * 给你一个以 (radius, xCenter, yCenter) 表示的圆和一个与坐标轴平行的矩形 (x1, y1, x2, y2) ，
 * 其中 (x1, y1) 是矩形左下角的坐标，而 (x2, y2) 是右上角的坐标。
 * <p>
 * 如果圆和矩形有重叠的部分，请你返回 true ，否则返回 false 。
 * <p>
 * 换句话说，请你检测是否 存在 点 (xi, yi) ，它既在圆上也在矩形上（两者都包括点落在边界上的情况）。
 * <p>
 * 示例 1 ：
 * 输入：radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
 * 输出：true
 * 解释：圆和矩形存在公共点 (1,0) 。
 * <p>
 * 示例 2 ：
 * 输入：radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
 * 输出：false
 * <p>
 * 示例 3 ：
 * 输入：radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
 * 输出：true
 * <p>
 * 提示：
 * <p>
 * 1 <= radius <= 2000
 * -10^4 <= xCenter, yCenter <= 10^4
 * -10^4 <= x1 < x2 <= 10^4
 * -10^4 <= y1 < y2 <= 10^4
 *
 * @author MCW 2023/6/25
 */
public class leetCode1401 {

    public boolean checkOverLap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // 圆心在矩形内部
        if (x1 <= xCenter && xCenter <= x2 && y1 <= yCenter && yCenter <= y2) {
            return true;
        }

        // 圆心在矩形上
        if (x1 <= xCenter && xCenter <= x2 && y2 <= yCenter && yCenter <= y2 + radius) {
            return true;
        }

        // 圆心在矩形下
        if (x1 <= xCenter && xCenter <= x2 && y1 - radius <= yCenter && yCenter <= y1) {
            return true;
        }

        // 圆心在矩形左部
        if (x1 - radius <= xCenter && xCenter <= x1 && y1 <= yCenter && yCenter <= y2) {
            return true;
        }

        // 圆心在矩形右部
        if (x2 <= xCenter && xCenter <= x2 + radius && y1 <= yCenter && yCenter <= y2) {
            return true;
        }

        // 矩形左上角
        if (distance(xCenter, yCenter, x1, y2) < radius * radius) {
            return true;
        }
        // 矩形左下角
        if (distance(xCenter, yCenter, x1, y1) < radius * radius) {
            return true;
        }
        // 矩形右上角
        if (distance(xCenter, yCenter, x2, y2) < radius * radius) {
            return true;
        }
        // 矩形右下角
        if (distance(xCenter, yCenter, x2, y1) < radius * radius) {
            return true;
        }

        // 无交点
        return false;
    }

    public long distance(int ux, int uy, int vx, int vy) {
        return (long) Math.pow(ux - vx, 2) + (long) Math.pow(uy - vy, 2);
    }

    /**
     * 求圆心到矩形区域的最短距离
     */
    public boolean checkOverLap2(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        double dist = 0;
        if (xCenter < x1 || xCenter > x2) {
            dist += Math.min(Math.pow(x1 - xCenter, 2), Math.pow(x2 - xCenter, 2));
        }
        if (yCenter < y1 || yCenter > y2) {
            dist += Math.min(Math.pow(y1 - yCenter, 2), Math.pow(y2 - yCenter, 2));
        }
        return dist < radius * radius;
    }
}
