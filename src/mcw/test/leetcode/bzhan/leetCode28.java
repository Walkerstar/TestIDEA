package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\11 0011-15:47
 * implement strStr()
 * 字符串匹配，返回子串在母串中的索引位置
 */
public class leetCode28 {
    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return -1;
        if (haystack == null || haystack.length() == 0) return -1;
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j >= needle.length()) {
            return i - needle.length();
        } else {
            return -1;
        }
    }
}
