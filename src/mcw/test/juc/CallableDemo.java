package mcw.test.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author mcw 2020\2\8 0008-13:52
 *
 * 实现第 3 种多线程的方式，
 * 实现 Callable 接口 和 适配 FutureTask 接口
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask=new FutureTask<>(new MyThread());
        new Thread(futureTask,"A").start();

        System.out.println(Thread.currentThread().getName()+"  计算完成");

        System.out.println(futureTask.get());
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("to --------- MyThread");
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }
}
