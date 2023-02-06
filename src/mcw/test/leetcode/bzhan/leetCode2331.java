package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

/**
 * <h2>计算布尔二叉树的值</h2>
 * 给你一棵 完整二叉树 的根，这棵树有以下特征：
 * <p>
 * 叶子节点 要么值为 0 要么值为 1 ，其中 0 表示 False ，1 表示 True 。
 * 非叶子节点 要么值为 2 要么值为 3 ，其中 2 表示逻辑或 OR ，3 表示逻辑与 AND 。
 * 计算 一个节点的值方式如下：
 * <p>
 * 如果节点是个叶子节点，那么节点的 值 为它本身，即 True 或者 False 。
 * 否则，计算 两个孩子的节点值，然后将该节点的运算符对两个孩子值进行 运算 。
 * 返回根节点 root 的布尔运算值。
 * <p>
 * 完整二叉树 是每个节点有 0 个或者 2 个孩子的二叉树。
 * <p>
 * 叶子节点 是没有孩子的节点。
 * <p>
 * <p>
 * 提示：
 * <li>树中节点数目在  [1, 1000]  之间。</li>
 * <li>0 <= Node.val <= 3</li>
 * <li>每个节点的孩子数为  0 或  2  。</li>
 * <li>叶子节点的值为  0  或  1  。</li>
 * <li>非叶子节点的值为  2  或  3 。</li>
 *
 * @author mcw 2023/2/6 14:23
 */
public class leetCode2331 {

    /**
     * 方法一：递归
     * 思路与算法
     * 根据题目要求，如果当前节点为叶子节点，那么节点的值为它本身；否则节点的值为两个孩子的节点值的逻辑运算结果。
     * 我们可以使用递归，如果要计算出当前节点 node 的值，我们需要先计算出两个叶子节点组成的子树的值分别为 lval 与 lval，
     * 然后再计算出当前节点组成的子树的值。计算过程如下：
     * <p>
     * 1.如果当前节点 node 为叶子节点，则直接返回当前节点的值。根据题中完整二叉树的定义，树中每个节点有 0 个或者 2 个孩子的二叉树，只
     * 需检测该节点是否有左孩子或者右孩子即可。<br/>
     * 2.如果当前节点 node 含有孩子节点，计算出其左右孩子节点的值为 lval 与 rval。如果 node 节点的值为 2，则返回 lval ∣ rval；
     * 如果node 节点的值为 3，则返回 lval & rval。
     */
    public boolean evaluateTree(TreeNode root) {
        if (root.left == null) {
            return root.val == 1;
        }
        if (root.val == 2) {
            //利用逻辑表达式，加快效率
            //左值为TRUE ，则右子树不用遍历
            return evaluateTree(root.left) || evaluateTree(root.right);
        } else {
            //左值为 false，则右子树不用遍历
            return evaluateTree(root.left) && evaluateTree(root.right);
        }
    }


}
