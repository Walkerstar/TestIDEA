package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * <p>
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * <p>
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 * <p>
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 *
 * @author mcw 2021\5\17 0017-9:52
 */
public class leetCode993 {

    //x 的信息
    int x;
    TreeNode xParent;
    int xDepth;
    boolean xFound = false;

    //y 的信息
    int y;
    TreeNode yParent;
    int yDepth;
    boolean yFound = false;

    /**
     * 方法一：深度优先
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        dfs(root, 0, null);
        return xDepth == yDepth && xParent != yParent;
    }

    private void dfs(TreeNode node, int depth, TreeNode parent) {
        if (node == null) {
            return;
        }
        if (node.val == x) {
            xParent = parent;
            xDepth = depth;
            xFound = true;
        } else if (node.val == y) {
            yParent = parent;
            yDepth = depth;
            yFound = true;
        }

        //如果两个节点都找到了，就可以提前退出遍历
        if (xFound && yFound) {
            return;
        }
        dfs(node.left, depth + 1, node);
        if (xFound && yFound) {
            return;
        }
        dfs(node.right, depth + 1, node);
    }

    /**
     * 方法二：广度优先
     */
    public boolean isCousions(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> depthQueue = new LinkedList<>();
        nodeQueue.offer(root);
        depthQueue.offer(0);
        update(root, null, 0);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int depth = depthQueue.poll();
            if (node.left != null) {
                nodeQueue.offer(node.left);
                depthQueue.offer(depth + 1);
                update(node.left, node, depth + 1);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                depthQueue.offer(depth + 1);
                update(node.right, node, depth + 1);
            }
            if (xFound && yFound) {
                break;
            }
        }
        return xDepth == yDepth && xParent != yParent;
    }

    //用来判断是否遍历到 x 或 y 的辅助函数
    private void update(TreeNode node, TreeNode parent, int depth) {
        if (node.val == x) {
            xParent = parent;
            xDepth = depth;
            xFound = true;
        } else if (node.val == y) {
            yParent = parent;
            yDepth = depth;
            yFound = true;
        }
    }
}
