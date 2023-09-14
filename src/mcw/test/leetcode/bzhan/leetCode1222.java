package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 在一个 8x8 的棋盘上，放置着若干「黑皇后」和一个「白国王」。
 * <p>
 * 给定一个由整数坐标组成的数组 queens ，表示黑皇后的位置；以及一对坐标 king ，表示白国王的位置，返回所有可以攻击国王的皇后的坐标(任意顺序)。
 * <p>
 * 示例 1：
 * 输入：queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
 * 输出：[[0,1],[1,0],[3,3]]
 * 解释：
 * [0,1] 的皇后可以攻击到国王，因为他们在同一行上。
 * [1,0] 的皇后可以攻击到国王，因为他们在同一列上。
 * [3,3] 的皇后可以攻击到国王，因为他们在同一条对角线上。
 * [0,4] 的皇后无法攻击到国王，因为她被位于 [0,1] 的皇后挡住了。
 * [4,0] 的皇后无法攻击到国王，因为她被位于 [1,0] 的皇后挡住了。
 * [2,4] 的皇后无法攻击到国王，因为她和国王不在同一行/列/对角线上。
 * <p>
 * 提示：
 * <p>
 * 1 <= queens.length <= 63
 * queens[i].length == 2
 * 0 <= queens[i][j] < 8
 * king.length == 2
 * 0 <= king[0], king[1] < 8
 * 一个棋盘格上最多只能放置一枚棋子。
 *
 * @author MCW 2023/9/14
 */
public class leetCode1222 {

    /**
     * 方法一：从国王出发
     * 思路与算法
     * <p>
     * 我们可以依次枚举八个方向，并从国王出发，其遇到的第一个皇后就可以攻击到它。
     * <p>
     * 记国王的位置为 (kx,ky)，枚举的方向为 (dx,dy))，那么我们不断地将 kx 加上 dx，将 ky加上 dy，直到遇到皇后或者走出边界位置。
     * 为了记录皇后的位置，我们可以使用一个 8×8 的二维数组，也可以使用一个哈希表，这样就可以在 O(1) 的时间内判断某一个位置是否有皇后。
     */
    public List<List<Integer>> queensAttackKing(int[][] queens, int[] king) {
        // 存储皇后位置
        Set<Integer> queenPos = new HashSet<>();
        for (int[] queen : queens) {
            int x = queen[0], y = queen[1];
            queenPos.add(x * 8 + y);
        }

        List<List<Integer>> ans = new ArrayList<>();
        // 从 国王 的八个方向 寻找皇后
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                // 0，0 是原地不动，所以跳过
                if (dx == 0 && dy == 0) {
                    continue;
                }
                int kx = king[0] + dx, ky = king[1] + dy;
                // 沿着当前方向，一直寻找最近的皇后，直至出界
                while (kx >= 0 && kx < 8 && ky >= 0 && ky < 8) {
                    int pos = kx * 8 + ky;
                    if (queenPos.contains(pos)) {
                        List<Integer> posList = new ArrayList<>();
                        posList.add(kx);
                        posList.add(ky);
                        ans.add(posList);
                        break;
                    }
                    kx += dx;
                    ky += dy;
                }
            }
        }
        return ans;
    }


    /**
     * 方法二：从皇后出发
     * 思路与算法
     * <p>
     * 我们枚举每个皇后，判断它是否在国王的八个方向上。如果在，说明皇后可以攻击到国王。
     * <p>
     * 记国王的位置为 (kx,ky)，皇后的位置为 (qx,qy)，那么皇后相对于国王的位置为 (x,y)=(qx−kx,qy−ky)，显然当 x=0 或 y=0 或 ∣x∣=∣y∣ 时，皇后可以攻击到国王，
     * 方向为 (sgn(x),sgn(y))，其中 sgn(x) 为符号函数，当 x>0 时为 1，x<0 时为 −1，x=0 时为 0。
     * <p>
     * 同一个方向的皇后可能有多个，我们需要选择距离国王最近的那一个，因此可以使用一个哈希映射，它的键表示某一个方向，值是一个二元组，
     * 分别表示当前距离最近的皇后以及对应的距离。当我们枚举到一个新的皇后时，如果它在国王的八个方向上，就与哈希映射中对应的值比较一下大小关系即可。
     * <p>
     * 当枚举完所有皇后，我们就可以从哈希映射值的部分中得到答案。
     */
    public List<List<Integer>> queensAttackTheKing(int[][] queens, int[] king) {
        Map<Integer, int[]> candidates = new HashMap<>();
        int kx = king[0], ky = king[1];
        // 枚举所有皇后
        for (int[] queen : queens) {
            int qx = queen[0], qy = queen[1];
            int x = qx - kx, y = qy - ky;
            // 如果 皇后 和 国王 在同一行、同一列、同一斜线上，则判断距离
            if (x == 0 || y == 0 || Math.abs(x) == Math.abs(y)) {
                // 判断此时 皇后 位于 国王 的方向
                int dx = sgn(x), dy = sgn(y);
                int key = dx * 10 + dy;
                // 如果此时的皇后不在候选之列，又或者 同方向候选的皇后的距离 大于 当前皇后的距离，那么就加入候选队列中
                if (!candidates.containsKey(key) || candidates.get(key)[2] > Math.abs(x) + Math.abs(y)) {
                    candidates.put(key, new int[]{queen[0], queen[1], Math.abs(x) + Math.abs(y)});
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, int[]> entry : candidates.entrySet()) {
            int[] value = entry.getValue();
            List<Integer> posList = new ArrayList<>();
            posList.add(value[0]);
            posList.add(value[1]);
            ans.add(posList);
        }
        return ans;
    }

    private int sgn(int x) {
        return x > 0 ? 1 : (x == 0 ? 0 : -1);
    }
}
