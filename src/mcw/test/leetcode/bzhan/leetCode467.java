package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 把字符串 s 看作是 “abcdefghijklmnopqrstuvwxyz” 的无限环绕字符串，所以 s 看起来是这样的：
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...." .
 * <p>
 * 现在给定另一个字符串 p 。返回 s 中 唯一 的 p 的 非空子串 的数量 。
 *
 * @author mcw 2022/5/25 10:15
 */
public class leetCode467 {

    public int findSubStringInWrapRoundString(String p) {
        //dp[α] 表示 p 中以字符 α 结尾且在 s 中的子串的最长长度，知道了最长长度，也就知道了不同的子串的个数
        int[] dp = new int[26];
        int k = 0;
        for (int i = 0; i < p.length(); i++) {
            // 字符之差为 1 或 -25
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) {
                //如果 p[i] 是 p[i-1] 在字母表中的下一个字母，则将 k 加一
                ++k;
            } else {
                //否则将 k 置为 1，表示重新开始计算连续递增的子串长度
                k = 1;
            }
            dp[p.charAt(i) - 'a'] = Math.max(dp[p.charAt(i) - 'a'], k);
        }
        return Arrays.stream(dp).sum();
    }


    public int findSubStringInWrapRoundString2(String p) {
        int[] longestSub = new int[26];
        char[] c = p.toCharArray();
        int l = 0, r = 1;
        while (r <= c.length) {
            while (r < c.length && (c[r] - 'a' + 25) % 26 == c[r - 1] - 'a') {
                r++;
            }
            for (int i = 0; i + l < r && i < 26; i++) {
                longestSub[c[i + 1] - 'a'] = Math.max(longestSub[c[i + 1] - 'a'], r - i - 1);
            }
            l = r;
            r++;
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += longestSub[i];
        }
        return ans;
    }
}
