package mcw.test.offer;

import mcw.test.common.TreeNode;

import java.util.ArrayList;

/**
 * @author mcw 2020\1\17 0017-16:14
 *
 * LeetCode 37题
 * 输入一棵二叉树的根节点和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 * 路径定义为从树的根节点开始往下一直到叶子节点，所经过的节点形成一条路径。
 * （注意：再返回值的list中，数组长度大的数组靠前）
 */
public class Test24 {

    static ArrayList<ArrayList<Integer>> listAll=new ArrayList<>();
    static ArrayList<Integer> list=new ArrayList<>();

    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target){
        if(root==null)
            return listAll;
        list.add(root.val);
        target=target-root.val;
        if(target==0 && root.left==null && root.right==null){
            //add()方法添加的是对象的引用，如果不new一个，后面的操作会更改这个list
            listAll.add(new ArrayList<>(list));
        }
        findPath(root.left,target);
        findPath(root.right,target);

        //递归到叶子节点没有找到路径，回退到父节点
        list.remove(list.size()-1);
        return listAll;
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

        ArrayList<ArrayList<Integer>> lists = findPath(root, 22);
        lists.forEach(System.out::println);
    }

}
