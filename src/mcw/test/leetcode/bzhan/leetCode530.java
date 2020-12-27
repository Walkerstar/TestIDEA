package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

/**
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 * @author mcw 2020/10/12 11:08
 */
public class leetCode530 {
    int pre,ans;
    public int getMinimumDifference(TreeNode root) {
        ans=Integer.MAX_VALUE;
        pre=-1;
        dfs(root);
        return ans;
    }
    private void dfs(TreeNode root) {
        if (root==null) {
            return;
        }
        dfs(root.left);
        if (pre != -1) {
            ans = Math.min(ans, root.val - pre);
        }
        pre= root.val;
        dfs(root.right);
    }
}
