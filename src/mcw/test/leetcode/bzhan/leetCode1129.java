package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 在一个有向图中，节点分别标记为 0, 1, ..., n-1。图中每条边为红色或者蓝色，且存在自环或平行边。
 * <p>
 * red_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的红色有向边。类似地，blue_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的蓝色有向边。
 * <p>
 * 返回长度为 n 的数组 answer，其中 answer[X] 是从节点 0 到节点 X 的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，那么 answer[x] = -1。
 * <p>
 * 提示：
 * <li>1 <= n <= 100</li>
 * <li>red_edges.length <= 400</li>
 * <li>blue_edges.length <= 400</li>
 * <li>red_edges[i].length == blue_edges[i].length == 2</li>
 * <li>0 <= red_edges[i][j], blue_edges[i][j] < n</li>
 *
 * @author MCW 2023/2/2
 */
public class leetCode1129 {

    /**
     * 方法一：广度优先搜索
     * 使用 0 表示红色，1 表示蓝色，对于某一个节点 x，从节点 0 到节点 x 的红色边和蓝色边交替出现的路径有两种类型：
     * <p>
     * 类型 0：路径最终到节点 x 的有向边为红色；
     * 类型 1：路径最终到节点 x 的有向边为蓝色。
     * <p>
     * 如果存在从节点 0 到节点 x 的类型 0 的颜色交替路径，并且从节点 x 到节点 y 的有向边为蓝色，
     * 那么该路径加上该有向边组成了从节点 0 到节点 y 的类型 1 的颜色交替路径。
     * 类似地，
     * 如果存在从节点 0 到节点 x 的类型 1 的颜色交替路径，并且从节点 x 到节点 y 的有向边为红色，
     * 那么该路径加上该有向边组成了从节点 0 到节点 y 的类型 0 的颜色交替路径。
     * <p>
     * 使用广度优先搜索获取从节点 0 到某一个节点的两种类型的颜色交替最短路径的长度，
     * 广度优先搜索的队列元素由节点编号和节点路径类型组成，初始时节点 0 到节点 0 的两种类型的颜色交替最短路径的长度都是 0，将两个初始值入队。
     * 对于某一个队列元素，节点编号为 x，节点路径类型为 t，那么根据类型 t 选择颜色为 1−t 的相邻有向边，
     * 如果有向边的终点节点 y 对应类型 1−t 没有被访问过，那么更新节点 y 的类型 1−t 的颜色交替最短路径的长度为节点 x 的类型 t 的颜色交替
     * 最短路径的长度加 1，并且将它入队。
     * <p>
     * 从节点 0 到某一个节点的颜色交替最短路径的长度为两种类型的颜色交替最短路径长度的最小值。
     */
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[][] next = new List[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                next[i][j] = new ArrayList<>();
            }
        }
        //next[0] 存储 红色边
        for (int[] edge : redEdges) {
            next[0][edge[0]].add(edge[1]);
        }
        //next[1] 存储 蓝色边
        for (int[] edge : blueEdges) {
            next[1][edge[0]].add(edge[1]);
        }
        //两种类型的颜色最短路径的长度，存储每个节点到起点的最短距离
        int[][] dist = new int[2][n];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        //存储 当前搜索到的节点，以及当前边的颜色
        Queue<int[]> queue = new ArrayDeque<>();
        dist[0][0] = 0;
        dist[1][0] = 0;
        queue.offer(new int[]{0, 0});
        queue.offer(new int[]{0, 1});
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            //t 代表 颜色类型， 0 是红色，1是蓝色
            //x 代表 当前的节点
            int x = pair[0], t = pair[1];
            //遍历 当前颜色相反颜色的 X 的节点的 所有相邻节点
            for (int y : next[1 - t][x]) {
                //表明已经计算过值了
                if (dist[1 - t][y] != Integer.MAX_VALUE) {
                    continue;
                }
                //计算 0 到 X 节点的距离
                dist[1 - t][y] = dist[t][x] + 1;
                queue.offer(new int[]{y, 1 - t});
            }
        }
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = Math.min(dist[0][i], dist[1][i]);
            if (answer[i] == Integer.MAX_VALUE) {
                answer[i] = -1;
            }
        }
        return answer;
    }
}
