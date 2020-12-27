package mcw.test.jdkstream;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author mcw 2020\4\17 0017-15:11
 *
 * 一：Stream 的三个步骤：
 * 1.创建 Stream       2.中间操作       3.终止操作（终端操作）
 */
public class TestStreamAPI {
    /**
     * 1.创建 Stream
     */
    public static void test1(){
        //1. 可以通过Collection 系列集合提供的 stream() 或 parallelStream()
        ArrayList<Object> list = new ArrayList<>();
        Stream<Object> stream1 = list.stream();

        //2.通过 Arrays 的静态方法 stream() 获取数组
        IntStream stream2 = Arrays.stream(new int[1]);

        //3.通过 Stream 类中的静态方法 of()
        Stream<String> stream3 = Stream.of("aa", "bb");

        //4.创建无限流 4-1.迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);

        //4-2.生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    /**
     * 2.中间操作
     * 筛选与切片
     * filter-----接收 lambda ，从流中排除某些元素
     * limit------截断流，使其元素不超过给定数量
     * skip(n)----跳过元素，返回一个扔掉了前 n 个元素的流，若该元素中不满足 n 个，则返回一个空流
     * distinct---筛选，通过流所生成元素的 hashCode() 和  equals() 去除重复元素
     * map--------接收 lambda，将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射为一个新的元素
     * flatMap----接受一个函数作为参数，将流中的每个值都转换成另一个流，然后把所有流连接成一个流
     * sorted()------------------自然排序
     * sorted(Comparator com)----定制排序
     */
    public static List<Employee> employees= Arrays.asList(
            new Employee("张三", 12, 78.65, Employee.Status.BUSY),
            new Employee("王五", 45, 789.12, Employee.Status.FREE),
            new Employee("赵柳", 22, 65.65, Employee.Status.VOCATION),
            new Employee("马奇", 30, 560.65, Employee.Status.FREE),
            new Employee("田七", 18, 123.65, Employee.Status.VOCATION));


    public static void test2(){
        employees.stream().filter((e) -> e.getAge() > 35).forEach(System.out::println);
        employees.stream().filter(employee -> employee.getSalary()<150.00).limit(2).forEach(System.out::println);
        employees.stream().filter(employee -> employee.getSalary()<150.00).skip(1).forEach(System.out::println);
        employees.stream().filter(employee -> employee.getSalary()<150.00).skip(1).distinct().forEach(System.out::println);

        Stream<String> stream = Stream.of("aaa", "bbb", "ccc", "ddd", "eee");
        stream.map(String::toUpperCase).forEach(System.out::println);
        employees.stream().map(Employee::getName).forEach(System.out::println);

        employees.stream().sorted((e1,e2)->{
            if(e1.getAge().equals(e2.getAge())){
                return e1.getName().compareTo(e2.getName());
            }else {
                return e1.getAge().compareTo(e2.getAge());
            }
        }).forEach(System.out::println);
    }

    /**
     * 3.终止操作
     * 查找与匹配
     * allMatch--------检查是否匹配所有元素
     * anyMatch--------检查是否至少匹配一个元素
     * noneMatch-------检查是否没有匹配所有元素
     * findFirst-------返回第一个元素
     * findAny---------返回当前流中的任意元素
     * count-----------返回流中元素的总个数
     * max-------------返回流中最大值
     * min-------------返回流中最小值
     *
     * 归约
     * reduce(T identity,BinaryOperator) / reduce(BinaryOperator)------可以将流中元素反复结合，得到一个值
     *
     * 收集
     * collect----将流转换为其他形式，接受一个Collector 接口的实现，用于给 Stream 中元素汇总的方法
     */
    public static void test3(){
        System.out.println(employees.stream().allMatch(e -> e.getStatus().equals(Employee.Status.BUSY)));
        System.out.println(employees.stream().anyMatch(e -> e.getStatus().equals(Employee.Status.BUSY)));

        Optional<Employee> op = employees.stream().sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())).findFirst();
        op.ifPresent(System.out::println);

        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(stream.reduce(0, Integer::sum));

        List<String> list = employees.stream().map(Employee::getName).collect(Collectors.toList());
        Set<String> set = employees.stream().map(Employee::getName).collect(Collectors.toSet());
        HashSet<String> hashSet = employees.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));

        //平均值，，总和
        Double avg = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        //collect(Collectors.summingDouble(Employee::getSalary))
        Double sum = employees.stream().mapToDouble(Employee::getSalary).sum();
    }

    public static void main(String[] args) {
        test3();
    }
}


class Employee{
    private String name;
    private Integer age;
    private Double salary;
    private Status status;

    public Employee() {
    }

    public Employee(String name, Integer age, Double salary, Status status) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", status=" + status +
                '}';
    }

    public enum Status{
        FREE,BUSY,VOCATION;
    }
}