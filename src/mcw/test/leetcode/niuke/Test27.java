package mcw.test.leetcode.niuke;

import mcw.test.common.TreeLinkedNode;

/**
 * @author mcw 2020\3\13 0013-20:02
 *
 * 给定一个二叉树，请计算节点值之和最大的路径的节点值之和是多少。
 * 这个路径的开始节点和结束节点可以是二叉树中的任意节点
 * 例： 1 / 2 , 3     返回 6
 */
public class Test27 {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeLinkedNode root) {
        if (root == null) return 0;
        fun(root);
        return maxSum;
    }

    private int fun(TreeLinkedNode root) {
        if (root == null) return 0;
        int sum = root.val;
        int left = fun(root.left);
        int right = fun(root.right);
        if (left > 0) sum += left;
        if (right > 0) sum += right;
        if (sum > maxSum) maxSum = sum;
        return Math.max(left, right) > 0 ? root.val + Math.max(left, right) : root.val;
    }

    public static void main(String[] args) {
        TreeLinkedNode root=new TreeLinkedNode(1);
        root.left=new TreeLinkedNode(2);
        root.right=new TreeLinkedNode(3);

        System.out.println(new Test27().maxPathSum(root));
    }
}
