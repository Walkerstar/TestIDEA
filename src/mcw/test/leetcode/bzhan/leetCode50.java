package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\6\3 0003-14:54
 * Pow(x,n)
 */
public class leetCode50 {
    public static double myPow(double num, int power) {
        if (power == 0 || num == 1) return 1;
        if (power == 1) return num;
        //use -(power+1) to avoid MIN_VALUE case
        if (power < 0) return 1 / (num * myPow(num, -(power + 1)));
        double res = 1;
        while (power > 1) {
            if (power % 2 == 1) {
                res *= num;
            }
            num = num * num;
            power /= 2;
        }
        res *= num;
        return res;
    }
}
