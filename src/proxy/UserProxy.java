package proxy;

import dao.UserDao;

/**
 * @author Mr.MC
 */
public class UserProxy implements UserDao {
    // 1、类中的成员变量设计为接口
    private UserDao userDao;    // 目标对象

    // 2、方法的形参设计为接口
    public UserProxy(UserDao userDao) { // 传入目标对象
        this.userDao = userDao;
    }

    @Override
    public void login() {
        System.out.println("请输入用户名");
        System.out.println("请输入密码");
        // 调用时接口指向实现类
        userDao.login();
        System.out.println("登录成功！");
    }

    @Override
    public void logout() {
        System.out.println("清除缓存");
        userDao.logout();
        System.out.println("登出成功！");
    }
}
