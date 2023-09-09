package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * <p>
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成 课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 *
 * @author MCW 2023/9/9
 */
public class leetCode207 {

    /**
     * 存放图结构
     */
    List<List<Integer>> edges;

    /**
     * 节点状态，0 —— 未搜索； 1 —— 搜索中； 2 —— 已完成
     */
    int[] visited;

    /**
     * 结果值。true —— 可以修完所有课； false —— 不可以修完所有课程
     */
    boolean valid = true;

    /**
     * 深度优先搜索
     * 拓扑排序
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 构建图结构
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }

        for (int i = 0; i < numCourses && valid; i++) {
            // 如果当前课程未修，则开始寻找前置课程
            if (visited[i] == 0) {
                dfs(i);
            }
        }

        return valid;
    }


    public void dfs(int u) {
        // 开始寻找前置课程前，将 本课程的进修状态改为 1，表示进修中
        visited[u] = 1;
        // 开始进修前置课程
        for (int v : edges.get(u)) {
            // 如果前置课程未进修，则继续递归进修其前置课程
            if (visited[v] == 0) {
                dfs(v);
                // 如果中途发现某些课程会产生进修冲突，则退出，表示不能完全进修所有课程
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                // 如果前置课程也处于进修中，则表明会产生课程冲突，因此不能完全修完所有课程，退出
                valid = false;
                return;
            }
        }
        // 如果所有前置课程都完成，则将 当前课程进修状态改为 2 ，表示已完成
        visited[u] = 2;
    }


    List<List<Integer>> edges2;
    int[] indeg;

    /**
     * 广度优先搜索
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        edges2 = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges2.add(new ArrayList<>());
        }
        indeg = new int[numCourses];

        for (int[] info : prerequisites) {
            edges2.get(info[1]).add(info[0]);
            // 记录 本课程 先于 多少课程进修
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            // 如果 课程 入度为 0 ，则说明 该课程 不是 其他课的先修课，即说明，要修习本课程，得先修习其先修课
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            // 记录 修习的课程 数量
            ++visited;
            int u = queue.poll();
            for (int v : edges2.get(u)) {
                // 度数减一，即说明 u 的先修课完成了一门
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        // 如果修习的课程数与总课数相等，则说明可以修完所有课程
        return visited == numCourses;
    }

}
