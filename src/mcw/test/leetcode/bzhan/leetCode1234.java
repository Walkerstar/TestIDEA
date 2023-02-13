package mcw.test.leetcode.bzhan;

/**
 * 有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
 * <p>
 * 假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
 * <p>
 * 给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
 * <p>
 * 你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
 * <p>
 * 请返回待替换子串的最小可能长度。
 * <p>
 * 如果原字符串自身就是一个平衡字符串，则返回 0。
 * <p>
 * 提示：
 * <li>1 <= s.length <= 10^5</li>
 * <li>s.length 是 4 的倍数</li>
 * <li>s 中只含有 'Q', 'W', 'E', 'R' 四种字符</li>
 *
 * @author mcw 2023/2/13 14:14
 */
public class leetCode1234 {

    /**
     * 滑动窗口
     */
    public int balancedString(String s) {
        //统计每个字符出现的次数
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cnt[idx(c)]++;
        }

        //计算 字符应该出现的次数
        int partial = s.length() / 4;
        int res = s.length();

        //如果 s 中 ‘Q’，‘W’，‘E’，‘R’ 的出现次数都小于等于 partial ，则认为 s 为「平衡字符串」。
        if (check(cnt, partial)) {
            return 0;
        }
        // L:左边界，R: 右边界 ，用滑动窗口维护区间 [L,R) 之外的剩余部分中 字符出现的次数
        for (int l = 0, r = 0; l < s.length(); l++) {
            // 因为 四个字符恰好出现 n/4 次，那么当窗口圈住部分字符时，剩余字符串的各个字符数一定小于等于 n/4 ，
            // 否则，该字符串绝对拼不成一个平衡字符串
            while (r < s.length() && !check(cnt, partial)) {
                cnt[idx(s.charAt(r))]--;
                r++;
            }
            //如果还不是 平衡字符串，就退出循环
            if (!check(cnt, partial)) {
                break;
            }
            //如果是的，就计算此时 替换字符串的 长度
            res = Math.min(res, r - l);
            //并将 L 右移一个单位。 否则，后序的 L 不会有合法的 R
            cnt[idx(s.charAt(l))]++;
        }
        return res;
    }

    public int idx(char c) {
        return c - 'A';
    }

    public boolean check(int[] cnt, int partial) {
        if (cnt[idx('Q')] > partial || cnt[idx('W')] > partial ||
                cnt[idx('E')] > partial || cnt[idx('R')] > partial) {
            return false;
        }
        return true;
    }
}
