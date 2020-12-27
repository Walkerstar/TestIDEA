package mcw.test.offer;

import mcw.test.common.TreeLinkedNode;

/**
 * @author mcw 2020\1\21 0021-20:02
 * <p>
 * 输入一棵树，判断该二叉树是否是一个平衡二叉树
 *
 * 平衡二叉树：若一个二叉树每个节点的左右子树的高度 最多 相差 1（即 <=1）,那么此二叉树称为平衡二叉树
 */
public class Test39 {

    public static int getDepth(TreeLinkedNode root) {
        if (root == null)
            return 0;
        int left = getDepth(root.left);
        if (left == -1) return -1;

        int right = getDepth(root.right);
        if (right == -1) return -1;

        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }

    public static boolean IsBalancedTree(TreeLinkedNode root) {
        return getDepth(root) != -1;
    }

    public static void main(String[] args) {
        TreeLinkedNode root=new TreeLinkedNode(1);
        root.left=new TreeLinkedNode(1);
        root.left.right=new TreeLinkedNode(1);
        root.right=new TreeLinkedNode(1);
        root.right.right=new TreeLinkedNode(1);

        System.out.println(IsBalancedTree(root));
    }
}
