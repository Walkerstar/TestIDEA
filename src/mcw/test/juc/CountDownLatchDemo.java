package mcw.test.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author mcw 2020\2\8 0008-13:59
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception {

        //做减法操作
        CountDownLatch countDownLatch=new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t班长关门走人");

    }

    private static void closeDoor() {
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t离开教室");
            },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName()+"\t班长关门走人");
    }
}
