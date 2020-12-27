package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 用数量最少的箭引爆气球
 * 给定气球的开始和结束坐标，开始坐标总是小于结束坐标。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
 * 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须
 * 射出的最小弓箭数。
 *
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]                  输出：2
 * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
 * @author mcw 2020\11\23 0023-19:30
 */
public class leetCode452 {

    public int findMinArrowShots(int[][] points) {
        if (points.length < 1) {
            return 0;
        }
        //按照右端点排序
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int count = 1;
        //最小右端点
        int axis = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (axis < points[i][0]) {
                count++;
                axis = points[i][1];
            }
        }
        return count;
    }
}
