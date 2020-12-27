package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

/**
 * 完全二叉树的节点个数
 * @author mcw 2020\11\24 0024-19:34
 */
public class leetCode222 {
    /**
     * 规定根节点位于第0层，完全二叉树的最大层数为 h 层。当 0≤ i <h 时，第 i 层包含 2^i个节点。
     * 最底层包含的节点数最少为 1，则完全二叉树节点为2^h个；最多为 2^h，则完全二叉树节点为2^(h+1)-1个。
     * 因此对于最大层数为 h 的完全二叉树，节点个数一定在 [2^h,2^{h+1}-1]的范围内
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //树的高度
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level;
        int high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    /**
     * 根据节点个数范围的上下界得到当前需要判断的节点个数 k，如果第 k 个节点存在，则节点个数一定大于或等于 k，
     * 如果第 k 个节点不存在，则节点个数一定小于 k，由此可以将查找的范围缩小一半，直到得到节点个数。
     *
     * 如何判断第 k 个节点是否存在呢？如果第 k 个节点位于第 h 层，则 k 的二进制表示包含 h+1 位，
     * 其中最高位是 1，其余各位从高到低表示从根节点到第 k 个节点的路径，0 表示移动到左子节点，
     * 1 表示移动到右子节点。通过位运算得到第 k 个节点对应的路径，判断该路径对应的节点是否存在，
     * 即可判断第 k 个节点是否存在。
     */
    private boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }
}
