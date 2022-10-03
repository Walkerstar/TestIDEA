package mcw.test.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author MCW 2022/10/3
 */
public class LockSupportDemo {

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(()->{
            System.out.println("测试 LockSupport 的park 方法");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+" 阻塞结束");
        },"t1");

        thread.start();

        LockSupport.unpark(thread);
    }

}
