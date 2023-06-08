package learn;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.junit.Test;
import proxy.UserProxy;

/**
 * 什么是静态代理？
 *  静态代理实际上是在不改变被代理对象功能（代码）的前提下，使用代理对象对其进行功能上的拓展。
 *
 * 静态代理的特点
 *  被代理对象和代理对象实现相同的接口
 *
 * @author Mr.MC
 */
public class StaticProxyTest {

    @Test
    public void test01() {
        // 被代理类作为代理类的一个成员变量，
        // 在代理类实现的接口方法中调用该被代理类同样实现的接口方法并增加其他的业务逻辑以对被代理类的方法进行增强
        // 最后只需要调用代理类的对应方法即可
        UserDao userDao = new UserDaoImpl();
        // 注：这里可以看出静态代理可以对被代理对象进行灵活切换，但是无法进行功能的灵活处理
        UserProxy proxy = new UserProxy(userDao);
        proxy.login();
        proxy.logout();
    }
}
