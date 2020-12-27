package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\27 0027-14:52
 * Multiply Strings
 * given two non-negiative num1 nad num2 represented as strings,return the product of num1 and num2.
 * 给定两个以字符串表示的非负数num1和num2，请返回num1和num2的乘积。
 */
public class leetCode43 {
    public static String multiply(String num1, String num2) {
        if (num1.length() == 0 || num2.length() == 0) return "0";
        int len1 = num1.length();
        int len2 = num2.length();
        int[] result = new int[len1 + len2];

        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int num = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int posLow = i + j + 1;
                int posHigh = i + j;
                num += result[posLow];
                result[posLow] = num % 10;
                result[posHigh] = num / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int res : result) {
            if (!(sb.length() == 0 && res == 0))
                sb.append(res);
        }
        return (sb.length() == 0) ? "0" : sb.toString();
    }

}
