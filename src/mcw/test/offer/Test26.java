package mcw.test.offer;

import mcw.test.common.TreeLinkedNode;

import java.util.Stack;

/**
 * @author mcw 2020\1\18 0018-13:30
 *
 * 将一颗二叉搜索树转换成一个排序的双向链表
 */
public class Test26 {

    public static TreeLinkedNode convertBSTToBiList(TreeLinkedNode root){
        if(root==null)
            return null;
        Stack<TreeLinkedNode> stack=new Stack<>();
        TreeLinkedNode p=root;
        TreeLinkedNode pre=null; //用于保存中序遍历序列的上一个节点
        boolean isFirst=true;

        while (p!=null || !stack.isEmpty()){
            while (p!=null){
                stack.push(p);
                p=p.left;
            }
            TreeLinkedNode node = stack.pop();
            if(isFirst){
                root=node; //将中序遍历序列中的第一个节点记为root
                pre=root;
                isFirst=false;
            }else{
                pre.right=node;
                node.left=pre;
                pre=node;
            }
            p=node.right;
        }
        return root;
    }

    public static TreeLinkedNode Convert(TreeLinkedNode root){
        if(root==null)
            return null;
        if(root.left==null && root.right==null)
            return root;
        TreeLinkedNode result=root;
        TreeLinkedNode right=Convert(root.right);
        if(right!=null){
            root.right=right;
            right.left=root;
        }
        TreeLinkedNode left=Convert(root.left);
        if(left!=null){
            result=left;
            while(left.right!=null){
                left=left.right;
            }
            root.left=left;
            left.right=root;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeLinkedNode root=new TreeLinkedNode(5);
        TreeLinkedNode left=new TreeLinkedNode(2);
        TreeLinkedNode right=new TreeLinkedNode(6);
        TreeLinkedNode a=new TreeLinkedNode(1);
        TreeLinkedNode b=new TreeLinkedNode(3);
        TreeLinkedNode c=new TreeLinkedNode(4);
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

        TreeLinkedNode list = convertBSTToBiList(root);
        System.out.println(list.val);
        System.out.println(list.right.left.val);
    }
}
