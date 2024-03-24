package thread;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * @ClassName ExceptionHandlerThread
 * @Description run()方法不允许throw exception，所以线程需要自己处理异常。
 * 对于checked exception，简单的try/catch就可以；
 * 对于unchecked exception，需要用到Thread里面的setUncaughtExceptionHandler(UncaughtExceptionHandler)处理。
 * setUncaughtExceptionHandler相当于一个事件注册的入口，UncaughtExceptionHandler则是一个接口。
 *
 * 如何处理线程中的unchecked exception
 *  1、定义一个类，实现UncaughtExceptionHandler接口，在实现的方法里包含对异常处理的逻辑和步骤。
 *  2、实现一个线程
 *  3、在线程调用start()方法启动前增加一个thread.setUncaughtExceptionHandler语句来实现处理异常逻辑的注册。
 *
 * @Author Mr.MC
 * @Date 2024/3/25 0:05
 * @Version 1.0
 **/
public class ExceptionHandlerThread implements UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("An exception has been captured");
        System.out.println("Thread: " + t.getId());
        System.out.println("Exception: " + e.getClass().getName() + ": " + e.getMessage());
        System.out.println("Stack Trace:");
        e.printStackTrace(System.out);
        System.out.println("Thread status: " + t.getState());
    }
}
