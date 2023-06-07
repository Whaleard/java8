package learn;

import domain.Employee;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ����java.lang.Class������
 *  1����ļ��ع��̣�
 *      ���򾭹�javac.exe�����Ժ󣬻�����һ�������ֽ����ļ���.class��β��
 *      ��������ʹ��java.exe�����ĳ���ֽ����ļ����н������С��൱�ڽ�ĳ���ֽ����ļ����ص��ڴ��С�
 *      �˹��̾ͳ�Ϊ��ļ��ء����ص��ڴ��е��࣬���Ǿͳ�Ϊ����ʱ�࣬��ʱ����ʱ�࣬����ΪClass��һ��ʵ����
 *
 *  2�����仰˵��Class��ʵ���Ͷ�Ӧ��һ������ʱ�ࡣ
 *
 *  3�����ص��ڴ��е�����ʱ�࣬�Ỻ��һ����ʱ�䡣�ڴ�ʱ��֮�ڣ����ǿ���ͨ����ͬ�ķ�ʽ����ȡ������ʱ�ࡣ
 *
 * @author Mr.MC
 */
public class ReflectionTest {

    @Test
    public void test01() throws Exception {
        // 1.ͨ�����䴴������
        Class<Employee> clazz = Employee.class;
        Constructor<Employee> cons = clazz.getConstructor(String.class, Integer.class, Double.class, Employee.Status.class);

        Employee employee = cons.newInstance("Tom", 12, 100.5, Employee.Status.BUSY);
        System.out.println(employee);

        // 2.ͨ�����䣬���ö���ָ�������ԡ�����
        Field age = clazz.getDeclaredField("age");
        // isAccessible()ֵΪtrue��ָʾ����Ķ�����ʹ��ʱӦ��ȡ��Java���Է��ʼ�顣ֵΪfalse��ָʾ����Ķ���Ӧ��ʵʩJava���Է��ʼ�顣
        // ʵ����setAccessible�����úͽ��÷��ʰ�ȫ���Ŀ��أ�������Ϊtrue���ܷ���Ϊfalse�Ͳ��ܷ��ʡ�
        // ����JDK�İ�ȫ����ʱ�϶ࡣ����ͨ��setAccessible(true)�ķ�ʽ�رհ�ȫ���Ϳ��Դﵽ���������ٶȵ�Ŀ�ġ�

        // isAccessible()���ص�Ϊoverride���ԡ�
        // overrideָʾ�ö����Ƿ񸲸����Լ����ʼ�顣��ʼ��Ϊ"false"�����ֶ����ֶΡ������͹��캯��ʹ�á�
        // ���ʼ��Ӧ���Ǹ��ݸ��ֶΡ������͹��캯���ķ������η����жϸ��ֶΡ������͹��캯���Ƿ�ɶ���д��
        age.setAccessible(true);
        age.set(employee, 10);
        System.out.println(employee);

        // ���÷���
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(employee);
    }

    @Test
    public void test02() throws ClassNotFoundException {
        // ��ʽһ����������ʱ������ԣ�.class
        Class<Employee> clazz1 = Employee.class;

        // ��ʽ����ͨ������ʱ��Ķ��󣬵���getClass()
        Employee employee = new Employee();
        Class<? extends Employee> clazz2 = employee.getClass();

        // ��ʽ��������Class�ľ�̬������forName(String classPath)
        Class<?> clazz3 = Class.forName("domain.Employee");

        // ��ʽ�ģ�ʹ����ļ�������ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> clazz4 = classLoader.loadClass("domain.Employee");
    }

    /**
     * Classʵ����������Щ�ṹ
     */
    @Test
    public void test03() {
        // ��
        Class<Object> c1 = Object.class;
        // �ӿ�
        Class<Comparable> c2 = Comparable.class;
        // ����
        Class<String[]> c3 = String[].class;
        Class<int[][]> c4 = int[][].class;
        // ö��
        Class<ElementType> c5 = ElementType.class;
        // ע��
        Class<Override> c6 = Override.class;
        // ��������
        Class<Integer> c7 = int.class;
        // void
        Class<Void> c8 = void.class;
        // Class
        Class<Class> c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class<? extends int[]> c10 = a.getClass();
        Class<? extends int[]> c11 = b.getClass();
        // ֻҪ�����Ԫ��������ά��һ��������ͬһ��Classʵ��
        System.out.println(c10 == c11);
    }
}
