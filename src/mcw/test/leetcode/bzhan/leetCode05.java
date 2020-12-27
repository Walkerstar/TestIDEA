package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\11 0011-14:14
 * longest palindromic substring(最长回文子串)
 *  同 牛客/Test26.java
 */
public class leetCode05 {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        int len = s.length();
        char[] sChar = s.toCharArray();
        int start = 0;
        int length = 1;
        boolean[][] isPalindrome = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < len - 1; i++) {
            if (sChar[i] == sChar[i + 1]) {
                isPalindrome[i][i + 1] = true;
                start = i;
                length = 2;
            }
        }
        // i 表示 当前子串的长度
        for (int i = 3; i <= len; i++) {
            for (int j = 0; j + i - 1 < len; j++) {
                if (sChar[j] == sChar[j + i - 1] && isPalindrome[j + 1][j + i - 2]) {
                    isPalindrome[j][j + i - 1] = true;
                    start = j;
                    length = i;
                }
            }
        }
        return s.substring(start, start + length);
    }

}
