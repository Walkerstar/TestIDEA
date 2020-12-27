package mcw.test.offer;

/**
 * @author mcw 2020\1\15 0015-13:59
 * <p>
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。 保证base和exponent不同时为0;
 */
public class Test12 {
    public static double Power(double base, int n) {
        double res = 1, cur = base;
        int exponent;
        if (n > 0) {
            exponent = n;
        } else if (n < 0) {
            if (base == 0)
                throw new RuntimeException("分母不能为0");
            exponent = -n;
        } else {
            return 1;
        }
        while (exponent != 0) {   //快速幂
            if ((exponent & 1) == 1)
                res *= cur;
            cur *= cur; //翻倍
            exponent >>= 1;  //右移一位

        }
        return n >= 0 ? res : (1 / res);
    }

    public static void main(String[] args) {
        System.out.println(Power(5.0,6));
    }
}
