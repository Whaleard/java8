package proxy;

/**
 * 静态代理
 *
 * 代理角色：实现抽象角色，是真实角色的代理，通过真实角色的业务逻辑方法来实现抽象方法，并可以附加自己的操作。
 * @author Mr.MC
 */
public class UserProxy implements UserDao {
    // 1、类中的成员变量设计为接口
    private UserDao userDao;    // 目标对象（抽象角色）作为代理类的成员变量

    /**
     * 2、方法的形参设计为接口
     * 构造方法传入被代理对象（传入的应该是真实角色，也就是接口或抽象类的实现类），赋值给代理类对应的成员变量，相当于初始化了被
     * @param userDao
     */
    public UserProxy(UserDao userDao) { // 传入目标对象
        this.userDao = userDao;
    }

    /**
     * 与被代理对象实现相同的接口或继承相同父类
     * 相当于是一种方法的增强，当需要在某个方法前后增加操作且无法直接对该方法进行修改时，
     * 若该方法实现了某个接口，可以通过代理类实现相同的接口来进行增强处理。类似于AOP的实现原理。
     */
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
