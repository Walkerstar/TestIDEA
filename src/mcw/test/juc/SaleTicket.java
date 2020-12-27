package mcw.test.juc;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    private int number=30;
    private Lock lock=new ReentrantLock();

    public /*synchronized*/ void sale(){
        lock.lock();
        try {
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了："+(number--)+"\t还剩："+number);
            }
        }finally {
            lock.unlock();
        }
    }
}
/**
 * @author mcw 2020\2\7 0007-15:01
 *
 * 题目：三个售票员 卖出 30 张票
 *
 * 在高内聚低耦合的前提下， 线程       操作（对外暴露的调用方法）     资源类
 */
public class SaleTicket {
    public static void main(String[] args) {

        Ticket ticket=new Ticket();

        new Thread(() -> {for (int i=1;i<40;i++) ticket.sale();},"A").start();
        new Thread(() -> {for (int i=1;i<40;i++) ticket.sale();},"B").start();
        new Thread(() -> {for (int i=1;i<40;i++) ticket.sale();},"C").start();

         /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    for (int i = 1; i <= 40; i++) {
                        ticket.sale();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B");


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        },"C").start();*/
    }
}
