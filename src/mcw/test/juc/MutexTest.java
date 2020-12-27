package mcw.test.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author mcw 2020\2\17 0017-11:50
 */
public class MutexTest {

    private static CyclicBarrier barrier=new CyclicBarrier(31);
    private static int a=0;
    private static Mutex mutex=new Mutex();


    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        //未加锁前
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    increment1();//没加同步措施的a++
                }
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        barrier.await();
        System.out.println("加锁前,a="+a);


        //加锁后
        barrier.reset();//重置
        a=0;
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    increment2();//a++,采用Mutex进行同步处理
                }
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        barrier.await();
        System.out.println("加锁后，a="+a);
    }

    private static void increment2() {
        mutex.lock();
        a++;
        mutex.unlock();
    }

    private static void increment1() {
        a++;
    }
}
