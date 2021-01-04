package mcw.test.leetcode.bzhan;

/**
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
 * @author mcw 2021/1/4 19:03
 */
public class leetCode509 {
    public int fib(int n){
        int a=0,b=1,c=0;
        if (n==0) return 0;
        if (n==1) return 1;
        for (int i = 2; i <= n; i++) {
            c=a+b;
            a=b;
            b=c;
        }
        return c;
    }


    public int fib1(int n){
        double sqrt5=Math.sqrt(5);
        double fibN=Math.pow((1+sqrt5)/2,n)-Math.pow((1-sqrt5)/2,n);
        return (int) Math.round(fibN/sqrt5);
    }

}
