package mcw.test.offer;

import mcw.test.common.TreeLinkedNode;

/**
 * @author mcw 2020\1\21 0021-19:51
 *
 * 输入一棵树，求该树的深度
 */
public class Test38 {
    public static int TreeDepth(TreeLinkedNode root){
        return root==null?0:Math.max(TreeDepth(root.left),TreeDepth(root.right))+1;
    }

    public static void main(String[] args) {
        TreeLinkedNode root=new TreeLinkedNode(1);
        root.left=new TreeLinkedNode(1);
        root.left.right=new TreeLinkedNode(1);
        root.right=new TreeLinkedNode(1);

        System.out.println(TreeDepth(root));
    }
}
