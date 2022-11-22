package mcw.test.leetcode.bzhan;

import com.atguigu.java.Main;

/**
 * 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
 * <p>
 * 给定三个整数 n , a , b ，返回第 n 个神奇的数字。因为答案可能很大，所以返回答案 对 10^9 + 7 取模 后的值。
 *
 * @author mcw 2022/11/22 17:08
 */
public class leetCode878 {
    static final int MOD = 1000000007;

    /**
     * 容斥原理 + 二分查找
     */
    public static int nthMagicalNumber(int n, int a, int b) {
        long l = Math.min(a, b); // 神奇数 下界
        long r = (long) n * Math.min(a, b); // 神奇数 上界
        int c = lcm(a, b); //最小公倍数
        //二分查找神奇数
        while (l <= r) {
            long mid = (l + r) / 2;
            //计算 在 mid 时，有多少数字可以被 a 或 b 整除
            //例如 mid = 25,a = 4,b = 6 ,则：
            // a 的倍数 : [ 4,8,12,14,16,20 ]  公倍数 : {12,24}  b 的倍数 :[ 6,,12,14,18 ]
            // 25时, a 的个数是 25/4 = 6 (向下取整); b 的个数是 25/6 = 4 ; 公倍数的个数是 25/12 = 2
            //所以结果是 result= ( [25/4]=6 + [25/6]=4 - [25/12]=2 ) = 8
            long cnt = mid / a + mid / b - mid / c;
            //如果个数大于等于 n ,则改变边界
            if (cnt >= n) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) ((r + 1) % MOD);
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }


    /**
     *
     */
    public static int nthMagicalNumber2(int n, int a, int b) {
        int c = lcm(a, b);
        int m = c / a + c / b - 1;
        int r = n % m;
        int res = (int) ((long) c * (n / m) % MOD);
        if (r == 0) {
            return res;
        }
        int addA = a, addB = b;
        for (int i = 0; i < r - 1; i++) {
            if (addA < addB) {
                addA += a;
            } else {
                addB += b;
            }
        }
        return (res + Math.min(addA, addB) % MOD) % MOD;
    }

    public static void main(String[] args) {
        System.out.println(nthMagicalNumber(4, 2, 3));
        System.out.println(nthMagicalNumber2(4, 2, 3));
    }
}
