package learn;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Mr.MC
 */
public class ClassLoaderTest {

    @Test
    public void test01() {
        // 对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        // 调用系统类加载器的getParent()，获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);
        // 调用扩展类加载器的getParent()，无法获取引导类加载器
        // 引导类加载器主要负责加载java的核心类库，无法加载自定义的类
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);
    }

    /**
     * Properties：用来读取配置文件
     */
    @Test
    public void test02() throws Exception {
        Properties pros = new Properties();
        // 读取配置文件方式一
        // 此时的文件默认在当前的module下
        FileInputStream fis = new FileInputStream("jdbc.properties");
        // pros.load(fis);

        // 读取配置文件方式二
        // 此时文件默认识别为：当前module的src下
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc1.properties");

        System.out.println(classLoader.getResource("jdbc.properties"));
        pros.load(is);

        String username = pros.getProperty("username");
        String password = pros.getProperty("password");
        System.out.println("username = " + username + ", password = " + password);
    }
}
