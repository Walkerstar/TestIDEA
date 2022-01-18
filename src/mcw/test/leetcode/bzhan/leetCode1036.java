package mcw.test.leetcode.bzhan;

import java.nio.file.Path;
import java.util.*;

/**
 * 在一个 10^6 x 10^6 的网格中，每个网格上方格的坐标为 (x, y) 。
 * <tr/>
 * 现在从源方格 source = [sx, sy] 开始出发，意图赶往目标方格 target = [tx, ty] 。数组 blocked 是封锁的方格列表，
 * 其中每个 blocked[i] = [xi, yi] 表示坐标为 (xi, yi) 的方格是禁止通行的。<br/>
 * 每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 不 在给出的封锁列表 blocked 上。同时，不允许走出网格。<br/>
 * 只有在可以通过一系列的移动从源方格 source 到达目标方格 target 时才返回 true。否则，返回 false。
 *
 * @author mcw 2022/1/11 10:05
 */
public class leetCode1036 {

    static class Solution1 {
        Set<Long> forbid = new HashSet<>();
        long max = (long) 1e6;
        long from;
        long to;

        public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
            from = max * (long) source[0] + (long) source[1];
            to = max * (long) target[0] + (long) target[1];
            for (int i = 0; i < blocked.length; i++) {
                forbid.add(max * (long) blocked[i][0] + (long) blocked[i][1]);
            }
            Set<Long> start = new HashSet<>();
            Set<Long> end = new HashSet<>();
            int maxSize = blocked.length * (blocked.length - 1) / 2;
            findPath(target[0], target[1], maxSize, end);
            findPath(source[0], source[1], maxSize, start);
            return start.size() > maxSize && end.size() > maxSize || end.contains(from) || start.contains(to);
        }

