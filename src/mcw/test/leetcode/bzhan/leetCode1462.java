package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 你总共需要上 numCourses 门课，课程编号依次为 0 到 numCourses-1 。你会得到一个数组 prerequisite ，其中 prerequisites[i] = [ai, bi] 表示如果你想选 bi 课程，你 必须 先选 ai 课程。
 * <p>
 * 有的课会有直接的先修课程，比如如果想上课程 1 ，你必须先上课程 0 ，那么会以 [0,1] 数对的形式给出先修课程数对。
 * 先决条件也可以是 间接 的。如果课程 a 是课程 b 的先决条件，课程 b 是课程 c 的先决条件，那么课程 a 就是课程 c 的先决条件。
 * <p>
 * 你也得到一个数组 queries ，其中 queries[j] = [uj, vj]。对于第 j 个查询，您应该回答课程 uj 是否是课程 vj 的先决条件。
 * <p>
 * 返回一个布尔数组 answer ，其中 answer[j] 是第 j 个查询的答案。
 * <p>
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * 输出：[false,true]
 * 解释：课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
 * <p>
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
 * 输出：[false,false]
 * 解释：没有先修课程对，所以每门课程之间是独立的。
 * <p>
 * 示例 3：
 * <p>
 * 输入：numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
 * 输出：[true,true]
 * <p>
 * 提示：
 * <p>
 * 2 <= numCourses <= 100
 * 0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
 * prerequisites[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 每一对 [ai, bi] 都 不同
 * 先修课程图中没有环。
 * 1 <= queries.length <= 10^4
 * 0 <= ui, vi <= n - 1
 * ui != vi
 *
 * @author MCW 2023/9/12
 */
public class leetCode1462 {

    /**
     * 深度优先 + 拓扑排序
     * <p>
     * 思路与算法
     * <p>
     * 「方法一」中「拓扑排序」的实现同样可以通过「深度优先搜索」来实现。
     * 与「广度优先搜索」计入每一个点的出度不同，通过「深度优先搜索」需要记录每一个点是否被访问，
     * 我们用 vi[x] 来表示课程 x 是否被访问，初始时为 False。
     * <p>
     * 我们从编号小到大遍历全部节点，若节点 i 未被访问，则进入「深度优先搜索」流程：
     * <p>
     * 1. 若当前节点 x 已被访问，则直接返回。
     * 2. 若当前节点 x 未被访问，将访问状态设为已访问，然后继续对其全部后继节点递归进行「深度优先搜索」流程。
     * 将节点 x 置为其每一个后继节点 y 的先决条件，有 isPre[x][y]=True，以及对于每一个以 y 为先决条件的节点 t，节点 x 同样为 t 的先决条件，有 isPre[x][t]=True。
     * <p>
     * 遍历完成后，「拓扑排序」完成，矩阵 isPre 计算完毕，然后我们遍历每一个查询，根据矩阵 isPre 即可得到每一个查询的结果。
     */
    public List<Boolean> checkIfPrerequisites(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] g = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] p : prerequisites) {
            g[p[0]].add(p[1]);
        }
        boolean[] vi = new boolean[numCourses];
        boolean[][] isPre = new boolean[numCourses][numCourses];
        for (int i = 0; i < numCourses; i++) {
            dfs(g, isPre, vi, i);
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            res.add(isPre[query[0]][query[1]]);
        }
        return res;
    }

    public void dfs(List<Integer>[] g, boolean[][] isPre, boolean[] vi, int cur) {
        // 课程已被访问，则返回
        if (vi[cur]) {
            return;
        }
        vi[cur] = true;
        for (int ne : g[cur]) {
            dfs(g, isPre, vi, ne);
            isPre[cur][ne] = true;
            for (int i = 0; i < isPre.length; i++) {
                isPre[cur][i] = isPre[cur][i] | isPre[ne][i];
            }
        }
    }

    /**
     * 方法一：广度优先搜索 + 拓扑排序
     * 思路与算法
     * <p>
     * 题目给出 numCourses 门课（编号从 0 开始），并给出了一个长度为 n 的课程之间的制约关系数组 prerequisite，
     * 其中 prerequisite[i]=[ai,bi] 表示在学习课程 bi 之前必须要完成课程 ai的学习，即课程 ai 为 bi 的先决条件。
     * 我们可以将上述条件构建一张有向图——将每一个课程看作一个点（课程编号即为点的编号），每一个制约关系 prerequisite[i]=[ai,bi]
     * 对应一条从点 ai指向 bi的有向边，并且题目保证了这样构建出来的图不存在环。
     * <p>
     * 现在有 m 个查询 queries，其中对于第 i 个查询 queries[i]=[ui,vi]，我们需要判断课程 ui是否是课程 vi的直接或间接先决条件。
     * 我们创建一个 numCourses × numCourses 的矩阵 isPre，其中 isPre[x][y] 表示课程 x 是否是课程 y 的直接或间接先决条件，
     * 若是则 isPre[x][y]=True，否则 isPre[x][y]=False。
     * 在完成 isPre 计算后，我们对于每一个查询就可以在 O(1) 时间得到结果。
     * 对于 isPre 的计算，我们可以通过「广度优先搜索」+「拓扑排序」来对矩阵 isPre 进行计算：
     * <p>
     * 首先我们需要计算有向图中每一个节点的入度，并对入度为 0 的节点加入队列。然后只要队列非空，就进行以下操作：
     * <p>
     * 取出队列队首元素，同时，将这个节点及其所有前置条件节点设置为所有后续节点的前置条件节点，然后对每一个后续节点入度进行 −1 操作，
     * 若操作后的节点入度为 0，则继续将该节点加入队列。
     * <p>
     * 「拓扑排序」结束后，矩阵 isPre 计算完毕，然后我们遍历每一个查询，根据矩阵 isPre 即可得到每一个查询的结果。
     */
    public List<Boolean> checkIfPrerequisites2(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] g = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            g[i] = new ArrayList<>();
        }
        int[] inDegree = new int[numCourses];
        boolean[][] isPre = new boolean[numCourses][numCourses];
        for (int[] p : prerequisites) {
            ++inDegree[p[1]];
            g[p[0]].add(p[1]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int ne : g[cur]) {
                isPre[cur][ne] = true;
                for (int i = 0; i < numCourses; i++) {
                    isPre[i][ne] = isPre[i][ne] | isPre[i][cur];
                }
                --inDegree[ne];
                if (inDegree[ne] == 0) {
                    queue.offer(ne);
                }
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            res.add(isPre[query[0]][query[1]]);
        }
        return res;
    }

}
