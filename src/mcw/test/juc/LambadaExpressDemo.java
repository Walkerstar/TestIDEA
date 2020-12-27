package mcw.test.juc;

/**
 * @author mcw 2020\2\7 0007-15:24
 */
public class LambadaExpressDemo {
    public static void main(String[] args) {

        /*Foo foo=new Foo() {
            @Override
            public void sayHello() {
                System.out.println("..............hello");
            }
        };
        foo.sayHello();*/

        /*Foo foo=()->{
            System.out.println("   wafaffaf    ");
        };
        foo.sayHello();*/

        Foo fo=(x,y) -> {return x+y;};
        System.out.println(fo.add(10, 'a'));

        System.out.println(Foo.mv(15, 3));
    }
}
//定义函数式接口，默认只允许接口类部只有一个方法,

@FunctionalInterface
interface Foo{

    int add(int x,int y);

    // 要实现多个方法，修饰符变为default，并实现方法
    //使用 default 的方法，在外部不能用 lambda 表达式
    default void sayHello(){
        System.out.println("HELLO");
    }

    //静态方法实现 使用时写 类名.方法名
    static int mv(int x,int y){
        return x/y;
    }


}
