package dao;

/**
 * 抽象角色：通过接口或抽象类声明真实角色实现的业务方法。
 * 抽象出来的接口或抽象类
 * @author Mr.MC
 */
public interface UserDao {

    void login();

    void logout();
}
