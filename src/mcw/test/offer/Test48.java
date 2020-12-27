package mcw.test.offer;

import java.math.BigInteger;

/**
 * @author mcw 2020\1\23 0023-21:23
 * <p>
 * 写一个函数，求两个整数的和，要求在函数体内不得使用+,-,*,/四则运算符号
 */
public class Test48 {

    public static int Add(int n1, int n2) {
        BigInteger b1 = new BigInteger(String.valueOf(n1));
        BigInteger b2 = new BigInteger(String.valueOf(n2));
        return b1.add(b2).intValue();
    }

    public static int Add1(int n1, int n2) {
        while (n2 != 0) {
            int temp = n1 ^ n2;
            n2 = (n1 & n2) << 1;
            n1 = temp;
        }
        return n1;
    }

    public static int add(int num1, int num2) {
        return num2 == 0 ? num1 : add(num1 ^ num2, (num1 & num2) << 1);
    }


    public static void main(String[] args) {
        System.out.println(Add1(1, 3));
    }
}
