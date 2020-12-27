package mcw.test.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author mcw 2020\2\8 0008-14:21
 * 信号灯 主要用于 多线程并发的控制  和  多个共享资源的互斥使用
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        Semaphore semaphore=new Semaphore(3);//模拟资源类，有3个空车位

        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢到了车位");

                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"\t离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
