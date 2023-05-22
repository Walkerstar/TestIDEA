package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

/**
 * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
 * <p>
 * 二叉搜索树的定义如下：
 * <p>
 * 任意节点的左子树中的键值都 小于 此节点的键值。
 * 任意节点的右子树中的键值都 大于 此节点的键值。
 * 任意节点的左子树和右子树都是二叉搜索树。
 * <p>
 * 示例 1：
 * 输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 * 输出：20
 * 解释：键值为 3 的子树是和最大的二叉搜索树。
 * <p>
 * 示例 2：
 * 输入：root = [4,3,null,1,2]
 * 输出：2
 * 解释：键值为 2 的单节点子树是和最大的二叉搜索树。
 * <p>
 * 示例 3：
 * 输入：root = [-4,-2,-5]
 * 输出：0
 * 解释：所有节点键值都为负数，和最大的二叉搜索树为空。
 * <p>
 * 示例 4：
 * 输入：root = [2,1,3]
 * 输出：6
 * <p>
 * 示例 5：
 * 输入：root = [5,4,8,3,null,6,3]
 * 输出：7
 * <p>
 * 提示：
 * <p>
 * 每棵树有 1 到 40000 个节点。
 * 每个节点的键值在 [-4 * 10^4, 4 * 10^4] 之间。
 *
 * @author MCW 2023/5/20
 */
public class leetCode1373 {
    /**
     * 方法一：递归
     * 思路与算法
     * <p>
     * 题目给定的是一颗二叉树，并不保证是二叉搜索树。因此，我们需要去独立判断每个子树是否是一个二叉搜索树。
     * 以 root 为根的子树是一颗二叉搜索树当且仅当以下条件满足：
     * <p>
     * 1. 左子树为空，或者左子树是一颗二叉搜索树并且左子树的最大值小于 root.val；
     * 2. 右子树为空，或者右子树是一颗二叉搜索树并且右子树的最小值大于 root.val。
     * <p>
     * 我们用一个结构体来描述一个子树的基本情况，它应当包含以下元素：
     * 1. isBST，表示该子树是否为二叉搜索树；
     * 2. minValue，表示该子树中的最小值；
     * 3. maxValue，表示该子树中的最大值；
     * 4. sumValue，表示该子树中所有节点的键值之和，用于求解答案。
     * <p>
     * 在判断以 root 为根的子树是否是二叉搜索树时，首先递归判断它的左子树是否是二叉搜索树，然后递归判断它的右子树是否是二叉搜索树。
     * 用 left 和 right 分别表示左子树和右子树的基本信息。
     * 当且仅当以下条件都满足时，以 root 为根的子树是二叉搜索树：
     * <p>
     * 1. left.isBST 为真；
     * 2. right.isBST 为真；
     * 3. left.maxValue < root.val；
     * 4. right.minValue > root.val。
     * 为了方便编写代码，对于空子树我们用 −∞ 来表示最大值，用 ∞ 来表示最小值，并且将 isBST 设置为真， sumValue 设置为 0。
     * 这样在父节点判断时，不论是其左子树为空还是右子树为空，都不会影响到条件判断。
     * <p>
     * 在确定以 root 为根的子树是二叉搜索树之后，设置其基本信息：
     * <p>
     * 1. isBST 设置为真；
     * 2. minValue 设置为 left.minValue 与 root.val 的最小值（因为当 left 为空子树时，left.minValue = ∞）；
     * 3. maxValue 设置为 right.maxValue 与  root.val 的最大值（原因同第 2 条）；
     * 4. sumValue 设置为  left.sumValue 与  right.sumValue 之和。
     * 同时，用  sumValue 更新题目答案，取所有二叉搜索树中的最大值。
     * <p>
     * 值得一提的是，本题需要把所有节点都遍历一次，因为每个子树都有成为二叉搜索树的可能。
     * 即便我们可以根据某个节点的左子树不是二叉搜索树就可以确定该子树不是二叉搜索树，我们也需要去遍历该结点的右子树。
     * 因为其右子树及其子树仍有成为二叉搜索树的可能。
     */
    public int maxSumBST(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }

    public SubTree dfs(TreeNode root) {
        if (root == null) {
            return new SubTree(true, INF, -INF, 0);
        }
        SubTree left = dfs(root.left);
        SubTree right = dfs(root.right);
        if (left.isBST && right.isBST && root.val > left.maxValue && root.val < right.minValue) {
            int sum = root.val + left.sumValue + right.sumValue;
            res = Math.max(res, sum);
            return new SubTree(true, Math.min(left.minValue, root.val), Math.max(root.val, right.maxValue), sum);
        } else {
            return new SubTree(false, 0, 0, 0);
        }
    }


    static final int INF = 0x3f3f3f3f;
    int res;

    static class SubTree {
        boolean isBST;
        int minValue;
        int maxValue;
        int sumValue;

        SubTree(boolean isBST, int minValue, int maxValue, int sumValue) {
            this.isBST = isBST;
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.sumValue = sumValue;
        }
    }


    private int ans; // 二叉搜索树可以为空

    public int maxSumBST2(TreeNode root) {
        dfs2(root);
        return ans;
    }

    private int[] dfs2(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        // 递归左子树
        int[] left = dfs2(node.left);
        // 递归右子树
        int[] right = dfs2(node.right);
        int x = node.val;
        // 不是二叉搜索树
        if (x <= left[1] || x >= right[0]) {
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
        }

        int s = left[2] + right[2] + x; // 这棵子树的所有节点值之和
        ans = Math.max(ans, s);

        return new int[]{Math.min(left[0], x), Math.max(right[1], x), s};
    }
}
