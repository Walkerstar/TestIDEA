package mcw.test.leetcode.niuke;

import mcw.test.common.TreeLinkedNode;

import java.util.ArrayList;

/**
 * @author mcw 2020\3\9 0009-19:01
 * <p>
 * 给定一个仅包含数字0-9的二叉树，每一条从根节点到叶子节点的路径都可以用一个数字表示。
 * 找出根节点到叶子节点的所有路径表示的数字之和
 * 例： 1          则 1->2 的数字为 12
 *    /  \           1->3 的数字为13
 *   2   3           路径之和为 12+13=25
 */
public class Test22 {
    ArrayList<ArrayList<Integer>> value_lists;



    //先序遍历思想，每一层都比上一层和 * 10 +当前根节点的值
    public int sumNumbers(TreeLinkedNode root){
        int sum=0;
        if(root==null)
            return sum;
        return preorderSumNumbers(root,sum);
    }

    private int preorderSumNumbers(TreeLinkedNode root, int sum) {
        if(root==null)
            return 0;
        sum=sum*10+root.val;
        if(root.left==null && root.right==null)
            return sum;
        return preorderSumNumbers(root.left,sum)+preorderSumNumbers(root.right,sum);
    }
    //==============================================================================
    public int sumNumbers1(TreeLinkedNode root) {
        if (root == null) return 0;
        value_lists = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        sumNumbers1(root, list);
        int sum = 0;
        int s ;
        for (int i = 0; i < value_lists.size(); i++) {
            s = 0;
            ArrayList<Integer> vl = value_lists.get(i);
            for (int j = 0; j < vl.size(); j++) {
                s += vl.get(j) * (Math.pow(10, vl.size() - 1 - j));
            }
            sum += s;
        }
        return sum;
    }

    private void sumNumbers1(TreeLinkedNode root, ArrayList<Integer> list) {
        if (root != null)
            list.add(root.val);
        if (root.left == null && root.right == null) {
            value_lists.add(list);
            return;
        } else {
            if (root.left != null) {
                ArrayList<Integer> value1 = new ArrayList<>(list);
                sumNumbers1(root.left, value1);
            }
            if (root.right != null) {
                ArrayList<Integer> value2 = new ArrayList<>(list);
                sumNumbers1(root.right, value2);
            }
        }
    }


    public static void main(String[] args) {
        TreeLinkedNode root=new TreeLinkedNode(1);
        root.right=new TreeLinkedNode(3);
        root.left=new TreeLinkedNode(2);
        root.left.left=new TreeLinkedNode(1);

        Test22 test=new Test22();
        int value = test.sumNumbers(root);
        System.out.println(value);
    }
}
