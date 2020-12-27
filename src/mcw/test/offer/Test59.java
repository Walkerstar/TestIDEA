package mcw.test.offer;

import mcw.test.common.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author mcw 2020\1\26 0026-15:22
 * <p>
 * 实现一个函数，按之字形打印二叉树。
 * <p>
 * 即第一行从左到右，第二行从右到左，以此类推
 */
public class Test59 {
    public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        if (pRoot == null) return ret;
        Stack<TreeNode> ltr = new Stack<>();
        Stack<TreeNode> rtl = new Stack<>();
        ltr.push(pRoot);

        while (!ltr.isEmpty() || !rtl.isEmpty()) {
            ArrayList<Integer> curLine = new ArrayList<>();
            TreeNode curNode = null;
            if (!ltr.isEmpty()) {
                while (!ltr.isEmpty()) {
                    curNode = ltr.pop();
                    curLine.add(curNode.val);
                    if (curNode.left != null) rtl.push(curNode.left);
                    if (curNode.right != null) rtl.push(curNode.right);
                }
            } else {
                while (!rtl.isEmpty()) {
                    curNode = rtl.pop();
                    curLine.add(curNode.val);
                    if (curNode.right != null) ltr.push(curNode.right);
                    if (curNode.left != null) ltr.push(curNode.left);
                }
            }
            ret.add(curLine);
        }
        return ret;
    }
}
