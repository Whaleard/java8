package dao.impl;

import dao.UserDao;

/**
 * @author Mr.MC
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void login() {
        System.out.println("���ڵ�¼��...");
    }

    public void logout() {
        System.out.println("���ڵǳ���...");
    }
}
