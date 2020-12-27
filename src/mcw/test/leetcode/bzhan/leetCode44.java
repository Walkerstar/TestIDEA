package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\27 0027-15:10
 * Wildcard Matching(通配符匹配)
 */
public class leetCode44 {

    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        //match[][] 中的数字 代表的是 字符的个数，而不是索引
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];

        match[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                match[0][i] = match[0][i - 1];
            }
        }

        for (int si = 1; si <= s.length(); si++) {
            for (int pi = 1; pi <= p.length(); pi++) {
                if (p.charAt(pi - 1) == '?' || p.charAt(pi - 1) == s.charAt(si - 1)) {
                    match[si][pi] = match[si - 1][pi - 1];
                } else if (p.charAt(pi - 1) == '*') {
                    match[si][pi] = match[si][pi - 1] || match[si - 1][pi];
                }
            }
        }
        return match[s.length()][p.length()];
    }
}
