package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

/**
 * 1038. 从二叉搜索树到更大和树
 * <p>
 * 给定一个二叉搜索树 root (BST)，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
 * <p>
 * 提醒一下， 二叉搜索树 满足下列约束条件：
 * <p>
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [0,null,1]
 * 输出：[1,null,1]
 * <p>
 * 提示：
 * <p>
 * 树中的节点数在 [1, 100] 范围内。
 * 0 <= Node.val <= 100
 * 树中的所有值均 不重复 。
 *
 * @author MCW 2023/12/4
 */
public class leetCode1038 {
    int sum = 0;

    /**
     * 方法一：反序中序遍历
     * 思路及算法
     * <p>
     * 本题中要求我们将每个节点的值修改为原来的节点值加上所有大于它的节点值之和。
     * 这样我们只需要反序中序遍历该二叉搜索树，记录过程中的节点值之和，并不断更新当前遍历到的节点的节点值，即可得到题目要求的累加树。
     */
    public TreeNode bstToGst(TreeNode root) {
        if (root != null) {
            // 递归右子树
            bstToGst(root.right);
            sum += root.val;
            root.val = sum;
            // 递归左子树
            bstToGst(root.left);
        }
        return root;
    }


    /**
     * 方法二：Morris 遍历
     * 思路及算法
     * <p>
     * 有一种巧妙的方法可以在线性时间内，只占用常数空间来实现中序遍历，
     * 这种方法由 J. H. Morris 在 1979 年的论文「Traversing Binary Trees Simply and Cheaply」中首次提出。
     * 因此被称为 Morris 遍历。
     * <p>
     * Morris 遍历的核心思想是利用树的大量空闲指针，实现空间开销的极限缩减。其反序中序遍历规则总结如下：
     * <p>
     * 1. 如果当前节点的右子节点为空，处理当前节点，并遍历当前节点的左子节点；
     * <p>
     * 2. 如果当前节点的右子节点不为空，找到当前节点右子树的最左节点（该节点为当前节点中序遍历的前驱节点）；
     * <p>
     * 2.1 如果最左节点的左指针为空，将最左节点的左指针指向当前节点，遍历当前节点的右子节点；
     * <p>
     * 2.2 如果最左节点的左指针不为空，将最左节点的左指针重新置为空（恢复树的原状），处理当前节点，并将当前节点置为其左节点；
     * <p>
     * 重复步骤 1 和步骤 2，直到遍历结束。
     * <p>
     * 这样我们利用 Morris 遍历的方法，反序中序遍历该二叉搜索树，即可实现线性时间与常数空间的遍历。
     */
    public TreeNode bstToGst2(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        while (node != null) {
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            } else {
                // 取 当前节点 右子树 的 最左子节点
                TreeNode succ = getSuccessor(node);
                if (succ.left == null) {
                    succ.left = node;
                    node = node.right;
                } else {
                    succ.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }
        return root;
    }

    public TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node.right;
        while (succ.left != null && succ.left != node) {
            succ = succ.left;
        }
        return succ;
    }
}
