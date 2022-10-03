package mcw.test.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author MCW 2022/9/21
 */
public class AtomicReferenceFieldUpdateDemo {

    public static void main(String[] args) {
        MyVar myVar=new MyVar();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                myVar.init(myVar);
            },i+"").start();
        }
    }
}

class MyVar{
    public volatile Boolean isInt=Boolean.FALSE;

    AtomicReferenceFieldUpdater<MyVar,Boolean> fieldUpdater=
            AtomicReferenceFieldUpdater.newUpdater(MyVar.class,Boolean.class,"isInt");

    public void init(MyVar myVar){
        if (fieldUpdater.compareAndSet(myVar,Boolean.FALSE,Boolean.TRUE)) {
            System.out.println(Thread.currentThread().getName()+"  start init ");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"  over init ");
        }else {
            System.out.println(Thread.currentThread().getName()+"  other thread  already working ");
        }
    }
}
