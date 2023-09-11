package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。
 * <p>
 * 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
 * <p>
 * 返回你最多可以修读的课程数目。
 * <p>
 * 示例 1：
 * 输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * 输出：3
 * 解释：
 * 这里一共有 4 门课程，但是你最多可以修 3 门：
 * 首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
 * 第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
 * 第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
 * 第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
 * <p>
 * 示例 2：
 * 输入：courses = [[1,2]]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：courses = [[3,2],[4,3]]
 * 输出：0
 * <p>
 * 提示:
 * <p>
 * 1 <= courses.length <= 10^4
 * 1 <= durationi, lastDayi <= 10^4
 *
 * @author MCW 2023/9/11
 */
public class leetCode630 {

    /**
     * 算法
     * <p>
     * 我们需要一个数据结构支持「取出 t 值最大的那门课程」，因此我们可以使用优先队列（大根堆）。
     * <p>
     * 我们依次遍历每一门课程，当遍历到 (ti,di) 时：
     * <p>
     * 如果当前优先队列中所有课程的总时间与 ti 之和小于等于 di，那么我们就把 ti 加入优先队列中；
     * <p>
     * 如果当前优先队列中所有课程的总时间与 ti 之和大于 di ，那么我们找到优先队列中的最大元素 t_xj 。如果 t_xj > t_i ，则将它移出优先队列，并把 ti加入优先队列中。
     * <p>
     * 在遍历完成后，优先队列中包含的元素个数即为答案。
     */
    public int scheduleCourse(int[][] courses) {
        // 按照课程结束时间升序排列
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));

        // 大根堆
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);

        // 优先队列中所有的课程的总时间
        int total = 0;

        for (int[] course : courses) {
            int ti = course[0], di = course[1];
            if (total + ti <= di) {
                total += ti;
                q.offer(ti);
            } else if (!q.isEmpty() && q.peek() > ti) {
                total -= q.poll() - ti;
                q.offer(ti);
            }
        }
        return q.size();
    }
}
