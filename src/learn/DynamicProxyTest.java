package learn;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.junit.Test;
import proxy.UserInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Mr.MC
 */
public class DynamicProxyTest {

    @Test
    public void test01() {
        UserDao userDao = new UserDaoImpl();
        InvocationHandler handler = new UserInvocationHandler<>(userDao);
        UserDao userDaoProxy = (UserDao) Proxy.newProxyInstance(UserDao.class.getClassLoader(), new Class[]{UserDao.class}, handler);
        userDaoProxy.login();
        userDaoProxy.logout();
    }
}
