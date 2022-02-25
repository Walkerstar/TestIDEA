package mcw.test.leetcode.bzhan;

/**
 * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
 *
 * 实部 是一个整数，取值范围是 [-100, 100]
 * 虚部 也是一个整数，取值范围是 [-100, 100]
 * i^2 == -1
 * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
 * @author mcw 2022/2/25 14:31
 */
public class leetCode537 {

    /**
     * ( k1 + k2 * i ) X ( h1 + h2 * i )
     * = k1 * h1 + k1 * h2 * i + h1 * k2 * i + k2 * i^2 * h2
     * = k1 * h1 + k1 * h2 * i + h1 * k2 * i - k2 * h2
     * = (k1 * h1 - k2 * h2) + (k1 * h2 + h1 * k2) * i
     */
    public static String complexNumberMultiply(String num1, String num2) {
        String[] complex1 = num1.split("\\+|i");
        String[] complex2 = num2.split("\\+|i");

        int k1 = Integer.parseInt(complex1[0]);
        int k2 = Integer.parseInt(complex1[1]);

        int h1 = Integer.parseInt(complex2[0]);
        int h2 = Integer.parseInt(complex2[1]);
        return String.format("%d+%di", k1 * h1 - k2 * h2, k1 * h2 + h1 * k2);
    }

    public static void main(String[] args) {
        String num1="1+1i";
        String num2="1+1i";
        System.out.println(complexNumberMultiply(num1, num2));
    }
}
