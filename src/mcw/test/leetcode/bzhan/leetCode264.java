package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 *
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * offer/Test33
 * @author mcw 2021\4\12 0012-13:02
 */
public class leetCode264 {

    /**
     * 定义数组 dp，其中 dp[i] 表示第 i 个丑数，第 n 个丑数即为 dp[n]。
     *
     * 由于最小的丑数是 1，因此 dp[1]=1。
     *
     * 如何得到其余的丑数呢？定义三个指针 p2,p3,p5，表示下一个丑数是当前指针指向的丑数乘以对应的质因数。
     * 初始时，三个指针的值都是 1。
     *
     * 当 2≤i≤n 时，令 dp[i]=min(dp[p2]×2,dp[p3]×3,dp[p5]×5)，然后分别比较 dp[i] 和 dp[p2],dp[p3],dp[p5] 是否相等，
     * 如果相等则将对应的指针加 1。
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }
}
