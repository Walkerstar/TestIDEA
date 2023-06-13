package Other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 如何计算树上任意两点 x 和  y 的最近公共祖先 lca 呢？
 * <p>
 * 设节点 i 的深度为 depth[i]。这可以通过一次 DFS 预处理出来。
 * <p>
 * 假设 depth[x] ≤ depth[y]（否则交换两点）。我们可以先把更靠下的 y 更新为 y 的第 depth[y]−depth[x] 个祖先节点，这样  x 和 y 就处在同一深度了。
 * <p>
 * 如果此时 x=y，那么 x 就是 lca。否则说明 lca 在更上面，那么就把 x 和 y 一起往上跳。
 * <p>
 * 由于不知道 lca 的具体位置，只能不断尝试，先尝试大步跳，再尝试小步跳。设 i=⌊(log2)^n⌋，循环直到 i<0。每次循环：
 * <p>
 * 1. 如果 x 的第 2^i 个祖先节点不存在，即 pa[x][i]=−1，说明步子迈大了，将 i 减 1，继续循环。
 * 2. 如果 x 的第 2^i 个祖先节点存在，且 pa[x][i]  ≠ pa[y][i]，说明  lca 在 pa[x][i] 的上面，
 * 那么更新 x 为  pa[x][i]，更新 y 为 pa[y][i]，将 i 减 1，继续循环。否则，若 pa[x][i]=pa[y][i]，
 * 那么 lca 可能在 pa[x][i] 下面，由于无法向下跳，只能将 i 减 1，继续循环。
 * <p>
 * 上述做法能跳就尽量跳，不会错过任何可以上跳的机会。所以循环结束时， x 与  lca 只有一步之遥，即  lca=pa[x][0]。
 *
 * @author MCW 2023/6/12
 */
public class LeastCommonAncestor {
    private int[] depth;
    private int[][] pa;

    public LeastCommonAncestor(int[][] edges) {
        int n = edges.length + 1;
        // n 的二进制长度
        int m = 32 - Integer.numberOfLeadingZeros(n);
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        depth = new int[n];
        pa = new int[n][m];
        dfs(g, 0, -1);
        for (int i = 0; i < m - 1; i++) {
            for (int x = 0; x < n; x++) {
                int p = pa[x][i];
                pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
            }
        }
    }

    public void dfs(List<Integer>[] g, int x, int fa) {
        pa[x][0] = fa;
        for (int y : g[x]) {
            if (y != fa) {
                depth[y] = depth[x] + 1;
                dfs(g, y, x);
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        for (; k > 0; k &= k - 1) {
            node = pa[node][Integer.numberOfTrailingZeros(k)];
        }
        return node;
    }

    public int getLCA(int x, int y) {
        if (depth[x] > depth[y]) {
            int temp = y;
            y = x;
            x = temp;
        }
        // 使 y 和 x 在同一深度
        y = getKthAncestor(y, depth[y] - depth[x]);
        if (y == x) {
            return x;
        }
        for (int i = pa[x].length - 1; i >= 0; i--) {
            int px = pa[x][i], py = pa[y][i];
            if (px != py) {
                x = px;
                y = py;
            }
        }
        return pa[x][0];
    }
}
