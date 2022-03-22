package mcw.test.leetcode.bzhan;

import mcw.test.leetcode.bzhan.leetCode589.Node;

import java.util.*;

/**
 * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
 * <p>
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 *
 * @author MCW 2022/3/12
 */
public class leetCode590 {

    /**
     * 方法一: 递归
     */
    public List<Integer> postorder(Node node) {
        List<Integer> res = new ArrayList<>();
        helper(node, res);
        return res;
    }

    public void helper(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        for (Node ch : root.children) {
            helper(ch, res);
        }
        res.add(root.val);
    }


    /**
     * 方法二 : 迭代
     */
    class Solution {
        public List<Integer> postorder2(Node root) {
            List<Integer> res = new ArrayList<Integer>();
            if (root == null) {
                return res;
            }
            Map<Node, Integer> map = new HashMap<Node, Integer>();
            Deque<Node> stack = new ArrayDeque<Node>();
            Node node = root;
            while (!stack.isEmpty() || node != null) {
                while (node != null) {
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
                    res.add(node.val);
                    stack.pop();
                    map.remove(node);
                    node = null;
                }
            }
            return res;
        }
    }

    public List<Integer> postorder3(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<Node> stack = new ArrayDeque<Node>();
        Set<Node> visited = new HashSet<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.peek();
            /* 如果当前节点为叶子节点或者当前节点的子节点已经遍历过 */
            if (node.children.size() == 0 || visited.contains(node)) {
                stack.pop();
                res.add(node.val);
                continue;
            }
            for (int i = node.children.size() - 1; i >= 0; --i) {
                stack.push(node.children.get(i));
            }
            visited.add(node);
        }
        return res;
    }

    /**
     * 方法四：利用前序遍历反转
     */
    public List<Integer> postorder4(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<Node> stack = new ArrayDeque<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);
            for (Node item : node.children) {
                stack.push(item);
            }
        }
        Collections.reverse(res);
        return res;
    }

}
