package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.*;

/**
 * 2583. 二叉树中的第 K 大层和
 * <p>
 * 给你一棵二叉树的根节点 root 和一个正整数 k 。
 * <p>
 * 树中的 层和 是指 同一层 上节点值的总和。
 * <p>
 * 返回树中第 k 大的层和（不一定不同）。如果树少于 k 层，则返回 -1 。
 * <p>
 * 注意，如果两个节点与根节点的距离相同，则认为它们在同一层。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [5,8,9,2,1,3,7,4,6], k = 2
 * 输出：13
 * 解释：树中每一层的层和分别是：
 * - Level 1: 5
 * - Level 2: 8 + 9 = 17
 * - Level 3: 2 + 1 + 3 + 7 = 13
 * - Level 4: 4 + 6 = 10
 * 第 2 大的层和等于 13 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [1,2,null,3], k = 1
 * 输出：3
 * 解释：最大的层和是 3 。
 * <p>
 * 提示：
 * <p>
 * 树中的节点数为 n
 * 2 <= n <= 10^5
 * 1 <= Node.val <= 10^6
 * 1 <= k <= n
 *
 * @author MCW 2024/2/23
 */
public class leetCode2583 {


    /**
     * 方法一：广度优先搜索 + 排序
     * 思路
     * <p>
     * 先使用广度优先搜索计算出树的每一层的节点值的和，保存在数组 levelSumArray 中。然后将数组进行排序，返回第 k 大的值。
     * 需要考虑数组长度小于 k 的边界情况。也可以使用快速选择的算法快速定位第 k 大的元素。
     */
    public long kthLargestLevelSum(TreeNode root, int k) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        List<Long> levelSumArray = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<TreeNode> levelNodes = new ArrayList<>(queue);
            long levelSum = 0;
            queue.clear();
            for (TreeNode node : levelNodes) {
                levelSum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            levelSumArray.add(levelSum);
        }
        if (levelSumArray.size() < k) {
            return -1;
        }
        Collections.sort(levelSumArray);
        return levelSumArray.get(levelSumArray.size() - k);
    }
}
