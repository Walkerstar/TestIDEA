package mcw.test.leetcode.bzhan;

import mcw.test.common.Interval;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Insert Interval(插入间隔)
 * <p>
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * <p>
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * <p>
 * 示例 3：
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * <p>
 * 示例 4：
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * <p>
 * 示例 5：
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 10^5
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 10^5
 *
 * @author mcw 2020\6\7 0007-14:35
 */
public class leetCode57 {
    public static List<Interval> insert(List<Interval> intList, Interval newInterval) {
        LinkedList<Interval> res = new LinkedList<>();
        int i = 0;
        int n = intList.size();
        int nStart = newInterval.start;
        int nEnd = newInterval.end;
        // 将原集合中线段终点 小于 插入线段起点 的线段加入新的集合中
        while (i < n && intList.get(i).end < newInterval.start) {
            res.add(intList.get(i));
            i++;
        }
        if (i == n) {
            res.add(newInterval);
            return res;
        }
        // 找到新的起点和终点，进行插入
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

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        var ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧 且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算他们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}
