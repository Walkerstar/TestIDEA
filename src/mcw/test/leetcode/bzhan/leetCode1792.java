package mcw.test.leetcode.bzhan;

import java.util.PriorityQueue;

/**
 * 一所学校里有一些班级，每个班级里有一些学生，现在每个班都会进行一场期末考试。给你一个二维数组 classes ，其中 classes[i] = [passi, totali] ，表示你提前知道了第 i 个班级总共有 totali 个学生，其中只有 passi 个学生可以通过考试。
 * <p>
 * 给你一个整数 extraStudents ，表示额外有 extraStudents 个聪明的学生，他们 一定 能通过任何班级的期末考。你需要给这 extraStudents 个学生每人都安排一个班级，使得 所有 班级的 平均 通过率 最大 。
 * <p>
 * 一个班级的 通过率 等于这个班级通过考试的学生人数除以这个班级的总人数。平均通过率 是所有班级的通过率之和除以班级数目。
 * <p>
 * 请你返回在安排这 extraStudents 个学生去对应班级后的 最大 平均通过率。与标准答案误差范围在 10-5 以内的结果都会视为正确结果。
 * <p>
 * 示例 1：
 * <p>
 * 输入：classes = [[1,2],[3,5],[2,2]], extraStudents = 2
 * 输出：0.78333
 * 解释：你可以将额外的两个学生都安排到第一个班级，平均通过率为 (3/4 + 3/5 + 2/2) / 3 = 0.78333 。
 * 示例 2：
 * <p>
 * 输入：classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
 * 输出：0.53485
 * <p>
 * 提示：
 *
 * <li>1 <= classes.length <= 10^5</li>
 * <li>classes[i].length == 2</li>
 * <li>1 <= passi <= totali <= 10^5</li>
 * <li>1 <= extraStudents <= 10^5</li>
 *
 * @author MCW 2023/2/19
 */
public class leetCode1792 {

    /**
     * 方法一：优先队列
     * 思路与算法
     * <p>
     * 由于班级总数不会变化，因此题目所求「最大化平均通过率」等价于「最大化总通过率」。设某个班级的人数为
     * total ，其中通过考试的人数为 pass，那么给这个班级安排一个额外通过考试的学生，其通过率会增加：
     * <p>
     * (pass + 1)/(total+1) - pass/total
     * <p>
     * 我们会优先选择通过率增加量最大的班级，这样做的正确性在于给同一个班级不断地增加安排的学生数量时，其增加的通过率是单调递减的，即：
     * <p>
     * [ (pass + 2)/(total + 2) - (pass + 1)/(total+1) ] < [ (pass + 1)/(total + 1) - pass/total ]
     * <p>
     * 因此当以下条件满足时，班级 j 比班级 i 优先级更大：
     * [ (pass_i+1)/(total_i+1) - pass_i/total_i ] < [ (pass_j+1)/(total_j+1) - pass_j/total_j ]
     * <p>
     * 化简后可得：
     * <p>
     * [ ( total_j + 1 ) × ( total_j ) × ( total_i-pas_i ) ] <  [ (total_i + 1) x (total_i) x ( total_j - pass_j )  ]
     * <p>
     * 我们按照上述比较规则将每个班级放入优先队列中，进行 extraStudents 次操作。每一次操作，我们取出优先队列的堆顶元素，
     * 令其  pass 和 total 分别加 1，然后再放回优先队列。
     * <p>
     * 最后我们遍历优先队列的每一个班级，计算其平均通过率即可得到答案。
     */
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        //维护一个大根堆，堆中存储的是每个班级的通过率增量
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
            long v1 = (long) (b[1] + 1) * b[1] * (a[1] - a[0]);
            long v2 = (long) (a[1] + 1) * a[1] * (b[1] - b[0]);
            if (v1 == v2) {
                return 0;
            }
            return v1 < v2 ? 1 : -1;
        });
        for (int[] c : classes) {
            pq.offer(new int[]{c[0], c[1]});
        }
        // 进行 extraStudents 次操作，每次从堆顶取出一个班级，将这个班级的人数和通过人数都加 1 ,
        // 然后将这个班级的通过率增量重新计算并放回堆中。
        for (int i = 0; i < extraStudents; i++) {
            int[] arr = pq.poll();
            int pass = arr[0], total = arr[1];
            pq.offer(new int[]{pass + 1, total + 1});
        }
        double res = 0;
        for (int i = 0; i < classes.length; i++) {
            int[] arr = pq.poll();
            int pass = arr[0], total = arr[1];
            res += 1.0 * pass / total;
        }
        return res / classes.length;
    }
}
