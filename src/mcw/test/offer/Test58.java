package mcw.test.offer;

import mcw.test.common.TreeLinkedNode;

import java.util.LinkedList;

/**
 * @author mcw 2020\1\26 0026-14:47
 *
 * 实现一个函数，判断一棵二叉树是不是对称的。
 *
 * 注意：如果一棵二叉树同此树的镜像是同样，定义其为对称的
 */
public class Test58 {

    public static boolean idSymmertrical(TreeLinkedNode pRoot){
        if(pRoot==null) return true;
        LinkedList<TreeLinkedNode> leftList=new LinkedList<>();
        LinkedList<TreeLinkedNode> rightList=new LinkedList<>();
        leftList.add(pRoot.left);
        rightList.add(pRoot.right);

        while(!leftList.isEmpty() && !rightList.isEmpty()){
            TreeLinkedNode leftNode=leftList.poll();
            TreeLinkedNode rightNode=rightList.poll();

            if(leftNode==null && rightNode==null)
                continue;
            if(leftNode==null ||rightNode==null)
                return false;
            if(leftNode.val!=rightNode.val)
                return false;

            leftList.add(leftNode.left);
            leftList.add(leftNode.right);

            rightList.add(rightNode.right);
            rightList.add(rightNode.left);
        }
        return true;
    }

    //递归
    public boolean IsSymmertrical(TreeLinkedNode root){
        if(root==null)
            return true;
        return comRoot(root.left,root.right);
    }

    private boolean comRoot(TreeLinkedNode left, TreeLinkedNode right) {

        if(left==null) return right==null;
        if(right==null) return false;

        if(left.val!=right.val)
            return false;
        return comRoot(left.right, right.left) && comRoot(left.left,right.right);
    }
}
