package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.*;

/**
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 *
 * @author mcw 2021\5\10 0010-10:18
 */
public class leetCode872 {

    //深度优先
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> s1 = new ArrayList<>();
        if (root1 != null) {
            dfs(root1, s1);
        }

        List<Integer> s2 = new ArrayList<>();
        if (root2 != null) {
            dfs(root2, s2);
        }
        return s1.equals(s2);
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node.left == null && node.right == null) {
            list.add(node.val);
        } else {
            if (node.left != null) {
                dfs(node.left, list);
            }
            if (node.right != null) {
                dfs(node.right, list);
            }
        }
    }

    //迭代
    private void dfs1(TreeNode node, List<Integer> list) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        while (node != null || !queue.isEmpty()) {
            while (node != null) {
                queue.addLast(node);
                node = node.left;
            }
            node = queue.pollLast();
            if (node.left == null && node.right == null) {
                list.add(node.val);
            }
            node = node.right;
        }
    }
}
