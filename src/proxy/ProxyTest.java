package proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 什么是代理模式？
 * 代理模式实际上是在不改变被代理对象功能（代码）的前提下，使用代理对象对其进行功能上的拓展。
 */
public class ProxyTest {

    /**
     * 什么是静态代理？
     * 静态代理就是在编写代码的时候就已经把代理类的源码写好了。
     *
     * 静态代理的特点
     * 被代理对象和代理对象实现相同的接口
     */
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

    /**
     * 什么是动态代理？
     * 动态代理
     */
    @Test
    public void test02() {
        UserDao userDao = new UserDaoImpl();
        InvocationHandler handler = new HandlerInvocationHandler<>(userDao);
        // UserDao userDaoProxy = (UserDao) Proxy.newProxyInstance(UserDao.class.getClassLoader(), new Class[]{UserDao.class}, handler);
        UserDao userDaoProxy = (UserDao) Proxy.newProxyInstance(UserDaoImpl.class.getClassLoader(), UserDaoImpl.class.getInterfaces(), handler);
        userDaoProxy.login();
        userDaoProxy.logout();
    }
}
