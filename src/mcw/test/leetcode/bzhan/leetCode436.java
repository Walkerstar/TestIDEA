package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * 给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。
 * <p>
 * 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。
 * <p>
 * 返回一个由每个区间 i 的 右侧区间 的最小起始位置组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
 * <p>
 * 1 <= intervals.length <= 2 * 104
 * intervals[i].length == 2
 * -106 <= starti <= endi <= 106
 * 每个间隔的起点都 不相同
 *
 * @author mcw 2022/5/20 14:20
 */
public class leetCode436 {

    /**
     * 方法一：二分查找
     * 最简单的解决方案是对于集合中的每个区间，我们扫描所有区间找到其起点大于当前区间的终点的区间（具有最先差值），
     * 时间复杂度为 O(n^2)，在此我们不详细描述。
     * <p>
     * 首先我们可以对区间 intervals 的起始位置进行排序，并将每个起始位置 intervals[i][0] 对应的索引 i 存储在数组 startIntervals 中，
     * 然后枚举每个区间 i 的右端点 intervals[i][1]，利用二分查找来找到大于等于 intervals[i][1] 的最小值 val 即可，
     * 此时区间 i 对应的右侧区间即为右端点 val 对应的索引。
     */
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] startIntervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            startIntervals[i][0] = intervals[i][0];
            startIntervals[i][1] = i;
        }
        Arrays.sort(startIntervals, Comparator.comparingInt(o -> o[0]));
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            int target = -1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (startIntervals[mid][0] >= intervals[i][1]) {
                    target = startIntervals[mid][1];
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            ans[i] = target;
        }
        return ans;
    }


    /**
     * 方法二：双指针
     * <p>
     * 我们可以开辟两个新的数组：
     * startIntervals，基于所有区间的起始点从小到大排序。
     * endIntervals，基于所有区间的结束点从小到大排序。
     */
    public int[] findRightInterval2(int[][] intervals) {
        int n = intervals.length;
        int[][] startIntervals = new int[n][2];
        int[][] endIntervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            startIntervals[i][0] = intervals[i][0];
            startIntervals[i][1] = i;
            endIntervals[i][0] = intervals[i][1];
            endIntervals[i][1] = i;
        }
        Arrays.sort(startIntervals, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(endIntervals, Comparator.comparingInt(o -> o[0]));

        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; i++) {
            while (j < n && endIntervals[i][0] > startIntervals[j][0]) {
                j++;
            }
            if (j < n) {
                ans[endIntervals[i][1]] = startIntervals[j][1];
            } else {
                ans[endIntervals[i][1]] = -1;
            }
        }
        return ans;
    }


    public int[] findRightInterval3(int[][] intervals) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            //存入区间 起点和下标
            treeMap.putIfAbsent(intervals[i][0], i);
        }

        int[] result = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            //取出 大于等于区间终点 的最近的 右区间起点
            Integer key = treeMap.ceilingKey(intervals[i][1]);
            result[i] = key == null ? -1 : treeMap.get(key);
        }
        return result;
    }
}
