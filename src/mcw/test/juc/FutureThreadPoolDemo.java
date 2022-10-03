package mcw.test.juc;

import java.util.concurrent.*;

/**
 * @author MCW 2022/9/7
 */
public class FutureThreadPoolDemo {

    public static void main(String[] args) throws Exception {
        //m1();
        System.out.println("-----------------------------------------");
        //m2();
    }

    private static void m2() throws InterruptedException, ExecutionException {
        //3个任务，多个异步任务线程处理
        long startTime = System.currentTimeMillis();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        FutureTask<String> futureTask1 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task1 over";
        });
        threadPool.submit(futureTask1);

        FutureTask<String> futureTask2 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task2 over";
        });
        threadPool.submit(futureTask2);

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();

        long endTime = System.currentTimeMillis();
        System.out.println("---------costTime:" + (endTime - startTime));

        System.out.println(Thread.currentThread().getName() + "\t -------end");
    }

    private static void m1() {
        //3个任务，一个main线程处理
        long startTime = System.currentTimeMillis();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("---------costTime:" + (endTime - startTime));

        System.out.println(Thread.currentThread().getName() + "\t -------end");
    }
}
