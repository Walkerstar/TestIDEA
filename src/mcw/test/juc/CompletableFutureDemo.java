package mcw.test.juc;

import java.util.concurrent.CompletableFuture;

/**
 * @author mcw 2020\2\8 0008-20:52
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {
        /* //异步无返回
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t没有返回,update");
        });

        completableFuture.get();*/

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
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
        }).get();


    }
}
