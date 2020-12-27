package mcw.test.leetcode.bzhan;

import mcw.test.common.Interval;

import java.util.LinkedList;
import java.util.List;

/**
 * Insert Interval(插入间隔)
 * @author mcw 2020\6\7 0007-14:35
 */
public class leetCode57 {
    public static List<Interval> insert(List<Interval> intList, Interval newInterval) {
        LinkedList<Interval> res = new LinkedList<>();
        int i = 0;
        int n = intList.size();
        int nStart = newInterval.start;
        int nEnd = newInterval.end;
        //将原集合中线段终点 小于 插入线段起点 的线段加入新的集合中
        while (i < n && intList.get(i).end < newInterval.start) {
            res.add(intList.get(i));
            i++;
        }
        if (i == n) {
            res.add(newInterval);
            return res;
        }
        //找到新的起点和终点，进行插入
        nStart = Math.min(intList.get(i).start, newInterval.start);
        while (i < n && intList.get(i).start <= newInterval.end) {
            nEnd = Math.max(newInterval.end, intList.get(i).end);
            i++;
        }
        res.add(new Interval(nStart, nEnd));
        while (i < n) {
            res.add(intList.get(i++));
        }
        return res;
    }
}
