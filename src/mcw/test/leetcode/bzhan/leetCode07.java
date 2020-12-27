package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\4\30 0030-15:39
 * 数字翻转
 * 例: x=123 x'=321  | y=-123 y'=-321
 * 问题
 *  1.最大数翻转后 会超出 范围 ? 直接返回 0
 */
public class leetCode07 {
    public static int revere(int x) {
        int rev = 0;
        while (x != 0) {
            int newrev = rev * 10 + x % 10;
            if ((newrev - x % 10) / 10 != rev) {
                return 0;
            }
            rev = newrev;
            x /= 10;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println(revere(-486164988));
    }
}
