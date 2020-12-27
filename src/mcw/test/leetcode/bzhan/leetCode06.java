package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\4\30 0030-15:10
 * 将给定的字符串以给定的 *行数* 按 “Z”字型排列，然后顺序输出
 * 例： s=“PAYPALISHIRING”  row=3   返回 ： PAHNAPLSIIGYIR
 *     P   A   H   N
 *     A P L S I I G
 *     Y   I   R
 */
public class leetCode06 {
    public static String convert(String s, int row) {
        //1.构建 StringBuilder 数组，并置空
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuilder[] sb = new StringBuilder[row];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }

        //2.按 Z 字型排列
        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < row && i < len; idx++) {
                sb[idx].append(c[i++]);
            }
            for (int idx = row - 2; idx >= 1 && i < len; idx--) {
                sb[idx].append(c[i++]);
            }
        }

        //3.将剩余的字符串拼接到第一个字符串后面 并 返回
        for (int j = 1; j < sb.length; j++) {
            sb[0].append(sb[j]);
        }
        return sb[0].toString();
    }

    public static void main(String[] args) {
        String s="PAYPALISHIRING";
        System.out.println(convert(s, 4));
    }
}
