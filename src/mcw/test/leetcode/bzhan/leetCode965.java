package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

/**
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * <p>
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 *
 * @author mcw 2022/5/24 17:16
 */
public class leetCode965 {

    /**
     * 深度优先
     */
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null) {
            if (root.val != root.left.val || !isUnivalTree(root.left)) {
                return false;
            }
        }
        if (root.right != null) {
            if (root.val != root.right.val || !isUnivalTree(root.right)) {
                return false;
            }
        }
        return true;
    }

    public boolean isUnivalTree3(TreeNode root) {
        return uniVal(root, root.val);
    }

    public boolean uniVal(TreeNode t, int val) {
        if (t == null) {
            return true;
        }
        if (t.val != val) {
            return false;
        }
        return uniVal(t.left, val) && uniVal(t.right, val);
    }

    public boolean isUnivalTree2(TreeNode root) {
        return (root.left == null || root.val == root.left.val && isUnivalTree2(root.left))
                &&
                (root.right == null || root.val == root.right.val && isUnivalTree2(root.right));
    }
}
