package learn;

import domain.Employee;
import function.LongFunction;
import function.StringFunction;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Mr.MC
 */
public class LambdaTest {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 999.9),
            new Employee("李四", 54, 1962.4),
            new Employee("王五", 32, 1844.9),
            new Employee("赵六", 5, 557.4),
            new Employee("田七", 97, 1099.5)
    );

    /**
     * stream流结合lambda表达式
     */
    @Test
    public void test01() {
        employees.stream()
                .filter((e) -> e.getSalary() >= 1000)
                .forEach(System.out::println);
    }

    /**
     * <T> List<T> 中，第一个T是告诉大家，T不是类T.class，
     * 而是泛型T（如果只写List<T>则编译器以为是类T.class，如果不存在T.class类，则报错）。
     *
     * 补充一点，使用泛型绝不是因为要使参数可以传入任意类型，如果仅仅是这样，直接用Object就可以了。
     * 用泛型是因为可以使该方法的返回值成为一个指定类型的集合，这样再次使用该集合的时候就有一个明确的类型了，
     * 这使的在将来该类型发生改变的时候编译器会报错，提醒你做相应的修改，而不是让问题暴露在运行阶段。
     * 这是泛型的重要作用之一。
     *
     * @param a
     * @return
     * @param <T>
     */
    public static <T> List<T> variableParameter(T... a) {
        List<T> list = new ArrayList<>();
        // 把可变参数当成数组处理就可以
        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }
        return list;
    }

    /**
     * 可变参数
     */
    @Test
    public void test02() {
        List<Employee> list = variableParameter(
                new Employee("张三", 18, 999.9),
                new Employee("李四", 54, 1962.4),
                new Employee("王五", 32, 1844.9),
                new Employee("赵六", 5, 557.4),
                new Employee("田七", 97, 1099.5));
        System.out.println(list);
    }

    /**
     * Lambda表达式语法格式：
     *  一、无参数，无返回值
     *      () -> System.out.println("Hello Lambda!");
     *
     *  二、有一个参数，无返回值
     *      (x) -> System.out.println(x);
     *
     *  三、若只有一个参数，小括号可以省略不写
     *      x -> System.out.println(x);
     *
     *  四、有两个以上的参数，有返回值，并且Lambda体中有多条语句
     *      Comparator<Integer> com = (x, y) -> {
     *          System.out.println("函数型接口");
     *          return Integer.compare(x, y);
     *      };
     *
     *  五、若Lambda体中只有一条语句，return和{}都可以省略不写
     *      (x, y) -> Integer.compare(x, y);
     *
     *  六：Lambda表达式的参数类型可以省略不写，因为JVM编译器通过上下文推断出数据类型，即“数据推断”
     *      (Integer x, Integer y) -> Integer.compare(x, y);
     */
    @Test
    public void test03() {
        // jdk1.7前如果内部类中使用同级别的变量则该变量必须是final
        // 现在可以不加final但是jdk会自己添加
        int i = 0;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + i);
            }
        };
        r.run();
        System.out.println("--------------------------------");

        Runnable r2 = () -> System.out.println("Hello Lambda!" + i);
        r2.run();
    }

    @Test
    public void test04() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("消费者接口");
    }

    @Test
    public void test05() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数型接口");
            return Integer.compare(x, y);
        };
        System.out.println(com.compare(4, 7));
    }

    /**
     * Lambda表达式需要“函数式接口”的支持
     * 函数式接口：接口中只有一个抽象方法的接口，成为函数式接口。可以使用@FunctionalInterface修饰
     *           该注解可以检查是否是函数式接口
     */


    @Test
    public void test06() {
        Collections.sort(employees, (x, y) -> {
            int result = Integer.compare(x.getAge(), y.getAge());
            return result == 0 ? x.getName().compareTo(y.getName()) : result;
        });

        employees.forEach(System.out::println);
    }

    public String strHandler(String str, StringFunction mf) {
        return mf.getValue(str);
    }

    @Test
    public void test07() {
        // String result = strHandler("drop procedure if exists productpricing;", (str) -> {
        //     String s = str.toUpperCase().substring(5, 14);
        //     return s;
        // });
        // System.out.println(result);

        // StringFunction mf = (str) -> {
        //     String s = str.toUpperCase().substring(5, 14);
        //     return s;
        // };
        // System.out.println(mf.getValue("drop procedure if exists productpricing;"));

        Function<String, String> fun = String::toUpperCase;
        System.out.println(fun.apply("drop procedure if exists productpricing;"));
    }

    public void longHandler(Long l1, Long l2, LongFunction<Long, Long> lf) {
        System.out.println(lf.getValue(l1, l2));
    }

    @Test
    public void test08() {
        longHandler(15L, 40L, (l1, l2) -> l1 + l2);
    }

    /**
     * java8内置的四大核心函数式接口
     *
     * Consumer<T>：消费型接口
     *      void accept(T t);
     *
     * Supplier<T>：供给型接口
     *      T get();
     *
     * Function<T, R>：函数型接口
     *      R apply(T t);
     *
     * Predicate<T>：断言型接口
     *      boolean test(T t);
     */
}
