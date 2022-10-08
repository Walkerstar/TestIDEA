package mcw.test.juc;

/**
 * 验证可重入锁
 *
 * @author MCW 2022/9/13
 */
public class ReEntryLockDemo {

    public static void main(String[] args) {
        ReEntryLockDemo reEntryLockDemo = new ReEntryLockDemo();
        new Thread(reEntryLockDemo::m1,"t1").start();
    }

    /**
     * 同步方法 验证 可重入锁
     */
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+" --------m1 in");
        m2();
        System.out.println(Thread.currentThread().getName()+" --------m1 end");
    }
    public synchronized void m2(){
        System.out.println(Thread.currentThread().getName()+" --------m2 in");
        m3();
        System.out.println(Thread.currentThread().getName()+" --------m2 end");
    }
    public synchronized void m3(){
        System.out.println(Thread.currentThread().getName()+" --------m3 in");
    }


    /**
     * 同步代码块 验证 可重入锁
     * 结果:
     *
     * t1 -------外层调用
     * t1 -------中层调用
     * t1 -------内层调用
     */
    private static void sync1() {
        final Object object = new Object();

        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + " -------外层调用");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + " -------中层调用");
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName() + " -------内层调用");
                    }
                }
            }
        }, "t1").start();
    }
}
