package mcw.test.jdkstream;

import java.util.Optional;

/**
 * @author mcw 2020\4\17 0017-20:06
 */
public class TestOptional {

    /**
     *  Optional容器类的常用方法：
     *  Optional.of()： 创建一个 Optional 实例
     *  Optional.empty(): 创建一个空的 Optional 实例
     *  Optional.ofNullable(T t): 若 t 不为null，创建 Optional 实例，否则创建空实例
     *  ifPresent(): 判断是否包含值
     *  orElse(T t): 如果调用对象包含值，返回改值，否则返回 t
     *  orElseGet(Supplier s): 如果调用对象包含值，返回该值， 否则返回 S 获取的值
     *  map(Function f): 如果有值对其处理，并返回处理后的 Optional ，否则返回 Optionsal.empty()
     *  flatMap(Function mapper): 与 map 类似，要求返回值必须是 Optional
     */
    public static void test(){
        Optional<Employee> op = Optional.of(new Employee());
        Employee employee = op.get();
        System.out.println(employee);

        Optional<Object> op1 = Optional.ofNullable(null);
        Object emp = op1.orElseGet(Employee::new);
    }
}