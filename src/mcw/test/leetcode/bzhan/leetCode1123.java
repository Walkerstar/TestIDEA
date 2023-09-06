package mcw.test.leetcode.bzhan;

import mcw.test.common.Pair;
import mcw.test.common.TreeNode;

/**
 * 给你一个有根节点 root 的二叉树，返回它 最深的叶节点的最近公共祖先 。
 * <p>
 * 回想一下：
 * <p>
 * 叶节点 是二叉树中没有子节点的节点
 * 树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
 * 如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
 * <p>
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4]
 * 输出：[2,7,4]
 * 解释：我们返回值为 2 的节点，在图中用黄色标记。
 * 在图中用蓝色标记的是树的最深的节点。
 * 注意，节点 6、0 和 8 也是叶节点，但是它们的深度是 2 ，而节点 7 和 4 的深度是 3 。
 * <p>
 * 示例 2：
 * 输入：root = [1]
 * 输出：[1]
 * 解释：根节点是树中最深的节点，它是它本身的最近公共祖先。
 * <p>
 * 示例 3：
 * 输入：root = [0,1,3,null,2]
 * 输出：[2]
 * 解释：树中最深的叶节点是 2 ，最近公共祖先是它自己。
 * <p>
 * 提示：
 * <p>
 * 树中的节点数将在 [1, 1000] 的范围内。
 * 0 <= Node.val <= 1000
 * 每个节点的值都是 独一无二 的。
 *
 * @author MCW 2023/9/6
 */
public class leetCode1123 {
    /**
     * 方法一：递归
     * 思路与算法
     * <p>
     * 题目给出一个二叉树，要求返回它最深的叶节点的最近公共祖先。其中树的根节点的深度为 0，我们注意到所有深度最大的节点，都是树的叶节点。
     * 为方便说明，我们把最深的叶节点的最近公共祖先，称之为 lca 节点。
     * <p>
     * 我们用递归的方式，进行深度优先搜索，对树中的每个节点进行递归，返回当前子树的最大深度 d 和 lca 节点。
     * 如果当前节点为空，我们返回深度 0 和空节点。
     * <p>
     * 在每次搜索中，我们递归地搜索左子树和右子树，然后比较左右子树的深度：
     * <p>
     * 如果左子树更深，最深叶节点在左子树中，我们返回 {左子树深度 + 1，左子树的 lca 节点}
     * 如果右子树更深，最深叶节点在右子树中，我们返回 {右子树深度 + 1，右子树的 lca 节点}
     * 如果左右子树一样深，左右子树都有最深叶节点，我们返回 {左子树深度 + 1，当前节点}
     * <p>
     * 最后我们返回根节点的 lca 节点即可。
     */
    public TreeNode lcaSDeepesLeaves(TreeNode root) {
        return f(root).getKey();
    }

    private Pair<TreeNode, Integer> f(TreeNode root) {
        if (root == null) {
            return new Pair<>(root, 0);
        }

        Pair<TreeNode, Integer> left = f(root.left);
        Pair<TreeNode, Integer> right = f(root.right);

        if (left.getValue() > right.getValue()) {
            return new Pair<>(left.getKey(), left.getValue() + 1);
        }
        if (left.getValue() < right.getValue()) {
            return new Pair<>(right.getKey(), right.getValue() + 1);
        }
        return new Pair<>(root, left.getValue() + 1);
    }

    private TreeNode ans;
    private int maxDepth = -1;

    public TreeNode lcaDeepesLeaves2(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private int dfs(TreeNode node, int depth) {
        if (node == null) {
            // 维护全局最大深度
            maxDepth = Math.max(maxDepth, depth);
            return depth;
        }
        // 获取左子树最深叶节点的深度
        int leftMathDepth = dfs(node.left, depth + 1);

        // 获取右子树最深叶节点的深度
        int rightMathDepth = dfs(node.right, depth + 1);

        if (leftMathDepth == rightMathDepth && leftMathDepth == maxDepth) {
            ans = node;
        }
        // 当前子树最深叶节点的深度
        return Math.max(leftMathDepth, rightMathDepth);
    }
}
