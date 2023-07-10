package mcw.test.leetcode.bzhan;

import java.util.PriorityQueue;

/**
 * 共有 k 位工人计划将 n 个箱子从旧仓库移动到新仓库。给你两个整数 n 和 k，以及一个二维整数数组 time ，数组的大小为 k x 4 ，其中 time[i] = [leftToRighti, pickOldi, rightToLefti, putNewi] 。
 * <p>
 * 一条河将两座仓库分隔，只能通过一座桥通行。旧仓库位于河的右岸，新仓库在河的左岸。开始时，所有 k 位工人都在桥的左侧等待。为了移动这些箱子，第 i 位工人（下标从 0 开始）可以：
 * <p>
 * 从左岸（新仓库）跨过桥到右岸（旧仓库），用时 leftToRighti 分钟。
 * 从旧仓库选择一个箱子，并返回到桥边，用时 pickOldi 分钟。不同工人可以同时搬起所选的箱子。
 * 从右岸（旧仓库）跨过桥到左岸（新仓库），用时 rightToLefti 分钟。
 * 将箱子放入新仓库，并返回到桥边，用时 putNewi 分钟。不同工人可以同时放下所选的箱子。
 * 如果满足下面任一条件，则认为工人 i 的 效率低于 工人 j ：
 * <p>
 * leftToRighti + rightToLefti > leftToRightj + rightToLeftj
 * leftToRighti + rightToLefti == leftToRightj + rightToLeftj 且 i > j
 * 工人通过桥时需要遵循以下规则：
 * <p>
 * 如果工人 x 到达桥边时，工人 y 正在过桥，那么工人 x 需要在桥边等待。
 * 如果没有正在过桥的工人，那么在桥右边等待的工人可以先过桥。如果同时有多个工人在右边等待，那么 效率最低 的工人会先过桥。
 * 如果没有正在过桥的工人，且桥右边也没有在等待的工人，同时旧仓库还剩下至少一个箱子需要搬运，此时在桥左边的工人可以过桥。如果同时有多个工人在左边等待，那么 效率最低 的工人会先过桥。
 * 所有 n 个盒子都需要放入新仓库，请你返回最后一个搬运箱子的工人 到达河左岸 的时间。
 * <p>
 * 示例 1：
 * 输入：n = 1, k = 3, time = [[1,1,2,1],[1,1,3,1],[1,1,4,1]]
 * 输出：6
 * 解释：
 * 从 0 到 1 ：工人 2 从左岸过桥到达右岸。
 * 从 1 到 2 ：工人 2 从旧仓库搬起一个箱子。
 * 从 2 到 6 ：工人 2 从右岸过桥到达左岸。
 * 从 6 到 7 ：工人 2 将箱子放入新仓库。
 * 整个过程在 7 分钟后结束。因为问题关注的是最后一个工人到达左岸的时间，所以返回 6 。
 * <p>
 * 示例 2：
 * 输入：n = 3, k = 2, time = [[1,9,1,8],[10,10,10,10]]
 * 输出：50
 * 解释：
 * 从 0 到 10 ：工人 1 从左岸过桥到达右岸。
 * 从 10 到 20 ：工人 1 从旧仓库搬起一个箱子。
 * 从 10 到 11 ：工人 0 从左岸过桥到达右岸。
 * 从 11 到 20 ：工人 0 从旧仓库搬起一个箱子。
 * 从 20 到 30 ：工人 1 从右岸过桥到达左岸。
 * 从 30 到 40 ：工人 1 将箱子放入新仓库。
 * 从 30 到 31 ：工人 0 从右岸过桥到达左岸。
 * 从 31 到 39 ：工人 0 将箱子放入新仓库。
 * 从 39 到 40 ：工人 0 从左岸过桥到达右岸。
 * 从 40 到 49 ：工人 0 从旧仓库搬起一个箱子。
 * 从 49 到 50 ：工人 0 从右岸过桥到达左岸。
 * 从 50 到 58 ：工人 0 将箱子放入新仓库。
 * 整个过程在 58 分钟后结束。因为问题关注的是最后一个工人到达左岸的时间，所以返回 50 。
 * <p>
 * 提示：
 * <p>
 * 1 <= n, k <= 10^4
 * time.length == k
 * time[i].length == 4
 * 1 <= leftToRighti, pickOldi, rightToLefti, putNewi <= 1000
 *
 * @author MCW 2023/7/7
 */
public class leetCode2532 {

