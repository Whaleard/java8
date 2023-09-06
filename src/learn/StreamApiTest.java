package learn;

import domain.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mr.MC
 */
public class StreamApiTest {

    /**
     * 一、Stream的三个操作步骤：
     *
     *  1.创建Stream流
     *
     *  2.中间操作
     *
     *  3.终止操作
     */

    @Test
    public void test01() {
        // 1.可以通过Collection系列集合提供的stream()或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2.通过Arrays中的静态方法stream()获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);

        // 3.通过Stream类中的静态方法of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");

        // 4.创建无限流
        // 迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.limit(10).forEach(System.out::println);

        // 生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);

    }

    /**
     * 中间操作
     *
     *  筛选与切片
     *      filter：接收Lambda，从流中排除某些元素
     *      limit：截断流，使其元素不超过给定数量
     *      skip(n)：跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
     *      distinct：筛选，通过流所生成元素的hashCode()和equals()去除重复元素
     */

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 999.9),
            new Employee("李四", 54, 1962.4),
            new Employee("王五", 32, 1844.9),
            new Employee("赵六", 5, 557.4),
            new Employee("田七", 97, 1099.5)
    );

    @Test
    public void test02() {
        employees.stream()
                .filter((e) -> e.getAge() > 35)
                .forEach(System.out::println);
    }

    /**
     * 映射
     *  map：接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     *  flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
     */

    @Test
    public void test03() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        Stream<Stream<Character>> stream = list.stream()
                .map(StreamApiTest::filterCharacter);

        stream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        Stream<Character> stream1 = list.stream()
                .flatMap(StreamApiTest::filterCharacter);

        stream1.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

    /**
     * 排序
     *  sorted()：自然排序
     *  sorted(Comparator com)：定制排序
     */

    @Test
    public void test04() {
        employees.stream()
                .sorted((e1, e2) -> {
                    boolean result = e1.getAge().equals(e2.getAge());
                    return result ? e1.getName().compareTo(e2.getName()) : e1.getAge().compareTo(e2.getAge());
                }).forEach(System.out::println);
    }

    /**
     * 查找与匹配
     *  allMatch：检查是否匹配所有元素
     *  anyMatch：检查是否至少匹配一个元素
     *  noneMatch：检查是否没有匹配所有元素
     *  findFirst：返回第一个元素
     *  findAny：返回当前流中任意元素
     *  count：返回流中元素的总个数
     *  max：返回流中最大值
     *  min：返回流中最小值
     */

    List<Employee> employees1 = Arrays.asList(
            new Employee("张三", 18, 999.9, Employee.Status.FREE),
            new Employee("李四", 54, 1962.4, Employee.Status.BUSY),
            new Employee("王五", 32, 1844.9, Employee.Status.VOCATION),
            new Employee("赵六", 5, 557.4, Employee.Status.FREE),
            new Employee("田七", 97, 1099.5, Employee.Status.BUSY)
    );

    @Test
    public void test05() {
        boolean b = employees1.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);

        boolean b1 = employees1.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        boolean b2 = employees1.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);

        Optional<Employee> optional = employees1.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(optional.get());

        Optional<Employee> optional1 = employees1.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(optional1.get());
    }

    @Test
    public void test06() {
        long count = employees1.stream()
                .count();
        System.out.println(count);

        Optional<Employee> optional = employees1.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(optional.get());

        Optional<Double> optional1 = employees1.stream()
                .map(Employee::getSalary)
                .min((e1, e2) -> Double.compare(e1, e2));
        System.out.println(optional1.get());
    }

    /**
     * 归约
     *  reduce(T identity, BinaryOperator) / reduce(BinaryOperator)：可以将流中元素反复结合起来，得到一个值。
     */

    @Test
    public void test07() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        // map-reduce模式
        Optional<Double> optional = employees1.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(optional.get());
    }

    /**
     * 收集
     *  collect：将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */

    @Test
    public void test08() {
        List<String> list = employees1.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        Set<String> set = employees1.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);

        HashSet<String> hashSet = employees1.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }

    @Test
    public void test09() {
        // 总个数
        Long count = employees1.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        // 平均值
        Double avg = employees1.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);

        Optional<Employee> max = employees1.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max.get());

        Optional<Double> min = employees1.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());
    }

    /**
     * 分组
     */
    @Test
    public void test10() {
        Map<Employee.Status, List<Employee>> map = employees1.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
        for (Enum status : map.keySet()) {
            System.out.println(status + "---" + map.get(status));
        }
        for(Map.Entry<Employee.Status, List<Employee>> entries : map.entrySet()) {
            System.out.println(entries.getKey() + "---" + entries.getValue());
        }
    }
}
