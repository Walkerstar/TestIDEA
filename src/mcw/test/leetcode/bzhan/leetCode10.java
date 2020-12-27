package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\1 0001-11:45
 * 字符串匹配,  .（点）表示匹配一个字符， *（星号）表示匹配 0 个或 n 个字符
 * 例：
 * “aa”,"a"---false
 * “aa”，“.a”---true
 * "aab","c*a*b"----true
 */
public class leetCode10 {
    public static boolean isMatch(String s, String p) {
        // p 的头部不能是 *， 否则，index 会溢出
        if (s == null || p == null) {
            return false;
        }
        //match[][] 中的数字 代表的是 字符的个数，而不是索引
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        //没有从任何一个字符串中选中字符时，一定匹配
        match[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                match[0][i] = match[0][i - 2];
            }
        }

        for (int j = 1; j <= s.length(); j++) {
            for (int k = 1; k <= p.length(); k++) {
                if (p.charAt(k - 1) == '.' || p.charAt(k - 1) == s.charAt(j - 1)) {
                    match[j][k] = match[j - 1][k - 1];
                } else if (p.charAt(k - 1) == '*') {
                    if (p.charAt(k - 2) == s.charAt(j - 1) || p.charAt(k - 2) == '.') {
                        match[j][k] = match[j][k - 2] || match[j - 1][k];
                    } else {
                        match[j][k] = match[j][k - 2];
                    }
                }
            }
        }
        return match[s.length()][p.length()];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aab", "c*a.a"));
    }
}
