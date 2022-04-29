package mcw.test.leetcode.bzhan;

/**
 * 给你一个 n * n 矩阵 grid ，矩阵由若干 0 和 1 组成。请你用四叉树表示该矩阵 grid 。
 * <p>
 * 你需要返回能表示矩阵的 四叉树 的根结点。
 * <p>
 * 注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
 * <p>
 * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
 * <p>
 * val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False；
 * isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
 * class Node {
 * public boolean val;
 * public boolean isLeaf;
 * public Node topLeft;
 * public Node topRight;
 * public Node bottomLeft;
 * public Node bottomRight;
 * }
 * 我们可以按以下步骤为二维区域构建四叉树：
 * <p>
 * 如果当前网格的值相同（即，全为 0 或者全为 1），将 isLeaf 设为 True ，将 val 设为网格相应的值，并将四个子节点都设为 Null 然后停止。
 * 如果当前网格的值不同，将 isLeaf 设为 False， 将 val 设为任意值，然后如下图所示，将当前网格划分为四个子网格。
 * 使用适当的子网格递归每个子节点。
 * <p>
 * <p>
 * 如果你想了解更多关于四叉树的内容，可以参考 wiki(https://en.wikipedia.org/wiki/Quadtree) 。
 * <p>
 * 四叉树格式：
 * <p>
 * 输出为使用层序遍历后四叉树的序列化形式，其中 null 表示路径终止符，其下面不存在节点。
 * <p>
 * 它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 [isLeaf, val] 。
 * <p>
 * 如果 isLeaf 或者 val 的值为 True ，则表示它在列表 [isLeaf, val] 中的值为 1 ；如果 isLeaf 或者 val 的值为 False ，则表示值为 0 。
 *
 * @author mcw 2022/4/29 11:42
 */
public class leetCode427 {

    /**
     * 方法一：递归
     * 思路与算法
     * <p>
     * 我们可以使用递归的方法构建出四叉树。
     * <p>
     * 具体地，我们用递归函数 dfs(r0, c0, r1, c1) 处理给定的矩阵 grid 从 r0 行开始到 r1 - 1 行，从 c0 和 c1 - 1 列的部分。
     * 我们首先判定这一部分是否均为 0 或 1，
     * 如果是，那么这一部分对应的是一个叶节点，我们构造出对应的叶节点并结束递归；
     * 如果不是，那么这一部分对应的是一个非叶节点，我们需要将其分成四个部分：行的分界线为 (r0+r1)/2，列的分界线为 (c0+c1)/2 ，
     * 根据这两条分界线递归地调用 dfs 函数得到四个部分对应的树，再将它们对应地挂在非叶节点的四个子节点上。
     */
    public Node construct(int[][] grid) {
        return dfs(grid, 0, 0, grid.length, grid.length);
    }

    public Node dfs(int[][] grid, int r0, int c0, int r1, int c1) {
        boolean same = true;
        for (int i = r0; i < r1; i++) {
            for (int j = c0; j < c1; j++) {
                if (grid[i][j] != grid[r0][c0]) {
                    same = false;
                    break;
                }
            }
            if (!same) {
                break;
            }
        }
        if (same) {
            return new Node(grid[r0][c0] == 1, true);
        }
        Node ret = new Node(true, false,
                            dfs(grid, r0, c0, (r0 + r1) / 2, (c0 + c1) / 2),
                            dfs(grid, r0, (c0 + c1) / 2, (r0 + r1) / 2, c1),
                            dfs(grid, (r0 + r1) / 2, c0, r1, (c0 + c1) / 2),
                            dfs(grid, (r0 + r1) / 2, (c0 + c1) / 2, r1, c1));
        return ret;
    }


    /**
     * 方法二：递归 + 二维前缀和优化
     * 思路与算法
     * <p>
     * 我们可以对方法一中暴力判定某一部分是否均为00 或 1 的代码进行优化。
     * <p>
     * 具体地，当某一部分均为 0 时，它的和为 0；某一部分均为 1 时，它的和为这一部分的面积大小。因此我们可以使用二维前缀和
     * （参考「304. 二维区域和检索 - 矩阵不可变」）进行优化。我们可以与处理出数组 grid 的二维前缀和，这样一来，
     * 当我们需要判定某一部分是否均为 0 或 1 时，可以在 O(1) 的时间内得到这一部分的和，从而快速地进行判断。
     */
    public Node construct2(int[][] grid) {
        int n = grid.length;
        int[][] pre = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }
        return dfs2(grid, pre, 0, 0, n, n);
    }

    public Node dfs2(int[][] grid, int[][] pre, int r0, int c0, int r1, int c1) {
        int total = getSum(pre, r0, c0, r1, c1);
        if (total == 0) {
            return new Node(false, true);
        } else if (total == (r1 - r0) * (c1 - c0)) {
            return new Node(true, true);
        }

        Node ret = new Node(
                true,
                false,
                dfs2(grid, pre, r0, c0, (r0 + r1) / 2, (c0 + c1) / 2),
                dfs2(grid, pre, r0, (c0 + c1) / 2, (r0 + r1) / 2, c1),
                dfs2(grid, pre, (r0 + r1) / 2, c0, r1, (c0 + c1) / 2),
                dfs2(grid, pre, (r0 + r1) / 2, (c0 + c1) / 2, r1, c1)
        );
        return ret;
    }

    public int getSum(int[][] pre, int r0, int c0, int r1, int c1) {
        return pre[r1][c1] - pre[r1][c0] - pre[r0][c1] + pre[r0][c0];
    }

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
