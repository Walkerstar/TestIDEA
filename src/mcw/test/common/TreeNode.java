package mcw.test.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author mcw 2020\1\13 0013-13:21
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    /**
     * 构建二叉树
     *
     * @param root 数组
     * @return 返回一颗 二叉树
     */
    public static TreeNode build(int[] root) {
        return s(root, 0);
    }

    private static TreeNode s(int[] root, int i) {
        TreeNode node = null;
        if (i < root.length) {
            node = new TreeNode(root[i]);
            node.left = s(root, i * 2 + 1);
            node.right = s(root, i * 2 + 2);
            return node;
        }
        return node;
    }

    /**
     * DFS 转化二叉树 为 数组
     */
    public List<Integer> DFSToArray(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            list.add(temp.val);
            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
        return list;
    }

}
