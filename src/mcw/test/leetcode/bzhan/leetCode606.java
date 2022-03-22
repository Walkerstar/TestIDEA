package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
 * <p>
 * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 *
 * @author MCW 2022/3/19
 */
public class leetCode606 {

    /**
     * 如果当前节点有两个孩子，那我们在递归时，需要在两个孩子的结果外都加上一层括号;
     * 如果当前节点没有孩子，那我们不需要在节点后面加上任何括号;
     * 如果当前节点只有左孩子，那我们在递归时，只需要在左孩子的结果外加上一层括号，而不需要给右孩子加上任何括号;
     * 如果当前节点只有右孩子，那我们在递归时，需要先加上一层空的括号 ‘()’ 表示左孩子为空，再对右孩子进行递归，并在结果外加上一层括号。
     */
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }
        if (root.left == null && root.right == null) {
            return Integer.toString(root.val);
        }
        if (root.right == null) {
            return new StringBuffer().append(root.val).append("(").append(tree2str(root.left)).append(")").toString();
        }
        return new StringBuffer()
                .append(root.val)
                .append("(").append(tree2str(root.left))
                .append(")(").append(tree2str(root.right)).append(")").toString();
    }

    public String tree2str2(TreeNode root) {
        StringBuffer ans = new StringBuffer();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        Set<TreeNode> visited = new HashSet<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (!visited.add(node)) {
                //已经拜访过该节点
                if (node != root) {
                    ans.append(")");
                }
                stack.pop();
            } else {
                if (node != root) {
                    ans.append("(");
                }
                ans.append(node.val);
                if (node.left == null && node.right != null) {
                    ans.append("()");
                }
                if (node.right != null) {
                    stack.push(node.right);
                }

                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        TreeNode tree=new TreeNode(1);
        TreeNode left=new TreeNode(2);
        left.left=new TreeNode(4);
        TreeNode right=new TreeNode(3);
        right.right=new TreeNode(6);
        tree.left=left;
        tree.right=right;

        System.out.println(new leetCode606().tree2str(tree));
    }
}
