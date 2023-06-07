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
        // �����Զ����࣬ʹ��ϵͳ����������м���
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        // ����ϵͳ���������getParent()����ȡ��չ�������
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);
        // ������չ���������getParent()���޷���ȡ�����������
        // �������������Ҫ�������java�ĺ�����⣬�޷������Զ������
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);
    }

    /**
     * Properties��������ȡ�����ļ�
     */
    @Test
    public void test02() throws Exception {
        Properties pros = new Properties();
        // ��ȡ�����ļ���ʽһ
        // ��ʱ���ļ�Ĭ���ڵ�ǰ��module��
        FileInputStream fis = new FileInputStream("jdbc.properties");
        // pros.load(fis);

        // ��ȡ�����ļ���ʽ��
        // ��ʱ�ļ�Ĭ��ʶ��Ϊ����ǰmodule��src��
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc1.properties");

        System.out.println(classLoader.getResource("jdbc.properties"));
        pros.load(is);

        String username = pros.getProperty("username");
        String password = pros.getProperty("password");
        System.out.println("username = " + username + ", password = " + password);
    }
}
