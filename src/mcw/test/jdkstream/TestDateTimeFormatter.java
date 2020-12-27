package mcw.test.jdkstream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * @author mcw 2020\4\18 0018-15:53
 */
public class TestDateTimeFormatter {

    public static ExecutorService pool = Executors.newFixedThreadPool(10);

    /**
     * 测试 DateTimeFormatter
     * @throws Exception
     */
    public static void testDateTimeFormatter() throws Exception {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = () -> LocalDate.parse("20200418", pattern);

        ArrayList<Future<LocalDate>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<LocalDate> future : results) {
            System.out.println("future.get() = " + future.get());
        }
        pool.shutdown();
    }

    /**
     * 测试 SimpleDateFormatter ，配合 DateFormThreadLocal 使用，利用 ThreadLocal 解决线程安全问题
     * @throws Exception
     */
    public static void testSimpleDateFormatter() throws Exception {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Callable<Date> task = () -> DateFormThreadLocal.convert("20200418");

        ArrayList<Future<Date>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<Date> future : results) {
            System.out.println("future.get() = " + future.get());
        }
        pool.shutdown();
    }

    /**
     * 1.熟悉 LocalDate , LocalTime , LocalDateTime 的常用 API
     * 2.Instant：时间戳 （以 1970-1-1 00:00:00 起）
     * 3.Duration:计算两个 "时间" 之间的间隔
     * 4.Period: 计算两个 "日期" 之间的间隔
     * 5.TemporalAdjuster: 时间较正器
     */
    public static void test(){
        System.out.println("now = " + LocalDateTime.now());
        System.out.println("LocalDateTime.of(2020,05,10,9,45) = " + LocalDateTime.of(2020, 05, 10, 9, 45));
        System.out.println("LocalDateTime.now().plusYears(2) = " + LocalDateTime.now().plusYears(2));

        //-----------------------------------------
        Instant now = Instant.now();
        //System.out.println("Instant.now() = " +now);
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        Instant now1 = Instant.now();
        System.out.println(Duration.between(now1, now));

        //-----------------------------------------
        LocalDate date1 = LocalDate.of(2020, 5, 1);
        LocalDate date2 = LocalDate.now();
        System.out.println("Period.between(date1,date2) = " + Period.between(date1, date2));

        //-----------------------------------------
        TemporalAdjuster adjuster = TemporalAdjusters.dayOfWeekInMonth(0, DayOfWeek.FRIDAY);
        System.out.println(adjuster.adjustInto(date2));

    }

    public static void main(String[] args) throws Exception {
        //testDateTimeFormatter();
        //testSimpleDateFormatter();
        test();
    }
}

class DateFormThreadLocal{
    private static final ThreadLocal<SimpleDateFormat> df= ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));

    public static Date convert(String source) throws ParseException {
        return df.get().parse(source);
    }
}
