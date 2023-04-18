package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

/**
 * 给定二叉树的根节点  root，找出存在于 不同 节点  A 和  B  之间的最大值 V，其中  V = |A.val - B.val|，且  A  是  B  的祖先。
 * <p>
 * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * 输出：7
 * 解释：
 * 我们有大量的节点与其祖先的差值，其中一些如下：
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * 在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
 * 示例 2：
 * <p>
 * 输入：root = [1,null,2,null,0,3]
 * 输出：3
 * <p>
 * 提示：
 * <p>
 * 树中的节点数在  2  到  5000  之间。
 * 0 <= Node.val <= 10^5
 *
 * @author mcw 2023/4/18 11:59
 */
public class leetCode1026 {

    /**
     * 方法一：深度优先搜索
     * <p>
     * 题目要求找出所有祖先节点与它的子孙节点的绝对差值的最大值。按照枚举的思路，我们可以枚举子孙节点，然后找出它的所有祖先节点，计算绝对差值。
     * 同样地，我们也可以枚举祖先节点，然后找出它的所有子孙节点，计算绝对差值。
     * <p>
     * 以第一种思路为例，并非所有祖先节点都需要被考虑到，我们只需要获取最小的祖先节点以及最大的祖先节点。
     * 我们对二叉树执行深度优先搜索，并且记录搜索路径上的节点的最小值 mi 与最大值 ma。
     * 假设当前搜索的节点值为 val，那么与该子孙节点与它的所有祖先节点的绝对差值最大值为 max(∣val−mi∣,∣val−ma∣)，
     * 搜索该节点的左子树与右子树时，对应的 mi = min(mi,val)，ma = max(ma,val)。
     * <p>
     * 为什么只需要获取最小的祖先节点以及最大的祖先节点？
     * 假设某一子孙节点为 x，对应的最小的祖先节点为 mi，最大的祖先节点为 ma。有任一祖先节点为 y，显然 mi ≤ y ≤ ma。
     * 如果 x ≤ y，那么 ∣x−y∣ = y−x ≤ ma−x = ∣x−ma∣，如果 x > y，那么 ∣x−y∣ = x−y ≤ x−mi = ∣x−mi∣，
     * 因此最大的绝对差值与祖先节点 y 无关。
     * <p>
     * 第二种思路是否可行？
     * 可行，需要返回当前子树的最小值和最大值，方法类似。
     */
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }

    public int dfs(TreeNode root, int mi, int ma) {
        if (root == null) {
            return 0;
        }

        int diff = Math.max(Math.abs(root.val - mi), Math.abs(root.val - ma));
        mi = Math.min(mi, root.val);
        ma = Math.max(ma, root.val);
        diff = Math.max(diff, dfs(root.left, mi, ma));
        diff = Math.max(diff, dfs(root.right, mi, ma));
        return diff;
    }

    /**
     * 思路二
     * 依次查找每一个节点的子孙节点的最大最小值。
     */
    public int maxAncestorDiff2(TreeNode root) {
        return dfs(root)[2];
    }

    public int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        int mi = node.val, ma = mi;
        int[] p = dfs(node.left);
        int[] q = dfs(node.right);
        mi = Math.min(mi, Math.min(p[0], q[0]));
        ma = Math.max(ma, Math.max(p[1], q[1]));
        int ans = Math.max(p[2], q[2]);
        ans = Math.max(ans, Math.max(node.val - mi, ma - node.val));
        return new int[]{mi, ma, ans};
    }

}
