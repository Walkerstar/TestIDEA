package mcw.test.leetcode.bzhan;

/**
 * 给你一个字符串 s ，每 两个 连续竖线 '|' 为 一对 。换言之，第一个和第二个 '|' 为一对，第三个和第四个 '|' 为一对，以此类推。
 * <p>
 * 请你返回 不在 竖线对之间，s 中 '*' 的数目。
 * <p>
 * 注意，每个竖线 '|' 都会 恰好 属于一个对。
 *
 * <li>1 <= s.length <= 1000</li>
 * <li>s 只包含小写英文字母，竖线 '|' 和星号 '*' 。</li>
 * <li>s 包含 偶数 个竖线 '|' 。</li>
 *
 * @author mcw 2023/1/29 14:59
 */
public class leetCode2315 {
    public int countAsterisks(String s) {
        boolean valid = false;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '|') {
                valid = !valid;
            } else if (c == '*' && valid) {
                res++;
            }
        }
        return res;
    }
}
