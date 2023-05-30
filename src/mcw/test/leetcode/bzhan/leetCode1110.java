package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 * <p>
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 * <p>
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * 输出：[[1,2,null,4],[6],[7]]
 * <p>
 * 示例 2：
 * 输入：root = [1,2,4,null,3], to_delete = [3]
 * 输出：[[1,2,4]]
 * <p>
 * 提示：
 * <p>
 * 树中的节点数最大为 1000。
 * 每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
 * to_delete.length <= 1000
 * to_delete 包含一些从 1 到 1000、各不相同的值。
 *
 * @author MCW 2023/5/30
 */
public class leetCode1110 {

    /**
     * 方法一：深度优先搜索
     * 思路
     * <p>
     * 题目给定一棵树 root，树的每个节点都有一个各不相同的值。
     * 并且给定一个数组 to_delete，包含需要删除的节点值。返回删除所有的 to_delete 中的节点后，剩余的树的集合。
     * <p>
     * 可以利用深度优先搜索来遍历每一个节点，定义函数 dfs，输入是参数是某个节点 node 和这个节点是否为潜在的新的根节点 is_root。
     * 函数中，首先判断这个节点是否要被删除，
     * 如果是，那么它的两个子节点（如果有的话）便成为了潜在的根节点。
     * 如果这个节点的值不在 to_delete 中并且 is_root 为 true，那么这个节点便成为了一个新的根节点，需要把它放入结果数组中。
     * 同时也要对它的两个子节点进行同样的操作。
     * <p>
     * dfs 的返回值为更新后的  node。
     * <p>
     * 对根节点调用一次 dfs，返回新的根节点数组即可。
     */
    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDeleteSet = new HashSet<>();
        for (int i : to_delete) {
            toDeleteSet.add(i);
        }
        List<TreeNode> roots = new ArrayList<>();
        dfs(root, true, toDeleteSet, roots);
        return roots;
    }

    public static TreeNode dfs(TreeNode node, boolean isRoot, Set<Integer> toDeleteSet, List<TreeNode> roots) {
        if (node == null) {
            return null;
        }
        boolean deleted = toDeleteSet.contains(node.val);
        node.left = dfs(node.left, deleted, toDeleteSet, roots);
        node.right = dfs(node.right, deleted, toDeleteSet, roots);
        if (deleted) {
            return null;
        } else {
            if (isRoot) {
                roots.add(node);
            }
            return node;
        }
    }

    /**
     * 灵茶山艾府
     * <p>
     * 写一个 DFS（后序遍历）：
     * <p>
     * 更新左儿子（右儿子）为递归左儿子（右儿子）的返回值。
     * 如果当前节点被删除，那么就检查左儿子（右儿子）是否被删除，如果没被删除，就加入答案。
     * 如果当前节点被删除，返回空节点，否则返回当前节点。
     * 最后，如果根节点没被删除，把根节点加入答案。
     */
    public List<TreeNode> delNodes2(TreeNode root, int[] toDelete) {
        var ans = new ArrayList<TreeNode>();
        var s = new HashSet<Integer>();
        for (int x : toDelete) {
            s.add(x);
        }
        if (dfs2(ans, s, root) != null) {
            ans.add(root);
        }
        return ans;
    }

    private TreeNode dfs2(List<TreeNode> ans, Set<Integer> s, TreeNode node) {
        if (node == null) {
            return null;
        }

        node.left = dfs2(ans, s, node.left);
        node.right = dfs2(ans, s, node.right);

        if (!s.contains(node.val)) {
            return node;
        }
        if (node.left != null) {
            ans.add(node.left);
        }
        if (node.right != null) {
            ans.add(node.right);
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.build(new int[]{1, 2, 3, 4, 5, 6, 7});

        System.out.println(delNodes(root, new int[]{3, 5}));

    }
}
