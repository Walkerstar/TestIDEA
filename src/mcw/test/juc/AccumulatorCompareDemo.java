package mcw.test.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * 需求：50个线程，每个线程 100w 次，总点赞数
 *
 * @author MCW 2022/9/21
 */
public class AccumulatorCompareDemo {

    public static final int _1W=10000;
    public static final int threadNumber=50;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch1=new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch2=new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch3=new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch4=new CountDownLatch(threadNumber);

        ClickNumber clickNumber=new ClickNumber();

        long  startTime;
        long  endTime;

        startTime=System.currentTimeMillis();
        for (int i = 0; i < threadNumber; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j <= 100*_1W; j++) {
                        clickNumber.clickBySynchronized();
                    }
                }finally {
                    countDownLatch1.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch1.await();
        endTime=System.currentTimeMillis();
        System.out.println("-------costTime :"+(endTime-startTime)+" 毫秒, clickBySynchronized :"+clickNumber.number);


        startTime=System.currentTimeMillis();
        for (int i = 0; i < threadNumber; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j <= 100*_1W; j++) {
                        clickNumber.clickByAtomicLong();
                    }
                }finally {
                    countDownLatch2.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch2.await();
        endTime=System.currentTimeMillis();
        System.out.println("-------costTime :"+(endTime-startTime)+" 毫秒, clickByAtomicLong :"+clickNumber.atomicLong.get());

        startTime=System.currentTimeMillis();
        for (int i = 0; i < threadNumber; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j <= 100*_1W; j++) {
                        clickNumber.clickByLongAdder();
                    }
                }finally {
                    countDownLatch3.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch3.await();
        endTime=System.currentTimeMillis();
        System.out.println("-------costTime :"+(endTime-startTime)+" 毫秒, clickByLongAdder :"+clickNumber.longAdder.sum());

        startTime=System.currentTimeMillis();
        for (int i = 0; i < threadNumber; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j <= 100*_1W; j++) {
                        clickNumber.clickByLongAccumulator();
                    }
                }finally {
                    countDownLatch4.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch4.await();
        endTime=System.currentTimeMillis();
        System.out.println("-------costTime :"+(endTime-startTime)+" 毫秒, clickByLongAccumulator :"+clickNumber.longAccumulator.get());
    }

}

class ClickNumber {
    int number = 0;

    public synchronized void clickBySynchronized() {
        number++;
    }

    AtomicLong atomicLong = new AtomicLong(0);

    public void clickByAtomicLong() {
        atomicLong.getAndIncrement();
    }

    LongAdder longAdder = new LongAdder();

    public void clickByLongAdder() {
        longAdder.increment();
    }

    LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);

    public void clickByLongAccumulator() {
        longAccumulator.accumulate(1);
    }
}
