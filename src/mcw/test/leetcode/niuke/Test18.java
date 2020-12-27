package mcw.test.leetcode.niuke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author mcw 2020\2\24 0024-20:24
 *
 * 本题要求复制一个无向图，图中的每个节点都包含一个标签和它的邻居列表
 * 使用 “#” 作为节点之间的分隔符，使用 “,” 作为节点标签和节点的节点的节点邻居的分隔符
 *
 * 例：{0,1,2#1,2#2,2}
 * 该图有三个节点：0  邻居节点为  1,2
 *                1             2
 *                2             2
 */
public class Test18 {
    /**
     * 解题思路：
     * DFS 和 BFS 遍历
     * 1）克隆节点 label
     * 2）克隆接地那与临近节点的关系
     *
     * 深度遍历或者广度遍历复制 label ，
     * 同时用 map 记录复制的 node 和对应的 neighbors
     */

    /**
     * 递归实现 DFS（深度优先）
     */
    public UndirectedGraphNode cloneGreph1(UndirectedGraphNode node) {
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return dfs(node, map);
    }

    private UndirectedGraphNode dfs(UndirectedGraphNode root, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        if (root == null)
            return null;

        //复制root节点
        UndirectedGraphNode cloneRoot = new UndirectedGraphNode(root.label);
        map.put(root, cloneRoot);

        //遍历root的邻居节点
        for (UndirectedGraphNode neighbor : root.neighbors) {
            if (map.containsKey(neighbor)) {
                //取出来直接添加到新复制的节点的neighbors
                cloneRoot.neighbors.add(map.get(neighbor));
            } else {
                UndirectedGraphNode neighborClone = dfs(neighbor, map);
                cloneRoot.neighbors.add(neighborClone);
            }
        }
        return cloneRoot;
    }

    /**
     * 队列非递归实现 BFS（广度优先）
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;

        //未访问节点的队列
        LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
        queue.push(node);

        //原始节点和已复制节点的映射
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        UndirectedGraphNode cloneRoot = new UndirectedGraphNode(node.label);
        map.put(node, cloneRoot);

        while (!queue.isEmpty()) {
            UndirectedGraphNode snode = queue.pop();
            UndirectedGraphNode snodeClone = map.get(snode);

            //遍历复制snode的邻居节点
            for (UndirectedGraphNode neighbor : snode.neighbors) {
                //如果neighbor已经复制，直接取出进行添加
                if (map.containsKey(neighbor)) {
                    snodeClone.neighbors.add(map.get(neighbor));
                } else {
                    //复制新的邻居节点
                    UndirectedGraphNode cloneNeighbor = new UndirectedGraphNode(neighbor.label);
                    map.put(neighbor, cloneNeighbor);
                    snodeClone.neighbors.add(cloneNeighbor);
                    queue.push(neighbor);
                }
            }
        }
        return cloneRoot;
    }


    class UndirectedGraphNode{
        int label;
        ArrayList<UndirectedGraphNode> neighbors;
        public UndirectedGraphNode(int x){
            label=x;
            neighbors=new ArrayList<>();
        }
    }

}
