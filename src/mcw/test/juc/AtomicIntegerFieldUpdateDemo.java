package mcw.test.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author MCW 2022/9/21
 */
public class AtomicIntegerFieldUpdateDemo {


    public static void main(String[] args) throws InterruptedException {
        BankAccount bank = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        //bank.add();
                        bank.transferMoney(bank);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "   " + bank.money2);
    }

}

class BankAccount {
    String bankName = "CCB";
    int money = 0;

    public synchronized void add() {
        money++;
    }

    public volatile int money2=0;
    AtomicIntegerFieldUpdater<BankAccount> fieldUpdater=
            AtomicIntegerFieldUpdater.newUpdater(BankAccount.class,"money2");

    public void transferMoney(BankAccount bankAccount){
        fieldUpdater.getAndIncrement(bankAccount);
    }
}
