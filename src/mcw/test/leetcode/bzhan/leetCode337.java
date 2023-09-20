package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

/**
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * <p>
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * <p>
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * <p>
 * 示例 1:
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * <p>
 * 示例 2:
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树的节点数在 [1, 10^4] 范围内
 * 0 <= Node.val <= 10^4
 *
 * @author MCW 2023/9/18
 */
public class leetCode337 {

    /**
     * 方法一：动态规划
     * 思路与算法
     *
     * 简化一下这个问题：
     * 一棵二叉树，树上的每个点都有对应的权值，每个点有两种状态（选中和不选中），
     * 问在不能同时选中有父子关系的点的情况下，能选中的点的最大权值和是多少。
     *
     * 我们可以用 f(o) 表示选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和；
     * g(o) 表示不选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和；
     * l 和 r 代表 o 的左右孩子。
     *
     * 1. 当 o 被选中时，o 的左右孩子都不能被选中，
     *      故 o 被选中情况下子树上被选中点的最大权值和为 l 和 r 不被选中的最大权值和相加，即 f(o) = g(l) + g(r)。
     * 2. 当 o 不被选中时，o 的左右孩子可以被选中，也可以不被选中。
     *      对于 o 的某个具体的孩子 x，它对 o 的贡献是 x 被选中和不被选中情况下权值和的较大值。故 g(o) = max{f(l),g(l)} + max{f(r),g(r)}。
     *
     * 至此，我们可以用哈希表来存 f 和 g 的函数值，用深度优先搜索的办法后序遍历这棵二叉树，我们就可以得到每一个节点的 f 和 g。
     * 根节点的 f 和 g 的最大值就是我们要找的答案。
     *
     */
    public int rob(TreeNode root) {
        int[] rootStatus = dfs(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs(node.left);
        int[] r = dfs(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }

}
