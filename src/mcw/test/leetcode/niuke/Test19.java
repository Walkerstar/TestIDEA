package mcw.test.leetcode.niuke;

/**
 * @author mcw 2020\2\25 0025-21:08
 *
 * 给出一个回文串S，分割 S 使得分割出的每一个子串都是回文串
 * 计算将字符串 S 分割成回文串 分割结果的最小切割数
 *
 * 例：S="aab"     返回 1 ，["aa","b"]是一次分割成
 */
public class Test19 {

    private String s;
    private int f[][] = new int[1000][1000];

    public int minCut(String s) {
        this.s = s;
        //先求解小段的子序列
        for (int t = 0; t <= s.length(); t++) {
            for (int i = 0, j = t; j < s.length(); i++, j++) {
                f[i][j] = compCut(i, j);
            }
        }
        return f[0][s.length() - 1];
    }

    //状态转移方程的实现
    private int compCut(int i, int j) {
        if (isPalindrome(i, j))
            return 0;
        int min = s.length();
        for (int p = i; p < j; p++) {
            int a = f[i][p];
            int b = f[p + 1][j];
            a = a + b + 1;
            min = min < a ? min : a;
        }
        return min;
    }

    //判断是否是回文串
    private boolean isPalindrome(int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }



    //另一个方法
    public int minCut1(String str) {

        int[] dp = new int[str.length() + 1];
        boolean[][] p = new boolean[str.length()][str.length()];
        dp[str.length()] = -1;
        dp[str.length() - 1] = 0;

        for (int i = str.length() - 1; i > 0; i--) {
            dp[i] = Integer.MAX_VALUE;

            for (int j = i; j < str.length(); j++) {
                if (str.charAt(i) == s.charAt(j) && (j - i < 2 || p[i + 1][j - 1])) {
                    p[i][j] = true;
                    dp[i] = Math.min(dp[i], dp[j + i] + 1);
                }
            }
        }
        return dp[0];
    }
}
