package mcw.test.leetcode.bzhan;

/**
 * 一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
 * <p>
 * 比方说，"abaacc" 的美丽值为 3 - 1 = 2 。
 * 给你一个字符串 s ，请你返回它所有子字符串的 美丽值 之和。
 *
 * @author mcw 2022/12/12 14:25
 */
public class leetCode1781 {

    /**
     * 用两个下标 i 和 j 表示子字符串的两端。
     * 用双层循环来遍历所有子字符串，第一层循环子字符串的起点 i，第二层循环固定 i，遍历子字符串的重点 j，
     * 遍历时维护更新用来记录字符频率的哈希表，
     * 并计算美丽值。
     */
    public static int beautySum(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] cnt = new int[26];
            int maxFreq = 0;
            for (int j = i; j < s.length(); j++) {
                cnt[s.charAt(j) - 'a']++;
                maxFreq = Math.max(maxFreq, cnt[s.charAt(j) - 'a']);
                int minFreq = s.length();
                for (int k = 0; k < 26; k++) {
                    if (cnt[k] > 0) {
                        minFreq = Math.min(minFreq, cnt[k]);
                    }
                }
                res += maxFreq - minFreq;
            }
        }
        return res;
    }
}
