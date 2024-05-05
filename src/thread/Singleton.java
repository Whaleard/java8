package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Singleton
 * @Description 单例模式
 * @Author Mr.MC
 * @Date 2024/5/5 1:41
 * @Version 1.0
 **/
public class Singleton {
    private static Singleton instance;

    private static byte[] lock = new byte[0];

    private static ReentrantLock lock2 = new ReentrantLock();

    private Singleton() {};

    public static Singleton getInstance() {
        // 线程A和线程B同时进入if判断并且同时为ture，会引发线程安全问题。
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // 线程安全但效率低
    public static synchronized Singleton getInstance2() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static Singleton getInstance3() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static Singleton getInstance4() {
        if (instance == null) {
            lock2.lock();
            if (instance == null) {
                instance = new Singleton();
            }
            lock2.unlock();
        }
        return instance;
    }
}
