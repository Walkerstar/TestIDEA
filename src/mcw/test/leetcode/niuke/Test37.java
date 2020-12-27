package mcw.test.leetcode.niuke;

import mcw.test.common.TreeNode;

import java.util.ArrayList;

/**
 * @author mcw 2020\4\24 0024-10:53
 * 剑指offer 24题
 * 给定一个二叉树和一个值 sum ，请找出所有的根节点到叶子节点的节点值之和等于 sum 的路径。
 * 例： sum=22 二叉树: 5 /4 8 / / 11  13 4 // 7  2 5 1
 * 返回： [ [5,4,11,2] ,[5,8,4,5]]
 */
public class Test37 {

    public static ArrayList<ArrayList<Integer>> res=new ArrayList<>();

    public static ArrayList<ArrayList<Integer>> pathSum(TreeNode root,int sum){
        if (root == null) {
            return res;
        }
        ArrayList<Integer> list = new ArrayList<>();
        h(root,sum,list);
        return res;
    }

    public static void h(TreeNode root,int sum,ArrayList<Integer> list){
        list.add(root.val);
        if(root.left==null && root.right==null){
            if(root.val==sum){
                res.add(new ArrayList<>(list));
            }
        }
        if (root.left != null) {
            h(root.left,sum-root.val,list);
        }
        if (root.right != null) {
            h(root.right,sum-root.val,list);
        }
        list.remove(list.size()-1);
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(5);
        TreeNode left = new TreeNode(4);
        TreeNode right = new TreeNode(8);
        TreeNode a = new TreeNode(11);
        TreeNode b = new TreeNode(13);
        TreeNode c = new TreeNode(4);
        a.left=new TreeNode(7);
        a.right=new TreeNode(2);
        c.left=new TreeNode(5);
        c.right=new TreeNode(1);
        left.left=a; left.right=b;
        right.left=c;
        root.left=left; root.right=right;

        ArrayList<ArrayList<Integer>> lists = pathSum(root, 22);
        lists.forEach(System.out::println);
    }
}
