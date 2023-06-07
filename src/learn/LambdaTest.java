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
            new Employee("����", 18, 999.9),
            new Employee("����", 54, 1962.4),
            new Employee("����", 32, 1844.9),
            new Employee("����", 5, 557.4),
            new Employee("����", 97, 1099.5)
    );

    /**
     * stream�����lambda���ʽ
     */
    @Test
    public void test01() {
        employees.stream()
                .filter((e) -> e.getSalary() >= 1000)
                .forEach(System.out::println);
    }

    /**
     * <T> List<T> �У���һ��T�Ǹ��ߴ�ң�T������T.class��
     * ���Ƿ���T�����ֻдList<T>���������Ϊ����T.class�����������T.class�࣬�򱨴���
     *
     * ����һ�㣬ʹ�÷��;�������ΪҪʹ�������Դ����������ͣ����������������ֱ����Object�Ϳ����ˡ�
     * �÷�������Ϊ����ʹ�÷����ķ���ֵ��Ϊһ��ָ�����͵ļ��ϣ������ٴ�ʹ�øü��ϵ�ʱ�����һ����ȷ�������ˣ�
     * ��ʹ���ڽ��������ͷ����ı��ʱ��������ᱨ������������Ӧ���޸ģ������������Ⱪ¶�����н׶Ρ�
     * ���Ƿ��͵���Ҫ����֮һ��
     *
     * @param a
     * @return
     * @param <T>
     */
    public static <T> List<T> variableParameter(T... a) {
        List<T> list = new ArrayList<>();
        // �ѿɱ�����������鴦��Ϳ���
        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }
        return list;
    }

    /**
     * �ɱ����
     */
    @Test
    public void test02() {
        List<Employee> list = variableParameter(
                new Employee("����", 18, 999.9),
                new Employee("����", 54, 1962.4),
                new Employee("����", 32, 1844.9),
                new Employee("����", 5, 557.4),
                new Employee("����", 97, 1099.5));
        System.out.println(list);
    }

    /**
     * Lambda���ʽ�﷨��ʽ��
     *  һ���޲������޷���ֵ
     *      () -> System.out.println("Hello Lambda!");
     *
     *  ������һ���������޷���ֵ
     *      (x) -> System.out.println(x);
     *
     *  ������ֻ��һ��������С���ſ���ʡ�Բ�д
     *      x -> System.out.println(x);
     *
     *  �ġ����������ϵĲ������з���ֵ������Lambda�����ж������
     *      Comparator<Integer> com = (x, y) -> {
     *          System.out.println("�����ͽӿ�");
     *          return Integer.compare(x, y);
     *      };
     *
     *  �塢��Lambda����ֻ��һ����䣬return��{}������ʡ�Բ�д
     *      (x, y) -> Integer.compare(x, y);
     *
     *  ����Lambda���ʽ�Ĳ������Ϳ���ʡ�Բ�д����ΪJVM������ͨ���������ƶϳ��������ͣ����������ƶϡ�
     *      (Integer x, Integer y) -> Integer.compare(x, y);
     */
    @Test
    public void test03() {
        // jdk1.7ǰ����ڲ�����ʹ��ͬ����ı�����ñ���������final
        // ���ڿ��Բ���final����jdk���Լ����
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
        con.accept("�����߽ӿ�");
    }

    @Test
    public void test05() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("�����ͽӿ�");
            return Integer.compare(x, y);
        };
        System.out.println(com.compare(4, 7));
    }

    /**
     * Lambda���ʽ��Ҫ������ʽ�ӿڡ���֧��
     * ����ʽ�ӿڣ��ӿ���ֻ��һ�����󷽷��Ľӿڣ���Ϊ����ʽ�ӿڡ�����ʹ��@FunctionalInterface����
     *           ��ע����Լ���Ƿ��Ǻ���ʽ�ӿ�
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
     * java8���õ��Ĵ���ĺ���ʽ�ӿ�
     *
     * Consumer<T>�������ͽӿ�
     *      void accept(T t);
     *
     * Supplier<T>�������ͽӿ�
     *      T get();
     *
     * Function<T, R>�������ͽӿ�
     *      R apply(T t);
     *
     * Predicate<T>�������ͽӿ�
     *      boolean test(T t);
     */
}
