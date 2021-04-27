package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 *
 * @author mcw 2021\4\27 0027-15:32
 */
public class leetCode938 {

    /**
     * 深度优先
     */
    public int rangeSumBST1(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val > high) {
            return rangeSumBST1(root.left, low, high);
        }
        if (root.val < low) {
            return rangeSumBST1(root.right, low, high);
        }
        return root.val + rangeSumBST1(root.left, low, high) + rangeSumBST1(root.right, low, high);
    }


    /**
     * 广度优先
     */
    public int rangeSumBST2(TreeNode root, int low, int high) {
        int sum = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                continue;
            }
            if (node.val > high) {
                q.offer(node.left);
            } else if (node.val < low) {
                q.offer(node.right);
            } else {
                sum += node.val;
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        return sum;
    }


    int sum = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return sum;
        }
        inorder(root, low, high);
        return sum;
    }

    private void inorder(TreeNode node, int low, int high) {
        if (node == null) {
            return;
        }
        if (node.val > low) {
            inorder(node.left, low, high);
        }
        if (node.val >= low && node.val <= high) {
            sum += node.val;
        }
        if (node.val < high) {
            inorder(node.right, low, high);
        }
    }
}
