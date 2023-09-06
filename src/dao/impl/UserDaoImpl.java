package dao.impl;

import dao.UserDao;

/**
 * @author Mr.MC
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void login() {
        System.out.println("正在登录中...");
    }

    public void logout() {
        System.out.println("正在登出中...");
    }
}
