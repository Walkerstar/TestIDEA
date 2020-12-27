package mcw.test.leetcode.niuke;


import mcw.test.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 求给定二叉树的前，中，后序遍历
 * @author mcw 2019\11\29 0029-14:20
 */
public class Test6 {

    //前序
    public static List<Integer> preorderTraversal(TreeNode root){
        List<Integer> ret=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node=stack.pop();
            if(node==null) continue;
            ret.add(node.val);
            stack.push(node.right);
            stack.push(node.left);
        }
        return ret;
    }

    //后序
    public static List<Integer> postorderTraversal(TreeNode root){
        List<Integer> ret=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node=stack.pop();
            if(node==null) continue;
            ret.add(node.val);
            stack.push(node.left);
            stack.push(node.right);
        }
        Collections.reverse(ret);
         return ret;
    }

    //中序
    public static List<Integer> inorderTraversal(TreeNode root){
        List<Integer> ret=new ArrayList<>();
        if (root==null) return ret;
         Stack<TreeNode> stack=new Stack<>();
        TreeNode cur=root;
        while (cur!=null||!stack.isEmpty()){
            while (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            TreeNode node=stack.pop();
            ret.add(node.val);
            cur=node.right;
        }
        return ret;
    }

    //中序 Morris 中序遍历 LeetCode94
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //predecessor 当前节点的左子树上最右的节点
        TreeNode predecessor;
        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    //非递归 后序
    public static ArrayList<Integer> postOrderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        stack.push(root);
        while (!stack.isEmpty()) {
            //只看栈顶元素，不弹出
            TreeNode cur = stack.peek();

            //如果p节点不存在左孩子和右孩子，就可以直接访问
            //或者 p 节点存在孩子，但其他孩子都已被访问过了，则同样可以访问该节点
            if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left || pre == cur.right))) {
                list.add(cur.val);
                stack.pop();
                pre = cur;
            } else {
                if (cur.right != null) stack.push(cur.right);
                if (cur.left != null) stack.push(cur.left);
            }
        }
        return list;
    }

    //递归 前序
    public static void preorder(TreeNode root){
        if(root!=null){
            System.out.print(root.val+" ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    //中序
    public static void inorder(TreeNode root){
        if(root!=null){
            inorder(root.left);
            System.out.print(root.val+" ");
            inorder(root.right);
        }
    }

    //后序
    public static void postorder(TreeNode root){
        if(root!=null){
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.val+" ");
        }
    }

    public static void main(String[] args) {
        /*TreeNode root=new TreeNode(2);
        TreeNode left=new TreeNode(1);
        TreeNode right=new TreeNode(4 );
        TreeNode a=new TreeNode(5);
        TreeNode b=new TreeNode(7);
        TreeNode c=new TreeNode(3);
        TreeNode d=new TreeNode(6);
        root.left=left;
        root.right=right;
        left.right=a;
        left.left=b;
        right.right=c;
        c.right=d;

        List<Integer> list = inorderTraversal(root);
        for (Integer integer : list) {
            System.out.print(integer+" ");
        }
        preorder(root);
        System.out.println();
        inorder(root);
        System.out.println();
        postorder(root);*/

        TreeNode root=new TreeNode(4);
        TreeNode left=new TreeNode(2);
        TreeNode right=new TreeNode(6);
        TreeNode a=new TreeNode(1);
        TreeNode b=new TreeNode(3);
        TreeNode c=new TreeNode(5);
        TreeNode d=new TreeNode(8);
        TreeNode e=new TreeNode(7);
        TreeNode f=new TreeNode(9);

        root.left=left;
        root.right=right;
        left.right=b;
        left.left=a;
        right.right=d;
        right.left=c;
        d.right=f;
        d.left=e;

        inorder(root);

    }
}
