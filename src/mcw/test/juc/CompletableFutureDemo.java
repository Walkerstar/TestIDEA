package mcw.test.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Future是Java5新加的一个接口，它提供了一种异步并行计算的功能。
 * 如果主线程需要执行一个很耗时的计算任务，我们就可以通过Future把这个任务放到异步线程中执行。
 * 主线程继续处理其他任务或先行结束，再通过Future获取计算结果。
 * @author mcw 2020\2\8 0008-20:52
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {
        /* //异步无返回
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t没有返回,update");
        });

        completableFuture.get();*/

        /*CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t completableFuture2");
            // int age=10/0;
            return "异步返回值===";
        });

        completableFuture.whenComplete((t,u)->{
            System.out.println("******t:"+t);
            System.out.println("******u:"+u);
        }).exceptionally(f->{
            System.out.println("*****exception："+f.getMessage());
            return "返回错误";
        }).get();*/

        FutureTask<String> futureTask=new FutureTask<>(new MyThreadCallable());
        Thread t1=new Thread(futureTask,"t1");
        t1.start();

    }


}
class MyThreadCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("----------come in");
        return "hello Callable";
    }
}