        public void findPath(int i, int j, int maxSize, Set<Long> set) {
            long p = max * (long) i + (long) j;
            if (forbid.contains(p) || set.contains(p) || i < 0 || j < 0 || i == max || j == max) {
                return;
            }
            set.add(p);
            if (set.size() > maxSize || set.contains(to) && set.contains(from)) {
                return;
            }
            findPath(i + 1, j, maxSize, set);
            findPath(i - 1, j, maxSize, set);
            findPath(i, j + 1, maxSize, set);
            findPath(i, j - 1, maxSize, set);
        }
    }

    static class Solution2 {
        boolean[][] cameBefore = new boolean[650][650];
        int r = 0;
        int k = 0;

        public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
            int max = (int) 1e6;
            TreeSet<Integer> row = new TreeSet<>();
            TreeSet<Integer> col = new TreeSet<>();
            row.add(source[0]);
            row.add(target[0]);
            col.add(source[1]);
            col.add(target[1]);
            for (int i = 0; i < blocked.length; i++) {
                row.add(blocked[i][0]);
                if (blocked[i][0] > 0) {
                    row.add(blocked[i][0] - 1);
                }
                if (blocked[i][0] < max - 1) {
                    row.add(blocked[i][0] + 1);
                }
                col.add(blocked[i][1]);
                if (blocked[i][1] > 0) {
                    col.add(blocked[i][1] - 1);
                }
                if (blocked[i][1] < max - 1) {
                    col.add(blocked[i][1] + 1);
                }
            }
            Map<Integer, Integer> map1 = new HashMap<>();//行映射
            Map<Integer, Integer> map2 = new HashMap<>();//列映射
            for (Integer a : row) {
                map1.put(a, r);
                r++;
            }
            for (Integer a : col) {
                map2.put(a, k);
                k++;
            }
            for (int i = 0; i < blocked.length; i++) {
                cameBefore[map1.get(blocked[i][0])][map2.get(blocked[i][1])] = true;
            }
            findPath(map1.get(source[0]), map2.get(source[1]));
            return cameBefore[map1.get(target[0])][map2.get(target[1])];
        }

        public void findPath(int x, int y) {
            if (x < 0 || y < 0 || x == r || y == k || cameBefore[x][y]) {
                return;
            }
            cameBefore[x][y] = true;
            findPath(x + 1, y);
            findPath(x - 1, y);
            findPath(x, y + 1);
            findPath(x, y - 1);
        }
    }

    static class Solution3 {
        //在包围圈中
        static final int BLOCKED = -1;
        //不在包围圈中
        static final int VALID = 0;
        //无论在不在包围圈中，但在 n(n-1)/2 步搜索的过程中经过了 target
        static final int FOUND = 1;

        static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        static final int BOUNDARY = 1000000;

        /**
         * .我们从 source 开始进行广度优先搜索。如果经过了不超过 n(n−1)/2 个非障碍位置就已经结束搜索，说明 source 在「包围圈」中。
         * 但如果我们在过程中经过了target，那么说明它们是可达的，否则一定不可达。<tr/>
         * <p>
         * .我们再从 target 开始进行广度优先搜索。如果经过了不超过 n(n−1)/2 个非障碍位置就已经结束搜索，说明 target 在「包围圈」中。
         * 否则说明 source 和 target 均不在「包围圈」中，此时一定可达。<tr/>
         * <p>
         * 搜索的过程中需要借助哈希表来标记每个位置是否已经搜索过。
         */
        public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
            if (blocked.length < 2) {
                return true;
            }
            Set<Pair> hashBlocked = new HashSet<>();
            for (int[] pos : blocked) {
                hashBlocked.add(new Pair(pos[0], pos[1]));
            }
            int result = check(blocked, source, target, hashBlocked);
            if (result == FOUND) {
                return true;
            } else if (result == BLOCKED) {
                return false;
            } else {
                result = check(blocked, target, source, hashBlocked);
                return result != BLOCKED;
            }
        }

        public int check(int[][] blocked, int[] start, int[] finish, Set<Pair> hashBlocked) {
            int sx = start[0], sy = start[1];
            int fx = finish[0], fy = finish[1];
            int countdown = blocked.length * (blocked.length - 1) / 2;
            Pair startPair = new Pair(sx, sy);
            Queue<Pair> queue = new ArrayDeque<>();
            queue.offer(startPair);
            Set<Pair> visited = new HashSet<>();
            visited.add(startPair);
            while (!queue.isEmpty() && countdown > 0) {
                Pair pair = queue.poll();
                int x = pair.x, y = pair.y;
                for (int d = 0; d < 4; d++) {
                    int nx = x + dirs[d][0], ny = y + dirs[d][1];
                    Pair newPair = new Pair(nx, ny);
                    if (nx > 0 && nx < BOUNDARY && ny >= 0 && ny < BOUNDARY
                            && !hashBlocked.contains(newPair) && !visited.contains(newPair)) {
                        if (nx == fx && ny == fy) {
                            return FOUND;
                        }
                        --countdown;
                        queue.offer(newPair);
                        visited.add(newPair);
                    }
                }
            }
            if (countdown > 0) {
                return BLOCKED;
            }
            return VALID;
        }

        class Pair {
            int x;
            int y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o) {
                if (o instanceof Pair) {
                    Pair pair = (Pair) o;
                    return x == pair.x && y == pair.y;
                }
                return false;
            }

            @Override
            public int hashCode() {
                return (int) ((long) x << 20 | y);
            }
        }
    }

    static class Solution4 {
        static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        static final int BOUNDARY = 1000000;

        public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
            if (blocked.length < 2) {
                return true;
            }
            //离散化
            TreeSet<Integer> rows = new TreeSet<>();
            TreeSet<Integer> columns = new TreeSet<>();
            for (int[] pos : blocked) {
                rows.add(pos[0]);
                columns.add(pos[1]);
            }
            rows.add(source[0]);
            rows.add(target[0]);
            columns.add(source[1]);
            columns.add(target[1]);

            Map<Integer, Integer> rMapping = new HashMap<>();
            Map<Integer, Integer> cMapping = new HashMap<>();

            int firstRow = rows.first();
            int rId = (firstRow == 0 ? 0 : 1);
            rMapping.put(firstRow, rId);
            int prevRow = firstRow;
            for (Integer row : rows) {
                if (row == firstRow) {
                    continue;
                }
                rId += (row == prevRow + 1 ? 1 : 2);
                rMapping.put(row, rId);
                prevRow = row;
            }
            if (prevRow != BOUNDARY - 1) {
                ++rId;
            }
            int firstColumn = columns.first();
            int cId = firstColumn == 0 ? 0 : 1;
            cMapping.put(firstColumn, cId);
            int prevColumn = firstColumn;
            for (Integer column : columns) {
                if (column == firstColumn) {
                    continue;
                }
                cId += column == prevColumn + 1 ? 1 : 2;
                cMapping.put(column, cId);
                prevColumn = column;
            }
            if (prevColumn != BOUNDARY - 1) {
                ++cId;
            }

            int[][] grid = new int[rId + 1][cId + 1];
            for (int[] pos : blocked) {
                int x = pos[0], y = pos[1];
                grid[rMapping.get(x)][cMapping.get(y)] = 1;
            }
            int sx = rMapping.get(source[0]), sy = cMapping.get(source[1]);
            int tx = rMapping.get(target[0]), ty = cMapping.get(target[1]);

            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{sx, sy});
            grid[sx][sy] = 1;
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                int x = arr[0], y = arr[1];
                for (int d = 0; d < 4; d++) {
                    int nx = x + dirs[d][0], ny = y + dirs[d][1];
                    if (nx >= 0 && nx <= rId && ny >= 0 && ny <= cId && grid[nx][ny] != 1) {
                        if (nx == tx && ny == ty) {
                            return true;
                        }
                        queue.offer(new int[]{nx, ny});
                        grid[nx][ny] = 1;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        int[][] blocked = {};//{{0, 1}, {1, 0}};
        int[] source = {0, 0};
        int[] target = {0, 2};
        System.out.println(new Solution2().isEscapePossible(blocked, source, target));
    }
}
