package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * <p>
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 *
 * @author mcw 2022/4/8 14:03
 */
public class leetCode429 {

    /**
     * 广度优先
     */
    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            if (root == null) {
                return new ArrayList<List<Integer>>();
            }

            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            Queue<Node> queue = new ArrayDeque<Node>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int cnt = queue.size();
                List<Integer> level = new ArrayList<Integer>();
                for (int i = 0; i < cnt; ++i) {
                    Node cur = queue.poll();
                    level.add(cur.val);
                    for (Node child : cur.children) {
                        queue.offer(child);
                    }
                }
                ans.add(level);
            }

            return ans;
        }

    }

    /**
     * 深度优先
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        dfs(root, 0, ans);
        return ans;
    }

    void dfs(Node u, int depth, List<List<Integer>> ans) {
        if (ans.size() == depth) {
            ans.add(new ArrayList<>());
        }
        List<Integer> list = ans.get(depth);
        list.add(u.val);
        for (Node node : u.children) {
            dfs(node, depth + 1, ans);
        }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", children=" + children +
                    '}';
        }
    }
}
