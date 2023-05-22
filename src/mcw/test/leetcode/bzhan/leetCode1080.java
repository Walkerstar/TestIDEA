package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

/**
 * 给你二叉树的根节点 root 和一个整数 limit ，请你同时删除树中所有 不足节点 ，并返回最终二叉树的根节点。
 * <p>
 * 假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为 不足节点 ，需要被删除。
 * <p>
 * 叶子节点，就是没有子节点的节点。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
 * 输出：[1,2,3,4,null,null,7,8,9,null,14]
 * <p>
 * 示例 2：
 * 输入：root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
 * 输出：[5,4,8,11,null,17,4,7,null,null,null,5]
 * <p>
 * 示例 3：
 * 输入：root = [1,2,-3,-5,null,4,null], limit = -1
 * 输出：[1,null,-3,4]
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [1, 5000] 内
 * -10^5 <= Node.val <= 10^5
 * -10^9 <= limit <= 10^9
 *
 * @author MCW 2023/5/22
 */
public class leetCode1080 {

    /**
     * 方法一：深度优先搜索
     * 思路与算法
     * <p>
     * 根据题意可知「不足节点」的定义为：通过节点  node 的每种可能的「根-叶」路径上值的总和全都小于给定的  limit，则该节点被称之为「不足节点」。
     * 按照上述定义可知：
     * <p>
     * 1. 假设节点  node 为根的子树中所有的叶子节点均为「不足节点」，则可以推断出  node 一定也为「不足节点」，
     *    即经过该节点所有“根-叶” 路径的总和都小于 limit，此时该节点需要删除；
     * 2. 假设节点  node 为根的子树中存在叶子节点不是「不足节点」，则可以推断出  node 一定也不是「不足节点」，
     *    因为此时一定存一条从根节点到叶子节点的路径和大于等于  limit，此时该节点需要保留。
     * <p>
     * 根据上述的分析，我们用 checkSufficientLeaf(node) 来检测  node 节点为子树是否含有叶子节点不为「不足节点」，
     * 每次进行深度优先搜索时并传入当前的路径和 sum，每次检测过程如下：
     * <p>
     * 1. 如果当前节点  node 为叶子节点，则当前 “根-叶” 路径和为  sum 加上  node 节点的值，
     *    如果当前的路径和小于  limit，则该叶子  node 一定为「不足节点」，返回  false，否则该节点一定不为「不足节点」，返回  true；
     * 2. 依次检测  node 节点的左子树与右子树，如果当前节点  node 的左子树中的叶子节点均为「不足节点」，则左孩子需要删除，否则需要保留；
     *    如果当前节点  node 的右子树中的叶子节点均为「不足节点」，则右孩子需要删除，否则需要保留。
     *    如果当前子树中的所有叶子节点均为「不足节点」则当前节点需要删除，否则当前节点需要删除。
     * 3. 最终检测  root 的叶子节点是否均为「不足节点」，如果是则返回  null，否则返回  root。
     */
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        boolean haveSufficient = checkSufficientLeaf(root, 0, limit);
        return haveSufficient ? root : null;
    }

    public boolean checkSufficientLeaf(TreeNode node, int sum, int limit) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) {
            return node.val + sum >= limit;
        }

        boolean haveSufficientLeft = checkSufficientLeaf(node.left, sum + node.val, limit);
        boolean haveSufficientRight = checkSufficientLeaf(node.right, sum + node.val, limit);

        if (!haveSufficientLeft) {
            node.left = null;
        }
        if (!haveSufficientRight) {
            node.right = null;
        }
        return haveSufficientLeft || haveSufficientRight;
    }
}
