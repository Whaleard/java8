package proxy;

import proxy.UserDao;

/**
 * 真实角色：实现抽象角色，定义真实角色所要实现的业务逻辑，供代理角色调用。
 * @author Mr.MC
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void login() {
        System.out.println("正在登录中...");
    }

    @Override
    public void logout() {
        System.out.println("正在登出中...");
    }
}
