package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * @author mcw 2020\7\1 0001-15:25
 * Scramble String
 */
public class leetCode87 {
    public static boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        if (s1.length() == 1 && s1.equals(s2)) return true;
        char[] s1Char = s1.toCharArray();
        char[] s2Char = s2.toCharArray();
        Arrays.sort(s1Char);
        Arrays.sort(s2Char);
        String str1 = new String(s1Char);
        String str2 = new String(s2Char);
        //如果排序后的 s1 和 s2 相等 ，返回 false
        if (!str1.equals(str2)) {
            return false;
        }
        for (int len = 1; len < s1.length(); len++) {
            String s1Left = s1.substring(0, len);
            String s1Right = s1.substring(len, s1.length());
            //将 s1 的左边 len 个字符与 s2 的 左边 len 个字符互换，
            // 同时，将 s1 的右边 与 s2 的右边互换
            if ((isScramble(s1Left, s2.substring(0, len)) &&
                    (isScramble(s1Right, s2.substring(len, s2.length()))))
                    ||
                    //将 s1 的左边 len 个字符与 s2 的 右边剩余的 s2.length() - len 个字符互换，
                    // 同时，将 s1 的右边 与 s2 的左边互换
                    isScramble(s1Left, s2.substring(s2.length() - len, s2.length())) &&
                            (isScramble(s1Right, s2.substring(0, s2.length() - len)))) {
                return true;
            }
        }
        return false;
    }
}
