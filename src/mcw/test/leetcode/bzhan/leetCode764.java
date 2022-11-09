package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。mines[i] = [xi, yi]表示 grid[xi][yi] == 0
 * <p>
 * 返回  grid 中包含 1 的最大的 轴对齐 加号标志的阶数 。如果未找到加号标志，则返回 0 。
 * <p>
 * 一个 k 阶由 1 组成的 “轴对称”加号标志 具有中心网格 grid[r][c] == 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。
 * 注意，只有加号标志的所有网格要求为 1 ，别的网格可能为 0 也可能为 1 。
 *
 * @author mcw 2022/11/9 10:22
 */
public class leetCode764 {

    /**
     * 暴力
     */
    public static int orderOfLargestPlusSign(int n, int[][] mines) {
        //构造初始矩阵
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(arr[i], 1);
        }
        for (int[] mine : mines) {
            arr[mine[0]][mine[1]] = 0;
        }

        //记录最大阶数
        int maxNum = 0;
        //加号标志的阶数
        int k = 0;

        for (int i = 0; i < n; i++) {
            if (i < maxNum || i > n - maxNum - 1) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                //如果 i < maxNum ,则表明 从 i 向左延伸的最大距离小于此时的最大阶数，故此排除
                //如果 i > (n-1 - maxNum),则表明从 i 向右延伸的最大距离小于等于此时的最大阶数，故此排除
                if (i < maxNum || i > n - maxNum - 1) {
                    break;
                }
                //如果 j < maxNum ,则表明 从 j 向上延伸的最大距离小于此时的最大阶数，故此排除
                //如果 j > (n-1 - maxNum),则表明从 j 向下延伸的最大距离小于等于此时的最大阶数，故此排除
                if (j < maxNum || j > n - maxNum - 1) {
                    continue;
                }
                if (arr[i][j] == 0) {
                    continue;
                }
                //以 (i,j) 为中心，向四个方向伸展,探索此时阶数 k 的最大值
                while ((i - k) >= 0 && (i + k) < n && (j - k) >= 0 && (j + k) < n) {
                    if (arr[i - k][j] == 1 && arr[i + k][j] == 1 &&
                            arr[i][j - k] == 1 && arr[i][j + k] == 1) {
                        k++;
                    } else {
                        break;
                    }
                }
                maxNum = Math.max(maxNum, k);
                k = 0;
            }
        }
        return maxNum;
    }

    /**
     * 方法一：动态规划
     * 思路与算法
     *
     * 对于每个中心点坐标 (i,j)，分别从上下左右四个方向计算从 (i,j) 开始最长连续 1 的个数。
     *
     * 设 dp[i][j][k] 表示以 (i,j) 为起点在方向 k 上的连续 1 的最大数目：
     *
     * 如果 grid[i][j] 为 0，那么此时该方向的连续 1 的最大数目为 0；
     * 如果 grid[i][j] 为 1, 那么此时该方向的连续 1 的最大数目为该方向上前一个单元为起点的最大数目加 1；
     * 假设当前 k=0,1,2,3 时，分别表示方向为左、右、上、下，则我们可以得到递推公式如下：
     *
     * dp[i][j][0]={ 0,                 grid[i][j]=0
     *               dp[i][j−1][0]+1,   grid[i][j]=1
     * dp[i][j][1]={ 0,                 grid[i][j]=0
     *               dp[i][j+1][1]+1,   grid[i][j]=1
     * dp[i][j][2]={ 0,                 grid[i][j]=0
     *               dp[i−1][j][2]+1,   grid[i][j]=1
     * dp[i][j][3]={ 0,                 grid[i][j]=0
     *               dp[i+1][j][3]+1,   grid[i][j]=1
     *
     * 假设网格中有一行为 01110110，当前方向为向左，那么对应的连续 1 的个数就是 012301201。
     * 以每个点为 (i,j)为中心的四个方向中最小连续 1 的个数即为以其为中心构成的加号标志的最大阶数，
     * 我们用公式表示
     *     3
     * L= min dp[i][j][k]
     *    k=0 。
     * 在实际计算时，我们为了方便计算只用 dp[i][j] 保存四个方向中最小的连续 1 的个数即可。
     */
    public static int orderOfLargestPlusSign2(int n, int[][] mines) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], n);
        }
        Set<Integer> banned = new HashSet<>();
        for (int[] mine : mines) {
            banned.add(mine[0] * n + mine[1]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            //left
            for (int j = 0; j < n; j++) {
                if (banned.contains(i * n + j)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[i][j] = Math.min(dp[i][j], count);
            }
            count = 0;
            //right
            for (int j = n - 1; j >= 0; j--) {
                if (banned.contains(i * n + j)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[i][j] = Math.min(dp[i][j], count);
            }
        }

        for (int i = 0; i < n; i++) {
            int count = 0;
            //up
            for (int j = 0; j < n; j++) {
                if (banned.contains(j * n + i)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[j][i] = Math.min(dp[j][i], count);
            }
            count = 0;
            //down
            for (int j = n - 1; j >= 0; j--) {
                if (banned.contains(j * n + i)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[j][i] = Math.min(dp[j][i], count);
                ans = Math.max(ans, dp[j][i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(orderOfLargestPlusSign2(100, new int[][]{{0, 0}, {2, 3}, {15, 12}, {78, 45}, {56, 45}}));
    }
}
