package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
 * <p>
 * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 * <p>
 * 返回这些数字之和。题目数据保证答案是一个 32 位 整数。
 *
 * @author mcw 2022/5/30 16:58
 */
public class leetCode1022 {

    /**
     * 方法一：递归
     * 后序遍历的访问顺序为：左子树——右子树——根节点。我们对根节点 root 进行后序遍历：
     * <p>
     * 如果节点是叶子节点，返回它对应的数字 val。
     * 如果节点是非叶子节点，返回它的左子树和右子树对应的结果之和。
     */
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        val = (val << 1) | root.val;
        if (root.left == null && root.right == null) {
            return val;
        }
        return dfs(root.left, val) + dfs(root.right, val);
    }

    /**
     * 方法二：迭代
     * 我们用栈来模拟递归，同时使用一个 prev 指针来记录先前访问的节点。算法步骤如下：
     * <p>
     * 1.如果节点 root 非空，我们将不断地将它及它的左节点压入栈中。
     * <p>
     * 2.我们从栈中获取节点：
     * 2.1该节点的右节点为空或者等于 prev，说明该节点的左子树及右子树都已经被访问，我们将它出栈。
     * 如果该节点是叶子节点，我们将它对应的数字 val 加入结果中。设置 prev 为该节点，设置 root 为空指针。
     * 2.2该节点的右节点非空且不等于 prev，我们令 root 指向该节点的右节点。
     * <p>
     * 3.如果 root 为空指针或者栈空，中止算法，否则重复步骤 1。
     * <p>
     * 需要注意的是，每次出入栈都需要更新 val。
     */
    public int sumRootToLeaf2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        int val = 0, ret = 0;
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                val = (val << 1) | root.val;
                stack.push(root);
                root = root.left;
            }
            root = stack.peek();
            if (root.right == null || root.right == prev) {
                if (root.left == null && root.right == null) {
                    ret += val;
                }
                val >>= 1;
                stack.pop();
                prev = root;
                root = null;
            } else {
                root = root.right;
            }
        }
        return ret;
    }
}
