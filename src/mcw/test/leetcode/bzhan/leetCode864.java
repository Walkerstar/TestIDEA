package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 给定一个二维网格 grid ，其中：
 * <p>
 * '.' 代表一个空房间
 * '#' 代表一堵
 * '@' 是起点
 * 小写字母代表钥匙
 * 大写字母代表锁
 * 我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。
 * 我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。
 * <p>
 * 假设 k 为 钥匙/锁 的个数，且满足 1 <= k <= 6，字母表中的前 k 个字母在网格中都有自己对应的一个小写和一个大写字母。
 * 换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。
 * <p>
 * 返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回 -1 。
 * <p>
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 30
 * grid[i][j] 只含有 '.', '#', '@', 'a'-'f' 以及 'A'-'F'
 * 钥匙的数目范围是 [1, 6]
 * 每个钥匙都对应一个 不同 的字母
 * 每个钥匙正好打开一个对应的锁
 *
 * @author mcw 2022/11/10 16:43
 */
public class leetCode864 {

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 方法一：状态压缩 + 广度优先搜索
     * 思路与算法
     * <p>
     * 给定一个只包含空房间、墙、起点和终点的二维网格，我们可以使用广度优先搜索的方法求出起点到终点的最短路径。
     * 这是因为在最短路径上，我们最多只会经过每个房间一次。
     * 因此从起点开始，使用队列进行广度优先搜索，当第一个搜索到某个节点的时候，我们就可以得到从起点到该节点正确的最短路。
     * <p>
     * 如果加上了钥匙和锁，我们应该如何解决问题呢？
     * 类似地，在最短路径上也不可能存在如下的情况：我们经过了某个房间两次，并且这两次我们拥有钥匙的情况是完全一致的。
     * <p>
     * 因此，我们可以用一个三元组 (x,y,mask) 表示当前的状态，其中 (x,y) 表示当前所处的位置，mask 是一个二进制数，长度恰好等于网格中钥匙的数目，
     * mask 的第 i 个二进制位为 1，当且仅当我们已经获得了网格中的第 i 把钥匙。
     * <p>
     * 这样一来，我们就可以使用上述的状态进行广度优先搜索。
     * 初始时，我们把 (sx,sy,0) 加入队列，其中 (sx,sy) 为起点。在搜索的过程中，我们可以向上下左右四个方向进行扩展：
     * <p>
     * 如果对应方向是空房间，那么 mask 的值不变；
     * <p>
     * 如果对应方向是第 i 把钥匙，那么将 mask 的第 i 位置为 1；
     * <p>
     * 如果对应方向是第 i 把锁，那么只有在 mask 的第 i 位为 1 时，才可以通过。
     * <p>
     * 当我们搜索到一个 mask 每一个二进制都为 1 的状态时，说明获取了所有钥匙，此时就可以返回最短路作为答案。
     */
    public static int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        int sx = 0, sy = 0;
        Map<Character, Integer> keyToIndex = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //记录起点的位置
                if (grid[i].charAt(j) == '@') {
                    sx = i;
                    sy = j;
                } else if (Character.isLowerCase(grid[i].charAt(j))) {
                    //如果是小写字母，则表示此处是一把钥匙
                    if (!keyToIndex.containsKey(grid[i].charAt(j))) {
                        int idx = keyToIndex.size();
                        //钥匙为 key , 个数 为 value
                        keyToIndex.put(grid[i].charAt(j), idx);
                    }
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        int[][][] dist = new int[m][n][1 << keyToIndex.size()];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], -1);
            }
        }

        //将起点坐标压入队列中
        queue.offer(new int[]{sx, sy, 0});
        dist[sx][sy][0] = 0;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1], mask = arr[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                //如果未超出边界，也未碰到墙
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx].charAt(ny) != '#') {
                    if (grid[nx].charAt(ny) == '.' || grid[nx].charAt(ny) == '@') {
                        //路径未访问，则将其加入队列
                        if (dist[nx][ny][mask] == -1) {
                            dist[nx][ny][mask] = dist[x][y][mask] + 1;
                            queue.offer(new int[]{nx, ny, mask});
                        }
                    } else if (Character.isLowerCase(grid[nx].charAt(ny))) {
                        //如果碰到了钥匙，取出该钥匙的标号
                        int idx = keyToIndex.get(grid[nx].charAt(ny));
                        if (dist[nx][ny][mask | (1 << idx)] == -1) {
                            dist[nx][ny][mask | (1 << idx)] = dist[x][y][mask] + 1;
                            //找齐了钥匙，返回
                            if ((mask | (1 << idx)) == (1 << keyToIndex.size()) - 1) {
                                return dist[nx][ny][mask | (1 << idx)];
                            }
                            queue.offer(new int[]{nx, ny, mask | (1 << idx)});
                        }
                    } else {
                        //碰到锁
                        int idx = keyToIndex.get(Character.toLowerCase(grid[nx].charAt(ny)));
                        if ((mask & (1 << idx)) != 0 && dist[nx][ny][mask] == -1) {
                            dist[nx][ny][mask] = dist[x][y][mask] + 1;
                            queue.offer(new int[]{nx, ny, mask});
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(shortestPathAllKeys(new String[]{"@.a.#", "###.#", "b.A.B"}));//8
    }
}
