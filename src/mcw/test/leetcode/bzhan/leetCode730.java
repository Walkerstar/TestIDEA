package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给定一个字符串 s，返回 s 中不同的非空「回文子序列」个数 。
 * <p>
 * 通过从 s 中删除 0 个或多个字符来获得子序列。
 * <p>
 * 如果一个字符序列与它反转后的字符序列一致，那么它是「回文字符序列」。
 * <p>
 * 如果有某个 i , 满足 ai != bi ，则两个序列 a1, a2, ... 和 b1, b2, ... 不同。
 * <p>
 * 注意：
 * 结果可能很大，你需要对 109 + 7 取模 。
 *
 * @author mcw 2022/6/10 17:02
 */
public class leetCode730 {

    public static int countPalindromicSubsequences(String s) {
        final int MOD = 1000000007;
        int n = s.length();
        int[][][] dp = new int[4][n][n];
        for (int i = 0; i < n; i++) {
            dp[s.charAt(i) - 'a'][i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                for (char c = 'a'; c <= 'd'; c++) {
                    int k = c - 'a';
                    if (s.charAt(i) == c && s.charAt(j) == c) {
                        dp[k][i][j] = (2 +
                                (dp[0][i + 1][j - 1] + dp[1][i + 1][j - 1]) % MOD +
                                (dp[2][i + 1][j - 1] + dp[3][i + 1][j - 1]) % MOD) % MOD;
                    } else if (s.charAt(i) == c) {
                        dp[k][i][j] = dp[k][i][j - 1];
                    } else if (s.charAt(j) == c) {
                        dp[k][i][j] = dp[k][i + 1][j];
                    } else {
                        dp[k][i][j] = dp[k][i + 1][j - 1];
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < 4; i++) {
            res = (res + dp[i][0][n - 1]) % MOD;
        }
        return res;
    }

    public int countPalindromicSubsequences2(String s) {
        final int MOD = 1000000007;
        int n = s.length();
        int[][] dp = new int[n][n];
        int[][] next = new int[n][4];
        int[][] pre = new int[n][4];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        int[] pos = new int[4];
        Arrays.fill(pos, -1);

        for (int i = 0; i < n; i++) {
            for (int c = 0; c < 4; c++) {
                pre[i][c] = pos[c];
            }
            pos[s.charAt(i) - 'a'] = i;
        }

        pos[0] = pos[1] = pos[2] = pos[3] = n;

        for (int i = n - 1; i >= 0; i--) {
            for (int c = 0; c < 4; c++) {
                next[i][c] = pos[c];
            }
            pos[s.charAt(i) - 'a'] = i;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    int low = next[i][s.charAt(i) - 'a'];
                    int high = pre[j][s.charAt(i) - 'a'];
                    if (low > high) {
                        dp[i][j] = (2 + dp[i + 1][j - 1] * 2) % MOD;
                    } else if (low == high) {
                        dp[i][j] = (1 + dp[i + 1][j - 1] * 2) % MOD;
                    } else {
                        dp[i][j] = (dp[i + 1][j - 1] * 2 % MOD - dp[low + 1][high - 1] + MOD) % MOD;
                    }
                } else {
                    dp[i][j] = ((dp[i + 1][j] + dp[i][j - 1]) % MOD - dp[i + 1][j - 1] + MOD) % MOD;
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequences("abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba"));
    }
}
