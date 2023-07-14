package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

/**
 * 给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，并且总共有 N 枚硬币。
 * <p>
 * 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。(移动可以是从父结点到子结点，或者从子结点移动到父结点。)。
 * <p>
 * 返回使每个结点上只有一枚硬币所需的移动次数。
 * <p>
 * 示例 1：
 * 输入：[3,0,0]
 * 输出：2
 * 解释：从树的根结点开始，我们将一枚硬币移到它的左子结点上，一枚硬币移到它的右子结点上。
 * <p>
 * 示例 2：
 * 输入：[0,3,0]
 * 输出：3
 * 解释：从根结点的左子结点开始，我们将两枚硬币移到根结点上 [移动两次]。然后，我们把一枚硬币从根结点移到右子结点上。
 * <p>
 * 示例 3：
 * 输入：[1,0,2]
 * 输出：2
 * <p>
 * 示例 4：
 * 输入：[1,0,0,null,3]
 * 输出：4
 * <p>
 * 提示：
 * <p>
 * 1<= N <= 100
 * 0 <= node.val <= N
 *
 * @author MCW 2023/7/14
 */
public class leetCode979 {

    int move = 0;

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return move;
    }

    /**
     * 方法一：深度优先搜索
     * 思路与算法
     * <p>
     * 题目中要求求出移动步数，设 dfs(a) 表示若使得以 a 为根节点的子树满足每个节点均只有一个金币时，
     * 节点 a 的父节点需要从节点 a 「拿走」的金币数目，我们可以定义如下：
     * <p>
     * 如果 dfs(a) > 0，则表示节点 a 需要向 a 的父节点移动 dfs(a) 个金币；<br>
     * 如果 dfs(a) = 0，则表示节点 a 与 a 的父节点之间不需要移动； <br>
     * 如果 dfs(a) < 0，则表示节点 a 的父节点需要向 a 移动 ∣dfs(a)∣ 个金币；<br>
     * 综上可知道无论哪个方向移动，节点 a 与其父节点之间的金币移动此时一定为 ∣dfs(a)∣； <br>
     * <p>
     * 设 count(a) 表示当以节点 a 为根节点的子树中含有的二叉树节点数目，
     * 设 sum(a) 表示以节点 a 为根节点的子树中含有的二叉树节点的值之和，
     * 此时可以知道  dfs(a) = sum(a) − count(a)，则可以按照以下几种情形分析：
     * <p>
     * 假设 sum(a) > count(a)，即此时子树中金币总数量大于节点的总数量，此时需要向 a 的父节点移动 sum(a)−count(a) 个金币；<br>
     * 假设 sum(a) < count(a)，即此时子树中金币总数量小于节点的总数量，此时需要从 a 的父节点需要移动 count(a)−sum(a) 个金币； <br>
     * 假设 sum(a) = count(a)，即此时子树中金币总数量等于节点的总数量，此时 a 的父节点与 a 之间不需要移动即可，
     * 最优策略一定是 a 的左子树与右子树之间的金币互相移动即可，此处不再证明； <br>
     * <p>
     * 假设当前节点为 node，设 val(node) 表示节点 node 初始时的金币数目，它的左孩子为 left，它的右孩子为 right，则此时可以知道如下：
     * <p>
     * 若要使得左子树每个节点的数目均为 1，此时 node 需要从 left「拿走」的为金币数目 dfs(left)，此时 left 与 node 之间需要移动 ∣dfs(left)∣ 次金币； <br>
     * 若要使得右子树每个节点的数目均为 1，此时 node 需要从 right「拿走」的金币数目 dfs(right)，此时 right 与 node 之间需要移动 ∣dfs(right)∣ 次金币； <br>
     * 此时根节点的金币总数目即为 move(left) + move(right) + val(node)，由于 node 本身需要保留一个金币，
     * 此时 node 的根节点需要向它「拿走」的金币数目即为 move(left)+move(right)+val(node)−1； <br>
     * <p>
     * 综上我们采用递归，每次递归遍历节点 node 时，返回其父节点需要从 node 「拿走」的金币数目，并统计当前节点与其子节点之间的移动金币的次数，
     * 我们通过递归遍历即可求得所有节点与其父节点之间的移动金币的次数统计之和即为总的金币移动次数。
     * <p>
     * 由于本题中树中金币的数目与树中节点的数目相等，根据上述推论可以知道根节点 root 一定不需要再向其父节点移动金币。
     */
    public int dfs(TreeNode root) {
        int moveLeft = 0;
        int moveRight = 0;
        if (root == null) {
            return 0;
        }
        if (root.left != null) {
            moveLeft = dfs(root.left);
        }
        if (root.right != null) {
            moveRight = dfs(root.right);
        }
        move += Math.abs(moveLeft) + Math.abs(moveRight);
        return moveLeft + moveRight + root.val - 1;
    }
}
