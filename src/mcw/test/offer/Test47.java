package mcw.test.offer;

/**
 * @author mcw 2020\1\23 0023-21:14
 *
 * 求1+2+3+....+n，要求不能用乘除法，for,while,if,else,switch,case等关键字以及条件判断语句(A?B:C)
 */
public class Test47 {
    /*1.需要利用逻辑与的短路特性实现递归终止
      2.当n==0 时，(n>0) && ((he+=Sum(n-1))>0)只执行前面的判断，为false，然后直接返回0
      3.当n>0，执行he+=Sun(n-1),实现递归计算Sum(n)的值
    */
    public static int Sum(int n){
        int he=n;
        boolean ans=(n>0) && ((he+=Sum(n-1))>0);
        return he;
    }

    public static void main(String[] args) {
        System.out.println(Sum(100));
    }
}
