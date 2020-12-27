package mcw.test.juc;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author mcw 2020\2\8 0008-14:15
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {

        //做加法操作 ，
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("人到齐了，正式开会");
        });

        for (int i = 1; i <= 7; i++) {
            final int temp=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t第"+temp+"个人到了");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
