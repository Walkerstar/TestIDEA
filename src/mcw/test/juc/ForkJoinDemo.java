package mcw.test.juc;



import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author mcw 2020\2\8 0008-19:14
 */
public class ForkJoinDemo {

    public static void main(String[] args) throws Exception {
        Instant start = Instant.now();

        MyTask myTask=new MyTask(0,100);
        ForkJoinPool threadPool=new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);
        System.out.println(forkJoinTask.get());
        threadPool.shutdown();

        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
    }
}

class MyTask extends RecursiveTask<Integer> {

    private static final Integer ADJUST_VALUE=10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin,int end) {
        this.begin=begin;
        this.end=end;
    }

    @Override
    protected Integer compute() {
        if((end-begin)<ADJUST_VALUE){
            for (int i = begin; i <= end; i++) {
                result=result+i;
            }
        }else {
            int middle=(end+begin)>>1;
            MyTask task01=new MyTask(begin,middle);
            MyTask task02=new MyTask(middle+1,end);
            task01.fork();
            task02.fork();
            result=task01.join()+task02.join();
        }
        return result;
    }
}
