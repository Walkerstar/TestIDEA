package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 *
 * @author mcw 2022/6/24 11:25
 */
public class leetCode515 {

    /**
     * 广度优先
     */
    public List<Integer> largestValue(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        while (!deque.isEmpty()) {
            int len = deque.size();
            int max = Integer.MIN_VALUE;
            //遍历每一层的所有节点
            while (len > 0) {
                len--;
                TreeNode t = deque.poll();
                max = Math.max(max, t.val);
                if (t.left != null) {
                    deque.offer(t.left);
                }
                if (t.right != null) {
                    deque.offer(t.right);
                }
            }
            res.add(max);
        }
        return res;
    }

    /**
     * 深度优先
     * 以数组的长度表示二叉树层高
     */
    public List<Integer> largestVale1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode root, int curHeight) {
        if (curHeight == res.size()) {
            res.add(root.val);
        } else {
            res.set(curHeight, Math.max(res.get(curHeight), root.val));
        }
        if (root.left != null) {
            dfs(res, root.left, curHeight + 1);
        }
        if (root.right != null) {
            dfs(res, root.right, curHeight + 1);
        }
    }
}