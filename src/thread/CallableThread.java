package thread;

import java.util.concurrent.Callable;

/**
 * @ClassName ThreadMain
 * @Description 实现Callable接口的方式实现多线程
 * @Author Mr.MC
 * @Date 2024/3/11 23:04
 * @Version 1.0
 **/
public class CallableThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int i = 0; i < 1000; i++) {
            System.out.println("这是实现Callable接口的线程");
        }
        return "CallableThread";
    }
}
