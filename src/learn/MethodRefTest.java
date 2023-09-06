package learn;

import domain.Employee;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * @author Mr.MC
 */
public class MethodRefTest {

    /**
     * 方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
     *
     *  三种语法格式：
     *      对象::实例方法名
     *
     *      类::静态方法名
     *
     *      类::实例方法名
     *
     *  注意：
     *      ①Lambda体中调用方法的参数列表和返回值类型，要与函数式接口中抽象方法的参数列表和返回值类型保持一致。
     *      ②若Lambda参数列表中的第一个参数是实例方法的调用者，第二个参数是实例方法的参数时，可以使用ClassName::method
     *
     * 构造器引用：
     *
     *  格式：
     *      ClassName::new
     *
     *  注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致。
     *
     * 数组引用：
     *
     *  Type[]::new
     *
     */

    @Test
    public void test01() {
        Consumer<String> con = (x) -> System.out.println(x);

        Consumer<String> con1 = System.out::println;
    }

    @Test
    public void test02() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> com1 = Integer::compare;
    }

    @Test
    public void test03() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);

        BiPredicate<String, String> bp1 = String::equals;
    }

    @Test
    public void test04() {
        Supplier<Employee> sup = () -> new Employee();

        Supplier<Employee> sup2 = Employee::new;
    }

    @Test
    public void test05() {
        Function<Integer, String[]> fun = (x) -> new String[x];

        Function<Integer, String[]> fun2 = String[]::new;
    }
}
