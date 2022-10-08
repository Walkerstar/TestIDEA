package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给你两个整数数组 startTime（开始时间）和 endTime（结束时间），并指定一个整数 queryTime 作为查询时间。
 * <p>
 * 已知，第 i 名学生在 startTime[i] 时开始写作业并于 endTime[i] 时完成作业。
 * <p>
 * 请返回在查询时间 queryTime 时正在做作业的学生人数。形式上，返回能够使 queryTime 处于区间 [startTime[i], endTime[i]]（含）的学生人数。
 *
 * @author MCW 2022/8/19
 */
public class leetCode1450 {

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int number = 0;
        int len = startTime.length;
        for (int i = 0; i < len; i++) {
            if (queryTime >= startTime[i] && queryTime <= endTime[i]) {
                number++;
            }
        }
        return number;
    }

    /**
     * 差分数组
     * 利用差分数组的思想，对差分数组求前缀和，可以得到统计出 t 时刻正在做作业的人数。我们初始化差分数组 cnt 每个元素都为 0，
     * 在每个学生的起始时间处 cnt[startTime[i]] 加 1，在每个学生的结束时间处 cnt[endTime[i]+1] 减 1，
     * 因此我们可以统计出 queryTime 时刻正在做作业的人数
     */
    public int busyStudent2(int[] startTime, int[] endTime, int queryTime) {
        int n = startTime.length;
        int maxEndTime = Arrays.stream(endTime).max().getAsInt();
        if (queryTime > maxEndTime) {
            return 0;
        }
        int[] cnt = new int[maxEndTime + 2];
        for (int i = 0; i < n; i++) {
            cnt[startTime[i]]++;
            cnt[endTime[i] + 1]--;
        }
        int ans = 0;
        for (int i = 0; i <= queryTime; i++) {
            ans += cnt[i];
        }
        return ans;
    }

    /**
     * 二分查找
     */
    public int busyStudent3(int[] startTime, int[] endTime, int queryTime) {
        Arrays.sort(startTime);
        Arrays.sort(endTime);
        int lessStart = upperbound(startTime, 0, startTime.length - 1, queryTime);
        int lessEnd = lowerbound(endTime, 0, endTime.length - 1, queryTime);
        return lessStart - lessEnd;
    }

    public int upperbound(int[] arr, int l, int r, int target) {
        int ans = r + 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] > target) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    public int lowerbound(int[] arr, int l, int r, int target) {
        int ans = r + 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] >= target) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}