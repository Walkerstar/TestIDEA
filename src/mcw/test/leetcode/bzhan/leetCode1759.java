package mcw.test.leetcode.bzhan;

/**
 * 给你一个字符串 s ，返回 s 中 同构子字符串 的数目。由于答案可能很大，只需返回对 10^9 + 7 取余 后的结果。
 * <p>
 * 同构字符串 的定义为：如果一个字符串中的所有字符都相同，那么该字符串就是同构字符串。
 * <p>
 * 子字符串 是字符串中的一个连续字符序列。
 * <p>
 * 提示：
 * <li>1 <= s.length <= 10^5</li>
 * <li>s 由小写字符串组成</li>
 *
 * @author mcw 2022/12/26 10:59
 */
public class leetCode1759 {

    /**
     * 方法一：数学
     * 思路与算法
     * <p>
     * 题目给出一个长度为 n 的字符串 s，并给出「同构字符串」的定义为：如果一个字符串中的所有字符都相同，那么该字符串就是同构字符串。
     * 现在我们需要求 s 中「同构子字符串」的数目。
     * 因为「同构子字符串」为一个连续的字符序列且需要序列中的字符都相同，那么我们首先按照连续相同的字符来对字符串 s 进行分组，
     * 比如对于字符串 “abbcccaa" 我们的分组结果为 ["a","bb","ccc","aa"]。
     * 因为对于一个组中字符串的任意子字符串都为「同构子字符串」，而一个长度为 m 的字符串的子字符串的数目为  m×(m+1) / 2 。
     * 那么我们对于每一个组来统计其贡献的「同构子字符串」数目并求和即可。
     */
    public static int countHomogenous(String s) {
        final int MOD = (int) (1e9 + 7);
        long res = 0;
        char prev = s.charAt(0);
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == prev) {
                cnt++;
            } else {
                res += (long) (cnt + 1) * cnt / 2;
                cnt = 1;
                prev = c;
            }
        }
        res += (long) (cnt + 1) * cnt / 2;
        return (int) res % MOD;
    }
}
