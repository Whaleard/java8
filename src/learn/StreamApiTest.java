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
     * һ��Stream�������������裺
     *
     *  1.����Stream��
     *
     *  2.�м����
     *
     *  3.��ֹ����
     */

    @Test
    public void test01() {
        // 1.����ͨ��Collectionϵ�м����ṩ��stream()��parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2.ͨ��Arrays�еľ�̬����stream()��ȡ������
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);

        // 3.ͨ��Stream���еľ�̬����of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");

        // 4.����������
        // ����
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.limit(10).forEach(System.out::println);

        // ����
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);

    }

    /**
     * �м����
     *
     *  ɸѡ����Ƭ
     *      filter������Lambda���������ų�ĳЩԪ��
     *      limit���ض�����ʹ��Ԫ�ز�������������
     *      skip(n)������Ԫ�أ�����һ���ӵ���ǰn��Ԫ�ص�����������Ԫ�ز���n�����򷵻�һ����������limit(n)����
     *      distinct��ɸѡ��ͨ����������Ԫ�ص�hashCode()��equals()ȥ���ظ�Ԫ��
     */

    List<Employee> employees = Arrays.asList(
            new Employee("����", 18, 999.9),
            new Employee("����", 54, 1962.4),
            new Employee("����", 32, 1844.9),
            new Employee("����", 5, 557.4),
            new Employee("����", 97, 1099.5)
    );

    @Test
    public void test02() {
        employees.stream()
                .filter((e) -> e.getAge() > 35)
                .forEach(System.out::println);
    }

    /**
     * ӳ��
     *  map������Lambda����Ԫ��ת����������ʽ����ȡ��Ϣ������һ��������Ϊ�������ú����ᱻӦ�õ�ÿ��Ԫ���ϣ�������ӳ���һ���µ�Ԫ�ء�
     *  flatMap������һ��������Ϊ�����������е�ÿ��ֵ��������һ������Ȼ������������ӳ�һ������
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
     * ����
     *  sorted()����Ȼ����
     *  sorted(Comparator com)����������
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
     * ������ƥ��
     *  allMatch������Ƿ�ƥ������Ԫ��
     *  anyMatch������Ƿ�����ƥ��һ��Ԫ��
     *  noneMatch������Ƿ�û��ƥ������Ԫ��
     *  findFirst�����ص�һ��Ԫ��
     *  findAny�����ص�ǰ��������Ԫ��
     *  count����������Ԫ�ص��ܸ���
     *  max�������������ֵ
     *  min������������Сֵ
     */

    List<Employee> employees1 = Arrays.asList(
            new Employee("����", 18, 999.9, Employee.Status.FREE),
            new Employee("����", 54, 1962.4, Employee.Status.BUSY),
            new Employee("����", 32, 1844.9, Employee.Status.VOCATION),
            new Employee("����", 5, 557.4, Employee.Status.FREE),
            new Employee("����", 97, 1099.5, Employee.Status.BUSY)
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
     * ��Լ
     *  reduce(T identity, BinaryOperator) / reduce(BinaryOperator)�����Խ�����Ԫ�ط�������������õ�һ��ֵ��
     */

    @Test
    public void test07() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        // map-reduceģʽ
        Optional<Double> optional = employees1.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(optional.get());
    }

    /**
     * �ռ�
     *  collect������ת��Ϊ������ʽ������һ��Collector�ӿڵ�ʵ�֣����ڸ�Stream��Ԫ�������ܵķ���
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
        // �ܸ���
        Long count = employees1.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        // ƽ��ֵ
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
     * ����
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
