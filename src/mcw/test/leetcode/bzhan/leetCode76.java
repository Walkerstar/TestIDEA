package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\6\7 0007-15:14
 * Minimum Window Substring
 * given a string S and T,find the minimum window in S which will all the characters in T in complexity O(n).
 * S="ADDBECDDEBANC" T="ABC"     return "BANC"
 */
public class leetCode76 {
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) return "";
        int matchCount = 0;
        String res = "";
        //计算 t 字符串中每个字符出现的次数
        int[] tArr = new int[256];
        for (char c : t.toCharArray()) {
            tArr[c]++;
        }
        //找出 s 字符串中 第一个出现在 t 字符串中的字符下标
        int[] sArr = new int[256];
        int left = findNextStrIdx(0, s, tArr);
        if (left == s.length()) return "";//no char in S and T
        int right = left;
        //find first char in t. it's the start point
        while (right < s.length()) {
            int rightChar = s.charAt(right);
            if (sArr[rightChar] < tArr[rightChar]) {
                matchCount++;
            }
            sArr[rightChar]++;
            while (left < s.length() && matchCount == t.length()) {
                if (res.isEmpty() || res.length() > right - left + 1) {
                    res = s.substring(left, right);
                }
                int leftChar = s.charAt(left);
                if (sArr[leftChar] <= tArr[leftChar]) {
                    matchCount--;
                }
                sArr[leftChar]--;
                left = findNextStrIdx(left + 1, s, tArr);
            }
            right = findNextStrIdx(right + 1, s, tArr);
        }
        return res;
    }

    /**
     * 找出 s 字符串中 出现在 t 字符串中的字符下标
     */
    private static int findNextStrIdx(int start, String s, int[] tArr) {
        while (start < s.length()) {
            char c = s.charAt(start);
            if (tArr[c] != 0) return start;
            start++;
        }
        return start;
    }
}
