package learn;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.junit.Test;
import proxy.UserProxy;

/**
 * @author Mr.MC
 */
public class StaticProxyTest {

    @Test
    public void test01() {
        UserDao userDao = new UserDaoImpl();
        UserProxy proxy = new UserProxy(userDao);
        proxy.login();
    }
}
