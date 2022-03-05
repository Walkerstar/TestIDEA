package mcw.test.leetcode.bzhan;

/**
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 * @author mcw 2022/3/3 10:19
 */
public class leetCode258 {

    public int addDigits(int num) {
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }

    public int addDigits2(int num) {
        return (num - 1) % 9 + 1;
    }
}
