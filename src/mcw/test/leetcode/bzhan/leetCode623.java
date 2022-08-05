package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 给定一个二叉树的根 root 和两个整数 val 和 depth ，在给定的深度 depth 处添加一个值为 val 的节点行。
 * <p>
 * 注意，根节点 root 位于深度 1 。
 * <p>
 * 加法规则如下:
 * <p>
 * 给定整数 depth，对于深度为 depth - 1 的每个非空树节点 cur ，创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。
 * cur 原来的左子树应该是新的左子树根的左子树。
 * cur 原来的右子树应该是新的右子树根的右子树。
 * 如果 depth == 1 意味着 depth - 1 根本没有深度，那么创建一个树节点，值 val 作为整个原始树的新根，而原始树就是新根的左子树。
 *
 * @author mcw 2022/8/5 11:12
 */
public class leetCode623 {

    /**
     * 深度优先
     * 时间复杂度 O(n) 空间复杂度 O(n)
     * 当输入 depth 为 1 时，需要创建一个新的 root，并将原 root 作为新 root 的左子节点。
     * 当 depth 为 2 时，需要在 root 下新增两个节点 left 和 right 作为 root 的新子节点，
     * 并把原左子节点作为 left 的左子节点，把原右子节点作为 right 的右子节点。
     * <p>
     * 当 depth 大于 2 时，需要继续递归往下层搜索，并将 depth 减去 1，直到搜索到 depth 为 2。
     */
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (root == null) {
            return null;
        }
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        if (depth == 2) {
            root.left = new TreeNode(val, root.left, null);
            root.right = new TreeNode(val, null, root.right);
        } else {
            root.left = addOneRow(root.left, val, depth - 1);
            root.right = addOneRow(root.right, val, depth - 1);
        }
        return root;
    }

    /**
     * 广度优先搜索
     * 时间复杂度 O(n) 空间复杂度 O(n)
     * <p>
     * 与深度优先搜索类似，我们用广度优先搜索找到要加的一行的上一行，然后对这一行的每个节点 node，
     * 都新增两个节点 left 和 right 作为 node 的新子节点，并把原左子节点作为 left 的左子节点，把原右子节点作为 right 的右子节点。
     */
    public TreeNode addOneRow2(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }

        List<TreeNode> curLevel = new ArrayList<>();
        curLevel.add(root);
        for (int i = 1; i < depth - 1; i++) {
            List<TreeNode> tmpt = new ArrayList<>();
            for (TreeNode node : curLevel) {
                if (node.left != null) {
                    tmpt.add(node.left);
                }
                if (node.right != null) {
                    tmpt.add(node.right);
                }
            }
            curLevel = tmpt;
        }
        for (TreeNode node : curLevel) {
            node.left = new TreeNode(val, node.left, null);
            node.right = new TreeNode(val, null, node.right);
        }
        return root;
    }

    public TreeNode addOnRow3(TreeNode root, int val, int depth) {
        TreeNode head = new TreeNode(-1, root, null);
        addLayer(0, depth, val, head);
        return head.left;
    }

    public void addLayer(int d, int depth, int val, TreeNode root) {
        if (root == null) {
            return;
        }
        if (d == depth - 1) {
            root.left = new TreeNode(val, root.left, null);
            root.right = new TreeNode(val, null, root.right);
            return;
        }
        addLayer(d + 1, depth, val, root.left);
        addLayer(d + 1, depth, val, root.right);
    }
}
