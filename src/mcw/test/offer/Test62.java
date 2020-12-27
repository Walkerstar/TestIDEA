package mcw.test.offer;

import mcw.test.common.TreeLinkedNode;

/**
 * <p>
 * 给定一棵二叉搜索树，请找出其中第K小的节点。
 * <p>
 * 二叉搜索树的中序遍历就是从小到大排好的序列，所以找第 k 小的节点就是找第 k 位的节点
 * @author mcw 2020\1\26 0026-18:03
 */
public class Test62 {

    public static int count = 0;

    public static TreeLinkedNode KthNode(TreeLinkedNode root, int k) {
        TreeLinkedNode node;
        if (k <= 0) return null;
        if (root != null) {

            node = KthNode(root.left, k);

            if (node != null)
                return node;
            count++;
            if (k == count)
                return root;

            node = KthNode(root.right, k);
            return node;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeLinkedNode root=new TreeLinkedNode(4);
        TreeLinkedNode left=new TreeLinkedNode(2);
        TreeLinkedNode right=new TreeLinkedNode(6);
        TreeLinkedNode a=new TreeLinkedNode(1);
        TreeLinkedNode b=new TreeLinkedNode(3);
        TreeLinkedNode c=new TreeLinkedNode(5);
        TreeLinkedNode d=new TreeLinkedNode(8);
        TreeLinkedNode e=new TreeLinkedNode(7);
        TreeLinkedNode f=new TreeLinkedNode(9);

        root.left=left;
        root.right=right;
        left.right=b;
        left.left=a;
        right.right=d;
        right.left=c;
        d.right=f;
        d.left=e;

        System.out.println(KthNode(root,5));
    }
}
