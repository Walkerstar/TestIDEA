package mcw.test.leetcode.bzhan;

/**
 * 你是一位施工队的工长，根据设计师的要求准备为一套设计风格独特的房子进行室内装修。
 * <p>
 * 房子的客厅大小为 n x m，为保持极简的风格，需要使用尽可能少的 正方形 瓷砖来铺盖地面。
 * <p>
 * 假设正方形瓷砖的规格不限，边长都是整数。
 * <p>
 * 请你帮设计师计算一下，最少需要用到多少块方形瓷砖？
 * <p>
 * 示例 1：
 * 输入：n = 2, m = 3
 * 输出：3
 * 解释：3 块地砖就可以铺满卧室。
 * 2 块 1x1 地砖
 * 1 块 2x2 地砖
 * <p>
 * 示例 2：
 * 输入：n = 5, m = 8
 * 输出：5
 * <p>
 * 示例 3：
 * 输入：n = 11, m = 13
 * 输出：6
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 13
 * 1 <= m <= 13
 *
 * @author MCW 2023/6/8
 */
public class leetCode1240 {

    int ans;

    /**
     * 由于本题为 NP-Complete 问题，没有类似于动态规划的递推公式。
     * 具体到本题给定的 n,m 的取值范围为 1 ≤ n ≤ 13,1 ≤ m ≤ 13，因此我们可以采用暴力搜索的方法，
     * 依次尝试在空余的格子中铺设正方形，尝试遍历所有可能铺设方法，找到最少的正方形的数目即可。
     * 我们用二维矩阵 rect[n][m] 来表示当前长方形中每个点被覆盖的情况，具体搜索过程如下：
     * <p>
     * 1. 初始时，由于长方形的每个点均未被覆盖，矩阵中每个单元格的状态均设置为 false .
     * <p>
     * 2. 从位置 (0,0) 开始依次尝试用正方形来覆盖部分区域，如果当前位置  (x,y) 已经覆盖，则尝试下一个位置 (x,y+1)。
     * 每次尝试从  (x,y) 进行正方形覆盖，假设当前覆盖的正方形的左上顶点为  (x,y)，正方形的长度为 k，则过程如下：
     * 2.1 由于当前覆盖的正方形不能超越长方形区域的边界，此时  k 的取值范围为 1 ≤ k < min(n−x,m−y)，为了减枝， k 的取值依次从大到小进行尝试。
     * 2.2 同时需要检测该正方形区域内是否已被其它正方形覆盖过，即检测该区域中是否存在  rect[i][j]=true，
     * 此时  i,j 的取值范围  x ≤ i < x+k,y ≤ j < y+k，如果可以填充则对该正方形区域进行填充，并移动到下一个位置  (x,y+k) 继续尝试搜索；
     * <p>
     * 3. 当前如果已经将该矩形进行完全覆盖完成，记录当前最小值并返回。
     * 在搜索时同时对当前已经覆盖的正方形进行计数，如果当前计数  cnt 大于等于当前的最小值  ans 时，说明当前的覆盖方法已经不是最优解则直接返回。
     */
    public int tilingRectangle(int n, int m) {
        ans = Math.max(n, m);
        boolean[][] rect = new boolean[n][m];
        dfs(0, 0, rect, 0);
        return ans;
    }

    public void dfs(int x, int y, boolean[][] rect, int cnt) {
        int n = rect.length, m = rect[0].length;
        if (cnt >= ans) {
            return;
        }
        if (x >= n) {
            ans = cnt;
            return;
        }
        // 检测下一行
        if (y >= m) {
            dfs(x + 1, 0, rect, cnt);
            return;
        }
        // 如当前已经被覆盖，则直接尝试下一个位置
        if (rect[x][y]) {
            dfs(x, y + 1, rect, cnt);
            return;
        }
        for (int k = Math.min(n - x, m - y); k >= 1 && isAvailable(rect, x, y, k); k--) {
            // 将长度为 k 的正方形区域标记覆盖
            fillUp(rect, x, y, k, true);
            // 跳过 k 个位置开始检测
            dfs(x, y + k, rect, cnt + 1);
            fillUp(rect, x, y, k, false);
        }
    }

    public boolean isAvailable(boolean[][] rect, int x, int y, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (rect[x + i][y + j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void fillUp(boolean[][] rect, int x, int y, int k, boolean val) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                rect[x + i][y + j] = val;
            }
        }
    }
}
