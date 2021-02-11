package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。 换句话说，第一个字符串的排列之一是第二个字符串的子串。<br>
 * 1.输入的字符串只包含小写字母
 * 2.两个字符串的长度都在 [1, 10,000] 之间
 *
 * @author mcw 2021/2/10 14:22
 */
public class leetCode567 {

    /**
     * 双指针
     */
    public boolean checkInclusion(String s1, String s2) {
        char[] pattern = s1.toCharArray();
        char[] text = s2.toCharArray();

        int pLen = s1.length();
        int tLen = s2.length();

        int[] pFreq = new int[26];
        int[] winFreq = new int[26];

        //统计 s1 字符串每个字符出现的次数
        for (int i = 0; i < pLen; i++) {
            pFreq[pattern[i] - 'a']++;
        }

        //统计 s1 中，有多少个不同的字符
        int pCount = 0;
        for (int i = 0; i < 26; i++) {
            if (pFreq[i] > 0) {
                pCount++;
            }
        }

        int left = 0;
        int right = 0;
        // 当滑动窗口中的某个字符个数与 s1 中对应相等的时候才计数
        int winCount = 0;
        //遍历 s2 字符串
        while (right < tLen) {
            //如果 当前字符 在 s1 中出现过，则在 winFreq[] 记录该字符出现的次数
            if (pFreq[text[right] - 'a'] > 0) {
                winFreq[text[right] - 'a']++;
                if (winFreq[text[right] - 'a'] == pFreq[text[right] - 'a']) {
                    winCount++;
                }
            }
            right++;
            //当 滑动窗口 中的不同字符个数 与 s1 中的不同字符个数相等时，
            while (pCount == winCount) {
                //检查 滑动窗口 的长度，如果相同，则返回 true
                if (right - left == pLen) {
                    return true;
                }
                //如果 滑动窗口的 左边界 字符 在 s1 中出现过，
                if (pFreq[text[left] - 'a'] > 0) {
                    //则 该字符 数量 减一
                    winFreq[text[left] - 'a']--;
                    // 且判断 s2 左边界的值 是否小于 s1 左边界的值，如果小于，则说明 该字符在 s2 中还未统计完成，将 winCount-1
                    if (winFreq[text[left] - 'a'] < pFreq[text[left] - 'a']) {
                        winCount--;
                    }
                }
                left++;
            }
        }
        return false;
    }

    /**
     * 滑动窗口
     */
    public boolean checkInclusion1(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] c1 = new int[26];
        int[] c2 = new int[26];
        for (int i = 0; i < n; i++) {
            ++c1[s1.charAt(i) - 'a'];
            ++c2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(c1, c2)) {
            return true;
        }
        for (int i = n; i < m; i++) {
            ++c2[s2.charAt(i) - 'a'];
            --c2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(c1, c2)) {
                return true;
            }
        }
        return false;
    }
}
