package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 特殊的二进制序列是具有以下两个性质的二进制序列：
 * 1. 0 的数量与 1 的数量相等。
 * 2. 二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。
 * <p>
 * 给定一个特殊的二进制序列 S，以字符串形式表示。定义一个操作 为首先选择S的两个连续且非空的特殊的子串，然后将它们交换。
 * （两个子串为连续的当且仅当第一个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。)
 * <p>
 * 在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？
 *
 * @author mcw 2022/8/8 16:16
 */
public class leetCode761 {

    /**
     * 分治
     *
     * 对于本题而言，将 1 看成左括号 (’，0 看成右括号 ‘)’，那么一个特殊的二进制序列就可以看成一个合法的括号序列。
     * 这种「映射」有助于理解题目中的操作，即交换两个相邻且非空的合法括号序列。
     *
     */
    public String makeLargesSpecial(String s) {
        if (s.length() <= 2) {
            return s;
        }
        int cnt = 0;
        int left = 0;
        List<String> subs = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ++cnt;
            } else {
                --cnt;
                if (cnt == 0) {
                    //等于0 时，表示我们找到了一个 [整体]的特殊序列
                    subs.add("1" + makeLargesSpecial(s.substring(left + 1, i)) + "0");
                    left = i + 1;
                }
            }
        }
        Collections.sort(subs, (a, b) -> b.compareTo(a));
        StringBuilder ans = new StringBuilder();
        for (String sub : subs) {
            ans.append(sub);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String s="11011000";
        System.out.println(new leetCode761().makeLargesSpecial(s));
    }
}
