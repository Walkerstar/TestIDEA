package mcw.test.leetcode.bzhan;

import mcw.test.common.Interval;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mcw 2020\6\5 0005-15:34
 * Merge Intervals
 * givne:{1,3} {2,6} {8,10} {15,18}  return :{1,6} {8,10} {15,18}
 */
public class leetCode56 {
    public static List<Interval> merge(List<Interval> intervals) {

        LinkedList<Interval> res = new LinkedList<>();
        if (intervals == null) return res;
        int[] start = new int[intervals.size()];
        int[] end = new int[intervals.size()];
        //分别存放线段的起点和终点，并排序
        for (int i = 0; i < intervals.size(); i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);

        //比较起点和终点的大小，进行整合
        int i = 0;
        while (i < intervals.size()) {
            int st = start[i];
            while (i < intervals.size() - 1 && start[i + 1] <= end[i]) i++;
            int en = end[i];
            Interval in = new Interval(st, en);
            res.add(in);
            i++;
        }
        return res;
    }
}
