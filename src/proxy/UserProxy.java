package proxy;

import dao.UserDao;

/**
 * @author Mr.MC
 */
public class UserProxy implements UserDao {
    // 1�����еĳ�Ա�������Ϊ�ӿ�
    private UserDao userDao;    // Ŀ�����

    // 2���������β����Ϊ�ӿ�
    public UserProxy(UserDao userDao) { // ����Ŀ�����
        this.userDao = userDao;
    }

    @Override
    public void login() {
        System.out.println("�������û���");
        System.out.println("����������");
        // ����ʱ�ӿ�ָ��ʵ����
        userDao.login();
        System.out.println("��¼�ɹ���");
    }

    @Override
    public void logout() {
        System.out.println("�������");
        userDao.logout();
        System.out.println("�ǳ��ɹ���");
    }
}
