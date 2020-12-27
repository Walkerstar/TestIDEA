package mcw.test.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author mcw 2020\2\8 0008-14:53
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache=new MyCache();

        for (int i = 1; i <= 5 ; i++) {
            final int temp=i;
            new Thread(()->{myCache.put(temp+"",temp+"");},String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5 ; i++) {
            final int temp=i;
            new Thread(()->{myCache.get(temp+"");},String.valueOf(i)).start();
        }
    }
}

class MyCache{
    private Map<String,String> map= new HashMap<>();
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();

    public void get(String key)  {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t读取数据");
            TimeUnit.MILLISECONDS.sleep(300);

            String s = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取完成"+s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public void put(String key,String value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t写入数据"+value);
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}