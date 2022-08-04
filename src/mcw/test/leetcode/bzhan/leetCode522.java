package mcw.test.leetcode.bzhan;

/**
 * 给定字符串列表 strs ，返回其中 最长的特殊序列 。如果最长特殊序列不存在，返回 -1 。
 * <p>
 * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
 * <p>
 * s 的 子序列可以通过删去字符串 s 中的某些字符实现。
 * <p>
 * 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
 *
 * @author mcw 2022/6/27 10:32
 */
public class leetCode522 {

    public int findLUSlength(String[] strs) {
        int n = strs.length;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            boolean check = true;
            for (int j = 0; j < n; j++) {
                if (i != j && isSubseq(strs[i], strs[j])) {
                    check = false;
                    break;
                }
            }
            if (check) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    /**
     * 要想判断 str[i] 是否为 str[j] 的子序列，我们可以使用贪心 + 双指针的方法：
     * 即初始时指针 pti和 ptj 分别指向两个字符串的首字符。
     * 如果两个字符相同，那么两个指针都往右移动一个位置，表示匹配成功；
     * 否则只往右移动指针 ptj  ，表示匹配失败。
     * 如果 pti 遍历完了整个字符串，就说明 str[i] 是 str[j] 的子序列
     */
    public boolean isSubseq(String s, String t) {
        int ptS = 0, ptT = 0;
        while (ptS < s.length() && ptT < t.length()) {
            if (s.charAt(ptS) == t.charAt(ptT)) {
                ++ptS;
            }
            ++ptT;
        }
        return ptS == s.length();
    }
}