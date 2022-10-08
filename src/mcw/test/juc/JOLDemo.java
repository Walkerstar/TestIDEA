package mcw.test.juc;

/**
 * 本类验证对象的内存分布
 * <p/>
 *
 *
 * 使用如下依赖，即可分析对象在JVM的大小和分布
 * <dependency>
 * <groupId>org.openjdk.jol</groupId>
 * <artifactId>jol-core</artifactId>
 * </dependency>
 *
 * <p/>
 * -XX:+PrintCommandLineFlags -version 查看JVM的初始参数
 *
 * @author MCW 2022/9/25
 */
public class JOLDemo {

    public static void main(String[] args) {
        //Customer c1=new Customer();
        //System.out.println(ClassLayout.parseInstance(c1).toPrintable);
    }
}

class Customer {
    //第一种情况：只有对象头，没有其他任何实例数据。
    // 那么一个对象占 16 字节（mark Word 占 8 字节，类型指针 占 8 字节（未开启指针压缩下，开启后占 4 字节））


    //第二种情况：含有实例数据
    //那么一个对象占 24 字节（mark Word 占 8 字节，类型指针 占 8 字节（未开启指针压缩下，开启后占 4 字节）,实例数据：4 + 1=5，对齐填充：3 字节）
    int id;
    boolean flag;
}