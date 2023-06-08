package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Mr.MC
 */
public class UserInvocationHandler<T> implements InvocationHandler {
    // 被代理类的对象
    private T target;

    public UserInvocationHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("准备操作");
        Object invoke = method.invoke(target, args);
        System.out.println("相关功能成功实现！");
        return invoke;
    }
}
