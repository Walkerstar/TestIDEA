package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 给你一棵二叉树的根节点 root ，请你构造一个下标从 0 开始、大小为 m x n 的字符串矩阵 res ，用以表示树的 格式化布局 。构造此格式化布局矩阵需要遵循以下规则：
 * <p>
 * 树的 高度 为 height ，矩阵的行数 m 应该等于 height + 1 。
 * 矩阵的列数 n 应该等于 2height+1 - 1 。
 * 根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2] 。
 * 对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，将其左子节点放置在 res[r+1][c-2 ^(height-r-1)] ，右子节点放置在 res[r+1][c+2^(height-r-1)] 。
 * 继续这一过程，直到树中的所有节点都妥善放置。
 * 任意空单元格都应该包含空字符串 "" 。
 * 返回构造得到的矩阵 res 。
 *
 * @author MCW 2022/8/22
 */
public class leetCode655 {

    /**
     * 深度优先
     */
    public List<List<String>> printTreeDFS(TreeNode root) {
        int height = calDepth(root);
        int m = height + 1;
        int n = (1 << (height + 1)) - 1;
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add("");
            }
            res.add(row);
        }
        dfs(res, root, 0, (n - 1) / 2, height);
        return res;
    }

    /**
     * 查找树的最大高度
     */
    public int calDepth(TreeNode root) {
        int h = 0;
        if (root.left != null) {
            h = Math.max(h, calDepth(root.left) + 1);
        }
        if (root.right != null) {
            h = Math.max(h, calDepth(root.right) + 1);
        }
        return h;
    }

    /**
     * 填充数组
     */
    public void dfs(List<List<String>> res, TreeNode root, int r, int c, int height) {
        res.get(r).set(c, Integer.toString(root.val));
        if (root.left != null) {
            dfs(res, root.left, r + 1, c - (1 << (height - r - 1)), height);
        }
        if (root.right != null) {
            dfs(res, root.right, r + 1, c + (1 << (height - r - 1)), height);
        }
    }

    /**
     * 广度优先
     */
    public List<List<String>> printTreeBFS(TreeNode root) {
        int height = maxDepth(root);
        int m = height + 1;
        int n = (1 << (height + 1) - 1);
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> row = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                row.add("");
            }
            res.add(row);
        }
        Queue<Tuple> queue = new ArrayDeque<>();
        queue.offer(new Tuple(root, 0, (n - 1) / 2));
        while (!queue.isEmpty()) {
            Tuple t = queue.poll();
            TreeNode node = t.node;
            int r = t.r, c = t.c;
            res.get(r).set(c, Integer.toString(node.val));
            if (node.left != null) {
                queue.offer(new Tuple(node.left, r + 1, c - (1 << (height - r - 1))));
            }
            if (node.right != null) {
                queue.offer(new Tuple(node.right, r + 1, c + (1 << (height - r - 1))));
            }
        }
        return res;
    }

    public int maxDepth(TreeNode root) {
        int h = -1;
        Queue<TreeNode> queue = new ArrayDeque();
        queue.offer(root);
        while (!queue.isEmpty()) {
            h++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                assert node != null;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return h;
    }


    class Tuple {
        TreeNode node;
        int r;
        int c;

        public Tuple(TreeNode node, int r, int c) {
            this.node = node;
            this.r = r;
            this.c = c;
        }
    }
}
