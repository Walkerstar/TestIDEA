package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\1 0001-14:31
 * Given n non-negative integers a1,a2...an,where each represents a point at coordinate(i,a),n vertical
 * lines are drawn such that the two endpoints of line is at (i,a) and (i,0). find two lines,which together
 * with x-axis froms a container,such that the contains the most water.
 *
 * 给定n个非负整数a1，a2 ... an，其中每个均代表坐标（i，a）上的一个点，则绘制 n 条垂直线使得线的两个端点位于（i，a）
 * 和（i ，0）。找到两条线，它们与x轴一起组成一个容器，使其中包含最多的水。
 */
public class leetCode11 {

    public static int maxAreo(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int area = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            area = Math.max(area, (right - left) * Math.min(height[left], height[right]));
            if (height[left] > height[right]) {
                right--;
            } else if (height[left] < height[right]) {
                left++;
            } else {
                left++;
                right--;
            }
        }
        return area;
    }
}
