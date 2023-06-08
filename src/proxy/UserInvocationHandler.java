package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Mr.MC
 */
public class UserInvocationHandler<T> implements InvocationHandler {
    // ��������Ķ���
    private T target;

    public UserInvocationHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("׼������");
        Object invoke = method.invoke(target, args);
        System.out.println("��ع��ܳɹ�ʵ�֣�");
        return invoke;
    }
}
