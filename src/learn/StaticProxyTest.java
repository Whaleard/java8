package learn;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.junit.Test;
import proxy.UserProxy;

/**
 * ʲô�Ǿ�̬����
 *  ��̬����ʵ�������ڲ��ı䱻��������ܣ����룩��ǰ���£�ʹ�ô�����������й����ϵ���չ��
 *
 * ��̬������ص�
 *  ���������ʹ������ʵ����ͬ�Ľӿ�
 *
 * @author Mr.MC
 */
public class StaticProxyTest {

    @Test
    public void test01() {
        // ����������Ϊ�������һ����Ա������
        // �ڴ�����ʵ�ֵĽӿڷ����е��øñ�������ͬ��ʵ�ֵĽӿڷ���������������ҵ���߼��ԶԱ�������ķ���������ǿ
        // ���ֻ��Ҫ���ô�����Ķ�Ӧ��������
        UserDao userDao = new UserDaoImpl();
        // ע��������Կ�����̬������ԶԱ���������������л��������޷����й��ܵ�����
        UserProxy proxy = new UserProxy(userDao);
        proxy.login();
        proxy.logout();
    }
}
