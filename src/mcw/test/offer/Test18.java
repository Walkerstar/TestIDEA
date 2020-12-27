package mcw.test.offer;

import mcw.test.common.TreeLinkedNode;

/**
 * @author mcw 2020\1\16 0016-13:12
 *
 * 给定二叉树，将其变为源二叉树的镜像
 */
public class Test18 {

    public static void Mirror(TreeLinkedNode root){
        if(root==null)
            return;
        TreeLinkedNode temp;
        temp=root.left;
        root.left=root.right;
        root.right=temp;
        Mirror(root.left);
        Mirror(root.right);
    }

    public static void main(String[] args) {
        TreeLinkedNode a=new TreeLinkedNode(10);
        TreeLinkedNode b=new TreeLinkedNode(5);
        TreeLinkedNode c=new TreeLinkedNode(9);
        TreeLinkedNode d=new TreeLinkedNode(7);
        TreeLinkedNode e=new TreeLinkedNode(100);
        TreeLinkedNode f=new TreeLinkedNode(15);
        a.left=b; a.right=c;
        b.left=d;
        c.right=e; c.left=f;

        Mirror(a);
        System.out.println(a);
    }
}
