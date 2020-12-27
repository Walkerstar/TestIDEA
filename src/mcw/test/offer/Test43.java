package mcw.test.offer;

import java.util.Collections;

/**
 * @author mcw 2020\1\22 0022-20:13
 * <p>
 * 对于一个给定的字符序列 S，输出循环左移 K 位后的序列
 */
public class Test43 {

    public static String LeftRotateString(String str, int k) {
        if (k == 0 || str == null || str.length() == 0)
            return str;
        int rol = k % str.length();
        return str.substring(rol, str.length()) + str.substring(0, rol);
    }

    public static String LeftRotateString1(String str, int n) {
        char[] chars = str.toCharArray();
        if (chars.length < n) return "";
        reverse(chars, 0, n - 1);
        reverse(chars, n, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        StringBuilder sb = new StringBuilder(chars.length);
        for (char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static void reverse(char[] chars, int low, int high) {
        char temp;
        while (low < high) {
            temp = chars[low];
            chars[low] = chars[high];
            chars[high] = temp;
            low++;
            high--;
        }
    }

    public static void main(String[] args) {
        System.out.println(LeftRotateString1("abcdef",3));
    }
}
