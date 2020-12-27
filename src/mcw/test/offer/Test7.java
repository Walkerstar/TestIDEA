package mcw.test.offer;

/**
 * @author mcw 2020\1\14 0014-13:12
 *
 * 斐波拉数列，（从0开始，第0项为0）
 */
public class Test7 {

    //普通递归     ，会发生栈溢出现象
    public static int Fibonacci(int n){
        if(n<=0)
            return 0;
        if(n==1)
            return 1;
       else
            return Fibonacci(n-1)+Fibonacci(n-2);
    }

    //尾递归
    public static int Fibonacci01(int n){
        return Fibonacci(n,0,1);
    }
    public static int Fibonacci(int n,int a1,int a2){
        if(n<=0)
            return 0;
        if(n==1)
            return a2;
        else
            return Fibonacci(n-1,a2,a1+a2);
    }

    //优化    ，循环实现
    public static int Fibonacci1(int n){
        int a=0,b=1,sum=0;
        if(n==0)
            return 0;
        if(n==1)
            return 1;
        for (int i=2;i<=n;i++){
            sum=a+b;
            a=b;
            b=sum;
        }
       return sum;
    }

    //再优化，增加一个数组
    public static int Fibonacci2(int n){
        int []f=new int[45];
        f[0]=0;
        f[1]=1;
        if(n<=0)
            return 0;
        if(n==1)
            return 1;
        for (int i=2;i<=n;i++){
            f[i]=f[i-1]+f[i-2];
        }
        return f[n];
    }

    public static void main(String[] args) {
        int f1 = Fibonacci(39);
        int f2 = Fibonacci01(39);
        int f3 = Fibonacci1(39);
        int f4 = Fibonacci2(39);
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
        System.out.println(f4);
    }
}
