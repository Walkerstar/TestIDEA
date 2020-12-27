package mcw.test.juc;

import java.util.concurrent.*;

/**
 * @author mcw 2020\2\8 0008-16:35
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {

        //System.out.println(Runtime.getRuntime().availableProcessors()); 获取本机CPU核数
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 1; i <= 10; i++) {

                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }finally {
            threadPool.shutdown();
        }
    }

    private static void initPool() {
        ExecutorService threadPool1=Executors.newFixedThreadPool(5);//一个线程池有固定个线程
        ExecutorService threadPool2=Executors.newSingleThreadExecutor();//一个线程池一个线程
        ExecutorService threadPool=Executors.newCachedThreadPool();//一个线程池 N 个线程
        try {
            for (int i = 1; i <= 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }finally {
            threadPool.shutdown();
        }
    }
}
