package mcw.test.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mcw 2020\2\7 0007-16:21
 */
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {

        AirCondition airCondition=new AirCondition();

        new Thread(()->{
            for (int i=1;i<=3;i++) {
                try {
                    Thread.sleep(300);
                    airCondition.printABC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i=1;i<=3;i++) {
                try {

                    Thread.sleep(400);
                    airCondition.print123();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i=1;i<=3;i++) {
                try {
                    Thread.sleep(300);
                    airCondition.print789();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

       /* new Thread(()->{
            for (int i=1;i<=3;i++) {
                try {
                    Thread.sleep(400);
                    airCondition.Print123();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();*/
    }
}


class AirCondition{
    private int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition1=lock.newCondition();
    private Condition condition2=lock.newCondition();
    private Condition condition3=lock.newCondition();

    //老版方法  使用synchronized ,wait(), notifyAll()
    public synchronized void increment() throws InterruptedException {
        while(number!=0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);

        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while(number==0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);

        this.notifyAll();
    }

    //新版方法 使用lock ,await(),signalAll()
    public void printABC(){
        lock.lock();
        try {
            while (number!=0){
                condition1.await();
            }

            number=1;
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName()+": A B C");
            }
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print123(){
        lock.lock();
        try {
            while (number!=1){
                condition2.await();
            }
            number=2;
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()+": 1 2 3");
            }

            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print789(){
        lock.lock();
        try {
            while (number!=2){
                condition3.await();
            }
            number=0;
            for (int i = 0; i < 4; i++) {
                System.out.println(Thread.currentThread().getName()+": 7 8 9");
            }

            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
