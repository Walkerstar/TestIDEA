package mcw.test.leetcode.bzhan;

import java.util.Collections;
import java.util.List;

/**
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 * @author mcw 2022/1/18 11:12
 */
public class leetCode539 {

    /**
     * 将 timePoints 排序后，最小时间差必然出现在 timePoints 的两个相邻时间，或者 timePoints 的两个首尾时间中。
     * 因此排序后遍历一遍 timePoints 即可得到最小时间差。
     */
    public int findMinDifference(List<String> timePoints) {
        //根据题意，一共有 24×60=1440 种不同的时间。由鸽巢原理可知，
        // 如果 timePoints 的长度超过 1440，那么必然会有两个相同的时间，此时可以直接返回 0。
        if (timePoints.size() > 1440) {
            return 0;
        }
        Collections.sort(timePoints);
        int ans = Integer.MAX_VALUE;
        int toMinutes = getMinuets(timePoints.get(0));
        int preMinuets = toMinutes;
        for (int i = 1; i < timePoints.size(); ++i) {
            int minuets = getMinuets(timePoints.get(i));
            //相邻时间的时间差
            ans = Math.min(ans, minuets - preMinuets);
            preMinuets = minuets;
        }
        //首尾时间的时间差 (1440 代指 24 小时, 即 24*60  =1440)
        ans = Math.min(ans, toMinutes + 1440 - preMinuets);
        return ans;
    }

    private int getMinuets(String t) {
        return ((t.charAt(0) - '0') * 10 + (t.charAt(1) - '0')) * 60 + (t.charAt(3) - '0') * 10 + (t.charAt(4) - '0');
    }
}
