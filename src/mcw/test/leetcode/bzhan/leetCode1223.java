package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 有一个骰子模拟器会每次投掷的时候生成一个 1 到 6 的随机数。
 * <p>
 * 不过我们在使用它时有个约束，就是使得投掷骰子时，连续 掷出数字 i 的次数不能超过 rollMax[i]（i 从 1 开始编号）。
 * <p>
 * 现在，给你一个整数数组 rollMax 和一个整数 n，请你来计算掷 n 次骰子可得到的不同点数序列的数量。
 * <p>
 * 假如两个序列中至少存在一个元素不同，就认为这两个序列是不同的。由于答案可能很大，所以请返回 模 10^9 + 7 之后的结果。
 * <p>
 * 提示：
 * <li>1 <= n <= 5000</li>
 * <li>rollMax.length == 6</li>
 * <li>1 <= rollMax[i] <= 15</li>
 *
 * @author mcw 2023/2/10 16:01
 */
public class leetCode1223 {

    static final int MOD = 1000000007;

    /*-------官方题解------*/
    //1.动态规划
    //未使用空间优化(Unused space optimization)
    public int dieSimulator1(int n, int[] rollMax) {
        int[][][] d = new int[n + 1][6][16];
        for (int i = 0; i < 6; i++) {
            d[1][i][1] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j]; k++) {
                    for (int p = 0; p < 6; p++) {
                        if (p != j) {
                            d[i][p][1] = (d[i][p][1] + d[i - 1][j][k]) % MOD;
                        } else if (k + 1 <= rollMax[j]) {
                            d[i][p][k + 1] = (d[i][p][k + 1] + d[i - 1][j][k]) % MOD;
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 6; i++) {
            for (int k = 1; k <= rollMax[i]; k++) {
                res = (res + d[n][i][k]) % MOD;
            }
        }
        return res;
    }

    //used space optimization
    public int dieSimulator2(int n, int[] rollMax) {
        int[][][] d = new int[2][6][16];
        for (int i = 0; i < 6; i++) {
            d[1][i][1] = 1;
        }
        for (int i = 2; i <= n; i++) {
            int t = i & 1;
            for (int j = 0; j < 6; j++) {
                Arrays.fill(d[t][j], 0);
            }
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j]; k++) {
                    for (int p = 0; p < 6; p++) {
                        if (p != j) {
                            d[t][p][1] = (d[t][p][1] + d[t ^ 1][j][k]) % MOD;
                        } else if (k + 1 <= rollMax[j]) {
                            d[t][p][k + 1] = (d[t][p][k + 1] + d[t ^ 1][j][k]) % MOD;
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 6; i++) {
            for (int k = 1; k <= rollMax[i]; k++) {
                res = (res + d[n & 1][i][k]) % MOD;
            }
        }
        return res;
    }

    //2.状态表示优化
    public int dieSimulator3(int n, int[] rollMax) {
        int[][] d = new int[n + 1][6];
        int[] sum = new int[n + 1];
        sum[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                int pos = Math.max(i - rollMax[j] - 1, 0);
                int sub = ((sum[pos] - d[pos][j]) % MOD + MOD) % MOD;
                d[i][j] = ((sum[i - 1] - sub) % MOD + MOD) % MOD;
                if (i <= rollMax[j]) {
                    d[i][j] = (d[i][j] + 1) % MOD;
                }
                sum[i] = (sum[i] + d[i][j]) % MOD;
            }
        }
        return sum[n];
    }

    //灵茶山艾府 的题解
    //1.回溯 (overtime)
    private int[] rollMax2;

    public int dieSimulator4(int n, int[] rollMax) {
        this.rollMax2 = rollMax;
        int m = rollMax.length;
        long ans = 0;
        for (int i = 0; i < m; i++) {
            ans += dfs(n - 1, i, rollMax[i] - 1);
        }
        return (int) (ans % MOD);
    }

    /**
     * 剩余掷骰子的次数，用 i 表示；
     * 上一个骰子值，用 last 表示；
     * last 的剩余连续出现次数，用 left 表示。
     */
    private int dfs(int i, int last, int left) {
        if (i == 0) {
            return 1;
        }
        long res = 0;
        for (int j = 0; j < rollMax2.length; j++) {
            if (j != last) {
                res += dfs(i - 1, j, rollMax2[j] - 1);
            } else if (left > 0) {
                res += dfs(i - 1, j, left - 1);
            }
        }
        return (int) (res % MOD);
    }

    //2.记忆化搜索 memorization search
    private int[][][] cache;

    public int dieSimulator5(int n, int[] rollMax) {
        this.rollMax2 = rollMax;
        int m = rollMax.length;
        cache = new int[n][m][15];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //-1表示没访问过
                Arrays.fill(cache[i][j], -1);
            }
        }
        long ans = 0;
        for (int j = 0; j < m; j++) {
            ans += dfs2(n - 1, j, rollMax[j] - 1);
        }
        return (int) (ans % MOD);
    }

    private int dfs2(int i, int last, int left) {
        if (i == 0) {
            return 1;
        }
        if (cache[i][last][left] >= 0) {
            return cache[i][last][left];
        }
        long res = 0;
        for (int j = 0; j < rollMax2.length; j++) {
            if (j != last) {
                res += dfs(i - 1, j, left - 1);
            } else if (left > 0) {
                res += dfs2(i - 1, j, left - 1);
            }
        }
        return cache[i][last][left] = (int) (res % MOD);
    }

    //3.递推
    //dfs 改成 f 数组； 递归改成循环（每个参数都对应一层循环）；递归边界改成 f 数组的初始值。
    public int dieSimulator6(int n, int[] rollMax) {
        int m = rollMax.length; // 6
        int[][][] f = new int[n][m][15];
        for (int j = 0; j < m; ++j)
            Arrays.fill(f[0][j], 1);
        for (int i = 1; i < n; ++i)
            for (int last = 0; last < m; ++last)
                for (int left = 0; left < rollMax[last]; ++left) {
                    long res = 0;
                    for (int j = 0; j < m; ++j)
                        if (j != last) res += f[i - 1][j][rollMax[j] - 1];
                        else if (left > 0) res += f[i - 1][j][left - 1];
                    f[i][last][left] = (int) (res % MOD);
                }
        long ans = 0;
        for (int j = 0; j < m; ++j)
            ans += f[n - 1][j][rollMax[j] - 1];
        return (int) (ans % MOD);
    }

    //3.1 优化
    public int dieSimulator7(int n, int[] rollMax) {
        int m = rollMax.length;
        int[][] f = new int[n][m];
        int[] s = new int[n];
        Arrays.fill(f[0], 1);
        s[0] = m;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int res = s[i - 1], mx = rollMax[j];
                if (i > mx) res -= s[i - mx - 1] - f[i - mx - 1][j];
                else if (i == mx) --res;
                f[i][j] = (res % MOD + MOD) % MOD; // 防止出现负数
                s[i] = (s[i] + f[i][j]) % MOD;
            }
        }
        return s[n - 1];
    }
}
