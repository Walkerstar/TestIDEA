package mcw.test.leetcode.bzhan;

/**
 * 给你一个由小写字母组成的字符串 s ，以及一个整数 k 。
 * <p>
 * 首先，用字母在字母表中的位置替换该字母，将 s 转化 为一个整数（也就是，'a' 用 1 替换，'b' 用 2 替换，... 'z' 用 26 替换）。接着，将整数 转换 为其 各位数字之和 。共重复 转换 操作 k 次 。
 * <p>
 * 例如，如果 s = "zbax" 且 k = 2 ，那么执行下述步骤后得到的结果是整数 8 ：
 * <p>
 * 转化："zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124
 * 转换 #1：262124 ➝ 2 + 6 + 2 + 1 + 2 + 4 ➝ 17
 * 转换 #2：17 ➝ 1 + 7 ➝ 8
 * 返回执行上述操作后得到的结果整数。
 * <p>
 * 提示：
 * <li>1 <= s.length <= 100</li>
 * <li>1 <= k <= 10</li>
 * <li>s 由小写英文字母组成</li>
 *
 * @author mcw 2022/12/15 11:43
 */
public class leetCode1945 {

    public static int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < s.length(); j++) {
            sb.append(s.charAt(j) - 'a' + 1);
        }
        String digits = sb.toString();

        for (int i = 1; i < k && digits.length() > 1; i++) {
            int tem = 0;
            for (int j = 0; j < digits.length(); j++) {
                tem += digits.charAt(j) - '0';
            }
            digits = Integer.toString(tem);
        }
        return Integer.parseInt(digits);
    }

    public static void main(String[] args) {
        System.out.println(getLucky("zbax", 2));
    }
}
