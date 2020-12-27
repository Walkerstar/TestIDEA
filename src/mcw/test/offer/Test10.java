package mcw.test.offer;

/**
 * @author mcw 2020\1\14 0014-14:06
 *
 * 用2*1的小矩形横着或竖着覆盖跟大的矩形。请问用 n 个2*1的小矩形无重复的覆盖一个2*n的大矩形，总共有多少种方法。
 */
public class Test10 {

    public static int Fibonacci(int n){
        if(n<1)
            return 0;
        if(n==1 || n==2)
            return n;
        else
            return Fibonacci(n-1)+Fibonacci(n-2);
    }
}
