package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
 *
 * @author MCW 2022/3/10
 */
public class leetCode589 {

    /**
     * 递归
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        for (Node child : root.children) {
            helper(child, res);
        }
    }

    /**
     * 迭代
     */
    public List<Integer> preorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Node, Integer> map = new HashMap<>();
        Deque<Node> stack = new ArrayDeque();
        Node node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                List<Node> children = node.children;
                if (children != null && children.size() > 0) {
                    map.put(node, 0);
                    node = children.get(0);
                } else {
                    node = null;
                }
            }
            node = stack.peek();
            int index = map.getOrDefault(node, -1) + 1;
            List<Node> children = node.children;
            if (children != null && children.size() > index) {
                map.put(node, index);
                node = children.get(index);
            } else {
                stack.pop();
                map.remove(node);
                node = null;
            }
        }
        return res;
    }


    /**
     * 优化迭代
     */
    public List<Integer> preorder3(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; --i) {
                stack.push(node.children.get(i));
            }
        }
        return res;
    }


    class Node {
        int val;
        List<Node> children;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }

    }
}
