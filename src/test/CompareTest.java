package test;

import domain.Employee;
import org.junit.Test;

import java.util.*;

public class CompareTest {

    @Test
    public void test01() {
        TreeMap<Employee, String> treeMap = new TreeMap<>();
        treeMap.put(new Employee("张三", 22, 10000.00), "这是张三");
        treeMap.put(new Employee("李四", 18, 2000.00), "这是李四");
        treeMap.put(new Employee("王五", 40, 50000.00), "这是王五");
        treeMap.put(new Employee("钱大", 34, 80000.00), "这是钱大");

        Set<Map.Entry<Employee, String>> set = treeMap.entrySet();
        for (Map.Entry<Employee, String> entry : set) {
            System.out.println(entry);
        }
    }

    /**
     * Comparator接口是比较器接口。
     * 针对于自身没有比较能力的对象为它们实现比较功能，不需要在类上定义，是一个外部的东西。通过重写compare(Object o1, Object o2)来定义比较规则
     */
    @Test
    public void test02() {
        List<Employee> employees = new ArrayList<>();
        Collections.addAll(employees, new Employee("张三", 22, 10000.00),
                new Employee("李四", 18, 2000.00), new Employee("王五", 40, 50000.00),new Employee("钱大", 34, 80000.00));
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                // 与Comparable的compareTo(Object o)返回值情况一致
                return o1.getAge() - o2.getAge();
            }
        });
        // 第一步简化
        // Collections.sort(employees, (o1, o2) -> o1.getAge() - o2.getAge());
        // 第二步简化
        Collections.sort(employees, Comparator.comparingInt(Employee::getAge));
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}
