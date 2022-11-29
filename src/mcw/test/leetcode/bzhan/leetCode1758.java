package mcw.test.leetcode.bzhan;

/**
 * 给你一个仅由字符 '0' 和 '1' 组成的字符串 s 。一步操作中，你可以将任一 '0' 变成 '1' ，或者将 '1' 变成 '0' 。
 * <p>
 * 交替字符串 定义为：如果字符串中不存在相邻两个字符相等的情况，那么该字符串就是交替字符串。例如，字符串 "010" 是交替字符串，而字符串 "0100" 不是。
 * <p>
 * 返回使 s 变成 交替字符串 所需的 最少 操作数。
 *
 * @author mcw 2022/11/29 17:50
 */
public class leetCode1758 {

    /**
     * 注意到，变成这两种不同的交替二进制字符串所需要的最少操作数加起来等于 s 的长度，
     * 我们只需要计算出变为其中一种字符串的最少操作数，就可以推出另一个最少操作数，然后取最小值即可。
     */
    public static int minOperations(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //('0' + i % 2) 默认字符串 格式为 010101.....
            if (c != (char) ('0' + i % 2)) {
                cnt++;
            }
        }
        return Math.min(cnt, s.length() - cnt);
    }

    public static void main(String[] args) {
        System.out.println(minOperations("11101110"));
    }
}
