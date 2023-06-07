package learn;

import domain.Employee;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 关于java.lang.Class类的理解
 *  1、类的加载过程：
 *      程序经过javac.exe命令以后，会生成一个或多个字节码文件（.class结尾）
 *      接着我们使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件加载到内存中。
 *      此过程就称为类的加载。加载到内存中的类，我们就称为运行时类，此时运行时类，就作为Class的一个实例。
 *
 *  2、换句话说，Class的实例就对应着一个运行时类。
 *
 *  3、加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式来获取此运行时类。
 *
 * @author Mr.MC
 */
public class ReflectionTest {

    @Test
    public void test01() throws Exception {
        // 1.通过反射创建对象
        Class<Employee> clazz = Employee.class;
        Constructor<Employee> cons = clazz.getConstructor(String.class, Integer.class, Double.class, Employee.Status.class);

        Employee employee = cons.newInstance("Tom", 12, 100.5, Employee.Status.BUSY);
        System.out.println(employee);

        // 2.通过反射，调用对象指定的属性、方法
        Field age = clazz.getDeclaredField("age");
        // isAccessible()值为true则指示反射的对象在使用时应该取消Java语言访问检查。值为false则指示反射的对象应该实施Java语言访问检查。
        // 实际上setAccessible是启用和禁用访问安全检查的开关，并不是为true就能访问为false就不能访问。
        // 由于JDK的安全检查耗时较多。所以通过setAccessible(true)的方式关闭安全检查就可以达到提升反射速度的目的。

        // isAccessible()返回的为override属性。
        // override指示该对象是否覆盖语言级访问检查。初始化为"false"。此字段由字段、方法和构造函数使用。
        // 访问检查应该是根据该字段、方法和构造函数的访问修饰符来判断该字段、方法和构造函数是否可读可写。
        age.setAccessible(true);
        age.set(employee, 10);
        System.out.println(employee);

        // 调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(employee);
    }

    @Test
    public void test02() throws ClassNotFoundException {
        // 方式一：调用运行时类的属性：.class
        Class<Employee> clazz1 = Employee.class;

        // 方式二：通过运行时类的对象，调用getClass()
        Employee employee = new Employee();
        Class<? extends Employee> clazz2 = employee.getClass();

        // 方式三：调用Class的静态方法：forName(String classPath)
        Class<?> clazz3 = Class.forName("domain.Employee");

        // 方式四：使用类的加载器：ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> clazz4 = classLoader.loadClass("domain.Employee");
    }

    /**
     * Class实例可以是哪些结构
     */
    @Test
    public void test03() {
        // 类
        Class<Object> c1 = Object.class;
        // 接口
        Class<Comparable> c2 = Comparable.class;
        // 数组
        Class<String[]> c3 = String[].class;
        Class<int[][]> c4 = int[][].class;
        // 枚举
        Class<ElementType> c5 = ElementType.class;
        // 注解
        Class<Override> c6 = Override.class;
        // 基本类型
        Class<Integer> c7 = int.class;
        // void
        Class<Void> c8 = void.class;
        // Class
        Class<Class> c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class<? extends int[]> c10 = a.getClass();
        Class<? extends int[]> c11 = b.getClass();
        // 只要数组的元素类型与维度一样，就是同一个Class实例
        System.out.println(c10 == c11);
    }
}
