package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
 *<P>
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）<br/>
 * 每个元音 'a' 后面都只能跟着 'e'<br/>
 * 每个元音 'e' 后面只能跟着 'a' 或者是 'i'<br/>
 * 每个元音 'i' 后面 不能 再跟着另一个 'i'<br/>
 * 每个元音 'o' 后面只能跟着 'i' 或者是 'u'<br/>
 * 每个元音 'u' 后面只能跟着 'a'
 * </P>
 * 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
 *
 * @author mcw 2022/1/17 15:59
 */
public class leetCode1220 {

    /**
     * 动态规划
     */
    public int countVowelPermutation(int n) {
        long mod = 1000000007;
        long[] dp = new long[5];
        long[] ndp = new long[5];
        for (int i = 0; i < 5; i++) {
            dp[i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            //元音字母 ‘a’ 前面只能跟着 ‘e’,‘i’,‘u’；
            ndp[0] = (dp[1] + dp[2] + dp[4]) % mod;

            //元音字母 ‘e’ 前面只能跟着 ’a’,‘i’；
            ndp[1] = (dp[0] + dp[2]) % mod;

            //元音 ‘i’ 前面只能跟着 ‘e’,‘o’；
            ndp[2] = (dp[1] + dp[3]) % mod;

            //元音 ‘o’ 前面只能跟着 ‘i’；
            ndp[3] = dp[2];

            //元音 ‘u’ 后面只能跟着 ‘o’,‘i’；
            ndp[4] = (dp[2] + dp[3]) % mod;
            System.arraycopy(ndp, 0, dp, 0, 5);
        }
        long ans = 0;
        for (int i = 0; i < 5; i++) {
            ans = (ans + dp[i]) % mod;
        }
        return (int) ans;
    }

    /**
     * 矩阵快速幂
     */
    public int countVowelPermutation2(int n) {
        long mod = 1_000_000_007;
        long[][] factor = {
                {0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0}
        };

        long[][] res = fastPow(factor, n - 1, mod);
        long ans = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = (ans + res[i][j]) % mod;
            }
        }
        return (int) ans;
    }

    public long[][] fastPow(long[][] matrix, int n, long mod) {
        int m = matrix.length;
        long[][] res = new long[m][m];
        long[][] curr = matrix;
        for (int i = 0; i < m; i++) {
            res[i][i] = 1;
        }
        for (int i = n; i != 0; i >>= 1) {
            if ((i % 2) == 1) {
                res = multiply(curr, res, mod);
            }
            curr = multiply(curr, curr, mod);
        }
        return res;
    }

    /**
     * 矩阵乘积 : C = 矩阵 A(2x3) * 矩阵 B(3x4) = A(2) * B(4) = C(2x4)
     */
    public long[][] multiply(long[][] matrixA, long[][] matrixB, long mod) {
        int m = matrixA.length;
        int n = matrixB[0].length;
        long[][] res = new long[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = 0;
                for (int k = 0; k < matrixA[i].length; k++) {
                    res[i][j] = (res[i][j] + matrixA[i][k] * matrixB[k][j]) % mod;
                }
            }
        }
        return res;
    }
}
