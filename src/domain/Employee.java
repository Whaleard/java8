package domain;

import java.util.Comparator;

/**
 * Comparable接口是排序接口。
 * 类实现Comparable接口，意味着“该类支持排序”，通过重写compareTo(Object o)来定义排序规则
 *
 * @author Mr.MC
 */
public class Employee implements Comparable<Employee> {
    private String name;
    private Integer age;
    private Double salary;

    private Status status;

    public Employee() {
    }

    public Employee(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
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

    public void show() {
        System.out.println("我是一名员工");
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        // 当前对象-入参：返回值大于0，交换当前参数与入参的顺序；返回值小于等于0，不交换位置，此为从小到大排序
        // 入参-当前对象：返回值大于0，交换入参与当前参数的顺序，返回值小于等于0，不交换位置，此为从大到小排序
        return this.age - o.age;
    }

    public enum Status {
        FREE,
        BUSY,
        VOCATION;
    }
}