    /**
     * 方法一：优先队列
     * 思路与算法
     * <p>
     * 在本题中，工人共有 4 种状态：
     * <p>
     * 1.在左侧等待
     * 2.在右侧等待
     * 3.在左侧工作（放下所选箱子）
     * 4.在右侧工作（搬起所选箱子）
     * <p>
     * 每一种工作状态都有相应的优先级计算方法，因此我们用 4 个优先队列来存放处于每种状态下的工人集合：
     * <p>
     * 1.在左侧等待的工人： wait_left，题目中定义的效率越高，优先级越高。
     * 2.在右侧等待的工人： wait_right，题目中定义的效率越高，优先级越高。
     * 3.在左侧工作的工人： work_left，完成时间越早，优先级越高。
     * 4.在右侧工作的工人： work_right，完成时间越早，优先级越高。
     * <p>
     * 我们令 remain 表示右侧还有多少个箱子需要搬运，当 remain > 0 时，搬运工作需要继续。
     * 除此之外，题目求解的是最后一个回到左边的工人的到达时间，
     * 因此当右侧还有工人在等待或在工作时（即 work_right 或 wait_right 不为空），搬运工作就需要继续：
     * <p>
     * 1. 若 work_left 或 work_right 中的工人在此刻已经完成工作，则需要将它们取出并分别加入到 wait_left 和 wait_right 中。
     * 2. 若 wait_right 不为空，则取其中优先级最低的工人过桥，将其加入到 work_left 队列中，并将时间更改为过桥后的时间。继续下一轮循环。
     * 3. 若 remain > 0，并且 wait_left 不为空，则需要取优先级最低的工人过桥去取箱子，将其加入到 work_right 队列中，令 remain 减 1，并将时间更改为过桥后的时间。继续下一轮循环。
     * 4. 若 2 和 3 都不满足，表示当前没有人需要过桥，当前时间应该过渡到 work_left 和 work_right 中的最早完成时间。然后继续下一轮循环。
     * <p>
     * 按照上述过程模拟，直到循环条件不再满足。
     */
    public static int findCrossingTime(int n, int k, int[][] time) {
        // 定义等待中的工人优先级比较规则，时间总和越高，效率越低，优先级越低，越优先被取出
        PriorityQueue<Integer> waitLeft = new PriorityQueue<>((x, y) -> {
            int timeX = time[x][0] + time[x][2];
            int timeY = time[y][0] + time[y][2];
            return timeX != timeY ? timeY - timeX : y - x;
        });
        PriorityQueue<Integer> waitRight = new PriorityQueue<>((x, y) -> {
            int timeX = time[x][0] + time[x][2];
            int timeY = time[y][0] + time[y][2];
            return timeX != timeY ? timeY - timeX : y - x;
        });
        PriorityQueue<int[]> workLeft = new PriorityQueue<>((x, y) -> {
            if (x[0] != y[0]) {
                return x[0] - y[0];
            } else {
                return x[1] - y[1];
            }
        });
        PriorityQueue<int[]> workRight = new PriorityQueue<>((x, y) -> {
            if (x[0] != y[0]) {
                return x[0] - y[0];
            } else {
                return x[1] - y[1];
            }
        });

        int remain = n, curTime = 0;
        for (int i = 0; i < k; i++) {
            waitLeft.offer(i);
        }
        while (remain > 0 || !workRight.isEmpty() || !waitRight.isEmpty()) {
            // 1. 若 workLeft 或 workRight 中的工人完成工作，则将他们取出，并分别放置到 waitLeft 和 waitRight 中。
            while (!workLeft.isEmpty() && workLeft.peek()[0] <= curTime) {
                waitLeft.offer(workLeft.poll()[1]);
            }
            while (!workRight.isEmpty() && workRight.peek()[0] <= curTime) {
                waitRight.offer(workRight.poll()[1]);
            }

            if (!waitRight.isEmpty()) {
                // 2. 若右侧有工人在等待，则取出优先级最低的工人并过桥
                int id = waitRight.poll();
                curTime += time[id][2];
                workLeft.offer(new int[]{curTime + time[id][3], id});
            } else if (remain > 0 && !waitLeft.isEmpty()) {
                // 3. 若右侧还有箱子，并且左侧有工人在等待，则取出优先级最低的工人并过桥
                int id = waitLeft.poll();
                curTime += time[id][0];
                workRight.offer(new int[]{curTime + time[id][1], id});
                remain--;
            } else {
                // 4. 否则，没有人需要过桥，时间过渡到 workLeft 和 workRight 中的最早完成时间
                int nextTime = Integer.MAX_VALUE;
                if (!workLeft.isEmpty()) {
                    nextTime = Math.min(nextTime, workLeft.peek()[0]);
                }
                if (!workRight.isEmpty()) {
                    nextTime = Math.min(nextTime, workRight.peek()[0]);
                }
                if (nextTime != Integer.MAX_VALUE) {
                    curTime = Math.max(nextTime, curTime);
                }
            }
        }
        return curTime;
    }
}
