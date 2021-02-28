package mcw.test.leetcode.bzhan;

/**
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 * @author mcw 2021\2\27 0027-20:01
 */
public class leetCode395 {

    /**
     * 方法一：分治
     * 找出 出现次数少于 k 的字符，包含该字符的串都不满足条件
     */
    public int longestSubstring(String s, int k) {
        int n = s.length();
        return dfs(s, 0, n - 1, k);
    }

    private int dfs(String s, int l, int r, int k) {
        //统计在 [l,r] 的字符串中，每个字符出现的次数
        int[] ch = new int[26];
        while (l < r) {
            ch[s.charAt(l) - 'a']++;
            l++;
        }
        //找出区间内不满足条件的字符
        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (ch[i] > 0 && ch[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        //如果没有找到，则表明区间 [l,r] 的字符串符合条件，返回长度
        if (split == 0) {
            return r - l + 1;
        }

        //如果找到了，则以该字符分割区间字符串
        int i = l;
        int ret = 0;
        while (i <= r) {
            //从左往右遍历，找出第一个符合条件的字符的位置
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            //如果 此时的位置 大于 区间右边界，则说明该区间不符合条件，直接结束循环
            if (i > r) {
                break;
            }
            //记录 第一个符合条件的字符的位置，继续往右遍历，找出符合的字符串的右边界
            int start = i;
            while (i <= r && s.charAt(i) != split) {
                i++;
            }
            //记录此时符合的字符串的长度
            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }
        return ret;
    }
}
