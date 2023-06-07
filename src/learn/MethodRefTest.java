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
     * �������ã���Lambda���е������з����Ѿ�ʵ���ˣ����ǿ���ʹ�á��������á�
     *
     *  �����﷨��ʽ��
     *      ����::ʵ��������
     *
     *      ��::��̬������
     *
     *      ��::ʵ��������
     *
     *  ע�⣺
     *      ��Lambda���е��÷����Ĳ����б�ͷ���ֵ���ͣ�Ҫ�뺯��ʽ�ӿ��г��󷽷��Ĳ����б�ͷ���ֵ���ͱ���һ�¡�
     *      ����Lambda�����б��еĵ�һ��������ʵ�������ĵ����ߣ��ڶ���������ʵ�������Ĳ���ʱ������ʹ��ClassName::method
     *
     * ���������ã�
     *
     *  ��ʽ��
     *      ClassName::new
     *
     *  ע�⣺��Ҫ���õĹ������Ĳ����б�Ҫ�뺯��ʽ�ӿ��г��󷽷��Ĳ����б���һ�¡�
     *
     * �������ã�
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
