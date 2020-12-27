package mcw.test.leetcode.niuke;

/**
 * @author mcw 2020\4\16 0016-19:53
 * 给定一个字符串 S 和一个字符串 T ，计算 S 中的 T 的不同子序列的个数。
 * 例： S="rabbbit"  T="rabbit" 返回 3
 *
 *   T\S  0  r  a  b  b  b  i  t
 *     0  1  1  1  1  1  1  1  1
 *     r  0  1  1  1  1  1  1  1
 *     a  0  0  1  1  1  1  1  1
 *     b  0  0  0  1  2  3  3  3
 *     b  0  0  0  0  1  3  3  3
 *     i  0  0  0  0  0  0  3  3
 *     t  0  0  0  0  0  0  0  3
 *
 */
public class Test36 {

    /**
     *二维数组 解决
     */
    public static int numDistinct0(String s, String t) {
        if (s == null || t == null || t.length() == 0 || s.length() < t.length()) {
            return 0;
        }
        int m = s.length();
        int n = t.length();
        int[][] array = new int[m + 1][n + 1];
        //初始化第一行，字符串 S 为"",除了字符串为""的情况，结果全部为0
        for (int i = 1; i < n + 1; i++) {
            array[0][i] = 0;
        }
        //初始化第一列，字符串为"",结果全为 1
        for (int i = 0; i < m + 1; i++) {
            array[i][0] = 1;
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j <= Math.min(i, n); j++) {
                if (s.charAt(i - 1) != t.charAt(j - 1)) {
                    array[i][j] = array[i - 1][j];
                } else {
                    array[i][j] = array[i - 1][j] + array[i - 1][j - 1];
                }
            }
        }
        return array[m][n];
    }


    /**
     * 将二维数组变换为一维数组
     * @param s 母串
     * @param t 子串
     * @return 母串中包含子串的最大个数
     */
    public static int numDistinct1(String s,String t){
        if (s == null || t == null || t.length() == 0 || s.length() < t.length()) {
            return 0;
        }
        int m = s.length();
        int n = t.length();
        int[] dp = new int[m + 1];
        dp[0]=1;

        for (int i = 1; i < m+1; i++) {
            for (int j = Math.min(i,n); j > 0; j--) {
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    dp[j]=dp[j]+dp[j-1];
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numDistinct0("rabbbit", "rabbit"));
    }
}
