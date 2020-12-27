package mcw.test.leetcode.niuke;


import mcw.test.common.TreeLinkedNode;

/**
 * 求给定二叉树的最小深度。  最小深度是指树的根节点到最近叶子节点的最短路径上节点的个数
 *
 * @author mcw 2019\11\28 0028-13:00
 */
public class Test1 {
    public static int run(TreeLinkedNode root) {
        if (root == null) {
            return 0;
        }
        int left = run(root.left);
        int right = run(root.right);
        if (left == 0 || right == 0) {
            return left + right + 1;
        } else {
            return Math.min(left, right) + 1;
        }
    }

    //求树的深度
    public static  int treeDepth(TreeLinkedNode root){
        return root==null?0:1+Math.max(treeDepth(root.left), treeDepth(root.right));
    }

    public static void main(String[] args) {
        TreeLinkedNode root=new TreeLinkedNode(2);
        TreeLinkedNode left=new TreeLinkedNode(2);
        TreeLinkedNode right=new TreeLinkedNode(2);
        TreeLinkedNode a=new TreeLinkedNode(2);
        TreeLinkedNode b=new TreeLinkedNode(2);
        TreeLinkedNode c=new TreeLinkedNode(2);
        TreeLinkedNode d=new TreeLinkedNode(2);
        root.left=left;
        root.right=right;
        left.right=a;
        left.left=b;
        right.right=c;
        c.right=d;

        int i = run(root);
        System.out.println(i);

        System.out.println(treeDepth(root));

    }
}

