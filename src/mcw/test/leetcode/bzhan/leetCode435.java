package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * @author mcw 2020/12/31 19:11
 */
public class leetCode435 {

    public int eraseOverlapIntervals(int[][] intervals){
        if (intervals.length == 0) {
            return 0;
        }
        //将所有区间按照右端点从小到大进行排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int right = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < intervals.length; i++) {
            //计算数组中存在的不重叠区间
            if (intervals[i][0]>=right){
                ++ans;
                right = intervals[i][1];
            }
        }
        return intervals.length - ans;
    }
}
