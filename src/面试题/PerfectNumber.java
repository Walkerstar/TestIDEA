package 面试题;

/**
 * Interview questions
 * 一个数如果恰好等于它的因子之和，这个数就称为“ 完数 ”。 如 6=1+2+3，求 1000，以内的所有完数。
 * @author mcw 2020\8\21 0021-14:06
 */
public class PerfectNumber{
    public static void main(String[] args) {
        for (int i = 1; i < 1000; i++) {
            int t=0;
            for (int j = 1; j <= i/2; j++) {
                if (i%j==0){
                    t=t+j;
                }
            }
            if(t==i){
                System.out.println(i+"");
            }
        }
    }
}
