package mcw.test.offer;

/**
 * @author mcw 2020\1\14 0014-14:25
 *
 * 输入一个整数，输出改数二进制表示中1的个数。其中负数用补码表示
 */
public class Test11 {

    //此代码只能运算正整数
    public static int NumberOf1(int n){
        int num=0;
        int index=1;
        while(index!=0){
            if((n&index)!=0){
                num++;
            }
            index=index<<1;
        }
        return num;
    }


    public static void main(String[] args) {
        System.out.println(NumberOf1(10));
    }
}
