package mcw.test.leetcode.bzhan;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * 在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
 * <p>
 * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。
 * (另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
 * <p>
 * 返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），代表学生坐的位置；
 * 函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。每次调用 ExamRoom.leave(p) 时都保证有学生坐在座位 p 上。
 * <p>
 * 提示：
 *
 * <li>1 <= N <= 10^9</li>
 * <li>在所有的测试样例中  ExamRoom.seat()  和  ExamRoom.leave()  最多被调用  10^4  次。</li>
 * <li>保证在调用  ExamRoom.leave(p)  时有学生正坐在座位 p 上。</li>
 *
 * @author MCW 2022/12/30
 */
public class leetCode855 {

    /**
     * 方法一：延迟删除 + 有序集合 + 优先队列
     * 假设有两个学生，他们的位置分别为 s1和 s2，我们用区间 [s1, s2] 表示他们之间的空闲座位区间。
     * 如果固定某一个区间，那么座位选择该区间的中点 s = s1 + ⌊ (s2-s1) /2 ⌋ 能够使新进入的学生与离他最近的人之间的距离达到最大化。
     * <p>
     * 由题意可知，我们需要实时地维护这些区间的顺序关系，并且能实时地获取这些区间中最优区间（最优区间：能够使新进入的学生与离他最近的人之间的距离达到最大化），
     * 同时还要求实时地删除某个学生占用的座位以及修改对应的区间关系。现成的数据结构并不能很好地满足这些需求，
     * 我们尝试将删除区间这一操作延迟到获取最优区间时执行。
     * <p>
     * 使用有序集合 seats 保存已经有学生的座位编号，优先队列 pq 保存座位区间（假设优先队列中的两个区间分别为 [s1, s2] 和 [s3, s4]，
     * 那么如果 ⌊ (s2-s1)/2⌋ > ⌊ (s4-s3)/2 ⌋ 或者 ⌊ (s2-s1) /2 ⌋=⌊ (s4-s3)/2 ⌋ and s1 < s3，那么区间 [s1, s2] 比区间 [s3, s4]更优）。
     * <p>
     * 对于 seat 函数：
     * 学生进入考场时，有三种情况：
     * <p>
     * 1.考场没有一个学生，那么学生只能坐在座位 0；
     * <p>
     * 将座位 0 插入有序集合 seats，并且返回座位 0。
     * <p>
     * 2.考场有超过两位学生，并且选择这些学生所在的座位组成的区间比直接坐在考场的最左或者最右的座位更优；
     * <p>
     * 首先判断优先队列中最优的区间是否有效（有效指当前区间的左右两个端点的座位有学生，中间的所有座位都没有学生），如果无效，删除该区间。
     * 设当前有效区间为 [s1, s2]，最左的座位跟最左的有学生的座位的距离为 left，最右的座位跟最右的有学生的座位的距离为 right，
     * 如果 ⌊ (s2-s1)/2 ⌋ > left 且 ⌊ (s2-s1)/2 ⌋ ≥ right，那么选择当前最优区间比直接坐在考场的最左或者最右的座位更优，
     * 学生坐下的座位为 s=s1 + ⌊ (s2-s1)/2 ⌋，将当前区间从优先队列 pq 中移除，然后分别将新增加的两个区间 [s1, s]和 [s, s2]插入优先队列 pq，
     * 将 s 插入有序集合 seats，返回座位 s。
     * <p>
     * 3.考场少于两位学生，或者直接坐在考场的最左或者最右的座位比选择这些学生组成的区间更优。
     * <p>
     * 如果是最左的座位更优，那么将新增加的区间插入优先队列 pq，最左的座位插入有序集合 seats，并且返回最左的座位；最右的座位的做法类似。
     * <p>
     * 对于 leave 函数：
     * <p>
     * 如果要删除的座位 p 上的学生不是所有学生的最左或者最右的学生，那么删除该学生会产生新的区间，我们将该区间放入优先队列 pq 中，
     * 然后在有序集合 seats 中删除该学生；否则只需要在有序集合 seats 中删除该学生。对于删除座位后已经无效的区间，
     * 我们只需要在 seat 函数中判断区间是否有效即可。
     */
    class ExamRoom {
        int n;
        TreeSet<Integer> seats;
        PriorityQueue<int[]> pq;

        public ExamRoom(int n) {
            this.n = n;
            this.seats = new TreeSet<>();
            this.pq = new PriorityQueue<int[]>((a, b) -> {
                int d1 = a[1] - a[0], d2 = b[1] - b[0];
                return d1 / 2 < d2 / 2 || (d1 / 2 == d2 / 2 && a[0] > b[0]) ? 1 : -1;
            });
        }

        public int seat() {
            if (seats.isEmpty()) {
                seats.add(0);
                return 0;
            }
            int left = seats.first(), right = n - 1 - seats.last();
            while (seats.size() >= 2) {
                int[] p = pq.peek();
                // 不属于延迟删除的区间
                if (seats.contains(p[0]) && seats.contains(p[1]) && seats.higher(p[0]) == p[1]) {
                    int d = p[1] - p[0];
                    //最左或最右的座位更优
                    if (d / 2 < right || d / 2 <= left) {
                        break;
                    }
                    pq.poll();
                    pq.offer(new int[]{p[0], p[0] + d / 2});
                    pq.offer(new int[]{p[0] + d / 2, p[1]});
                    seats.add(p[0] + d / 2);
                    return p[0] + d / 2;
                }
                //leave 函数中延迟删除的区间在此时删除
                pq.poll();
            }
            //最右的位置更优
            if (right > left) {
                pq.offer(new int[]{seats.last(), n - 1});
                seats.add(n - 1);
                return n - 1;
            } else {
                pq.offer(new int[]{0, seats.first()});
                seats.add(0);
                return 0;
            }
        }

        public void leave(int p) {
            if (p != seats.first() && p != seats.last()) {
                int prev = seats.lower(p), next = seats.higher(p);
                pq.offer(new int[]{prev, next});
            }
            seats.remove(p);
        }
    }
}
